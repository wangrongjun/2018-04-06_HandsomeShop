<%--
  Created by IntelliJ IDEA.
  User: wangrongjun
  Date: 2017/9/21
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue-router.js"></script>
</head>
<body>

<div id="app">
    <button v-on:click="font++">放大字体</button>
    <button v-on:click="font--">缩小字体</button>
    {{font}}
    <div v-bind:style="{fontSize: font + 'em'}">{{hello}}</div>
    <button-counter></button-counter>
    <button-counter></button-counter>
    <button-counter></button-counter>
</div>

<script>
    const router = new VueRouter({
        routes: [
            {
                path: "/wang",
                component: {
                    template: `
                        <transition name="slide-left">
                            <div>Wang id: {{$route.query}} hash:{{$route.hash}}</div>
                        </transition>
                    `
                }
            },
            {
                path: "/footer",
                component: {
                    template: "<div>#footer</div>"
                }
            },
        ]
    });

    // 定义一个名为 button-counter 的新组件
    Vue.component('button-counter', {
        data: function () {
            return {count: 1};
        },
        template: '<button v-on:click="count++">You clicked me {{ count }} times.</button>'
    });
    new Vue({
        el: "#app",
        // router: router,
        data: {
            font: 2,
            hello: "Hello World!!!"
        },
    });
</script>

</body>
</html>
