<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/18
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header_content_footer.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <title>用户注册</title>
    <style type="text/css">
        content {
            background: url(/img/bg_wood.jpg);
        }

        .register_box {
            margin: 20px auto;
            width: 50%;
            max-width: 400px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px gray;
            background-color: rgba(238, 238, 238, 0.5);
        }

        #msg {
            margin-left: 20px;
            color: red;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<content>
    <div class="register_box">
        <div class="text-center"><h1>用户注册</h1></div>
        <form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return checkInput()">
            <div class="form-group">
                <label>手机号：</label>
                <input type="number" class="form-control" name="phone" placeholder="请输入手机号">
                <span id="phone_hint"></span>
            </div>
            <div class="form-group">
                <label>密码：</label>
                <input type="password" class="form-control" name="password" placeholder="请输入密码">
            </div>
            <div class="form-group">
                <label>昵称：</label>
                <input type="text" class="form-control" name="nickname" placeholder="请输入昵称">
            </div>
            <div class="form-group">
                <label>真实姓名：</label>
                <input type="text" class="form-control" name="realName" placeholder="请输入真实姓名">
            </div>
            <div class="form-group">
                <label>性别：</label> &nbsp;
                <label><input type="radio" name="gender" value="man" checked="checked">男</label> &nbsp;
                <label><input type="radio" name="gender" value="woman">女</label>
            </div>
            <div class="form-group">
                <label>身份：</label> &nbsp;
                <label>
                    <input type="radio" name="identity" value="customer" checked="checked">客户
                </label> &nbsp;
                <label><input type="radio" name="identity" value="seller">卖家</label>
            </div>
            <button type="reset" class="btn btn-default">重置</button>
            <button type="submit" class="btn btn-primary">注册</button>
            <span id="msg">${msg}</span>
        </form>
        <a href="${pageContext.request.contextPath}/login.jsp">已有帐号，马上登录</a>
    </div>
</content>

<jsp:include page="footer.jsp"/>

<script type="text/javascript">
    function checkInput() {
        if ($("[name='phone']").val() == "" ||
            $("[name='password']").val() == "" ||
            $("[name='nickname']").val() == "" ||
            $("[name='realName']").val() == "") {
            $("#msg").text("请填写完整信息");
            return false;
        }
        return true;
    }

    $("input[name=phone]").on("focusout", function () {
        var phone = $(this).val();
        if (phone != "") {
            queryExists(phone);
        }
    });

    function queryExists(phone) {
        var queryExistsXmlHttp;
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            queryExistsXmlHttp = new XMLHttpRequest();
        }
        else {// code for IE6, IE5
            queryExistsXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        var url = "phoneExists?phone=" + phone;
        queryExistsXmlHttp.open("POST", url, true);
        queryExistsXmlHttp.onreadystatechange = function () {
            if (queryExistsXmlHttp.readyState == 4) {
                if (queryExistsXmlHttp.status == 200) {
                    console.log(queryExistsXmlHttp.responseText);
                    if (queryExistsXmlHttp.responseText == "true") {
                        queryExistsXmlHttp = null;
                        $("#phone_hint").text("该手机号已注册");
                        $("#phone_hint").css("color", "red");
                    } else {
                        $("#phone_hint").text("该手机号可以使用");
                        $("#phone_hint").css("color", "green");
                    }
                } else {
                    alert("404");
                }
            }
        };
        $("#phone_hint").text("正在校验...");
        queryExistsXmlHttp.send();
    }
</script>

</body>
</html>
