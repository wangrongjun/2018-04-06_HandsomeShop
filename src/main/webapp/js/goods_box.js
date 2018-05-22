let goodsBoxVm;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    goodsBoxVm = new Vue({
        el: "#goods_box",
        data: {
            goodsList: null,
            pageIndex: 0,
            pageSize: 0,
            totalCount: 0,
            showSortBtns: true,
            sortType: 0,// 0.综合排序 1.销量 2.价格由低到高 3.价格由高到低
        },
        methods: {
            reload: reload,
            getPictureUrl:

                function (index) {
                    let goods = goodsBoxVm.goodsList[index];
                    if (goods.pictureSet != null && goods.pictureSet.size() > 0) {
                        return "/rest/picture/" + goods.pictureSet.iterator().next().pictureId;
                    }
                    return null;
                }
        }
        ,
        computed: {
            pageCount: function () {
                return goodsBoxVm.totalCount / goodsBoxVm.pageSize - 1 +
                    (goodsBoxVm.totalCount % goodsBoxVm.pageSize === 0 ? 0 : 1);
            }
        }
    })
    ;
});

function reload(pageIndex, sortType) {
    console.log("pageIndex:" + pageIndex);
    var map = {
        "pageIndex": pageIndex,
        "sortType": sortType
    };
    location.href = updateParamInUrl(location.href, map) + "";
}
