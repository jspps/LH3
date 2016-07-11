$(function() {
	$(".u_meun li").hover(function() {
		$(this).addClass("hover");
	}, function() {
		$(this).removeClass("hover");
	});

	$(".userr_input").Ipt_defult_word({
		"word" : "请输入您想搜索的课程",
		"cls" : "colorhover"
	});
});