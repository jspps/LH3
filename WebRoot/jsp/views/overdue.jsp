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
<base href="<%=basePath%>" />

<title>提示 - session过期</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="首页" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

</head>

<body style="background:#CCC;">
	<div class="tck_cont_style">
		<div class="tck_cont_left">
			<img src="jsp/imgs/client/173.jpg" />
		</div>
		<div class="tck_cont_right">
			<div class="tck_cont_close"
				onmouseout="this.className='tck_cont_close'"
				onmouseover="this.className='tck_cont_close_hover'"></div>
			<div class="tck_cont_nr">
				<div style="height: 170px;text-align: center;line-height: 30px;">Session已过期，单击按钮，请重新登录！</div>
				<span class="tck_cont_bottom">
					<a href="${action_url}">重新登录</a>
				</span>
			</div>
		</div>
	</div>
</body>
</html>