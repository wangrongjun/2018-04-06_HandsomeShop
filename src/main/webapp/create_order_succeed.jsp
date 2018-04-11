<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/20
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>下单完成</title>
</head>
<body>
<h1>你已成功下单！</h1>
<div>商品：${sessionScope.orders.goods.goodsName}</div>
<div>价格：${sessionScope.orders.price}</div>
<div>购买数量：${sessionScope.orders.buyCount}</div>
<div>创建时间：${sessionScope.orders.createTime}</div>
<div>收货人：${sessionScope.orders.receiverName}</div>
<div>联系电话：${sessionScope.orders.phone}</div>
<div>收货地址：${sessionScope.orders.address}</div>
<a href="index.jsp">返回首页</a>
</body>
</html>
