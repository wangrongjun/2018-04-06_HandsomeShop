package com.handsome.shop.controller;

import com.handsome.shop.controller.rest.OrdersController;
import com.handsome.shop.dao.AddressDao;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.entity.Address;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.entity.Goods;
import com.handsome.shop.entity.Orders;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.util.Pager;
import com.wangrj.java_lib.java_util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by wangrongjun on 2018/5/1.
 */
@Controller
public class BuyGoodsController extends BaseController {

    @Resource
    private GoodsDao goodsDao;// TODO 把 Dao 换成 RestController
    @Resource
    private AddressDao addressDao;// TODO 把 Dao 换成 RestController
    @Resource
    private OrdersDao ordersDao;// TODO 把 Dao 换成 RestController
    @Resource
    private OrdersController ordersController;

    @GetMapping("/orders")
    public String listOrders(HttpServletRequest request, Integer pageIndex, Integer pageSize) {
        Pager<Orders> pager = ordersController.list(request, pageIndex, pageSize, "-createTime");
        request.setAttribute("ordersList", pager.getDataList());
        request.setAttribute("ordersCount", pager.getTotalCount());
        return "customer_order_list";
    }

    @PostMapping("/confirmOrders")
    public String confirmOrders(HttpServletRequest request, int goodsId, int count) {
        Customer customer = getLoginCustomerFromSession(request);
        Goods goods = goodsDao.queryById(goodsId);
        List<Address> addressList = addressDao.queryByCustomerId(customer.getCustomerId());

        request.getSession().setAttribute("goods", goods);
        request.getSession().setAttribute("addressList", addressList);
        request.getSession().setAttribute("count", count);// TODO 应该不需要放到session中
        return "create_order";
    }

    @PostMapping("/orders")
    @ResponseBody
    public boolean create(HttpServletRequest request, String address, String phone, String receiverName) {
        // TODO 把参数保存到Contact对象中
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
                phone, receiverName, address, DateUtil.getCurrentDateAndTime(), Orders.STATE_CONTINUE);
        ordersDao.insert(orders);

        // 成功添加订单后，address_list,goods,count等变量就没必要了，可以清除
        request.getSession().removeAttribute("address_list");
        request.getSession().removeAttribute("goods");
        request.getSession().removeAttribute("count");

        request.getSession().setAttribute("orders", orders);
        return true;
    }
}
