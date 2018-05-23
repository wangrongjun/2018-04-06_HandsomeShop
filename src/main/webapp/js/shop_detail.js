let contentVm;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            editable: editable,
            shop: shop,
            // newShopName: shop.shopName,// 保存更新商品信息的模态弹出窗的输入框内容
            // newDescription: shop.description,// 保存更新商品信息的模态弹出窗的输入框内容
            // newShopHead: newShopHead,
        },
        methods: {},
    });
});

function updateShopInfo() {
    let shopName = $("input[name=shopName]").val();
    let description = $("input[name=description]").val();
    if (shopName == null || description == null) {
        alert("不能为空");
        return;
    }
    let formData = new FormData($("#update_shop_info_form")[0]);
    $.ajax({
        url: "/seller/shop/" + contentVm.shop.shopId + "/updateShopInfo",
        type: "POST",// 只要上传文件，就必须是POST。否则会报400 - Bad Request。可以借助SpringMVC的HiddenHttpMethodFilter实现PUT请求。
        data: formData,
        processData: false,// 不处理发送的数据
        contentType: false,// 不能设置Content-Type请求头。注意！是false！不是null！
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
