let contentVm;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            editable: editable,
            shop: shop,
        },
        methods: {
            toAddGoodsPage: function (shopId) {
                window.location.href = "/seller/shop/" + shopId + "/addGoods";
            }
        },
    });
});

function updateShopInfo() {
    let shopName = $("input[name=shopName]").val();
    let description = $("input[name=description]").val();
    if (shopName == null || description == null) {
        alert("不能为空");
        return;
    }
    let formData = new FormData(document.querySelector("#update_shop_info_form"));
    $.ajax({
        url: "/seller/shop/" + contentVm.shop.shopId + "/updateShopInfo",
        type: "POST",// 只要上传文件，就必须是POST。否则会报400 - Bad Request。说明：即使使用SpringMVC的HiddenHttpMethodFilter也无法实现PUT请求，因为在multipart/form-data下，request.getParameter("_method")返回null。
        data: formData,
        processData: false,// 不处理发送的数据。没有的话报400 - Bad Request。
        contentType: false,// 不能设置Content-Type请求头。注意！是false！不是null！否则报400 - Bad Request。
        cache: false,// 上传的文件不需要缓存
        success: function (result) {
            contentVm.shop.shopName = shopName;
            contentVm.shop.description = description;
            if (result) {
                contentVm.shop.head.pictureId = result;
            }
        },
        error: function (xhr, status, error) {
            alert("操作失败！错误信息：" + error);
        }
    });
}
