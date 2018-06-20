<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/18
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的购物车</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web_lib/css/bootstrap.min-3.2.0.css"/>
    <script src="${pageContext.request.contextPath}/web_lib/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/web_lib/js/bootstrap.min-3.2.0.js"></script>
</head>
<body>

<jsp:include page="header.jsp"/>
<content>
    <div class="text-center"><h1>我的购物车(${requestScope.totalCount})</h1></div>
    <jsp:include page="goods_box.jsp"/>
    <c:if test="${requestScope.goodsList.size()>0}">
        <div class="text-center">
            <button class="btn btn-danger btn-lg" style="margin-top: 30px">结算</button>
        </div>
    </c:if>
</content>
<jsp:include page="footer.jsp"/>

</body>
</html>
