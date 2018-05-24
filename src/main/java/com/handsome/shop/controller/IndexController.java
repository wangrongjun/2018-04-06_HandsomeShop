package com.handsome.shop.controller;

import com.handsome.shop.constant.C;
import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.GoodsTypeDao;
import com.handsome.shop.dao.SellerDao;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.entity.Goods;
import com.handsome.shop.entity.GoodsType;
import com.handsome.shop.entity.Seller;
import com.handsome.shop.framework.BaseController;
import com.wangrj.java_lib.java_util.TextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2017/11/2.
 */
@Controller
public class IndexController extends BaseController {

    @Resource
    private GoodsDao goodsDao;
    @Resource
    private GoodsTypeDao goodsTypeDao;
    @Resource
    private SellerDao sellerDao;
    @Resource
    private CustomerDao customerDao;

    @RequestMapping("/")
    public String showIndex(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestParam(defaultValue = "0") int pageIndex,
                            @RequestParam(defaultValue = "0") int sortType) throws ServletException, IOException {

        // 如果未登录，就根据Cookie的信息判断能否自动登录 // TODO 以后把这段代码放到一个控制方法的前置切面当中。
        if (!isLogin(request)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String phone = null;
                String password = null;// cipher
                String identity = null;// C.SESSION_CUSTOMER or C.SESSION_SELLER
                String autoLogin = null;// true or null
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("phone")) {
                        phone = cookie.getValue();
                    }
                    if (cookie.getName().equals("password")) {
                        password = cookie.getValue();
                    }
                    if (cookie.getName().equals("identity")) {
                        identity = cookie.getValue();
                    }
                    if (cookie.getName().equals("autoLogin")) {
                        autoLogin = cookie.getValue();
                    }
                }
                if (!TextUtil.isEmpty(phone, password, identity)) {
                    if ("true".equals(autoLogin)) {
                        if (C.SESSION_CUSTOMER.equals(identity)) {
                            Customer customer = customerDao.queryByPhone(phone);
                            if (customer != null && customer.getPassword().equals(password)) {
                                setLoginCustomerToSession(request, customer);
                            }
                        } else if (C.SESSION_SELLER.equals(identity)) {
                            Seller seller = sellerDao.queryByPhone(phone);
                            if (seller != null && seller.getPassword().equals(password)) {
                                setLoginSellerToSession(request, seller);
                            }
                        }
                    }
                }
            }
        }

        // 如果用户已经登录并且身份是卖家，就显示卖家首页
        if (getLoginSellerFromSession(request) != null) {
            request.getRequestDispatcher("/seller").forward(request, response);
            return null;
        }

        // 显示买家首页
        List<GoodsType> goodsTypeList = goodsTypeDao.queryAll();
        List<Goods> goodsList = goodsDao.begin(12 * pageIndex).count(12).sortType(sortType).queryAll();
        long totalCount = goodsDao.queryCount(null);
        request.getSession().setAttribute("goodsTypeList", goodsTypeList);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("sortType", sortType);
        return "index";
    }

}
