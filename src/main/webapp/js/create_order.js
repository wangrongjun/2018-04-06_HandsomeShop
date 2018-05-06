var contentVm;
$(function () {
    // 在发起JQuery的Ajax请求前后，显示和去除遮罩层
    $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    contentVm = new Vue({
        el: "#content",
        data: {
            goods: goods,
            count: count,
            contactList: contactList,
            selectedContact: contactList.length > 0 ? contactList[0] : null,
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
        success: function (data) {
            Vue.set(contentVm.contactList, contentVm.contactList.length, newContact);
            contentVm.selectedContact = newContact;
        },
        error: function (xhr, errorMsg, exception) {
            alert("添加地址失败！错误信息：" + exception);
        }
    });

    // var address = $("#address").val();
    // if (!address || address === "") {
    //     alert("不能为空");
    //     return;
    // }
    // var url = "/address";
    // var data = {address: address};
    // $.post(url, data, function (data, status) {
    //     if (status === "success") {
    //         if (data === true) {
    //             alert("添加地址成功");
    //             var newOption = '<option value="' + address + '">' + address + '</option>';
    //             $(newOption).appendTo($("#address_list"));
    //         } else {
    //             alert("添加地址失败");
    //         }
    //     } else {
    //         window.location.href = "/login.jsp";
    //     }
    // });
}

function createOrder() {
    var address = $("#address_list").val();
    if (!address || address === "") {
        alert("请填写收货地址");
        return;
    }
    var phone = $("#phone").text();
    if (!phone || phone === "") {
        alert("请填写收联系电话");
        return;
    }
    var receiverName = $("#receiverName").text();
    if (!receiverName || receiverName === "") {
        alert("请填写收货人姓名");
        return;
    }

    var url = "/orders";
    var data = {address: address, phone: phone, receiverName: receiverName};
    $.post(url, data, function (result, status) {
        if (status === "success") {
            if (result === true) {
                window.location.href = "/create_order_succeed.jsp";
            } else {
                alert("订单创建失败");
            }
        } else {
            alert("订单创建失败");
        }
    });
}

function updatePhone() {
    var $phone = $("#phone");
    var phone = prompt("输入收货人的联系电话", $phone.text());
    $phone.text(phone);
}

function updateReceiverName() {
    var $receiverName = $("#receiverName");
    var receiverName = prompt("输入收货人的姓名", $receiverName.text());
    $receiverName.text(receiverName);
}