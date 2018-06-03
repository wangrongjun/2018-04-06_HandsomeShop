<%@ page import="com.wangrj.java_lib.web.ImageCode" %><%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2018/6/4
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    new ImageCode().out(request, response);
%>