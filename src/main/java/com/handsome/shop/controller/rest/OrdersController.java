package com.handsome.shop.controller.rest;

import com.handsome.shop.dao.ContactDao;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.entity.Contact;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.entity.Goods;
import com.handsome.shop.entity.Orders;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.framework.ReturnObjectToJsonIgnoreFields;
import com.handsome.shop.util.Pager;
import com.handsome.shop.util.RequestStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * by wangrongjun on 2018/5/1.
 */
@Controller
@RequestMapping("/rest/customer/{customerId}/orders")
public class OrdersController extends BaseController {

    @Resource
    private OrdersDao ordersDao;
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private ContactDao addressDao;

    @GetMapping
    @ReturnObjectToJsonIgnoreFields({"shop", "goodsImageList"})
    public Pager<Orders> list(HttpServletRequest request, Integer pageIndex, Integer pageSize, String sortBy) {
        Customer customer = getLoginCustomerFromSession(request);
        Pager<Orders> pager = new Pager<>(pageIndex, pageSize, parseSortByList(sortBy));
        ordersDao.queryByCustomerId(customer.getCustomerId(), pager);
        return pager;
    }

    @PostMapping
    public RequestStatus create(HttpServletRequest request, int goodsId, int count, int contactId) {
        // TODO 把参数保存到Contact对象中
        Customer customer = getLoginCustomerFromSession(request);
        Goods goods = goodsDao.queryById(goodsId);
        Orders orders = new Orders(new Customer(customer.getCustomerId()), goods, count,
                count * goods.getPrice(), new Contact(contactId), new Date(), Orders.STATE_CONTINUE);
        ordersDao.insert(orders);
        return RequestStatus.success();
    }

}
