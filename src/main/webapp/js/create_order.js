var contentVm;
var selectContactModelVm;
$(function () {
    // 在发起JQuery的Ajax请求前后，显示和去除遮罩层
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            goods: goods,
            count: count,
            remark: null,// 这个属性需要用户输入，而其他数据都是从请求中获取的。
            selectedContact: contactList.length > 0 ? contactList[0] : null,
        }
    });
    selectContactModelVm = new Vue({
        el: "#select_contact_model",
        data: {
            contactList: contactList,
        },
        methods: {
            selectContact: function (contactId) {
                for (const contact of selectContactModelVm.contactList) {
                    if (contact.contactId === contactId) {
                        contentVm.selectedContact = contact;
                        break;
                    }
                }
            }
        }
    });
});

function addContact() {
    var addContactReceiver = $("#addContactReceiver").val();
    var addContactPhone = $("#addContactPhone").val();
    var addContactAddress = $("#addContactAddress").val();
    var newContact = {
        receiver: addContactReceiver,
        phone: addContactPhone,
        address: addContactAddress,
    };
    $.ajax({
        url: "/rest/customer/" + customer.customerId + "/contact",
        type: "POST",
        data: newContact,
        success: function (response) {
            newContact.contactId = response.data;
            Vue.set(selectContactModelVm.contactList, selectContactModelVm.contactList.length, newContact);
            contentVm.selectedContact = newContact;
        },
        error: function (xhr, errorMsg, exception) {
            alert("添加地址失败！错误信息：" + exception);
        }
    });
}

function createOrder() {
    $.ajax({
        url: "/rest/orders",
        type: "POST",
        data: {
            customerId: customer.customerId,
            goodsId: goods.goodsId,
            count: count,
            remark: contentVm.remark,
            contactId: contentVm.selectedContact.contactId
        },
        success: function (response) {
            // 不是跳转到新地址，而是直接把新地址覆盖了当前地址，防止用户点击后退
            window.location.replace("/create_orders_succeed.jsp");
        },
        error: function (xhr, errorMsg, exception) {
            alert("订单创建失败！错误信息：" + exception);
        }
    });
}
