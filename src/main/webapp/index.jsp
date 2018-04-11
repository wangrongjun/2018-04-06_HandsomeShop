<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>英俊商城 - 首页</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min-3.2.0.css"/>
    <link rel="stylesheet" type="text/css" href="/css/index.css"/>
    <script src="/js/jquery.min-1.9.0.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/bootstrap.min-3.2.0.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content>

    <center>
        <div class="goods_type_box">
            <span>商品类型：</span>
            <c:forEach var="goodsType" items="${sessionScope.goodsTypeList}">
                <a href="search_goods_type.jsp?goodsTypeId=${goodsType.goodsTypeId}">${goodsType.name}</a>
            </c:forEach>
        </div>

        <div class="search_box">
            <input type="text" class="form-control" id="search_word" placeholder="请输入商品关键字">
            <button class="btn btn-primary" onclick="search()">搜索</button>
        </div>

        <%--<a class="ad" title="618狂欢节会场" href="#">--%>
        <%--<img src="img/img_618.jpg">--%>
        <%--</a>--%>

    </center>

    <jsp:include page="goods_box.jsp"/>

</content>

<jsp:include page="footer.jsp"/>

<script type="text/javascript">
    function search() {
        var searchWord = $("#search_word").val();
        if (searchWord == "") {
            alert("请输入关键字搜索");
            return;
        }
        window.location.href = "search.do?searchWord=" + $("#search_word").val();
    }
</script>

</body>
</html>
