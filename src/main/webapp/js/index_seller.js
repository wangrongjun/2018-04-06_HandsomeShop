$(function () {
    new Vue({
        el: "#content",
        data: {
            sellerId: sellerId,
            shopList: shopList,
        },
        methods: {
            addShop: addShop,
            showShopInfo: showShopInfo,
        }
    });
});

function addShop() {
    alert("创建商店");
}

function showShopInfo(shopId) {
    alert("showShopInfo: " + shopId);
}
