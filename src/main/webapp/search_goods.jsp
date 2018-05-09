<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/21
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${requestScope.searchWord}-搜索结果</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <style type="text/css">
        .search_box input {
            width: 50%;
            max-width: 500px;
            margin: 10px auto;
            display: inline-block;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<content>

    <div class="text-center">
        <div class="search_box">
            <input type="text" class="form-control" id="search_word"
                   placeholder="请输入商品关键字" value="${requestScope.searchWord}">
            <button class="btn btn-primary" onclick="search()">搜索</button>
        </div>
    </div>
    <hr>

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
