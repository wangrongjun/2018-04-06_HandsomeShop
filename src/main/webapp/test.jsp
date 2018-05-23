<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/9/21
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue-router.js"></script>
    <script>
        function upload() {
            let formData = new FormData($("#form")[0]);
            $.ajax({
                url: "/test1",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                cache: false,
                success: function (result) {
                    alert(result);
                },
                error: function (xhr, status, error) {
                    alert(error);
                }
            });
        }
    </script>
</head>
<body>

<form action="${pageContext.request.contextPath}/test1" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_method" value="PUT">
    <input type="number" name="testId" value="1">
    <input type="text" name="testName" value="a">
    <input type="file" name="testFile">
    <input type="submit" value="上传">
</form>

<br>
<hr>
<br>

<form id="form" enctype="multipart/form-data">
    <input type="hidden" name="_method" value="PUT">
    <input type="number" name="testId" value="1">
    <input type="text" name="testName" value="a">
    <input type="file" name="testFile">
</form>
<button onclick="upload()">上传</button>

</body>
</html>
