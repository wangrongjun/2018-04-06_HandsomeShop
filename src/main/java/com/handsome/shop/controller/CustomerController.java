package com.handsome.shop.controller;

import com.handsome.shop.controller.rest.OrdersController;
import com.handsome.shop.dao.ContactDao;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.dao.ShopDao;
import com.handsome.shop.entity.*;
import com.handsome.shop.entity.view.PageParam;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.util.GsonConverter;
import com.handsome.shop.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    private OrdersDao ordersDao;
    @Resource
    private ShopDao shopDao;
    @Resource
    private OrdersController ordersController;

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
        Pager<Orders> pager = ordersController.listByCustomer(customerId, new PageParam("-createTime"), createBR());
        request.setAttribute("ordersCount", pager.getTotalCount());
        request.setAttribute("ordersListJson", GsonConverter.toJson(pager.getDataList(), "Shop.seller", "GoodsImage.goods"));
        return "customer_order_list";
    }

    @PostMapping("/confirmOrders")
    public String confirmOrders(HttpServletRequest request, int goodsId, int count) {
        Customer customer = getLoginCustomerFromSession(request);
        Goods goods = goodsDao.queryById(goodsId);
        List<Contact> contactList = contactDao.queryByCustomerId(customer.getCustomerId());

        request.setAttribute("customerJson", GsonConverter.toJson(customer));
        request.setAttribute("goodsJson", GsonConverter.toJson(goods, "goods"));
        request.setAttribute("contactListJson", GsonConverter.toJson(contactList));
        request.setAttribute("count", count);
        return "create_order";
    }

    @PostMapping("/orders")
    @ResponseBody
    public boolean create(HttpServletRequest request, int contactId) {
        Customer customer = getLoginCustomerFromSession(request);
        Goods goods = (Goods) request.getSession().getAttribute("goods");
        if (goods == null) {
            return false;
        }

        int count;
        try {
            count = (int) request.getSession().getAttribute("count");
        } catch (Exception e) {
            return false;
        }

        Orders orders = new Orders(new Customer(customer.getCustomerId()), goods, count, count * goods.getPrice(),
                new Contact(contactId), new Date(), Orders.STATE_CONTINUE);
        ordersDao.insert(orders);

        // 成功添加订单后，address_list,goods,count等变量就没必要了，可以清除
        request.getSession().removeAttribute("address_list");
        request.getSession().removeAttribute("goods");
        request.getSession().removeAttribute("count");

        request.getSession().setAttribute("orders", orders);
        return true;
    }

}
