<%@ page language="java" pageEncoding="UTF8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>个人中心 - 登录</title>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
</head>

<body style="background:#CCC;">
	<div class="login_cont_style">
		<div class="login_top_close"
			onmouseout="this.className='login_top_close'"
			onmouseover="this.className='login_top_close_hover'"
			onclick="click2CloseLogin();"></div>
		<div class="login_top_title">登录！</div>

		<div class="login_cont_nr">
			<form action="sigle/welcome" method="post" onsubmit="return true;"
				name="login_form" id="login_form">
				<div class="xylogo_zh"
					style="background:url(jsp/imgs/client/166.jpg) no-repeat;">
					<input type="text" class="input_zh" id="login_id" name="login_id" />
				</div>
				<div class="xylogo_mm"
					style="border-left:1px solid #d3d3d3; margin-top:22px;">
					<input type="password" class="input_zh" id="login_pwd" name="login_pwd"/>
				</div>
				<div class="xylogo_wjmm_div"
					style="width:343px; text-align:left; color:#f68909;">
					<span id="login_error_tip" style="display: none;">请输入正确的用户名和密码</span>
				</div>
				<div class="login_zt_div">
					<span class="login_zt_span"><input type="checkbox"
						style="float:left; margin-right:7px; margin-top:2px;" />记住我的登录状态</span>
					<span class="login_wjmi_span"><a href="javascript:void(0);">忘记密码？</a>
					</span>
				</div>
				<div>
					<input type="submit" value="登录" class="login_bottom_style"
						onmouseout="this.className='login_bottom_style'"
						onmouseover="this.className='login_bottom_style_hover'" />
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function click2CloseLogin(){
			var url = "${sessionScope.UrlPre}";
			var intdex = url.indexOf("/");
			if(intdex == 0){
				url = url.substring(1);
			};
			
			if(url == "")
				url = "client";
				
			url ='<%=basePath%>'+url;
			
			window.location.href = url;
		};
	</script>
</body>
</html>