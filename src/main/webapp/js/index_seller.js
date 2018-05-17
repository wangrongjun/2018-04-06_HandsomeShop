$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockIU);

    new Vue({
        el: "#content",
        data: {
            sellerId: sellerId,
            shopList: shopList,
            shopName: null,// 创建商店时用到
            shopDescription: null,// 创建商店时用到
        },
        methods: {
            showShopInfo: showShopInfo,
            createShop: createShop,
        }
    });
});

function showShopInfo(shopId) {
    alert("showShopInfo: " + shopId);
}

function createShop(shopName, shopDescription) {
    // formData.append("shopName", shopName);
    // formData.append("shopDescription", shopDescription);
    // formData.append("shopHead", $("#shop_head")[0].files[0]);
    $.ajax({
        url: "/createShop",
        type: "POST",
        data: {
            shopName: shopName,
            shopDescription: shopDescription,
        },
        cache: false,
        success: function (response) {
            alert("" + response);
        },
        error: function (xhr, errorMsg, exception) {
            alert("" + exception);
        }
    });
}
