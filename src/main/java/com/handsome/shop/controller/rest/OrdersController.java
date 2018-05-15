package com.handsome.shop.controller.rest;

import com.google.gson.JsonObject;
import com.handsome.shop.dao.EvaluateDao;
import com.handsome.shop.dao.GoodsDao;
import com.handsome.shop.dao.OrdersDao;
import com.handsome.shop.dao.RefundDao;
import com.handsome.shop.entity.*;
import com.handsome.shop.entity.view.PageParam;
import com.handsome.shop.framework.BaseController;
import com.handsome.shop.framework.ReturnObjectToJsonIgnoreFields;
import com.handsome.shop.util.Pager;
import com.handsome.shop.util.RequestStatus;
import com.wangrj.java_lib.java_util.DateUtil;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

/**
 * by wangrongjun on 2018/5/1.
 */
@Controller
@RequestMapping("/rest/orders")
public class OrdersController extends BaseController {

    @Resource
    private OrdersDao ordersDao;
    @Resource
    private RefundDao refundDao;
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private EvaluateDao evaluateDao;

    @GetMapping
    @ReturnObjectToJsonIgnoreFields({"Goods.shop", "Refund.orders"})
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
                new Contact(contactId), remark, Orders.Status.Pending_Receive);
        ordersDao.insert(orders);
        return RequestStatus.success();
    }

    @DeleteMapping("/{ordersId}")
    public RequestStatus delete(@PathVariable Integer ordersId) {
        ordersDao.deleteLogically(ordersId);
        return RequestStatus.success();
    }

    /**
     * 卖家发货
     */
    @PutMapping("/{ordersId}/action/sellerDeliverGoods")
    public RequestStatus sellerDeliverGoods(@PathVariable Integer ordersId) {
        Orders orders = ordersDao.queryById(ordersId);
        Orders.Status newStatus = getNewStatus(orders.getStatus(), "sellerDeliverGoods");
        orders.setStatus(newStatus);
        ordersDao.update(orders);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("newStatus", newStatus.toString());
        return RequestStatus.success(jsonObject);
    }

    /**
     * 买家收货
     */
    @PutMapping("/{ordersId}/action/customerReceiveGoods")
    public RequestStatus customerReceiveGoods(@PathVariable Integer ordersId) {
        Orders orders = ordersDao.queryById(ordersId);
        Orders.Status newStatus = getNewStatus(orders.getStatus(), "customerReceiveGoods");
        orders.setStatus(newStatus);
        ordersDao.update(orders);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("newStatus", newStatus.toString());
        return RequestStatus.success(jsonObject);
    }

    /**
     * 买家评价订单
     */
    @PutMapping("/{ordersId}/action/customerEvaluateOrders")
    public RequestStatus customerEvaluateOrders(@PathVariable Integer ordersId,
                                                @RequestParam String evaluateContent,
                                                @RequestParam @Size(max = 2) Integer evaluateLevel, BindingResult evaluateLevelResult) {
        if (evaluateLevelResult.hasErrors()) {
            throw new IllegalArgumentException(evaluateLevelResult.getAllErrors().get(0).getDefaultMessage());
        }

        Orders orders = ordersDao.queryById(ordersId);
        Orders.Status newStatus = getNewStatus(orders.getStatus(), "customerEvaluateOrders");
        orders.setStatus(newStatus);
        ordersDao.update(orders);

        Evaluate evaluate = new Evaluate(orders, evaluateContent, evaluateLevel, DateUtil.getCurrentDateAndTime());
        evaluateDao.insert(evaluate);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("newStatus", newStatus.toString());
        return RequestStatus.success(jsonObject);
    }

    /**
     * 买家申请退款
     */
    @PutMapping("/{ordersId}/action/customerApplyForRefund")
    @ReturnObjectToJsonIgnoreFields("Refund.orders")
    public RequestStatus customerApplyForRefund(@PathVariable Integer ordersId,
                                                @RequestParam String refundReason) {
        Orders orders = ordersDao.queryById(ordersId);
        Orders.Status newStatus = getNewStatus(orders.getStatus(), "customerApplyForRefund");
        orders.setStatus(newStatus);
        ordersDao.update(orders);

        Refund refund = new Refund(orders, refundReason, null);
        refundDao.insert(refund);

        Map<String, Object> result = new HashMap<>();
        result.put("newStatus", newStatus.toString());
        result.put("refund", refund);
        return RequestStatus.success(result);
    }

    /**
     * 卖家退款
     */
    @PutMapping("/{ordersId}/action/sellerRefund")
    public RequestStatus sellerRefund(@PathVariable Integer ordersId) {
        return RequestStatus.success(ordersId);
    }

    /**
     * 根据订单当前状态以及执行的动作推导出订单的下一个状态。同时会检查某个动作对某个订单是否可以执行。
     * 比如订单是等待买家收货的状态，如果操作是“买家确认收货”，则合理。如果是“卖家发货”，则不合理。
     */
    private Orders.Status getNewStatus(Orders.Status currStatus, String action) throws IllegalArgumentException {
        Orders.Status nextStatus = null;
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
        return nextStatus;
    }

}
