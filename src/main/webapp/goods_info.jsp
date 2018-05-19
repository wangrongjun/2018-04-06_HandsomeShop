<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/18
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${requestScope.goods.goodsName}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/luara.left.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header_content_footer.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/goods_info.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.luara.0.0.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script>
        let goods = ${requestScope.goodsJson};
        let evaluateList = ${requestScope.evaluateListJson};
    </script>
    <script src="${pageContext.request.contextPath}/js/goods_info.js"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content id="content">

    <div class="top">
        <div class="img_box">
            <ul>
                <li v-for="picture in goods.pictureSet">
                    <img :src="'/rest/picture/' + picture.pictureId">
                </li>
                <%--<c:forEach var="picture" items="${requestScope.goods.pictureSet}">--%>
                <%--<li><img src="/rest/picture/${picture.pictureId}"></li>--%>
                <%--</c:forEach>--%>
            </ul>
            <ol>
                <li v-for="picture in goods.pictureSet">
                    <img :src="'/rest/picture/' + picture.pictureId">
                </li>
                <%--<c:forEach var="picture" items="${requestScope.goods.pictureSet}">--%>
                <%--<li><img src="/rest/picture/${picture.pictureId}"></li>--%>
                <%--</c:forEach>--%>
            </ol>
        </div>

        <div class="right_box">
            <div class="goods_info_box">
                <div class="goods_name">
                    商品名称
                    <span id="goods_name">{{goods.goodsName}}</span>
                </div>

                <div class="goods_price">
                    商品价格：￥
                    <span id="goods_price">{{goods.price}}</span>
                </div>
                <div class="goods_description">
                    商品描述：
                    <span id="goods_description">{{goods.description}}</span>
                </div>
                <div class="goods_type">
                    商品类型：
                    <span id="goods_type">{{goods.goodsType.name}}</span>
                </div>
            </div>

            <hr>
            选择商品属性：
            <div id="goods_attribute">
                <template v-for="attrName in goods.goodsAttrNames">
                    <div class="attr_name text-info">{{attrName.name}}</div>
                    <span class="attr_value label label-default"
                          v-for="attrValue in attrName.attrValues">{{attrValue.value}}</span>
                </template>
                <%--<c:forEach var="attrName" varStatus="status" items="${requestScope.goods.goodsAttrNames}">--%>
                <%--<div class="text-info">${attrName.name}</div>--%>
                <%--<c:forEach var="attrValue" items="${attrName.attrValues}">--%>
                <%--<span class="label label-default">${attrValue.value}</span>--%>
                <%--</c:forEach>--%>
                <%--</c:forEach>--%>
            </div>

            <form action="${pageContext.request.contextPath}/confirmOrders" method="post">
                <input type="hidden" name="goodsId" v-model="goods.goodsId"/>
                <div class="buy_box">
                    <label for="count">购买数量：</label>
                    <input type="number" name="count" id="count" class="form-control" v-model="buyCount"/>
                    <a class="btn btn-warning" onclick="addGoodsToShopCar()">加入购物车</a>
                    <input class="btn btn-danger" type="submit" value="购买"/>
                </div>
            </form>
        </div>
    </div>

    <hr/>

    <div class="seller_box">
        <div class="seller_head">
            <img :src="'/rest/picture/' + goods.shop.head.pictureId"/>
        </div>
        <div class="right_box">
            <div class="seller_info">
                <div class="seller_name">{{goods.shop.shopName}}</div>
                <div class="seller_description">{{goods.shop.description}}</div>
                <div class="btn_box">
                    <a class="btn btn-success" :href="'/shop/' + goods.shop.shopId">进店逛逛</a>
                    <button class="btn btn-danger" onclick="showSellerInfo()">联系卖家</button>
                </div>
            </div>
        </div>
    </div>

    <hr/>

    <div class="evaluate_box">
        <div class="text-center">用户评价</div>
        <div class="divider"></div>

        <%--<c:if test="${requestScope.evaluateList.size()==0}">--%>
        <%--<div class="text-center">暂无评价</div>--%>
        <%--</c:if>--%>
        <div class="text-center" v-if="evaluateList.length == 0">暂无评价</div>

        <c:forEach var="evaluate" items="${requestScope.evaluateList}">
            <div class="item_box">
                <div class="head">
                    <img src="/rest/picture/${evaluate.orders.customer.head.pictureId}"/>
                </div>
                <div class="right">
                    <div>
                        <div class="gender">
                            <img src="${evaluate.orders.customer.gender=='男'?
                            "/img/ic_gender_man.png":"/img/ic_gender_woman.png"}"/>
                        </div>
                        <div class="evaluate">
                            <c:choose>
                                <c:when test="${evaluate.level == 'Good'}">
                                    <span class="label label-success">好评</span>
                                </c:when>
                                <c:when test="${evaluate.level == 'Normal'}">
                                    <span class="label label-warning">中评</span>
                                </c:when>
                                <c:when test="${evaluate.level == 'Bad'}">
                                    <span class="label label-danger">差评</span>
                                </c:when>
                            </c:choose>
                        </div>
                        <span class="name">${evaluate.orders.customer.nickname}</span>
                        <span class="time">${evaluate.createdOn}</span>
                    </div>
                    <div class="content">${evaluate.content}</div>
                </div>
            </div>
            <div class="divider"></div>
        </c:forEach>
    </div>

    <%--显示卖家信息的窗口--%>
    <div class="modal fade" id="mymodal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">卖家信息</h4>
                </div>
                <div class="modal-body">
                    <div style="height: 100px;width: 100px">
                        <img :src="'/rest/picture/' + goods.shop.seller.head.pictureId"
                             style="height: 100% ;width: 100%;">
                    </div>
                    <div>卖家昵称：{{goods.shop.seller.nickname}}</div>
                    <div>卖家姓名：{{goods.shop.seller.realName}}</div>
                    <div>卖家性别：{{goods.shop.seller.gender}}</div>
                    <div>卖家电话：{{goods.shop.seller.phone}}</div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <%--显示卖家信息的窗口--%>

</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
