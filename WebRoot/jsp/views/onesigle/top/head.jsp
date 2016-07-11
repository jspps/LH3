<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="userheader">
	<div class="user_content">
		<span class="fl">
			<a href="client/home">
				<img src="jsp/imgs/onesigle/usermanange/logo.png"/>
			</a>
		</span>
		<span style="padding-top: 30px;display: inline-block;">
			<a href="logout" style="color:rgba(255,255,255,255);">退出登录</a>
		</span>
		<div class="u_meun">
			<ul class="a_u">
				<li type="1"><a href="sigle/examRecord" class="au_1">学习</a></li>
				<li type="2"><a href="sigle/info4question" class="au_2">问答</a></li>
				<li type="3"><a href="sigle/accCoin" class="au_3">账户</a></li>
				<li type="4"><a href="sigle/info" class="au_4">消息</a></li>
				<li type="5"><a href="sigle/setBinfo" class="au_5">设置</a></li>
			</ul>
			<span class="user_research">
				<form action="sigle/searchBuySub" method="post">
				<input type="text" class="userr_input" id="sigle_search_sub" name="searchname" />
				<input type="submit" class="userr_btn" value=""/>
				</form>
			</span>
		</div>
		<i class="clear"></i>
	</div>
</div>
<script type="text/javascript">
	var isLogined = "${sessionScope.Customer != null}";
	var curSigleTop = "${sessionScope.CurSigleTop}";

	$(document).ready(function() {
		
		if (isLogined != "true") {
			windows.location.href = '<%=basePath%>' + "logout";
			return;
		}
		
		curSigleTop = parseInt(curSigleTop, 10);
		if (isNaN(curSigleTop))
			curSigleTop = 1;
		
		var ullis = $("div.u_meun ul.a_u li");
		ullis.mouseover(function() {
			$(this).css("cursor", "pointer");
		});
		
		ullis.each(function() {
			var that = $(this);
			that.removeClass();
			var tpVal = that.attr("type");
			tpVal = parseInt(tpVal, 10);
			if (tpVal == curSigleTop) {
				that.addClass("current");
			};
		});
	});
</script>
<div style="clear:both;"></div>