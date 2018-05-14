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
                    case "Pending_Return_Money":
                        return "退款中";
                    case "Closed":
                        return "交易已关闭";
                    default:
                        return "订单异常";
                }
            },
            showLogisticsBtn: function (ordersStatus) {
                return ordersStatus === "Pending_Receive";
            },
            showReturnMoneyBtn: function (ordersStatus) {
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
            showLogistics: showLogistics,
            returnMoney: returnMoney,
            receiveGoods: receiveGoods,
            deleteOrders: deleteOrders,
            evaluate: evaluate,
        },
    });
});

function showLogistics(ordersId) {
    alert("showLogistics: " + ordersId);
}

function returnMoney(ordersId) {
    alert("returnMoney: " + ordersId);
}

function receiveGoods(ordersId) {
    $.ajax({
        url: "/rest/orders/" + ordersId + "/toNewStatus/Received",
        type: "PUT",
        success: function (data) {
            alert("succeed: " + data);
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
        success: function (data) {
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

function evaluate(ordersId) {
    alert("evaluate: " + ordersId);
}
