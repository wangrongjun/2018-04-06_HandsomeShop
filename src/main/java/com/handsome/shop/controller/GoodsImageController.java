package com.handsome.shop.controller;

import com.handsome.shop.entity.Customer;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.GoodsImageDao;
import com.handsome.shop.framework.BaseController;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * by wangrongjun on 2018/4/19.
 */
@Controller
public class GoodsImageController extends BaseController {

    @Resource
    private GoodsImageDao goodsImageDao;
    @Resource
    private CustomerDao customerDao;

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
                customerDao.updateHeadUrl(customer.getCustomerId(), url);
                customer.setHeadUrl(url);
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

    @DeleteMapping("/goodsImage")
    @ResponseBody
    public Map<String, Object> deleteGoodsImage(String deleteIdList) {
        String[] split = deleteIdList.split(",");
        boolean succeedAll = true;
        long failId = 0;
        for (String s : split) {
            long id = Long.parseLong(s);
            if (!goodsImageDao.deleteById(id)) {
                succeedAll = false;
                failId = id;
                break;
            }
        }

        Map<String, Object> map = new HashMap<>();
        if (succeedAll) {
            map.put("state", 0);
            map.put("msg", "ok");
        } else {
            map.put("state", -1);
            map.put("msg", "fail to delete: id=" + failId);
        }
        return map;
    }


}
