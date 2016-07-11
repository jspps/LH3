<%@ page language="java" pageEncoding="UTF8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心 - <decorator:title></decorator:title></title>
<decorator:head></decorator:head>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/onesigle_style.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/usermanager.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/onesigle/common.js"></script>
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/onesigle/user_common.js"></script>
</head>

<body style="background:#FFF;">
	<!--头部-->
	<div class="userheader">
		<div class="user_content">
			<span class="fl"><a href="#"><img
					src="jsp/imgs/onesigle//usermanange/logo.png" width="175" height="63" /> </a> </span>
			<div class="u_meun">
				<ul class="a_u">
					<li class="current"><a href="#" class="au_1">学习</a></li>
					<li><a href="#" class="au_2">问答</a></li>
					<li><a href="#" class="au_3">账户</a></li>
					<li><a href="#" class="au_4">消息</a></li>
					<li><a href="#" class="au_5">设置</a></li>
				</ul>
				<span class="user_research"><input type="text"
					class="userr_input" /><input type="button" class="userr_btn" /> </span>

			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div style="clear:both;"></div>
	<div>
		<!--内容-->
		<decorator:body></decorator:body>
		<div style="clear:both;"></div>
	</div>
</body>
</html>