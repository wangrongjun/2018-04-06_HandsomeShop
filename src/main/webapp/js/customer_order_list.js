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
            showGoodsInfo: showGoodsInfo,
            confirmOrders: confirmOrders,
            deleteOrders: deleteOrders,
        }
    });
});

function showGoodsInfo(goodsId) {
    window.location.href = "/goods/" + goodsId;
}

function confirmOrders(ordersId) {
    alert("confirmOrders: " + ordersId);
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
