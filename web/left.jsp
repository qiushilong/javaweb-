<%@ page import="cn.jsu.dao.RoomUserDao" %>
<%@ page import="cn.jsu.bean.User" %>
<%@ page import="cn.jsu.bean.RoomUser" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.jsu.bean.Room" %>
<%@ page import="cn.jsu.dao.RoomDao" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>left</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/left.css">
    <%
        RoomDao dao=new RoomDao();
        User user= (User) session.getAttribute("USER");
        ArrayList<Room> roomList = dao.getRoomListByIdFormRoomUser(user.getId());
        request.setAttribute("roomList",roomList);
    %>
</head>

<body>
    <div class="left">
        <div class="add"><a href="findRoom.jsp" target="_parent"><span class="iconfont addRoom" title="添加聊天室">&#xe609;</span></a>
            <a href="createRoom.jsp" target="_parent"><span class="iconfont createRoom" title="创建聊天室">&#xe60a;</span></a>
        </div>
        <div class="roomlist">
            <ul>
                <c:forEach items="${roomList}" var="temp">
                    <li><span class="iconfont">&#xe62e;</span>&nbsp;
                        <a href="chatServlet?roomAccountId=${temp.getRoomAccountId()}" target="_parent">
                            ${temp.getRoomName()}&nbsp;:&nbsp;${temp.getRoomAccountId()}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span style="color: green">在线人数：${ROOMONLINENUM.get(temp.getId())}人</span>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>

</html>