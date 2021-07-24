$(
    function (){

        $('#img').click(
            function (){
                $('#img').prop("src","checkCodeServlet?time="+new Date().getTime());
            }
        );

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
            alert('账号错误!');
            return false;
        }
        if(!pattern2.test(password)){
            alert('密码错误');
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