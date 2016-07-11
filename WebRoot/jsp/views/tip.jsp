<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<base href="<%=basePath%>" />
<title>尚学在线后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学后台管理" />
<meta http-equiv="description" content="登录" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/tip.css" />
<script type="text/javascript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" charset="utf-8" src="jsp/js/tip.js"></script>
</head>

<body style="background:#CCC;">
	<div class="tck_cont_style">
		<div class="tck_cont_left">
			<img src="jsp/imgs/client/173.jpg" />
		</div>
		<form action="<%=basePath%>${sessionScope.tipUrl}" id="admin_tip" name="admin_tip"
			method="post" onsubmit="return verifyAdminTip();">
			<div class="tck_cont_right">
				<div class="tck_cont_close"
					onmouseout="this.className='tck_cont_close'"
					onmouseover="this.className='tck_cont_close_hover'"
					onclick="clickTipClose();"></div>
				<div class="tck_cont_nr">
					<span class="tck_cont_title">提示信息:</span> <span
						class="tck_cont_title" style="color:#9d9d9d;">${sessionScope.tip}</span>
					<span class="tck_cont_bottom"> <a href="javascript:void(0);"
						onclick="doSubmitAdminTip();">确定</a> </span>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
