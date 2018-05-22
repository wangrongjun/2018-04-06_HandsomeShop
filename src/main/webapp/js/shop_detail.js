let contentVm;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            editable: editable,
            shop: shop,
            newShopName: shop.shopName,// 保存更新商品信息的模态弹出窗的输入框内容
            newDescription: shop.description,// 保存更新商品信息的模态弹出窗的输入框内容
        },
        methods: {
            updateShopInfo: updateShopInfo,
            updateShopHead: updateShopHead,
        },
    });
});

function updateShopInfo() {
    if (contentVm.newShopName === "" || contentVm.newDescription === "") {
        alert("不能为空");
        return;
    }
    $.ajax({
        url: "/seller/shop/" + contentVm.shop.shopId + "/updateInfo",
        type: "POST",
        data: {
            _method: "PUT",
            shopName: contentVm.newShopName,
            description: contentVm.newDescription,
        },
        success: function (response) {
            contentVm.shop.shopName = contentVm.newShopName;
            contentVm.shop.description = contentVm.newDescription;
        },
        error: function (xhr, errorMsg, exception) {
            alert("操作失败！错误信息：" + exception);
        }
    });
}

let $shop_head_field = null;

function updateShopHead() {
    if ($shop_head_field == null) {
        $("body").append(`
            <form style='visibility: hidden' id='shop_head_form' enctype="multipart/form-data">
                <input type='hidden' name='_method' value="PUT">
                <input id='shop_head_field' type='file' name='shopHead'>
            </form>
        `);
        $shop_head_field = $("#shop_head_field");
    }
    $shop_head_field.on("change", function () {
        console.log("selected file: " + $shop_head_field.val());
        let $shop_head_form = $("#shop_head_form");
        $shop_head_form.attr("action", "/seller/shop/" + contentVm.shop.shopId + "/updateHead");
        $shop_head_form.attr("method", "POST");
        $shop_head_form.submit();
    });
    $shop_head_field.click();
}
