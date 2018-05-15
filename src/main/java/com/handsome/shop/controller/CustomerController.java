package com.handsome.shop.controller;

import com.handsome.shop.controller.rest.OrdersController;
import com.handsome.shop.dao.ContactDao;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.entity.*;
import com.handsome.shop.entity.view.PageParam;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.framework.ReturnObjectToJsonIgnoreFields;
import com.handsome.shop.util.GsonConverter;
import com.handsome.shop.util.Pager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * by wangrongjun on 2018/5/1.
 */
@Controller
public class CustomerController extends BaseController {

    @Resource
    private GoodsDao goodsDao;
    @Resource
    private ContactDao contactDao;
    @Resource
    private ShopDao shopDao;
    @Resource
    private OrdersController ordersController;

    @GetMapping("/contact")
    @ReturnObjectToJsonIgnoreFields("customer")
    public List<Contact> listContacts(HttpServletRequest request) {
        Customer customer = getLoginCustomerFromSession(request);
        // TODO 创建一个显示所有收货地址的页面
        return contactDao.queryByCustomerId(customer.getCustomerId());
    }

    @RequestMapping("/shop/{shopId}")
    public String showShopDetail(HttpServletRequest request,
                                 @PathVariable int shopId,
                                 @RequestParam(defaultValue = "0") int pageIndex) {
        int totalCount = goodsDao.queryCountByShopId(shopId);
        List<Goods> goodsList = goodsDao.queryByShopId(shopId, pageIndex * 12, 12);
        Shop shop = shopDao.queryById(shopId);

        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("shop", shop);
        return "shop_detail";
    }

    @GetMapping("/orders")
    public String listOrders(HttpServletRequest request) {
        int customerId = getLoginCustomerFromSession(request).getCustomerId();
        Pager<Orders> pager = ordersController.listByCustomer(customerId, new PageParam("-createdOn"), createBR());
        request.setAttribute("ordersCount", pager.getTotalCount());
        request.setAttribute("ordersListJson", GsonConverter.toJson(pager.getDataList(), "Shop.seller", "Refund.orders"));
        return "customer_orders_list";
    }

    @PostMapping("/confirmOrders")
    public String confirmOrders(HttpServletRequest request, int goodsId, int count) {
        Customer customer = getLoginCustomerFromSession(request);
        Goods goods = goodsDao.queryById(goodsId);
        List<Contact> contactList = contactDao.queryByCustomerId(customer.getCustomerId());

        request.setAttribute("customerJson", GsonConverter.toJson(customer));
        request.setAttribute("goodsJson", GsonConverter.toJson(goods, "Goods.shop"));
        request.setAttribute("contactListJson", GsonConverter.toJson(contactList));
        request.setAttribute("count", count);
        return "create_orders";
    }


    @PostMapping("/user/head")
    public String uploadUserHead(HttpServletRequest request) {
        Customer customer = getLoginCustomerFromSession(request);
        String url = "/admin/img/customer_" + customer.getCustomerId() + ".jpg";
        String savePath = request.getServletContext().getRealPath(url);
        /*
         * 状态：
         * 0：上传成功
         * 1：大小超过1M
         * 2：未知错误
         */
        int state;
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            upload.setFileSizeMax(1024 * 1024);
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                InputStream is = item.getInputStream();
                FileOutputStream fos = new FileOutputStream(savePath);
                byte[] buf = new byte[1024];
                int len;
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                is.close();
                fos.close();
                item.delete();
            }
            state = 0;
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            state = 1;
        } catch (Exception e) {
            state = 2;
            e.printStackTrace();
        }
        switch (state) {
            case 0:
//                customerDao.updateHeadUrl(customer.getCustomerId(), url);
//                customer.setHeadUrl(url);
                request.setAttribute("msg", "上传成功");
                break;
            case 1:
                request.setAttribute("msg", "上传失败，图片大小不能超过1MB");
                break;
            case 2:
                request.setAttribute("msg", "上传失败，未知异常");
                break;
        }
        return "customer_info";
    }

}
