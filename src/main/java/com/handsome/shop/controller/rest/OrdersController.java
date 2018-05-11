package com.handsome.shop.controller.rest;

import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.entity.Contact;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.entity.Goods;
import com.handsome.shop.entity.Orders;
import com.handsome.shop.entity.view.PageParam;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.framework.ReturnObjectToJsonIgnoreFields;
import com.handsome.shop.util.Pager;
import com.handsome.shop.util.RequestStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * by wangrongjun on 2018/5/1.
 */
@Controller
@RequestMapping("/rest/orders")
public class OrdersController extends BaseController {

    @Resource
    private OrdersDao ordersDao;
    @Resource
    private GoodsDao goodsDao;

    @GetMapping
    @ReturnObjectToJsonIgnoreFields({"Goods.shop", "GoodsImage.goods"})
    public Pager<Orders> listByCustomer(@RequestParam Integer customerId,
                                        @Valid PageParam pageParam, BindingResult pageParamResult) {
        if (pageParamResult.hasErrors()) {
            throw new IllegalArgumentException(pageParamResult.getAllErrors().get(0).getDefaultMessage());
        }
        Pager<Orders> pager = pageParam.toPager(Orders.class);
        ordersDao.queryByCustomerId(customerId, pager);
        return pager;
    }

    @PostMapping
    public RequestStatus create(@RequestParam Integer customerId,
                                @RequestParam Integer goodsId,
                                @RequestParam Integer count,
                                @RequestParam Integer contactId) {
        Goods goods = goodsDao.queryById(goodsId);
        Orders orders = new Orders(new Customer(customerId), goods, count,
                count * goods.getPrice(), new Contact(contactId), new Date(), Orders.STATE_CONTINUE);
        ordersDao.insert(orders);
        return RequestStatus.success();
    }

    @DeleteMapping("/{ordersId}")
    public RequestStatus delete(@PathVariable Integer ordersId) {
        ordersDao.deleteLogically(ordersId);
        return RequestStatus.success();
    }

}
