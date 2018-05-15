<%@ page import="com.handsome.shop.entity.Customer" %>
<%@ page import="com.handsome.shop.constant.C" %>
<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/18
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>个人信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <style type="text/css">
        .panel {
            width: 500px;
            margin: 50px auto;
        }

        .user_head {
            display: inline-block;
            width: 200px;
            height: 200px;
        }

        .user_head img {
            width: 100%;
            height: 100%;
        }

        .user_info {
            display: inline-block;
            font-size: 18px;
            padding: 10px;
        }
    </style>
</head>
<body onload="showMsg('${requestScope.msg}')">

<%
    Customer customer = (Customer) request.getSession().getAttribute(C.SESSION_CUSTOMER);
    if (customer == null) {
        request.setAttribute("msg", "请登录");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
        return;
    }

    String msg = (String) request.getAttribute("msg");
    if (msg != null && msg.equals("上传成功")) {//如果有返回的信息，并且是成功上传
        // 避免浏览器的图片缓存导致头像成功上传后，刷新也不显示新头像
        request.setAttribute("headTempId", "?headTempId=" + (int) (Math.random() * 1000));
    }

%>

<jsp:include page="header.jsp"/>

<content>
    <div class="panel panel-primary">
        <div class="panel-heading">个人信息</div>
        <div class="panel-body">
            <div class="user_head"><img src="/rest/picture/${sessionScope.customer.head.pictureId}${headTempId}"/></div>
            <div class="user_info">
                <div>昵称：<span>${sessionScope.customer.nickname}</span></div>
                <div>姓名：<span>${sessionScope.customer.realName}</span></div>
                <div>性别：<span>${sessionScope.customer.gender}</span></div>
                <div>电话：<span>${sessionScope.customer.phone}</span></div>
            </div>
        </div>
        <div class="panel-footer">
            <button class="btn btn-warning" onclick="changeHead()">更换头像</button>
            <button class="btn btn-info">修改昵称</button>
            <hr>
            <div id="upload_box" style="display: none;">
                <form method="post" action="${pageContext.request.contextPath}/user/head" enctype="multipart/form-data">
                    <input type="file" name="file">
                    <input type="submit" value="上传">
                </form>
            </div>
        </div>
    </div>
</content>

<jsp:include page="footer.jsp"/>

<script type="text/javascript">
    function showMsg(msg) {
        if (!msg && msg !== "") {
            alert(msg);
        }
    }

    function changeHead() {
        document.getElementById("upload_box").style.display = "block";
    }
</script>

</body>
</html>
