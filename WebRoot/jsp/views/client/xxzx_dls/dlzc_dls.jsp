<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String host = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort() + "/";
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>代理商注册登录页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="代理商注册登录页面" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="../top/head_dls_xxzx.jsp" />
	
	<!--头部-->
	<div class="dlslogin_top">
		<img src="jsp/imgs/client/37.jpg" />
	</div>
	<div style="clear:both;"></div>
	<div class="xylogo_cont_div">

		<!--注册-->
		<div class="dlslogin_left">
			<div class="dlslogin_title">注册</div>
			<div class="dlslogin_sm">请认真阅读每类用户的权责利说明，务必正确选择！</div>
			<jsp:include page="Agent_Registration_Protocol.jsp" />
			<div class="dlslogin_bottom"
				onmouseout="this.className='dlslogin_bottom'"
				onmouseover="this.className='dlslogin_bottom_hover'"
				onclick="onclick2Regist();">
				<a href="javascript:void(0);" >同意</a>
			</div>
		</div>

		<!--登录-->
		<div class="dlslogin_right">
			<div class="dlslogin_title" style="width:341px; padding-bottom:32px;">登录</div>
			<div class="xylogo_zh">
				<input type="text" class="input_zh" id="lgid" />
			</div>
			<div class="xylogo_mm"
				style="border-left:1px solid #d3d3d3; margin-top:22px;">
				<input type="password" class="input_zh" id="lgpwd" />
			</div>
			<div class="xylogo_wjmm_div" style="width:343px; text-align:right;">
				<a href="javascript:void(0);" onclick="forgetPswd();">忘记密码或账号了！</a>
			</div>
			<div class="xylogo_buttom" style="width:344px;"
				onmouseout="this.className='xylogo_buttom'"
				onmouseover="this.className='xylogo_buttom_hover'"
				onclick="onclick2Login();">
				<a href="javascript:void(0);">登录</a>
			</div>
		</div>

		<div style="clear:both;"></div>
	</div>
	<!--底部-->
	<jsp:include page="../top/bot.jsp"></jsp:include>
	<script type="text/javascript">
		function onclick2Regist(){
			window.location.href = '<%=basePath%>'+ "regAgent";
		}
		
		function onclick2Login(){
			var lgid = $("#lgid").val();
			if(lgid.length <= 0){
				alert("请输入帐号!");
				$("#lgid").focus();
				return;
			}
			
			var lgpwd = $("#lgpwd").val();
			if(lgpwd.length <= 0){
				alert("请输入密码!");
				$("#lgpwd").focus();
				return;
			}
			
			var url = '<%=basePath%>'+ "client/loginAgent";
			var data = {"lgid":lgid,"lgpwd":lgpwd};
			var callBackIn = function(back){
				if(back.status == 1){
					window.location.href = '<%=host%>'+ "LH3Manager/go2Agent?data="+back.data;
				}else{
					alert(back.msg);
				}
			};
			$.post(url,data,callBackIn,"json");
		}
		
		function forgetPswd(){
			window.location.href = '<%=basePath%>' +  "client/forgetPswd";
		}
	</script>
</body>
</html>
