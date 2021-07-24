<%@page pageEncoding="UTF-8" contentType="text/html;" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>聊天室信息</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/addRoom.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/addRoom.js"></script>
    <script>
        $(
            function (){
                $('#confirm').click(
                    function (){
                        //异步请求
                        $.post(
                            "/webchat/addRoomServlet",
                            {roomId:'${requestScope.room.getId()}'},
                            function (data){
                                if(data){
                                    alert(data);
                                }
                            },
                            "text"
                        );
                    }
                );
            }
        );
    </script>
</head>

<body>
    <iframe src="top.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
    <div class="content">
        <iframe src="left.jsp" frameborder="0" width="25%" height="100%" scrolling="no"></iframe>
        <div class="right">
            <div>聊天室名称<br>${requestScope.room.getRoomName()}</div>
            <div>聊天室管理员<br>${requestScope.managerName}</div>
            <div>聊天室交友宣言<br><textarea name="" id="" cols="30" rows="10" disabled>${requestScope.room.getRoomDeclaration()}</textarea></div>
            <div><input type="submit" value="申请加入" id="confirm"></div>
        </div>
    </div>
    <iframe src="bottom.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
</body>

</html>