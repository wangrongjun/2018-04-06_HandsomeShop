let contentVm;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            editable: editable,
            goods: goods,
            evaluateList: evaluateList,
            buyCount: 1,
        },
        methods: {
            getGenderPictureUrl: function (evaluate) {
                if (evaluate.orders.customer.gender == "男") {
                    return "/img/ic_gender_man.png";
                } else {
                    return "/img/ic_gender_woman.png";
                }
            },
            toEditGoodsPage: function () {
                window.location.href = "/seller/goods/" + contentVm.goods.goodsId + "/edit";
            }
        },
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
    var url = "/shopCar";
    var data = {count: contentVm.buyCount, goodsId: contentVm.goods.goodsId};
    $.post(url, data, function (result, status) {
        if (result === true && status === "success") {
            if (result === true) {
                alert("已添加到购物车！");
                window.location.href = "/goods/" + contentVm.goods.goodsId;
            } else {
                alert("添加失败！");
            }
        } else {
            window.location.href = "/login.jsp";
        }
    });
}
