let contentVm;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            ordersCount: ordersCount,
            ordersList: ordersList,

            selectedOrdersId: null,// 只在申请退款和评价订单中，模态窗弹出时由于无法传id才需要用到
            refundReason: null,
            evaluateLevel: "Good",
            evaluateContent: null,
        },
        methods: {
            showGoodsInfo: function (goodsId) {
                window.location.href = "/goods/" + goodsId;
            },
            ordersStatusMsg: function (ordersStatus) {
                switch (ordersStatus) {
                    case "Created":
                        return "等待卖家发货";
                    case "Pending_Receive":
                        return "卖家已发货";
                    case "Received":
                        return "买家已收货";
                    case "Finish":
                        return "订单完成";
                    case "Pending_Refund":
                        return "退款中";
                    case "Closed":
                        return "交易已关闭";
                    default:
                        return "异常状态：" + ordersStatus;
                }
            },
            showLogisticsBtn: function (ordersStatus) {
                return ordersStatus === "Pending_Receive";
            },
            showRefundBtn: function (ordersStatus) {
                return ordersStatus === "Created" ||
                    ordersStatus === "Pending_Receive" ||
                    ordersStatus === "Received" ||
                    ordersStatus === "Finish";
            },
            showReceiveGoodsBtn: function (ordersStatus) {
                return ordersStatus === "Pending_Receive";
            },
            showDeleteOrdersBtn: function (ordersStatus) {
                return ordersStatus === "Finish" ||
                    ordersStatus === "Closed";
            },
            showEvaluateBtn: function (ordersStatus) {
                return ordersStatus === "Received";
            },
            queryLogistics: queryLogistics,
            receiveGoods: receiveGoods,
            evaluateOrders: evaluateOrders,
            applyForRefund: applyForRefund,
            deleteOrders: deleteOrders,
        },
    });
});

function queryLogistics(ordersId) {
    alert("查看订单号为 " + ordersId + " 的物流信息");
}

function receiveGoods(ordersId) {
    $.ajax({
        url: "/rest/orders/" + ordersId + "/action/customerReceiveGoods",
        type: "PUT",
        success: function (response) {
            for (let i = 0; i < contentVm.ordersList.length; i++) {
                let orders = contentVm.ordersList[i];
                if (orders.ordersId === ordersId) {
                    orders.status = response.data.newStatus;
                }
            }
        },
        error: function (xhr, errorMsg, exception) {
            alert("fail: " + exception);
        }
    });
}

function evaluateOrders(ordersId, evaluateContent, evaluateLevel) {
    $.ajax({
        url: "/rest/orders/" + ordersId + "/action/customerEvaluateOrders",
        type: "POST",
        data: {
            _method: "PUT",
            evaluateContent: evaluateContent,
            evaluateLevel: evaluateLevel
        },
        success: function (response) {
            for (let i = 0; i < contentVm.ordersList.length; i++) {
                let orders = contentVm.ordersList[i];
                if (orders.ordersId === ordersId) {
                    orders.status = response.data.newStatus;
                    orders.refund = response.data.refund;
                }
            }
        },
        error: function (xhr, errorMsg, exception) {
            alert("fail: " + exception);
        }
    });
}

function applyForRefund(ordersId, refundReason) {
    $.ajax({
        url: "/rest/orders/" + ordersId + "/action/customerApplyForRefund",
        type: "POST",
        data: {
            _method: "PUT",
            refundReason: refundReason
        },
        success: function (response) {
            for (let i = 0; i < contentVm.ordersList.length; i++) {
                let orders = contentVm.ordersList[i];
                if (orders.ordersId === ordersId) {
                    orders.status = response.data.newStatus;
                    orders.refund = response.data.refund;
                }
            }
        },
        error: function (xhr, errorMsg, exception) {
            alert("fail: " + exception);
        }
    });
}

function deleteOrders(ordersId) {
    if (!confirm("确实要删除该订单吗？")) {
        return
    }
    $.ajax({
        url: "/rest/orders/" + ordersId,
        type: "DELETE",
        success: function (response) {
            contentVm.ordersCount--;
            for (let i = 0; i < contentVm.ordersList.length; i++) {
                const orders = contentVm.ordersList[i];
                if (orders.ordersId === ordersId) {
                    contentVm.ordersList.splice(i, 1);
                }
            }
        },
        error: function (xhr, errorMsg, exception) {
            alert("订单删除失败！错误信息：" + exception);
        }
    });
}
