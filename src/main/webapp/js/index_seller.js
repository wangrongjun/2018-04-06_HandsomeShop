let contentVue;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockIU);

    contentVue = new Vue({
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
    $.ajax({
        url: "/rest/shop",
        type: "POST",
        data: {
            sellerId: sellerId,
            shopName: shopName,
            shopDescription: shopDescription,
        },
        cache: false,
        success: function (data) {
            contentVue.shopList.push(data);
        },
        error: function (xhr, errorMsg, exception) {
            alert("" + exception);
        }
    });
}
