<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>学员登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="学员登录" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="top/head.jsp"></jsp:include>

	<!--头部-->
	<div class="xylogin_top">
		<img src="jsp/imgs/client/32.jpg" />
	</div>
	<div style="clear:both;"></div>
	<div class="xylogo_cont_div">
		<div class="xylogo_title">登录</div>
		<div class="xylogo_zh">
			<input type="text" class="input_zh" />
		</div>
		<div class="xylogo_mm">
			<input type="password" class="input_zh" />
		</div>
		<div class="xylogo_yzm">
			<input type="text" class="input_yzm" />
		</div>
		<div class="xylogo_yzmimg">
			<img src="jsp/imgs/client/35.jpg" />
		</div>
		<div class="xylogo_wjmm_div">
			<a href="javascript:void(0);">忘记密码或账号了！</a>
		</div>
		<div class="xylogo_buttom" onmouseout="this.className='xylogo_buttom'"
			onmouseover="this.className='xylogo_buttom_hover'">
			<a href="javascript:void(0);">登录</a>
		</div>
		<div style="clear:both;"></div>
	</div>
	<!--底部-->
	<jsp:include page="top/bot.jsp"></jsp:include>
</body>
</html>
