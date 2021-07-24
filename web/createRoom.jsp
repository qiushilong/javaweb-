<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>创建聊天室</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/createRoom.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/createRoom.js"></script>
</head>

<body>
    <iframe src="top.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
    <div class="content">
        <iframe src="left.jsp" frameborder="0" width="25%" height="100%" scrolling="no"></iframe>
        <div class="right">
            <form action="/webchat/createRoomServlet" method="post" onsubmit="return examine();">
                <ul>
                    <li>输入聊天室id<br><input type="text" name="roomAccountId"></li>
                    <li>聊天室名称<br><input type="text" name="roomName"></li>
                    <li>聊天室交友宣言<br><textarea name="roomDeclaration"  cols="30" rows="10" ></textarea></li>
                    <li>聊天室公告<br><textarea name="msg"  cols="30" rows="10"></textarea></li>
                    <li>
<%--                        是否需要同意加入<br>是--%>
<%--                        <input type="radio" name="isPrivate" value="是">--%>
<%--                        &nbsp;&nbsp;&nbsp;&nbsp;否--%>
                        <input type="hidden" name="isPrivate" checked value="否">
                    </li>
                    <li><input type="submit" value="创建"></li>
                </ul>
            </form>
        </div>
    </div>
    <iframe src="bottom.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
</body>

</html>