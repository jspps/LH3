<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="user_content">
	<div class="user_chlidmenu">
		<a href="sigle/examRecord" class="wytw">学习管理</a> 
		<a type="1" href="sigle/examRecord">模考记录</a> 
		<a type="2" href="sigle/learnBuy">已购课程</a>
		<a type="5" href="sigle/learnExperience">体验课程</a>
		<a type="3" href="sigle/learnRecommend">推荐课程</a>
		<a type="4" href="sigle/comment">我的点评</a>
		<i class="clear"></i>
	</div>
</div>
<script type="text/javascript">
	var menuChild = "${menuChild}";

	$(document).ready(function() {
		menuChild = parseInt(menuChild, 10);
		if (isNaN(menuChild))
			menuChild = 1;
		
		var list = $("div.user_content a[type]");
		
		list.each(function() {
			var that = $(this);
			that.removeClass();
			var tpVal = that.attr("type");
			tpVal = parseInt(tpVal, 10);
			if (tpVal == menuChild) {
				that.addClass("current");
			};
		});
	});
</script>
<div style="clear:both;"></div>