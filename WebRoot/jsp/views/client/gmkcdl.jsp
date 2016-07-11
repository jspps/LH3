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
<title>购买课程登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="购买课程登录" />
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

	<!--内容-->
	<div class="dl_tck_cont">
		<div class="dk_tck_close" onmouseout="this.className='dk_tck_close'"
			onmouseover="this.className='dk_tck_close_hover'"></div>
		<!--新学员购买-->
		<div class="dl_tck_right_cont">
			<div class="dlslogin_right" style="float:left;">
				<div class="dlslogin_title"
					style="width:341px; padding-bottom:32px; color:#000000;">新学员购买</div>
				<div class="xylogo_zh"
					style="background:url(jsp/imgs/client/166.jpg) no-repeat;">
					<input type="text" class="input_zh" />
				</div>
				<div class="xylogo_mm"
					style="border-left:1px solid #d3d3d3; margin-top:22px;">
					<input type="password" class="input_zh" />
				</div>
				<div class="xylogo_mm"
					style="border-left:1px solid #d3d3d3; margin-top:22px;">
					<input type="password" class="input_zh" />
				</div>
				<div class="xylogo_zh"
					style="background:url(jsp/imgs/client/167.jpg) no-repeat; margin-top:22px;">
					<input type="text" class="input_zh" />
				</div>
				<div class="xylogo_yzm"
					style="border-left:1px solid #d3d3d3; width:230px; margin-top:22px;">
					<input type="text" class="input_yzm" />
				</div>
				<div class="xylogo_yzmimg" style="margin-top:22px;">
					<img src="jsp/imgs/client/35.jpg" />
				</div>
				<div class="xylogo_wjmm_div"
					style="width:343px; text-align:left; color:#f68909;">请输入正确的用户名和密码</div>
				<div class="xylogo_buttom_hover" style="width:344px;"
					onmouseout="this.className='xylogo_buttom_hover'"
					onmouseover="this.className='xylogo_buttom'">
					<a href="javascript:void(0);">付款</a>
				</div>

			</div>
		</div>

		<!--老学员购买-->
		<div class="dl_tck_left_cont">
			<div class="dlslogin_right" style="float:left;">
				<div class="dlslogin_title"
					style="width:341px; padding-bottom:32px; color:#000000;">老学员购买</div>
				<div class="xylogo_zh"
					style="background:url(jsp/imgs/client/166.jpg) no-repeat;">
					<input type="text" class="input_zh" />
				</div>
				<div class="xylogo_mm"
					style="border-left:1px solid #d3d3d3; margin-top:22px;">
					<input type="password" class="input_zh" />
				</div>
				<div class="xylogo_yzm"
					style="border-left:1px solid #d3d3d3; width:230px; margin-top:22px;">
					<input type="text" class="input_yzm" />
				</div>
				<div class="xylogo_yzmimg" style="margin-top:22px;">
					<img src="jsp/imgs/client/35.jpg" />
				</div>
				<div class="xylogo_wjmm_div"
					style="width:343px; text-align:left; color:#f68909;">请输入正确的用户名和密码</div>
				<div class="xylogo_buttom" style="width:344px; margin-top:133px;"
					onmouseout="this.className='xylogo_buttom'"
					onmouseover="this.className='xylogo_buttom_hover'">
					<a href="javascript:void(0);">登录</a>
				</div>
				<div class="xylogo_wjmm_div"
					style="width:343px; padding:15px 0px 10px 0px; text-align:left;">
					<a href="javascript:void(0);">忘记密码或账号了！</a>
				</div>
			</div>
		</div>


	</div>
	<!--底部-->
	<jsp:include page="top/bot.jsp"></jsp:include>
</body>
</html>
