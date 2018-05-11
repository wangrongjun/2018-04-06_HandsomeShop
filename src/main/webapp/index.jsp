<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>英俊商城 - 首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js" type="text/javascript"
            charset="utf-8"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content>

    <div class="text-center">

        <div class="search_box">
            <input type="text" class="form-control" id="search_word" placeholder="请输入商品关键字">
            <button class="btn btn-primary" onclick="search()">搜索</button>
        </div>

        <div class="goods_type_box">
            <%--<span>商品类型：</span>--%>
            <%--<c:forEach var="goodsType" items="${sessionScope.goodsTypeList}">--%>
            <%--<a href="${pageContext.request.contextPath}/searchGoodsListByType?goodsTypeId=${goodsType.goodsTypeId}">--%>
            <%--${goodsType.name}--%>
            <%--</a>--%>
            <%--</c:forEach>--%>
            <div id="iframe-wrap">
                <iframe id="iframe" src="/index.html" frameborder="0" width="100%" height="200px"></iframe>
            </div>
        </div>

        <%--<a class="ad" title="618狂欢节会场" href="#">--%>
        <%--<img src="img/img_618.jpg">--%>
        <%--</a>--%>
    </div>

    <jsp:include page="goods_box.jsp"/>

</content>

<jsp:include page="footer.jsp"/>

<script type="text/javascript">
    function search() {
        var $search_word = $("#search_word");
        var searchWord = $search_word.val();
        if (!searchWord || searchWord === "") {
            alert("请输入关键字搜索");
            return;
        }
        window.location.href = "/searchGoods?searchWord=" + encodeURIComponent($search_word.val());
    }
</script>

</body>
</html>
