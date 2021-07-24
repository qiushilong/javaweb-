<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加聊天室</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/findRoom.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/findRoom.js"></script>
</head>

<body>
<iframe src="top.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
<div class="content">
    <iframe src="left.jsp" frameborder="0" width="25%" height="100%" scrolling="no"></iframe>
    <div class="right">
        <form action="/webchat/findRoomServlet" onsubmit="return examine();">
            <div class="byid">输入聊天室id <span style="color: red">${requestScope.error}</span><br><input type="text" name="roomAccountId"></div>
            <div class="submit"><input type="submit" value="查找"></div>
        </form>
    </div>
</div>
<iframe src="bottom.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
</body>

</html>