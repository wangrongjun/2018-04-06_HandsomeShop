<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/17
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>英俊商城 - 商家首页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index_seller.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script>
        var sellerId = ${requestScope.sellerId};
        var shopList = ${requestScope.shopListJson};
    </script>
    <script src="${pageContext.request.contextPath}/js/index_seller.js"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content id="content">
    <center><h2>所有商店 ({{shopList.length}})</h2></center>
    <div class="shop_item" v-for="shop in shopList">
        <img v-bind:src="shop.headUrl">
        <br>
        商店编号：{{shop.shopId}}
        <br>
        商店名称：{{shop.shopName}}
        <br>
        商店描述：{{shop.description}}
        <hr>
    </div>

</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
