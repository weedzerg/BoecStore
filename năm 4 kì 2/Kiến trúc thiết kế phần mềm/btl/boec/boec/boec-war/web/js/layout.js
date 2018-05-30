jQuery(document).ready(function ($) {
    // Slide ảnh tự động
    var arr = new Array();
    arr[0] = "images/top1.jpg";
    arr[1] = "images/top2.jpg";
    arr[2] = "images/top3.jpg";
    arr[3] = "images/top4.jpg";
    arr[4] = "images/top5.jpg";
    var n = 0;
    setInterval(function () {
        n++;
        if (n == arr.length)
            n = 0;
        $("#image").attr({
            "src": arr[n]
        });
    }, 2000);
    // Click next và prev
    $(".next").click(function () {
        n++;
        if (n == arr.length) {
            n = 0;
        }
        $("#image").attr({
            "src": arr[n]
        });
    });
    $(".prev").click(function () {
        n--;
        if (n == -1) {
            n = arr.length - 1;
        }
        $("#image").attr({
            "src": arr[n]
        });
    });

    // Click and hold
//    let kt = false;
//    $(".logo-right ul li:eq(0)").mousedown(function () {
//        kt = true;
//    });
//    $(window).mousemove(function (event) {
//        if (kt) {
//            $(".logo-right ul").css('width', '100%');
//            $(".logo-right ul li:eq(0)").css({
//                "position": "absolute ",
//                "top": event.pageY - 95 - $(window).scrollTop(),
//                "left": event.pageX - 905 - $(window).scrollLeft(),
//                "right": "auto",
//                "bottom": "auto",
//                "z-index": 1
//            });
//        }
//
//    });
//    $(window).mouseup(function () {
//        kt = false;
//        //console.log($(".logo-right ul li:eq(0)").css('position'));
//        if ($(".logo-right ul li:eq(0)").css('position') == 'absolute') {
//            if (parseFloat($(".logo-right ul li:eq(0)").css('top')) < 0 && parseFloat($(".logo-right ul li:eq(0)").css('top')) > -65 && parseFloat($(".logo-right ul li:eq(0)").css('left')) > 0 && parseFloat($(".logo-right ul li:eq(0)").css('left')) < 200) {
//                $(".logo-right ul li:eq(0)").css({
//                    "position": "static",
//                    "top": "auto",
//                    "left": "auto",
//                    "right": "auto",
//                    "bottom": "auto",
//                    "border": "0px",
//                    "background-color": "rgba(0,0,0,0)"
//                });
//            } else {
//                $(".logo-right ul li:eq(0)").css({
//                    "position": "fixed",
//                    "top": "600px",
//                    "left": "auto",
//                    "right": "110px",
//                    "bottom": "auto",
//                    "border": "2px solid green",
//                    "background-color": "white"
//                });
//            }
//        }
//    });

    //Hover image book
    $('.product img').hover(function () {
        $(this).css({
            "width": parseFloat($(this).css('width')) + 12.5 + "px",
            "height": parseFloat($(this).css('height')) + 25 + "px"
        });
        $(this).parent().css({
            "margin-top": "0px"
        });
    }, function () {
        $(this).css({
            "width": "100%",
            "height": "100%"
        });
        $(this).parent().css({
            "margin-top": "25px"
        });
    });
    // Click check thanh toán
    $("#btn-check").click(function () {
        $("#frm-payment").css("display", "block");
        $("#frm-payment").css("margin-top", "25px");
        $("#btn-check").css("display","none");
    });

});