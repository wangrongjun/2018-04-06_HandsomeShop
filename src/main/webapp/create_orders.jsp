<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/19
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>创建订单-${requestScope.goods.goodsName}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script>
        var customer = ${requestScope.customerJson};
        var goods = ${requestScope.goodsJson};
        var contactList = ${requestScope.contactListJson};
        var count = ${requestScope.count};
    </script>
    <script src="${pageContext.request.contextPath}/js/create_order.js"></script>
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

        /*-----------收货地址------------*/

        .order_info .contact_item {
            border: 1px solid orangered;
            border-radius: 5px;
        }

        #select_contact_model .select_contact_item {
            margin: 5px 10px;
        }

        #select_contact_model .select_contact_item:hover {
            box-shadow: 0 0 10px gray;
            background-color: #ccc;
            cursor: default;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<content id="content">

    <div class="text-center"><h1>创建订单 - {{goods.goodsName}}</h1></div>

    <div class="order_box">
        <div class="goods_img">
            <img :src="'/rest/picture/' + goods.pictureList[0].pictureId"/>
        </div>

        <div class="order_info">
            <div>商品：<span>{{goods.goodsName}}</span></div>
            <div>单价：<span>{{goods.price}}</span></div>
            <div>数量：<span>{{count}}</span></div>
            <div>运费：<span>0</span></div>
            <div>合计：<span>{{goods.price * count}}</span></div>
            <div>
                收货地址：
                <div class="contact_item">
                    <div>
                        收件人：
                        <span v-if="selectedContact != null">{{selectedContact.receiver}}</span>
                        &nbsp;&nbsp;&nbsp;
                        收件人电话：
                        <span v-if="selectedContact != null">{{selectedContact.phone}}</span>
                    </div>
                    <div>
                        收件人地址：
                        <span v-if="selectedContact != null">{{selectedContact.address}}</span>
                    </div>
                </div>
            </div>
            <div class="goods_name">
                <a href="javascript:void(0)" onclick="$('#modal_add_contact').modal('toggle')">添加收货地址</a>
                <br/>
                <a href="javascript:void(0)" onclick="$('#modal_select_contact').modal('toggle')">更改收货地址</a>
            </div>
            <div>
                备注：<input class="form-control" type="text" v-model="remark">
            </div>
            <button onclick="createOrder()" class="btn btn-danger">确认付款</button>
        </div>
    </div>

</content>

<!-- 添加收货地址的模态弹出窗 -->
<div class="modal fade" id="modal_add_contact">
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
                <div>
                    <div>
                        收件人名字：<input type="text" id="addContactReceiver"/>
                    </div>
                    <div>
                        收件人电话：<input type="text" id="addContactPhone"/>
                    </div>
                    <div>
                        收件人地址：<input type="text" id="addContactAddress"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="addContact()" data-dismiss="modal">添加</button>
            </div>
            <%--</form>--%>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- 添加收货地址的模态弹出窗 -->

<!-- 选择收货地址的模态弹出窗 -->
<div class="modal fade" id="modal_select_contact">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择收货地址</h4>
            </div>
            <div class="modal-body" id="select_contact_model">
                <div v-for="(contact,index) in contactList" :key="contact.contactId"
                     @click="selectContact(contact.contactId)"
                     class="select_contact_item" data-dismiss="modal">
                    <strong>{{index + 1}}</strong>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    收件人名字：<span>{{contact.receiver}}</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    收件人电话：<span>{{contact.phone}}</span>
                    <br/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    收件人地址：<span>{{contact.address}}</span>
                </div>
            </div>
            <%--<div class="modal-footer">--%>
            <%--<button class="btn btn-primary" onclick="addContact()" data-dismiss="modal">选择</button>--%>
            <%--</div>--%>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- 选择收货地址的模态弹出窗 -->

<jsp:include page="footer.jsp"/>

</body>
</html>
