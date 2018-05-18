let contentVm;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            ordersCount: ordersCount,
            ordersList: ordersList,
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
            showSendGoodsBtn: function (ordersStatus) {
                return ordersStatus === "Created";
            },
            showConfirmRefundBtn: function (ordersStatus) {
                return ordersStatus === "Pending_Refund";
            },
            queryLogistics: queryLogistics,
            deliverGoods: deliverGoods,
        },
    });
});

function queryLogistics(ordersId) {
    alert("查看订单号为 " + ordersId + " 的物流信息");
}

function deliverGoods(ordersId) {
    $.ajax({
        url: "/rest/orders/" + ordersId + "/action/sellerDeliverGoods",
        type: "PUT",
        success: function (data) {
            for (let i = 0; i < contentVm.ordersList.length; i++) {
                let orders = contentVm.ordersList[i];
                if (orders.ordersId === ordersId) {
                    orders.status = data.data.newStatus;
                }
            }
        },
        error: function (xhr, errorMsg, exception) {
            alert("fail: " + exception);
        }
    });
}
