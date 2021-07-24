window.onload = function () {
    var lis = document.querySelectorAll('.show ul li');
    lis[0].onclick = function () {
        if (this.className == 'box_left') {
            this.className = 'box_center';
            lis[1].className = 'box_right';
            lis[2].className = 'box_left';
        }
        if (this.className == 'box_right') {
            this.className = 'box_center';
            lis[1].className = 'box_right';
            lis[2].className = 'box_left';
        }
    }
    lis[1].onclick = function () {
        if (this.className == 'box_left') {
            this.className = 'box_center';
            lis[0].className = 'box_left';
            lis[2].className = 'box_right';
        }
        if (this.className == 'box_right') {
            this.className = 'box_center';
            lis[0].className = 'box_left';
            lis[2].className = 'box_right';
        }
    }
    lis[2].onclick = function () {
        if (this.className == 'box_left') {
            this.className = 'box_center';
            lis[0].className = 'box_right';
            lis[1].className = 'box_left';
        }
        if (this.className == 'box_right') {
            this.className = 'box_center';
            lis[0].className = 'box_right';
            lis[1].className = 'box_left';
        }
    }
}