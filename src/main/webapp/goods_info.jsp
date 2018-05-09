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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/luara.left.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/goods_info.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header_content_footer.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body onload="showMsg('${requestScope.msg}')">

<jsp:include page="header.jsp"/>

<content>

    <div class="top">
        <div class="img_box">
            <ul>
                <c:forEach var="picture" items="${requestScope.goods.pictureList}">
                    <li><img src="/rest/picture/${picture.pictureId}"></li>
                </c:forEach>
                <%--<li><img src="${pageContext.request.contextPath}/admin/img/goods_1.jpg"></li>--%>
            </ul>
            <ol>
                <c:forEach var="picture" items="${requestScope.goods.pictureList}">
                    <li><img src="/rest/picture/${picture.pictureId}"></li>
                </c:forEach>
            </ol>
        </div>

        <div class="right_box">
            <div class="goods_info_box">
                <div class="goods_name">
                    商品名称：
                    <span id="goods_name">${requestScope.goods.goodsName}</span>
                </div>

                <div class="goods_price">
                    商品价格：￥
                    <span id="goods_price">${requestScope.goods.price}</span>
                </div>
                <div class="goods_description">
                    商品描述：
                    <span id="goods_description">${requestScope.goods.description}</span>
                </div>
                <div class="goods_type">
                    商品类型：
                    <span id="goods_type">${requestScope.goods.goodsType.name}</span>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/confirmOrders" method="post">
                <input type="hidden" name="goodsId" value="${requestScope.goods.goodsId}"/>
                <div class="buy_box">
                    <label for="count">购买数量：</label>
                    <input type="number" name="count" id="count" class="form-control" value="1"/>
                    <a class="btn btn-warning" onclick="addGoodsToShopCar()">加入购物车</a>
                    <input class="btn btn-danger" type="submit" value="购买"/>
                </div>
            </form>
        </div>
    </div>

    <hr/>

    <div class="seller_box">
        <div class="seller_head">
            <img src="/rest/picture/${requestScope.goods.shop.head.pictureId}"/>
        </div>
        <div class="right_box">
            <div class="seller_info">
                <div class="seller_name">${requestScope.goods.shop.shopName}</div>
                <div class="seller_description">${requestScope.goods.shop.description}</div>
                <div class="btn_box">
                    <a class="btn btn-success" href="/shop/${requestScope.goods.shop.shopId}">进店逛逛</a>
                    <button class="btn btn-danger" onclick="showSellerInfo()">联系卖家</button>
                </div>
            </div>
        </div>
    </div>

    <hr/>

    <div class="evaluate_box">
        <div class="text-center">用户评价</div>
        <div class="divider"></div>

        <c:if test="${requestScope.evaluateList.size()==0}">
            <div class="text-center">暂无评价</div>
        </c:if>

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
                            <img src="${evaluate.evaluateLevel==0?"/img/ic_evaluate_bad.png":
                            (evaluate.evaluateLevel==1?"/img/ic_evaluate_normal.png":"/img/ic_evaluate_good.png")}"/>
                        </div>
                        <span class="name">${evaluate.orders.customer.nickname}</span>
                        <span class="time">${evaluate.createTime}</span>
                    </div>
                    <div class="content">${evaluate.content}</div>
                </div>
            </div>
            <div class="divider"></div>
        </c:forEach>
    </div>

</content>

<jsp:include page="footer.jsp"/>

<div class="modal fade" id="mymodal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">商家信息</h4>
            </div>
            <div class="modal-body">
                <div style="height: 100px;width: 100px">
                    <img src="/rest/picture/${requestScope.goods.shop.seller.head.pictureId}" style="height: 100% ;width: 100%;">
                </div>
                <div>商家昵称：${requestScope.goods.shop.seller.nickname}</div>
                <div>商家姓名：${requestScope.goods.shop.seller.realName}</div>
                <div>商家性别：${requestScope.goods.shop.seller.gender}</div>
                <div>商家电话：${requestScope.goods.shop.seller.phone}</div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script>

    function showSellerInfo() {
        $('#mymodal').modal('toggle');
    }

    function showMsg(msg) {
        if (!msg && msg !== "") {
            alert(msg);
        }
    }

    function addGoodsToShopCar() {
        var count = $("#count").val();
        var goodsId = "${requestScope.goods.goodsId}";

        var url = "/shopCar";
        var data = {count: count, goodsId: goodsId};
        $.post(url, data, function (result, status) {
            if (result === true && status === "success") {
                if (result === true) {
                    alert("已添加到购物车！");
                    window.location.href = "/goods/" + goodsId;
                } else {
                    alert("添加失败！");
                }
            } else {
                window.location.href = "/login.jsp";
            }
        });
    }

    $(function () {
        <!--调用Luara示例-->
        $(".img_box").luara({
            width: "500",
            height: "334",
            interval: 2000,
            selected: "seleted",
            deriction: "left"
        });

    });
</script>

</body>
</html>
