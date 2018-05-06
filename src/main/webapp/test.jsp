<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/9/21
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>url: <input type="text" value="/rest/orders/customer/25" id="url"/></div>
<div>
    method:
    <br/>
    <input type="radio" value="GET" id="methodGet" name="method" checked/><label for="methodGet">GET</label><br/>
    <input type="radio" value="POST" id="methodPost" name="method"/><label for="methodPost">POST</label><br/>
</div>
<div>requestBody: <input type="text" id="requestBody"/></div>
<div>
    <button onclick="submit()">Submit</button>
</div>

<div id="vm">
    <div>{{abc.def}}</div>
</div>
<button onclick="changeUser()">改变用户</button>

<script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script>
    var vm;
    $(function () {
        vm = new Vue({
            el: "#vm",
            data: {
                abc: {def: "wang"},
            }
        });
    });

    function changeUser() {
        vm.abc = {def: "rong"};
    }
</script>

</body>
</html>
