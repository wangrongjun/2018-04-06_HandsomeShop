<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/9/21
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
<script>
    $.ajax({
        url: "/rest/customer/25/contact",
        success: function (data) {
            alert("success: " + data);
        },
        error: function (XMLHttpRequest, errorMsg, exception) {
            alert("errorMsg: " + errorMsg + ", exception: " + exception);
        }
    });
</script>

</body>
</html>
