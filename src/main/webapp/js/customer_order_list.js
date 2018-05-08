$(function () {
    var contentVm = new Vue({
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
    alert("deleteOrders: " + ordersId);
}
