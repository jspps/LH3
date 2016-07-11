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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>立即购买登录</title>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="jsp/js/dateEx.js"></script>
</head>

<body>
	<div class="dl_tck_cont">
		<div class="dk_tck_close" onmouseout="this.className='dk_tck_close'"
			onmouseover="this.className='dk_tck_close_hover'" onclick="close2back()"></div>
		<!--新学员购买-->
		<div class="dl_tck_right_cont">
			<div class="dlslogin_right" style="float:left;">
				<div class="dlslogin_title" style="width:341px; padding-bottom:32px; color:#000000;">新学员购买</div>
				<div class="xylogo_zh" style="background:url(jsp/imgs/client/166.jpg) no-repeat;">
					<input type="text" class="input_zh" id="newUserId" style="color:gray" value="请输入用户名" onfocus="if(this.value=='请输入用户名'){this.value=''};this.style.color='black';" onblur="if(this.value==''||this.value=='请输入用户名'){this.value='请输入用户名';this.style.color='gray';}" />
				</div>
				<div class="xylogo_mm" style="border-left:1px solid #d3d3d3; margin-top:22px;">
					<input type="password" class="input_zh" id="newUserPwd1" style="display: none" />
					<input type="text" class="input_zh" id="newUserPwd11" value="请输入密码" style="color:gray"/>
				</div>
				<div class="xylogo_mm" style="border-left:1px solid #d3d3d3; margin-top:22px;">
					<input type="password" class="input_zh" id="newUserPwd2" style="display: none" />
					<input type="text" class="input_zh" id="newUserPwd22" value="请再次输入密码" style="color:gray"/>
				</div>
				<div class="xylogo_zh" style="background:url(jsp/imgs/client/167.jpg) no-repeat; margin-top:22px;">
					<input type="text" class="input_zh" id="newUserMail" style="color:gray" value="请输入您的邮箱" onfocus="if(this.value=='请输入您的邮箱'){this.value=''};this.style.color='black';" onblur="if(this.value==''||this.value=='请输入您的邮箱'){this.value='请输入您的邮箱';this.style.color='gray';}"  />
				</div>
				<div class="xylogo_yzm" style="border-left:1px solid #d3d3d3; width:230px; margin-top:22px;">
					<input type="text" class="input_yzm" id="newVerifyCode" />
				</div>
				<div class="xylogo_yzmimg" style="margin-top:22px;">
					<img src="client/checkCode?jtime=<%=Calendar.getInstance().getTimeInMillis()%>" width="109" height="44" onclick="getNewCode(this);" />
				</div>
				<div id="newprompt" class="xylogo_wjmm_div" style="width:343px; text-align:left; color:#f68909; display:none;" >
					请输入正确的用户名和密码
				</div>
				<div class="xylogo_buttom_hover" style="width:344px;"
					onmouseout="this.className='xylogo_buttom_hover'"
					onmouseover="this.className='xylogo_buttom'" onclick="new2login()" >
					<a href="javascript:void(0);" >付款</a>
				</div>
			</div>
			
			<script type="text/javascript">
				$(function() {
					var pwd = $("#newUserPwd22");
					var password = $("#newUserPwd2");
					pwd.focus(function() {
						pwd.hide();
						password.show().focus();
					});
					password.focusout(function() {
						if (password.val() == "") {
							password.hide();
							pwd.show();
						}
					});
				});
				
				$(function() {
					var pwd = $("#newUserPwd11");
					var password = $("#newUserPwd1");
					pwd.focus(function() {
						pwd.hide();
						password.show().focus();
					});
					password.focusout(function() {
						if (password.val() == "") {
							password.hide();
							pwd.show();
						}
					});
				});
			</script>
		</div>

		<!--老学员购买-->
		<div class="dl_tck_left_cont">
			<div class="dlslogin_right" style="float:left;">
				<div class="dlslogin_title" style="width:341px; padding-bottom:32px; color:#000000;">老学员购买</div>
				<div class="xylogo_zh" style="background:url(jsp/imgs/client/166.jpg) no-repeat;">
					<input type="text" class="input_zh" id="olduserid" style="color:gray" value="请输入用户名" onfocus="if(this.value=='请输入用户名'){this.value=''};this.style.color='black';" onblur="if(this.value==''||this.value=='请输入用户名'){this.value='请输入用户名';this.style.color='gray';}"  />
				</div>
				<div class="xylogo_mm" style="border-left:1px solid #d3d3d3; margin-top:22px;">
					<input type="password" class="input_zh" id="olduserpwd" style="display: none" />
					<input type="text" class="input_zh" id="olduserpwd1" value="请输入密码" style="color:gray"/>
				</div>
				<div class="xylogo_yzm" style="border-left:1px solid #d3d3d3; width:230px; margin-top:22px;">
					<input type="text" class="input_yzm" id="verifyCode" />
				</div>
				<div class="xylogo_yzmimg" style="margin-top:22px;">
					<img src="client/checkCode?jtime=<%=Calendar.getInstance().getTimeInMillis()%>" width="109" height="44" onclick="getNewCode(this);" />
					<!-- <img src="jsp/imgs/client/35.jpg" />  -->
				</div>
				<div id="oldprompt" class="xylogo_wjmm_div" style="width:343px; text-align:left; color:#f68909; display:none;" >
					请输入正确的用户名和密码
				</div>
				<div class="xylogo_buttom" style="width:344px; margin-top:133px;"
					onmouseout="this.className='xylogo_buttom'"
					onmouseover="this.className='xylogo_buttom_hover'" onclick="old2login()">
					<a href="javascript:void(0);" >登录</a>
				</div>
				<div class="xylogo_wjmm_div"
					style="width:343px; padding:15px 0px 10px 0px; text-align:left;">
					<a href="javascript:void(0);" onclick="forgetPswd()">忘记密码或账号了！</a>
				</div>
			</div>
			
			<script type="text/javascript">
				$(function() {
					var pwd = $("#olduserpwd1");
					var password = $("#olduserpwd");
					pwd.focus(function() {
						pwd.hide();
						password.show().focus();
					});
					password.focusout(function() {
						if (password.val() == "") {
							password.hide();
							pwd.show();
						}
					});
				});
			</script>
		</div>
	</div>
	
	<form action="client/shopCart" id="fm_kind2buy" method="post">
		<input type="hidden" id="kindid4buy" name="kindid" value="${kindId}" />
		<input type="hidden" id="ses4ShopCart" name="ses4ShopCart" value="0" />
	</form>
	
	<script type="text/javascript">
	
		function close2back() {
			window.history.back();
		}
	
		function getNewCode(that){
			var url = "client/checkCode?jtime=" + getTimeMSecond();
			that.src = url;
		}
		
		// 老学员登录
		function old2login(){
			var olduserid = $("#olduserid").val();
			var olduserpwd = $("#olduserpwd").val();
			var verifyCode = $("#verifyCode").val();
			$.post("client/doLogin4OldNew", {
				olduserid : olduserid, 
				olduserpwd : olduserpwd, 
				verifyCode : verifyCode
			}, function (back) {
				$("#oldprompt").html(back.msg).show();
				if (back.status == 1) {
					$("#ses4ShopCart").val(getTimeMSecond());
					$("#fm_kind2buy").submit();
				}
			}, "json");
		}
		
		// 新学员注册
		function new2login(){
			var newUserId = $("#newUserId").val();
			var newUserPwd1 = $("#newUserPwd1").val();
			var newUserPwd2 = $("#newUserPwd2").val();
			var newUserMail = $("#newUserMail").val();
			var newVerifyCode = $("#newVerifyCode").val();
			
			$.post("client/register4NewCust", {
				newUserId : newUserId,
				newUserPwd1 : newUserPwd1,
				newUserPwd2 : newUserPwd2,
				newUserMail : newUserMail,
				newVerifyCode : newVerifyCode
			}, function(back) {
				$("#newprompt").html(back.msg).show();
				if (back.status == 1) {
					$("#ses4ShopCart").val(getTimeMSecond());
					$("#fm_kind2buy").submit();
				}
			}, "json");
		}
	
		function forgetPswd(){
			window.location.href = '<%=basePath%>' +  "client/forgetPswd";
		}
	</script>
</body>
</html>
