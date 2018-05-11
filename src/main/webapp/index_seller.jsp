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

    <div id="title"><h2>所有商店 ({{shopList.length}})</h2></div>

    <div class="shop_box container">
        <div class="row">
            <div class="shop_item_box col-sm-3" v-for="shop in shopList" v-on:click="showShopInfo(shop.shopId)">
                <div class="img_box">
                    <img :src="'/rest/picture/' + shop.head.pictureId" class="img">
                </div>
                <div class="info_box">
                    <div class="shop_name">{{shop.shopName}}</div>
                    <div>{{shop.description}}</div>
                </div>
            </div>
            <%--添加商店的按钮--%>
            <div class="shop_item_box col-sm-3" v-on:click="addShop">
                <div class="img_box">
                    <img src="${pageContext.request.contextPath}/img/ic_add.png" class="img">
                </div>
                <div class="info_box">创建商店</div>
            </div>
        </div>
    </div>

</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
