<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/22
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>我的订单</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/customer_order_list.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script>
        var ordersCount = ${requestScope.ordersCount};
        var ordersList = ${requestScope.ordersListJson};
    </script>
    <script src="${pageContext.request.contextPath}/js/customer_order_list.js"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content id="content">

    <div class="text-center"><h1>我的订单({{ordersCount}})</h1></div>

    <template v-for="orders in ordersList">
        <div class="order_item">
            <div class="goods_image">
                <img :src="'/rest/picture/' + orders.goods.pictureList[0].pictureId"
                     v-on:click="showGoodsInfo(orders.goods.goodsId)"/>
            </div>
            <div class="right_box">
                <div class="order_info">
                    <div>订单编号：{{orders.ordersId}}</div>
                    <div>商品名称：{{orders.goods.goodsName}}</div>
                    <div>店鋪名称：{{orders.goods.shop.shopName}}</div>
                    <div>购买数量：{{orders.buyCount}}</div>
                    <div>收货人：{{orders.contact.receiver}}</div>
                    <div>联系电话：{{orders.contact.phone}}</div>
                    <div>收货地址：{{orders.contact.address}}</div>
                    <div>创建时间：{{orders.createTime}}</div>
                    <div>订单状态：<span>
                        {{orders.state==0?"交易中":(orders.state==1?"交易成功":"交易失败")}}
                    </span>
                    </div>
                    <div>总价：￥ <span>{{orders.price}}</span></div>
                    <div class="btn_box">
                        <template v-if="orders.state==0">
                            <button class="btn btn-danger">申请退款</button>
                            <button class="btn btn-success" v-on:click="confirmOrders(orders.ordersId)">确认收货</button>
                        </template>
                        <template v-else-if="orders.state==1 || orders.state==2">
                            <button class="btn btn-danger">申请售后</button>
                            <button class="btn btn-danger" v-on:click="deleteOrders(orders.ordersId)">删除订单</button>
                        </template>
                    </div>
                </div>
            </div>
        </div>
        <hr>
    </template>

</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
