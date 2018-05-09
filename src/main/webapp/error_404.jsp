<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/23
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_content_footer.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content>
    <center style="height: 500px">
        <h1>404 - 页面不存在</h1>
        <hr>
        <a href="index.jsp">返回首页</a>
    </center>
</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
