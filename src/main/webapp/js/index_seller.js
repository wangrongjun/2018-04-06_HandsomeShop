let contentVm;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            sellerId: sellerId,
            shopList: shopList,
            // shopName: null,// 创建商店时用到
            // shopDescription: null,// 创建商店时用到
        },
        methods: {
            showShopInfo: showShopInfo,
        }
    });
});

function showShopInfo(shopId) {
    window.location.href = "/seller/shop/" + shopId;
}

function createShop() {
    let formData = new FormData(document.querySelector("#create_shop_form"));
    $.ajax({
        url: "/rest/shop",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        cache: false,
        success: function (result) {
            contentVm.shopList.push(result);
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
}
