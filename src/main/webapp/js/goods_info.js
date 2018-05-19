let contentVm;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            goods: goods,
            evaluateList: evaluateList,
            buyCount: 1,
        },
        methods: {}
    });

    <!--调用Luara来轮播图片-->
    $(".img_box").luara({
        width: "500",
        height: "334",
        interval: 2000,
        selected: "seleted",
        deriction: "left"
    });
});

function showSellerInfo() {
    $('#mymodal').modal('toggle');
}

function addGoodsToShopCar() {
    var count = $("#count").val();
    var goodsId = "${requestScope.goods.goodsId}";

    var url = "/shopCar";
    var data = {count: count, goodsId: goodsId};
    $.post(url, data, function (result, status) {
        if (result === true && status === "success") {
            if (result === true) {
                alert("已添加到购物车！");
                window.location.href = "/goods/" + goodsId;
            } else {
                alert("添加失败！");
            }
        } else {
            window.location.href = "/login.jsp";
        }
    });
}
