<%@ page language="java" pageEncoding="UTF8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
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
<title>尚学在线 - <decorator:title></decorator:title></title>
<decorator:head></decorator:head>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8" src="jsp/js/jquery-1.11.1.js"></script>
</head>

<body style="background:#FFF;">
	<!--导航条-->
	<div class="top_style">
		<div class="top_center">
			<div class="logo_div">
				<a href="javascript:void(0);"><img src="jsp/imgs/client/logo.jpg" /> </a>
			</div>
			<div class="dhl_div">
				<ul class="dhl_ul">
					<li class="dhl_li_hover">首页</li>
					<li style="margin-right:40px;"><a href="javascript:void(0);">考试榜</a>
					</li>
					<li><a href="javascript:void(0);">全部课程</a></li>
				</ul>
			</div>
			<div class="login_cont">
				<div class="login_cont_ico">
					<img src="jsp/imgs/client/02.jpg" />
				</div>
				<div class="login_cont_text">
					<a href="javascript:void(0);">登陆</a>
				</div>
			</div>
		</div>
	</div>
	<div style="clear:both;"></div>
	<div>
		<!--内容-->
		<decorator:body></decorator:body>
		<div style="clear:both;"></div>
	</div>
	<!--底部-->
	<div class="db_div_style">
		<div class="db_div_cont">
			<div class="db_div_top">
				<span class="db_list_cont"> <span class="db_list_ico">
						<!--底部图标--> </span> <span class="db_list_text"><a
						href="javascript:void(0);">新手指引</a> </span> </span> <span class="db_list_cont">
					<span class="db_list_ico_a"> <!--底部图标--> </span> <span
					class="db_list_text"><a href="javascript:void(0);">学习保障</a>
				</span> </span> <span class="db_list_cont"> <span class="db_list_ico_b">
						<!--底部图标--> </span> <span class="db_list_text"><a
						href="javascript:void(0);">支付方式</a> </span> </span> <span class="db_list_cont">
					<span class="db_list_ico_c"> <!--底部图标--> </span> <span
					class="db_list_text"><a href="javascript:void(0);">服务条款</a>
				</span> </span>
				<div class="db_dz_cont">
					电话：028-66753505&nbsp;&nbsp;&nbsp;QQ：636544785<br />地址：成都市金牛区华侨城徐州路1栋3单元8010
				</div>
				<div class="dh_ewm_div">
					<img src="jsp/imgs/client/30.jpg" />
				</div>
			</div>
			<div class="dh_div_bottom">成都尚学在线网络科技有限公司版权所有&nbsp;&nbsp;&nbsp;蜀ICP备12345678</div>
		</div>
	</div>
</body>
</html>
