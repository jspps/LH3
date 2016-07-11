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
<title>忘记密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="忘记密码" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<div class="tck_cont_style">
		<div class="tck_cont_left">
			<img src="jsp/imgs/client/178.jpg" />
		</div>
		<div class="tck_cont_right">
			<div class="tck_cont_close"
				onmouseout="this.className='tck_cont_close'"
				onmouseover="this.className='tck_cont_close_hover'"></div>
			<div class="tck_cont_nr">
				<span class="tck_cont_title">请输入您购买时登记的QQ邮箱：</span> <input
					type="text" class="tck_cont_ipnut" style="width:290px;" /> <span
					class="tck_cont_title"
					style="width:44px; margin-top:10px; float:right;">qq.com</span> <span
					class="tck_cont_title" style="color:#9d9d9d;">系统将在一分钟内将您的账户名和密码发到您的QQ邮箱，请查收后重新登录！</span>
				<span class="tck_cont_bottom_qx"><a
					href="javascript:void(0);">取消</a> </span> <span class="tck_cont_bottom"
					style="margin-left:0px;"><a href="javascript:void(0);">确定</a>
				</span>
			</div>
		</div>
	</div>
</body>
</html>
