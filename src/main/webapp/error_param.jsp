<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/22
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>参数错误</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min-3.2.0.css"/>
    <script src="js/jquery.min-1.9.0.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/bootstrap.min-3.2.0.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>

<jsp:include page="header.jsp"/>

<content>
    <center style="margin: 200px 0">
        <h1>对不起，你输入的参数有误！</h1>
        <br>
        <a href="index.jsp"><h5>返回首页</h5></a>
    </center>
</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
