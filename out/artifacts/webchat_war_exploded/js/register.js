$(
    function (){

        //点击验证码刷新
        $('#img').click(
            function () {
                //给一个带时间戳的路径,以免浏览器缓存不刷新
                $('#img').prop("src","checkCodeServlet?time="+new Date().getTime());
            }
        );
        //用Ajax判断账号是否被注册过了
        $('input[name="accountId"]').focusout(
            function (){
                var accountId=$('input[name="accountId"]').val();
                var pattern=/^[a-zA-Z0-9]{6,18}$/;
                if(!pattern.test(accountId)){
                    alert('账号为字母和数字的组合,并且在6~18位!');
                }else{
                    $.post("/webchat/examineAccountIdServlet",{accountId:accountId},function(data){
                        if(!data=='')
                            alert(data);
                    },"text");
                }
            }
        )
    }
);

//提交前的检查
function examine(){

    var accountId=$('input[name="accountId"]').val();
    var password=$('input[name="password"]').val();
    var checkCode=$('input[name="checkCode"]').val();

    //判断是否填写完所有数据
    if(accountId&&password&&checkCode){
        //填完了
        //判断账号和密码格式
        var pattern=/^[a-zA-Z0-9]{6,18}$/;
        var pattern2=/^[a-zA-Z0-9!@#$%^&*?.]{6,18}$/;
        if(!pattern.test(accountId)){
            alert('账号为字母和数字的组合,并且在6~18位!');
            return false;
        }
        if(!pattern2.test(password)){
            alert('密码为字母、数字、常用特殊字符的组合,并且在6~18位!');
            return false;
        }
        if(checkCode.length!=4){
            alert('验证码错误!');
            return false;
        }
    }
    else{
        //没有填完
        alert('请先输入!');
        return false;
    }
    return true;
}
window.onload = function () {
    for (var i = 0; i < 50; i++) {
        var snowList = document.querySelector('.snowList');
        var li = document.createElement('li');
        var img = document.createElement('img');
        img.className = 'snow';
        img.style.animation = 'snowdown ' + (20 * Math.random() + 20) + 's linear ' + parseInt(30 * Math.random()) + 's infinite';
        img.src = 'img/system_img/雪花.png';
        img.style.width = '' + (20 * Math.random() + 20) + 'px';
        li.append(img);
        snowList.append(img);
    }
}