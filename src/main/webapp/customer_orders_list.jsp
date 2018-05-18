<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/22
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>我的订单</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min-3.2.0.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/order_list.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min-3.2.0.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script src="${pageContext.request.contextPath}/js/util/DateUtil.js"></script>
    <script>
        let ordersCount = ${requestScope.ordersCount};
        let ordersList = ${requestScope.ordersListJson};
    </script>
    <script src="${pageContext.request.contextPath}/js/customer_order_list.js"></script>
</head>
<body>

<jsp:include page="header.jsp"/>

<content id="content">

    <div class="text-center"><h1>我的订单({{ordersCount}})</h1></div>

    <div v-for="orders in ordersList" class="order_item">
        <div class="goods_image">
            <img :src="'/rest/picture/' + orders.goods.pictureSet[0].pictureId"
                 @click="showGoodsInfo(orders.goods.goodsId)"/>
        </div>
        <div class="right_box">
            <div class="order_info">
                <div>订单编号：{{orders.ordersId}}</div>
                <div>商品名称：{{orders.goods.goodsName}}</div>
                <div>店鋪名称：{{orders.goods.shop.shopName}}</div>
                <div>购买数量：{{orders.buyCount}}</div>
                <div>收货人：{{orders.contact.receiver}}</div>
                <div>联系电话：{{orders.contact.phone}}</div>
                <div>收货地址：{{orders.contact.address}}</div>
                <div>创建时间：{{new Date(orders.createdOn).format("yyyy-MM-dd HH:mm:ss")}}</div>
                <div>总价：￥ <span>{{orders.price}}</span></div>
                <div>备注：{{orders.remark}}</div>
                <div>订单状态：<span>{{ordersStatusMsg(orders.status)}}</span></div>
                <div v-if="orders.refund">退款理由：{{orders.refund.reason}}</div>
                <div class="btn_box">
                    <button v-if="showLogisticsBtn(orders.status)" @click="queryLogistics(orders.ordersId)"
                            class="btn btn-default">查看物流
                    </button>
                    <button v-if="showReceiveGoodsBtn(orders.status)" @click="receiveGoods(orders.ordersId)"
                            class="btn btn-success">确认收货
                    </button>
                    <button v-if="showEvaluateBtn(orders.status)" onclick="$('#modal_evaluate_orders').modal('toggle')"
                            @click="selectedOrdersId=orders.ordersId"
                            class="btn btn-info">评价订单
                    </button>
                    <button v-if="showRefundBtn(orders.status)" onclick="$('#modal_refund').modal('toggle')"
                            @click="selectedOrdersId=orders.ordersId"
                            class="btn btn-warning">申请退款
                    </button>
                    <button v-if="showDeleteOrdersBtn(orders.status)" @click="deleteOrders(orders.ordersId)"
                            class="btn btn-danger">删除订单
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- 填写退款理由的模态弹出窗 -->
    <div class="modal fade" id="modal_refund">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">退款理由</h4>
                </div>
                <div class="modal-body">
                    <label><input type="radio" name="refundReason" value="不喜欢" v-model="refundReason">不喜欢</label>
                    <br>
                    <label><input type="radio" name="refundReason" value="拍错了" v-model="refundReason">拍错了</label>
                    <br>
                    <label><input type="radio" name="refundReason" value="颜色不好看" v-model="refundReason">颜色不好看</label>
                    <br>
                    <label><input type="radio" name="refundReason" value="商品与描述不一致"
                                  v-model="refundReason">商品与描述不一致</label>
                    <br>
                    <br>
                    <label>理由：<textarea v-model="refundReason" class="form-control"></textarea></label>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">取消</button>
                    <button class="btn btn-primary" @click="applyForRefund(selectedOrdersId, refundReason)"
                            data-dismiss="modal">申请退款
                    </button>
                </div>
                <%--</form>--%>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- 填写退款理由的模态弹出窗 -->

    <!-- 填写评价内容的模态弹出窗 -->
    <div class="modal fade" id="modal_evaluate_orders">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">评价订单</h4>
                </div>
                <div class="modal-body">
                    <label><input type="radio" name="evaluateLevel" value="Good" v-model="evaluateLevel">好评</label>
                    <br>
                    <label><input type="radio" name="evaluateLevel" value="Normal" v-model="evaluateLevel">中评</label>
                    <br>
                    <label><input type="radio" name="evaluateLevel" value="Bad" v-model="evaluateLevel">差评</label>
                    <br>
                    <br>
                    <label>评价：<textarea v-model="evaluateContent" class="form-control"></textarea></label>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">取消</button>
                    <button class="btn btn-primary"
                            @click="evaluateOrders(selectedOrdersId, evaluateContent, evaluateLevel)"
                            data-dismiss="modal">评价
                    </button>
                </div>
                <%--</form>--%>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- 填写评价内容的模态弹出窗 -->

</content>

<jsp:include page="footer.jsp"/>

</body>
</html>
