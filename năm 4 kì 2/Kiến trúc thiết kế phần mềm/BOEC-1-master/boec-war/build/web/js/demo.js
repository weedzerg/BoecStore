$(document).ready(function() {
	$("#ButtonSearchChoice").mouseenter(function(event) {
	$(".UlSearch").css({
		display: 'block',
	});
    });
    $(".UlSearch").mouseleave(function(event) {
	$(".UlSearch").css({
		display: 'none'
	});
    });
    $(".UlSearch li").click(function(event) {
    	var y = parseFloat($("#ButtonSearchChoice").css("width"));
    	var k = parseFloat($(".InputSearch").css("width"));
    	var i = $(this).index();
        var val = $(this).val();
    	i = $(".UlSearch li:eq("+i+")").text();
        $("#valLi").val(val);
    	$("#ButtonSearchChoice").text(i);
    	i = parseFloat($("#ButtonSearchChoice").css("width"));
    	$(".InputSearch").css({"width": k-i+y+"px"});
        $(".UlSearch").css({
        display: 'none'
    });
    });
    
});