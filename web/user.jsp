<%@page contentType="text/html;chatset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CNz">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户中心</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/user.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/user.js"></script>
</head>

<body>
<iframe src="top.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>

<div class="content">
    <form action="/webchat/userServlet" method="post" enctype="multipart/form-data">
        <ul>
            <li>
                    <div class="headShot">
                        <img src="
                            <c:if test="${empty sessionScope.USER}">img/system_img/头像.jpg</c:if>
                            <c:if test="${not empty sessionScope.USER}">img/user_headShot/${sessionScope.USER.getHeadShot()}</c:if>
                        " alt="" width="100px"><br>
                    </div>
            </li>
            <li><input type="file" name="headShot" id="headShot" accept="image/*" onchange="updateHeadShot();"></li>
            <li>昵称<input type="text" name="userName" value="${sessionScope.USER.getName()}"></li>
            <c:if test="${sessionScope.USER.getGender()==null}">
                <li>性别<br>男<input type="radio" name="gender" checked value="男">
                    &nbsp;&nbsp;&nbsp;女<input type="radio" name="gender" value="女"></li>
            </c:if>
            <c:if test="${sessionScope.USER.getGender() == '男'}">
                <li>性别<br>男<input type="radio" name="gender" checked value="男">
                    &nbsp;&nbsp;&nbsp;女<input type="radio" name="gender" value="女"></li>
            </c:if>
            <c:if test="${sessionScope.USER.getGender() == '女'}">
                <li>性别<br>男<input type="radio" name="gender" value="男">
                    &nbsp;&nbsp;&nbsp;女<input type="radio" name="gender" checked value="女"></li>
            </c:if>
            <li>qq<br><input type="text" name="qq" value="${sessionScope.USER.getQq()}"></li>
            <li>个性签名<textarea name="attitude" id="" cols="30" rows="10" >${sessionScope.USER.getAttitude()}</textarea></li>
            <li><input type="submit" value="保存"><input type="button" value="取消"></li>
        </ul>

    </form>
</div>

<iframe src="bottom.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
</body>

</html>