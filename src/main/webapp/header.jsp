<%@ page import="com.handsome.shop.entity.Customer" %>
<%@ page import="com.handsome.shop.dao.OrdersDao" %>
<%@ page import="com.handsome.shop.dao.ShopCarDao" %>
<%@ page import="com.handsome.shop.framework.SpringContextHolder" %>
<%@ page import="com.handsome.shop.entity.Seller" %>
<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/6/18
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_content_footer.css">
</head>
<body>

<header>
    <a class="brand" href="${pageContext.request.contextPath}/">英俊商城</a>
    <div class="menu">
        <a href="${pageContext.request.contextPath}/">首页</a>
        <%
            Customer customer = (Customer) request.getSession().getAttribute("customer");
            Seller seller = (Seller) request.getSession().getAttribute("seller");
            if (customer == null && seller == null) {
        %>
        <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
        <a href="${pageContext.request.contextPath}/register.jsp">注册</a>
        <%
        } else if (customer != null && seller == null) {// 顾客登录
            ShopCarDao shopCarDao = SpringContextHolder.getBean(ShopCarDao.class);
            OrdersDao ordersDao = SpringContextHolder.getBean(OrdersDao.class);
            long shopCarCount = shopCarDao.queryCountByCustomerId(customer.getCustomerId());
            int ordersCount = ordersDao.queryCountByCustomerId(customer.getCustomerId());
        %>
        <a href="${pageContext.request.contextPath}/customer_info.jsp"><%=customer.getNickname()%>
        </a>
        <a href="${pageContext.request.contextPath}/shopCar">我的购物车(<%=shopCarCount%>)</a>
        <a href="${pageContext.request.contextPath}/orders">我的订单(<%=ordersCount%>)</a>
        <%--<a href="${pageContext.request.contextPath}/contact">我的收货地址</a>--%>
        <a href="${pageContext.request.contextPath}/logout">[退出登录]</a>
        <%
        } else if (customer == null) {// 商家登录
        %>
        <a href="#"><%=seller.getNickname()%>
        </a>
        <a href="${pageContext.request.contextPath}/logout">[退出登录]</a>
        <%
        } else {
        %>
        程序异常！
        <%
            }
        %>
    </div>
</header>

</body>
</html>
