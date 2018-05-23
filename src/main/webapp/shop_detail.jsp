<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/22
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>商店详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css">
    <%--TODO 把goods_box变为vue组件--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods_box.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index_seller.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script src="${pageContext.request.contextPath}/js/util/PicturePreviewUtil.js"></script>
    <script>
        let editable = ${requestScope.editable != null ? requestScope.editable : "false"};
        let shop = ${requestScope.shopJson};
    </script>
    <script src="${pageContext.request.contextPath}/js/shop_detail.js"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content id="content">

    <div class="text-center">
        <h2>{{shop.shopName}}</h2>
        <br>
        <div>{{shop.description}}</div>
        <br>
        <div style="height: 120px;width: 120px;margin: 0 auto;">
            <img :src="'/rest/picture/' + shop.head.pictureId" style="height: 100% ;width: 100%;">
        </div>
        <br>
        <button class="btn btn-info" v-if="editable === true" onclick="$('#modal_update_shop_info').modal('toggle')">
            修改商店信息
        </button>
    </div>

    <hr>

    <div class="text-center" v-if="!shop.goodsSet || shop.goodsSet.length == 0">
        <h2>抱歉，没有商品！</h2>
    </div>
    <div class="goods_box container" v-else>
        <div class="row">
            <div class="col-sm-3" v-for="goods in shop.goodsSet">
                <div class="goods">
                    <div class="goods_image">
                        <a :href="'/seller/goods/' + goods.goodsId">
                            <img :src="'/rest/picture/' + goods.pictureSet[0].pictureId"/>
                        </a>
                    </div>
                    <a :href="'/goods/' + goods.goodsId" class="goods_name">{{goods.goodsName}}</a>
                    <div class="price">￥ {{goods.price}}</div>
                    <div class="remain_and_sell">
                        <span class="remain">库存：<span id="remainCount">0</span>&nbsp;件</span>
                        <span class="sell">销量：<span id="sellCount">0</span>&nbsp;笔</span>
                    </div>
                    <div class="description">{{goods.description}}</div>
                </div>
            </div>
        </div>
    </div>

    <!-- 修改商店信息的模态弹出窗 -->
    <div class="modal fade" id="modal_update_shop_info">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">修改商店信息</h4>
                </div>
                <div class="modal-body">
                    <form id="update_shop_info_form" enctype="multipart/form-data">
                        <label>商店名称：<input type="text" class="form-control" name="shopName"
                                           :value="shop.shopName"></label>
                        <label>商店描述：<input type="text" class="form-control" name="description"
                                           :value="shop.description"></label>
                        <label>
                            商店头像：
                            <input type="file" class="form-control" name="shopHead"
                                   onchange="previewNewShopHead('#shop_head_preview', 'input[name=shopHead]')">
                            <img style="max-height: 300px;max-width: 300px" src="" id="shop_head_preview">
                        </label>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">取消</button>
                    <button class="btn btn-primary" onclick="updateShopInfo()" data-dismiss="modal">修改
                    </button>
                </div>
            </div>
        </div>
    </div>
    <!-- 修改商店信息的模态弹出窗 -->

</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
