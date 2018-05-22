let contentVm;

$(function () {
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            editable: editable,
            shop: shop,
        },
    });
});
