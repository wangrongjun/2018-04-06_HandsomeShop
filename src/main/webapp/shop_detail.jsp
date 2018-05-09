<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/22
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${requestScope.shop.shopName}</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<content>

    <div class="text-center">
        <h1>${requestScope.shop.shopName}</h1>
        <div style="height: 120px;width: 120px">
            <img src="${requestScope.shop.seller.headUrl}" style="height: 100% ;width: 100%;">
        </div>
        <div>商家姓名：${requestScope.shop.seller.realName}</div>
        <div>商家性别：${requestScope.shop.seller.gender==1?"男":"女"}</div>
        <div>商家电话：${requestScope.shop.seller.phone}</div>
    </div>

    <hr>

    <jsp:include page="goods_box.jsp"/>

</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
