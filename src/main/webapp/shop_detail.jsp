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
        <div style="height: 120px;width: 120px;margin: 0 auto;">
            <img src="/rest/picture/${requestScope.shop.seller.head.pictureId}" style="height: 100% ;width: 100%;">
        </div>
        <div>卖家姓名：${requestScope.shop.seller.realName}</div>
        <div>卖家性别：${requestScope.shop.seller.gender}</div>
        <div>卖家电话：${requestScope.shop.seller.phone}</div>
    </div>

    <hr>

    <jsp:include page="goods_box.jsp"/>

</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
