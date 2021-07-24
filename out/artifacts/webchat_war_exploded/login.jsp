<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登陆</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/login.js"></script>
</head>

<body>
    <iframe src="top.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
    <div class="content">

        <img src="img/system_img/bg.jpg" class="bg" alt="">
        <ul class="snowList">
            <li><img src="img/system_img/雪花.png" class="snow"></li>
        </ul>
        <div class=" runBear">
        </div>

        <div class="login">
            <form action="/webchat/loginServlet" method="post" onsubmit="return examine();">
                <ul>
                    <li>账号&nbsp;<span style="color: green;font-weight: 700">${success_tip}</span><span class="tip">${accountid_error}</span><br><input type="text" name="accountId"></li>
                    <li>密码<br><input type="password" name="password"></li>
                    <li>验证码&nbsp;<span class="tip">${cc_error}</span><br><input type="text" name="checkCode"><img src="" alt=""></li>
                    <li><img id="img" src="checkCodeServlet" alt=""></li>
                    <li><input type="submit" value="登陆"></li>
                </ul>
            </form>
        </div>

    </div>
    <iframe src="bottom.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
</body>

</html>