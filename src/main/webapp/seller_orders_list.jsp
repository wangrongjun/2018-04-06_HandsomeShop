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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/order_list.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script src="${pageContext.request.contextPath}/js/util/DateUtil.js"></script>
    <script>
        let ordersCount = ${requestScope.ordersCount};
        let ordersList = ${requestScope.ordersListJson};
    </script>
    <script src="${pageContext.request.contextPath}/js/seller_order_list.js"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content id="content">

    <div class="text-center"><h1>待处理订单({{ordersCount}})</h1></div>

    <div v-for="orders in ordersList" class="order_item">
        <div class="goods_image">
            <img :src="'/rest/picture/' + orders.goods.pictureSet[0].pictureId"
                 @click="showGoodsInfo(orders.goods.goodsId)"/>
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
                <div>创建时间：{{new Date(orders.createdOn).format("yyyy-MM-dd HH:mm:ss")}}</div>
                <div>总价：￥ <span>{{orders.price}}</span></div>
                <div>备注：{{orders.remark}}</div>
                <div>订单状态：<span>{{ordersStatusMsg(orders.status)}}</span></div>
                <div v-if="orders.refund">退款理由：{{orders.refund.reason}}</div>
                <div class="btn_box">
                    <button v-if="showLogisticsBtn(orders.status)" @click="queryLogistics(orders.ordersId)"
                            class="btn btn-default">查看物流
                    </button>
                    <button v-if="showConfirmDeliverGoodsBtn(orders.status)" @click="deliverGoods(orders.ordersId)"
                            class="btn btn-success">确认发货
                    </button>
                    <button v-if="showConfirmRefundBtn(orders.status)" @click="confirmRefund(orders.ordersId)"
                            class="btn btn-warning">确认退款
                    </button>
                </div>
            </div>
        </div>
    </div>

</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
