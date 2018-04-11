<%@ page import="com.handsome.shop.dao.GoodsImageDao" %>
<%@ page import="com.handsome.shop.dao.impl.GoodsImageDaoImpl" %>
<%@ page import="com.handsome.shop.bean.GoodsImage" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/9/29
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>所有图片</title>
</head>
<body>
<%
    GoodsImageDao goodsImageDao = new GoodsImageDaoImpl();
    List<GoodsImage> goodsImageList = goodsImageDao.queryAll();
    request.setAttribute("goodsImageList", goodsImageList);
%>

<a href="javascript:requestDelete()">删除</a>
<hr>
<table>
    <tr>
        <th>选择</th>
        <th>图片id</th>
        <th>图片路径</th>
    </tr>
    <c:if test="${empty goodsImageList || fn:length(goodsImageList)==0}">
        <p align="center">没有数据</p>
    </c:if>
    <c:forEach items="${goodsImageList}" var="goods">
        <tr>
            <td><input name="check" type="checkbox" value="${goods.imageId}"></td>
            <td>${goods.imageId}</td>
            <td>${goods.imageUrl}</td>
        </tr>
    </c:forEach>
</table>

<script src="../js/jquery.min-1.9.0.js"></script>
<script>
    function requestDelete() {
        var deleteIdList = "";
        $("input[name=check]").each(function () {
            // $(this).attr("checked")=="true"
            if (this.checked == true) {
                deleteIdList += $(this).val() + ",";
            }
        });
        if (deleteIdList.length == 0) {
            alert("请选择要删除的记录");
            return;
        }
        deleteIdList = deleteIdList.substring(0, deleteIdList.length - 1);
        if (confirm("确实要删除以下记录吗？\n" + deleteIdList)) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "deleteGoodsImage.do111",
                data: {deleteIdList: deleteIdList},
                success: function (responseData) {
                    alert(responseData.msg);
                    if (responseData.stateCode == 0) {
                        // 删除成功的操作
                        $(":checked").parents("tr").remove();
                    }
                },
                error: function (XMLHttpRequest, textStates, errorThrown) {
                    alert("error: " + textStates + "  " + errorThrown);
                }
            })
        }
    }
</script>

</body>
</html>
