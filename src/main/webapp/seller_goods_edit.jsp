<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2018/5/24
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>编辑商品</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web_lib/css/bootstrap.min-3.2.0.css">
    <script src="${pageContext.request.contextPath}/web_lib/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/web_lib/js/bootstrap.min-3.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/web_lib/js/vue.js"></script>
    <script src="${pageContext.request.contextPath}/web_lib/js/jquery.blockUI.js"></script>
    <script src="${pageContext.request.contextPath}/web_lib/js/util/PicturePreviewUtil.js"></script>
    <script>
        let goods = ${requestScope.goodsJson};
    </script>
    <script>
        let contentVm;

        $(function () {
            $(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

            contentVm = new Vue({
                el: "#content",
                data: {
                    goods: goods,
                },
            });
        });

        function editGoods() {
            let formData = new FormData(document.querySelector("#edit_goods_form"));
            $.ajax({
                url: "/seller/goods/" + contentVm.goods.goodsId + "/edit",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                cache: false,
                success: function (result) {
                    window.location.href = "/goods/" + contentVm.goods.goodsId;
                },
                error: function (xhr) {
                    alert(xhr.responseText);
                }
            });
        }
    </script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content id="content">
    <div class="text-center">
        <form id="edit_goods_form" enctype="multipart/form-data">
            <label>
                商品名称：
                <input class="form-control" type="text" name="goodsName" :value="goods.goodsName">
            </label>
            <br>
            <label>
                商品描述：<input class="form-control" type="text" name="description" :value="goods.description">
            </label>
            <br>
            <label>
                商品价格：
                <input class="form-control" type="number" name="price" :value="goods.price">
            </label>

            <br>
            <hr>
            <br>

            <div class="label label-warning" style="font-size: 1.5em">以下是商品图片的编辑区域，如果没有选择，则维持不变。</div>

            <hr>

            <label>
                商品图片1
                <input type="file" class="form-control" name="goodsPicture1"
                       onchange="previewNewShopHead('#goods_picture1_preview', 'input[name=goodsPicture1]')">
            </label>
            <img style="max-height: 300px;max-width: 300px" src="" id="goods_picture1_preview">

            <hr>

            <label>
                商品图片2
                <input type="file" class="form-control" name="goodsPicture2"
                       onchange="previewNewShopHead('#goods_picture2_preview', 'input[name=goodsPicture2]')">
            </label>
            <img style="max-height: 300px;max-width: 300px" src="" id="goods_picture2_preview">

            <hr>

            <label>
                商品图片3
                <input type="file" class="form-control" name="goodsPicture3"
                       onchange="previewNewShopHead('#goods_picture3_preview', 'input[name=goodsPicture3]')">
            </label>
            <img style="max-height: 300px;max-width: 300px" src="" id="goods_picture3_preview">

            <hr>

            <label>
                商品图片4
                <input type="file" class="form-control" name="goodsPicture4"
                       onchange="previewNewShopHead('#goods_picture4_preview', 'input[name=goodsPicture4]')">
            </label>
            <img style="max-height: 300px;max-width: 300px" src="" id="goods_picture4_preview">

            <hr>

            <label>
                商品图片5
                <input type="file" class="form-control" name="goodsPicture5"
                       onchange="previewNewShopHead('#goods_picture5_preview', 'input[name=goodsPicture5]')">
            </label>
            <img style="max-height: 300px;max-width: 300px" src="" id="goods_picture5_preview">

        </form>
        <button class="btn btn-primary" onclick="editGoods()">修改</button>
    </div>
</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
