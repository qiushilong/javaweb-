$(
    function () {
        //ajax提交聊天信息
        $('#submit').click(
            function () {
                var content = $('textarea[name="content"]').val();
                if (content == '') {
                    alert("请先输入内容!");
                    return false;
                }
                var url = document.location.toString();
                if (!url.includes('roomAccountId=')) {
                    alert("请先选择聊天室");
                    return false;
                }
                var userId = $('input[name="userId"]').val();
                var roomId = $('input[name="roomId"]').val();
                var roomAccountId = $('input[name="roomAccountId"]').val();

                $.get("/webchat/sendMsgServlet",
                    {
                        content: content,
                        userId: userId,
                        roomId: roomId,
                        roomAccountId: roomAccountId
                    }, "text");
                $('textarea[name="content"]').val('');
            }
        );

        //图片绑定ajax提交
        var imgs = document.querySelectorAll('.right_bottom_right img');

        for (var i = 0; i < imgs.length; i++) {
            imgs[i].addEventListener('click', function () {
                var src = this.src;

                var url = document.location.toString();
                if (!url.includes('roomAccountId=')) {
                    alert("请先选择聊天室");
                    return false;
                }

                var content='<img src='+src+' class="img_msg" width="150px">';
                var userId = $('input[name="userId"]').val();
                var roomId = $('input[name="roomId"]').val();
                var roomAccountId = $('input[name="roomAccountId"]').val();

                $.get("/webchat/sendMsgServlet",
                    {
                        content:content,
                        userId:userId,
                        roomId:roomId,
                        roomAccountId:roomAccountId
                    },"text");
            });
        }

        //去除图片的气泡
        var img_msg=document.querySelectorAll('.img_msg');
        for(var i=0;i<img_msg.length;i++){
            var parent = img_msg[i].parentElement;
            $(parent).css("background-color","transparent");
        }

        //js轮播
        $('#change_speak').click(function () {
            animate($('.right_bottom').get(0), 0);
        });
        $('#change_emoji').click(function () {
            animate($('.right_bottom').get(0), -parseInt($('.right_bottom_left').css('width')));
        });
        $('#change_add').click(function () {
            animate($('.right_bottom').get(0), -2*parseInt($('.right_bottom_left').css('width')));
        });

        //下滑按钮
        var todown = document.querySelector('.todown');
        var center = document.querySelector('.center');


        todown.onclick = function () {
            console.log(center.scrollHeight);
            console.log(center.scrollTop);
            console.log(center.style.offsetHeight);

            animate2(center, center.scrollHeight);
        }
        // 动画函数
        function animate2(obj, target, callback) {
            var temp;
            clearInterval(obj.timer);
            obj.timer = setInterval(function () {
                var step = (target - obj.scrollTop) / 10;
                step = step > 0 ? Math.ceil(step) : Math.floor(step);
                //重复两次，说明得停了
                if (step == temp)
                    clearInterval(obj.timer);
                temp = step;
                if (obj.scrollTop == target) {
                    clearInterval(obj.timer);
                    callback && callback();
                }
                // 把每次加1 这个步长值改为一个慢慢变小的值  步长公式：(目标值 - 现在的位置) / 10
                obj.scrollTop = obj.scrollTop + step;
            }, 15);
        }
    }
);

function animate(obj, target, callback) {

    // 先清除以前的定时器，只保留当前的一个定时器执行
    clearInterval(obj.timer);
    obj.timer = setInterval(function () {
        // 步长值写到定时器的里面
        // 把我们步长值改为整数 不要出现小数的问题
        // var step = Math.ceil((target - obj.offsetLeft) / 10);
        var step = (target - obj.offsetLeft) / 10;
        step = step > 0 ? Math.ceil(step) : Math.floor(step);
        if (obj.offsetLeft == target) {
            // 停止动画 本质是停止定时器
            clearInterval(obj.timer);
            // 回调函数写到定时器结束里面
            // if (callback) {
            //     // 调用函数
            //     callback();
            // }
            callback && callback();
        }
        // 把每次加1 这个步长值改为一个慢慢变小的值  步长公式：(目标值 - 现在的位置) / 10
        obj.style.left = obj.offsetLeft + step + 'px';

    }, 15);
}