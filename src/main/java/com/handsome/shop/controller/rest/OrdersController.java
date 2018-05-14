package com.handsome.shop.controller.rest;

import com.google.gson.JsonObject;
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
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
                                @RequestParam Integer contactId,
                                @NotBlank String remark) {
        Goods goods = goodsDao.queryById(goodsId);
        Orders orders = new Orders(new Customer(customerId), goods, count, count * goods.getPrice(),
                new Contact(contactId), remark, Orders.Status.Pending_Receive, null);
        ordersDao.insert(orders);
        return RequestStatus.success();
    }

    @DeleteMapping("/{ordersId}")
    public RequestStatus delete(@PathVariable Integer ordersId) {
        ordersDao.deleteLogically(ordersId);
        return RequestStatus.success();
    }

    /**
     * @param refundReason 该变量只有 action 为 customerApplyForRefund 时才有效
     */
    @PutMapping("/{ordersId}/action/{action}")
    public RequestStatus changeToNewStatus(@PathVariable Integer ordersId,
                                           @PathVariable String action,
                                           String refundReason) {
        Orders orders = ordersDao.queryById(ordersId);
        Orders.Status currStatus = orders.getStatus();
        Orders.Status nextStatus = null;
        // 根据订单当前状态以及执行的动作推导出订单的下一个状态
        switch (action) {
            case "sellerDeliverGoods":// 卖家发货
                if (currStatus == Orders.Status.Created) {
                    nextStatus = Orders.Status.Pending_Receive;
                }
                break;
            case "customerReceiveGoods":// 买家收货
                if (currStatus == Orders.Status.Pending_Receive) {
                    nextStatus = Orders.Status.Received;
                }
                break;
            case "customerEvaluateOrders":// 买家评价订单
                if (currStatus == Orders.Status.Received) {
                    nextStatus = Orders.Status.Finish;
                }
                break;
            case "customerApplyForRefund":// 买家申请退款
                if (currStatus == Orders.Status.Created ||
                        currStatus == Orders.Status.Pending_Receive ||
                        currStatus == Orders.Status.Received) {
                    nextStatus = Orders.Status.Pending_Refund;
                    orders.setRefundReason(refundReason);
                }
                break;
            case "sellerRefund":// 卖家退款
                if (currStatus == Orders.Status.Pending_Refund) {
                    nextStatus = Orders.Status.Closed;
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid action: " + action);
        }
        if (nextStatus == null) {
            throw new IllegalArgumentException("Not support to take the action on this orders: " + action);
        }
        orders.setStatus(nextStatus);
        ordersDao.update(orders);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("newStatus", nextStatus.toString());
        return RequestStatus.success(jsonObject);
    }

}
