<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/17
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>英俊商城 - 卖家首页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index_seller.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script>
        var sellerId = ${requestScope.sellerId};
        var shopList = ${requestScope.shopListJson};
    </script>
    <script src="${pageContext.request.contextPath}/js/index_seller.js"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content id="content">

    <div id="title"><h2>所有商店 ({{shopList.length}})</h2></div>

    <div class="shop_box container">
        <div class="row">
            <div class="shop_item_box col-sm-3" v-for="shop in shopList" @click="showShopInfo(shop.shopId)">
                <div class="img_box">
                    <img :src="'/rest/picture/' + shop.head.pictureId" class="img">
                </div>
                <div class="info_box">
                    <div class="shop_name">{{shop.shopName}}</div>
                    <div>{{shop.description}}</div>
                </div>
            </div>
            <%--添加商店的按钮--%>
            <div class="shop_item_box col-sm-3" onclick="$('#modal_add_shop').modal('toggle')">
                <div class="img_box">
                    <img src="${pageContext.request.contextPath}/img/ic_add.png" class="img">
                </div>
                <div class="info_box">创建商店</div>
            </div>
        </div>
    </div>

    <!-- 创建商店的模态弹出窗 -->
    <div class="modal fade" id="modal_add_shop">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">创建商店</h4>
                </div>
                <div class="modal-body">
                    <div>
                        <div>
                            商店名称：<input class="form-control" type="text" v-model="shopName"/>
                        </div>
                        <br>
                        <div>
                            商店描述：<input class="form-control" type="text" v-model="shopDescription"/>
                        </div>
                        <br>
                        <div>
                            <%--商店头像：<input class="form-control" type="file" name="shopHead"--%>
                            <%--accept="image/gif,image/jpeg,image/jpg,image/png">--%>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary"
                            @click="createShop(shopName, shopDescription)"
                            data-dismiss="modal">创建商店
                    </button>
                </div>
                <%--</form>--%>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- 创建商店的模态弹出窗 -->

</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
