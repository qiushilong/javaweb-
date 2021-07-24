<%@ page import="cn.jsu.dao.ChatMsgDao" %>
<%@ page import="cn.jsu.bean.User" %>
<%@ page import="cn.jsu.bean.Room" %>
<%@ page import="cn.jsu.bean.ChatMsg" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.jsu.dao.UserDao" %>
<%@ page import="cn.jsu.dao.RoomDao" %>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>chat</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/chat.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/chat.js"></script>
    <%--  避免预加载调用  --%>
    <c:if test="${not empty requestScope.createRoomTip}">
        <script>
            $(
                function () {
                    alert('${requestScope.createRoomTip}');
                }
            )
        </script>
    </c:if>
    <script>
        $(function () {
            //ajax轮询
            var timer = window.setInterval(function () {
                //跳到其他页面会自动关闭计时器
                //每十秒发送一次请求，请求界面内容，有变化则让servlet把更新的内容发过来
                if(!location.toString().includes("roomAccountId="))
                    return;
                $.get("/webchat/flashChatServlet", function (data) {
                    if (data.length>0) {
                        var str = data.split('*/*');
                        //会分出来一个空串
                        for (var i = 0; i < str.length-1; i = i + 5) {
                            //创建节点
                            var li = document.createElement('li');
                            //判断是自己发的还是别人发的
                            if (str[i] ==${USER.getId().toString()}) {
                                li.className = 'txt_right';
                                li.innerHTML = '  <div class="whowhen">\n' +
                                    '                            <div class="name">' + str[i + 1] + '</div>\n' +
                                    '                            <div class="date">' + str[i + 3].substr(0,19) + '</div>\n' +
                                    '                        </div>\n' +
                                    '                        <div class="msg">' + str[i + 4] + '<br><br></div>\n' +
                                    '                        <span class="tx"><img src="img/user_headShot/' + str[2] + '" alt="" width="40px"></span>';
                            } else {
                                li.className = 'txt_left';
                                li.innerHTML = ' <span class="tx"><img src="img/user_headShot/' + str[2] + '" alt=""\n' +
                                    '                                              width="40px"></span>\n' +
                                    '                        <div class="whowhen">\n' +
                                    '                            <div class="name">' + str[i + 1] + '</div>\n' +
                                    '                            <div class="date">' + str[i + 3].substr(0,19) + '</div>\n' +
                                    '                        </div>\n' +
                                    '                        <div class="msg">' + str[i + 4] + '<br><br></div>'
                            }
                            var ul = document.querySelector('.center ul');
                            ul.appendChild(li);

                            //如果这个li里的信息是图片，就去掉气泡
                            var msg=li.querySelector('.msg');
                            if(msg.firstElementChild.tagName=='IMG'){
                                $(msg).css("background-color","transparent");
                            }
                        }
                    }
                }, "text");
            }, 1000);
        });
    </script>
</head>

<body>
<iframe src="top.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
<div class="content">
    <iframe src="left.jsp" frameborder="0" width="25%" height="100%" scrolling="no"></iframe>
    <div class="todown" title="置底"></div>
    <div class="right iconfont">
        <div class="right_top">
            <span title="发言" id="change_speak">&#xe695;</span>
            <span title="表情" id="change_emoji">&#xe663;</span>
            <span title="添加表情" id="change_add">&#xe60a;</span>
        </div>
        <div class="right_bottom">
            <div class="right_bottom_left">
                <textarea name="content" id="" cols="30" rows="10"></textarea><br>
                <input type="hidden" name="userId" value="${USER.getId()}">
                <input type="hidden" name="roomId" value="${roomId}">
                <input type="hidden" name="roomAccountId" value="${roomAccountId}">
                <input type="submit" id="submit" value="发送">
            </div>
            <div class="right_bottom_right">
                <%--    系统自带表情包     --%>
                <img src="img/user_emoji/xtzdbqb1.jpg" alt="" width="100px">
                <img src="img/user_emoji/xtzdbqb2.jpeg" alt="" width="100px">
                <img src="img/user_emoji/xtzdbqb3.jpeg" alt="" width="100px">
                <img src="img/user_emoji/xtzdbqb4.jpeg" alt="" width="100px">
                <img src="img/user_emoji/xtzdbqb5.jpg" alt="" width="100px">
                <img src="img/user_emoji/xtzdbqb6.jpeg" alt="" width="100px">
                <c:forEach items="${EMOJILIST}" var="temp">
                    <c:if test="${not empty temp.getPath()}">
                        <img src="img/user_emoji/${temp.getPath()}" alt="" width="100px">
                    </c:if>
                </c:forEach>
            </div>
            <div class="addEmoji">
                <form action="/webchat/addEmojiServlet" method="post" enctype="multipart/form-data" onsubmit="return examine();">
                    <ul>
                        <li><input type="file" name="addEmoji" id="addEmoji" value="选择文件"></li>
                        <li><input type="submit" value="添加"></li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
    <div class="center">
        <ul>
            <%--遍历该聊天室的所有信息--%>
            <c:forEach items="${chatMsgList}" begin="0" end="${empty chatMsgList?0:(chatMsgList.size()-1)}" var="temp"
                       step="1" varStatus="s">
                <%--判断是自己发的还是别人发的，自己发的在右边--%>
                <c:if test="${temp.getUserId()==USER.getId()}">
                    <li class="txt_right">
                        <div class="whowhen">
                            <div class="name">${userList.get(s.index).getName()}</div>
                            <div class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
                                                              value="${temp.getDate()}"/></div>
                        </div>
                        <div class="msg">${temp.getContent()}<br><br></div>
                        <span class="tx"><img src="img/user_headShot/${USER.getHeadShot()}" alt="" width="40px"></span>
                    </li>
                </c:if>
                <c:if test="${temp.getUserId()!=USER.getId()}">
                    <li class="txt_left">
                        <span class="tx"><img src="img/user_headShot/${userList.get(s.index).getHeadShot()}" alt=""
                                              width="40px"></span>
                        <div class="whowhen">
                            <div class="name">${userList.get(s.index).getName()}</div>
                            <div class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
                                                              value="${temp.getDate()}"/></div>
                        </div>
                        <div class="msg">${temp.getContent()}<br><br></div>
                    </li>
                </c:if>
            </c:forEach>

        </ul>
    </div>
</div>
<iframe src="bottom.jsp" frameborder="0" width="100%" height="60px" scrolling="no"></iframe>
</body>

</html>