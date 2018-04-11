<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/19
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建订单-${goods.goodsName}</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min-3.2.0.css"/>
    <script src="js/jquery.min-1.9.0.js"></script>
    <script src="js/bootstrap.min-3.2.0.js"></script>
    <style type="text/css">
        /*---------总体--------*/

        .order_box {
            background-color: #feedf0;
            box-shadow: 0 0 2px gray;
            width: 90%;
            padding: 20px;
            margin: 20px auto;
        }

        /*---------商品图片--------*/

        .goods_img {
            width: 300px;
            height: 240px;
            float: left;
        }

        .goods_img img {
            width: 100%;
            height: 100%;
        }

        /*---------订单信息--------*/

        .order_info {
            line-height: 30px;
            font-size: 14px;
            max-width: 65%;
            display: inline-block;
            margin-left: 20px;
        }

        .order_info > * {
            margin: 10px;
        }

        .order_info span {
            font-size: 20px;
            font-weight: bold;
        }

        .order_info .btn {
            border-radius: 0;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<content>

    <center><h1>创建订单-${goods.goodsName}</h1></center>

    <div class="order_box">
        <div class="goods_img">
            <img src="${goods.goodsImageList[0].imageUrl}"/>
        </div>
        <div class="order_info">
            <div>商品：<span>${goods.goodsName}</span></div>
            <div>单价：<span>${goods.price}</span></div>
            <div>数量：<span>${count}</span></div>
            <div>运费：<span>0</span></div>
            <div>合计：<span>${goods.price * count}</span></div>
            <div>联系电话：<span id="phone">${sessionScope.customer.phone}</span></div>
            <div><a href="javascript:void(0);" onclick="updatePhone()">修改联系电话</a></div>
            <div>收货人：<span id="receiverName">${sessionScope.customer.realName}</span></div>
            <div><a href="javascript:void(0);" onclick="updateReceiverName()">修改收货人姓名</a></div>
            <div>收货地址：
                <div class="form-group">
                    <select class="form-control" id="address_list">
                        <c:forEach var="address" items="${sessionScope.addressList}">
                            <option value="${address.address}">${address.address}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="goods_name">
                <a href="javascript:void(0);" onclick="javascript:$('#mymodal').modal('toggle');">添加收货地址</a>
            </div>
            <button onclick="createOrder()" class="btn btn-danger">确认付款</button>
        </div>
    </div>

</content>

<!-- 模态弹出窗 -->
<div class="modal fade" id="mymodal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加收货地址</h4>
            </div>
            <%--TODO 模态弹出窗在提交时会失效--%>
            <%--<form action="addAddress.do" method="post">--%>
            <div class="modal-body">
                <input class="form-control" type="text" name="address" id="address" placeholder="请输入收货地址"/>
            </div>
            <div class="modal-footer">
                <%--<input type="reset" class="btn btn-default"/>--%>
                <button class="btn btn-primary" onclick="addAddress()" data-dismiss="modal">保存</button>
            </div>
            <%--</form>--%>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- 模态弹出窗 -->

<jsp:include page="footer.jsp"/>

<script type="text/javascript">
    function addAddress() {
        var address = $("#address").val();
        if (address == "") {
            alert("不能为空")
            return;
        }
        window.location.href = "addAddress.do?address=" + address;
    }

    function createOrder() {
        var address = $("#address_list").val();
        if (address == null || address == "") {
            alert("请填写收货地址");
            return;
        }
        var phone = $("#phone").text();
        if (phone == null || phone == "") {
            alert("请填写收联系电话");
            return;
        }
        var receiverName = $("#receiverName").text();
        if (receiverName == null || receiverName == "") {
            alert("请填写收货人姓名");
            return;
        }
        window.location.href = "createOrder.do?address=" + address +
            "&phone=" + phone +
            "&receiverName=" + receiverName;
    }

    function updatePhone() {
        var phone = prompt("输入收货人的联系电话", $("#phone").text());
        $("#phone").text(phone);
    }

    function updateReceiverName() {
        var receiverName = prompt("输入收货人的姓名", $("#receiverName").text());
        $("#receiverName").text(receiverName);
    }
</script>

</body>
</html>
