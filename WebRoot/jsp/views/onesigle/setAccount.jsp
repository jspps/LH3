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
<title>个人中心 - 设置--修改账号</title>
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

<body class="user_bg">
	<!--头部-->
	<jsp:include page="top/head.jsp" />

	<!--内容-->
	<div class="user_content">
		<div class="user_chlidmenu">
			<a href="sigle/setBinfo">基本资料</a> <a href="sigle/setPwd">密码设置</a> <a href="sigle/setAccount" class="current">修改账号</a>
			<i class="clear"></i>
		</div>
	</div>
	<div class="user_content bg_fff">
		<div class="preson_info">
			<p class="zh_info1">
				如果您登记的手机号码更改，登录账号也要做相应变化，请牢记
			</p>
		</div>
		<table class="user_table f_st person_table" border="0" cellpadding="0"
			cellspacing="0" style="margin-top:10px;">
			<tr>
				<th>新账号：</th>
				<td><input type="text" class="p_input_1 p_w1" id="new_lgid1" /></td>
			</tr>
			<tr>
				<th>再次输入新账号：</th>
				<td><input type="text" class="p_input_1 p_w1" id="new_lgid2" /></td>
			</tr>
			<tr>
				<th>请输入您的密码：</th>
				<td><input type="password" class="p_input_1 p_w1" id="cur_pwd" /></td>
			</tr>
			<tr>
				<th></th>
				<td style="text-align:center;"><input type="button"
					class="btn_yes mt_10" value="确定" onclick="click2Chane();" /></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		$(function() {
			$("#new_lgid1").Ipt_defult_word({
				"word" : "请输入登录名由英文，数字组成",
				"cls" : "colorhover"
			});
		});
		function click2Chane(){
			var npwd1 = $("#new_lgid1").val();
			var npwd2 = $("#new_lgid1").val();
			if(npwd1 != npwd2){
				alert("两次登录帐号不正确！");
				return;
			};
			var curpwd = $("#cur_pwd").val();
			var data = {curpwd:curpwd,newlgid:npwd1};
			var callBack = function(back) {
					// console.info(back);
					if (back.status) {
						alert(back.msg);
						if (back.status == 1) {
							window.location.reload();
						};
					}
			};
			$.post("sigle/doChageAccount", data,callBack , "json");
		}
	</script>
</body>
</html>