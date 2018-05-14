<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/20
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>成功下单</title>
</head>
<body>
<h1>你已成功下单！</h1>

<button onclick="window.location.href='${pageContext.request.contextPath}/orders'" class="btn-default">
    查看订单
</button>

<button onclick="window.location.href='${pageContext.request.contextPath}/'" class="btn-default">
    返回首页
</button>

</body>
</html>
