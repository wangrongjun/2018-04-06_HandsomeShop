<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/18
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GoodsBox</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min-3.2.0.css"/>
    <link rel="stylesheet" href="css/goods_box.css">
    <script src="js/jquery.min-1.9.0.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/bootstrap.min-3.2.0.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/wang.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>

<%--该页面需要4个参数：goodsList,pageIndex,totalCount,sortType--%>
<%--sortType：0.综合排序  1.销量  2.价格由低到高  3.价格由高到低--%>

<center>
    <div class="sort_box btn-group">
        <button class="btn btn-default <c:if test="${sortType==0}">active</c:if>">综合排序</button>
        <button class="btn btn-default <c:if test="${sortType==1}">active</c:if>">销量由高到低</button>
        <button class="btn btn-default <c:if test="${sortType==2}">active</c:if>">价格由低到高</button>
        <button class="btn btn-default <c:if test="${sortType==3}">active</c:if>">价格由高到低</button>
    </div>
</center>

<div class="goods_box container">
    <div class="row">

        <c:forEach var="goods" items="${goodsList}" varStatus="status">
            <div class="col-sm-3">
                <div class="goods">
                    <div class="goods_image">
                        <a href="goods_info.jsp?goodsId=${goods.goodsId}">
                                <%--<c:if test="${goods.goodsImageList!=null&&goods.goodsImageList.size()>0}">--%>
                                <%--<img src="${goods.goodsImageList.get(0).imageUrl}"/>--%>
                                <%--</c:if>--%>
                            <img src="admin/img/goods_1.jpg"/>
                        </a>
                    </div>
                    <a href="goods_info.jsp?goodsId=${goods.goodsId}" class="goods_name">
                            ${goods.goodsName}
                    </a>
                    <div class="price">￥ ${goods.price}</div>
                    <div class="remain_and_sell">
                        <span class="remain">库存：<span id="remainCount">${goods.remainCount}</span>&nbsp;件</span>
                        <span class="sell">销量：<span id="sellCount">${goods.sellCount}</span>&nbsp;笔</span>
                    </div>
                    <div class="description">${goods.description}</div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>

<%--换页--%>
<div class="change_page_box">

    <c:choose>
        <c:when test="${totalCount>0}">
            <ul class="pagination">
                    <%--<li><a href="#">&laquo;&nbsp;首页</a></li>--%>
                <c:forEach begin="0" end="${totalCount/12-1+(totalCount%12==0?0:1)}" varStatus="status">
                    <li <c:if test="${status.index==pageIndex}">class="active"</c:if>>
                        <a href="javascript:void(0);" onclick="reload(${status.index},${sortType})">
                                ${status.index+1}
                        </a>
                    </li>
                </c:forEach>
                    <%--<li><a href="javascript:void(0);">尾页&nbsp;&raquo;</a></li>--%>
            </ul>
        </c:when>
        <c:otherwise>
            <center><h2>抱歉，没有商品！</h2></center>
        </c:otherwise>
    </c:choose>

</div>

<script src="js/util/TextUtil.js"></script>
<script type="text/javascript">
    function reload(pageIndex, sortType) {
        console.log("pageIndex:" + pageIndex);
        var map = {
            "pageIndex": pageIndex,
            "sortType": sortType
        };
        location.href = updateParamInUrl(location.href, map) + "";
    }
</script>

</body>
</html>
