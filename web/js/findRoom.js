function examine() {
    var roomAccountId = $("input[name='roomAccountId']").val();
    var pattern=/^[a-zA-Z0-9]{6,18}$/;
    if(!pattern.test(roomAccountId)){
        alert('房间号错误!');
        return false;
    }
    return true;
}