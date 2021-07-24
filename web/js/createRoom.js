$(
    function () {

        //用Ajax判断房间账号是否被注册过了
        $('input[name="roomAccountId"]').focusout(
            function () {
                var roomAccountId = $('input[name="roomAccountId"]').val();
                var pattern = /^[a-zA-Z0-9]{6,18}$/;
                if (!pattern.test(roomAccountId)) {
                    alert('账号为字母和数字的组合,并且在6~18位!');
                } else {
                    $.post("/webchat/examineRoomIdServlet", {roomAccountId: roomAccountId}, function (data) {
                        if (!data == '')
                            alert(data);
                    }, "text");
                }
            }
        );

    }
);

//提交前的检查
function examine() {

    var roomAccountId = $('input[name="roomAccountId"]').val();
    var roomName = $('input[name="roomName"]').val();

    //判断是否填写完所有数据
    if (roomAccountId && roomName ) {
        //填完了
        //判断账号和密码格式
        var pattern = /^[a-zA-Z0-9]{6,18}$/;
        if (!pattern.test(roomAccountId)) {
            alert('房间账号为字母和数字的组合,并且在6~18位!');
            return false;
        }
    } else {
        //没有填完
        alert('必须输入房间账号和房间名称!');
        return false;
    }
    return true;
}
