<%@ page import="cn.jsu.bean.User" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=
    , initial-scale=1.0">
    <title>top</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/top.css">
    <script src="js/jquery.min.js"></script>
    <script>
        //这里不直接使用jsp，因为js预加载执行了jsp，删除了我的Session
        $(function (){
            //退出监听
            $('.exit').click(function (){
                $.post("/webchat/deleteUserSessionServlet",{"":""},function(data){
                    if(!data=='')
                        alert(data);
                },"text");
            });
        });

    </script>
</head>

<body>
<div class="top">
    <div class="logo"><a href="index.jsp" target="_parent"><img src="img/system_img/在线聊天室.png" alt=""
                                                                title="在线聊天室"></a></div>
    <div class="user">
        <%--     没登陆       --%>
        <c:if test="${empty sessionScope.USER}">
            <div><a href="user.jsp" target="_parent"><img src="img/system_img/用户.png" alt="" width="25px" title="用户中心"></a>
            </div>
            <div><a href="register.jsp" target="_parent">注册</a></div>
            <div><a href="login.jsp" target="_parent">登陆</a></div>
        </c:if>
        <%--     登陆了       --%>
        <c:if test="${not empty sessionScope.USER}">
            <div><a href="user.jsp" target="_parent"><span class="headShot"><img
                    src="img/user_headShot/${sessionScope.USER.getHeadShot()}" title="用户中心"></span></a></div>
            <div>${sessionScope.USER.getName()}</div>
            <div><a href="chat.jsp?roomAccountId=${sessionScope.roomAccountId}" target="_parent" id="join">进入聊天室</a></div>
            <div><a href="index.jsp" target="_parent" class="exit">退出</a></div>
        </c:if>
    </div>
</div>
</body>

</html>