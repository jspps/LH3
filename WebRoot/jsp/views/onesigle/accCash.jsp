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
<title>个人中心 - 帐户--提现</title>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/onesigle_style.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/usermanager.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="jsp/js/onesigle/common.js"></script>
<script type="text/javascript" src="jsp/js/onesigle/user_common.js"></script>
</head>

<body class="user_bg">
	<!--头部-->
	<jsp:include page="top/head.jsp" />

	<!--内容-->
	<jsp:include page="top/account_menus.jsp" />
	
	<div class="user_content bg_fff">
     	<div class="preson_info">
        <p class="tkj_cont">
        	<a href="javascript:void(0);" style="width:100px; height:35px; border:0px; background:#F60; color:#FFF; font-size:15px; cursor:pointer; margin-right:30px;padding: 8px;">申请提现</a>
        	<a href="sigle/accCashRecord" style="width:100px; height:35px; border:0px; background:#999; color:#FFF; font-size:15px; cursor:pointer;padding: 8px;">提现记录</a>
        </p>
       	<div class="div_table tjh_info" style="border:0px;">
                <table border="0" cellspacing="0" cellpadding="30" style="line-height:50px;">
                <tr>
                	<td colspan="3">可提现金额为：<label style="font-size:17px; font-weight:bold; color:#F00;">${customer.moneyCur}元</label></td>
                </tr>
                 <tr>
                   <td height="71">申请理由：</td>
                   <td><input type="text" class="p_input_1 p_w1" id="apply_reason" name="reason" /></td>
                   <td>&nbsp;</td>
                 </tr>
                 <tr>
                   <td>提现金额：</td>
                   <td><input type="text" class="p_input_1 p_w1" id="apply_money" name="monyeApply" /></td>
                   <td></td>
                 </tr>
                 <tr>
                   <td colspan="3"><input type="button"  value="提交提现申请" style="margin-top:30px; margin-left:20px; font-size:15px; background:#F60; border:0px; color:#FFF; cursor:pointer; height:40px; width:160px;" onclick="click4ApplyExchangeRmb();" /></td>
                 </tr>
               </table>
          </div>
          <div style="width:960px; float:left; text-align:left; margin-bottom:30px; margin-top:30px; border-top:1px solid #CCC; padding-top:20px;">
            	您每次可申请的提现金额不得低于<label style="font-weight:bold; font-size:15px; color:#F00;">20元</label>,也不得超过<label style="font-weight:bold; font-size:15px; color:#F00;">300元</label>
            	<br/>
            	客服人员将在3个工作日内进行审核，无误后您的支付宝账户将会收到相应的提现金额.
            	<br/>
            	此外，网站将收取提现金额的<label style="font-weight:bold; font-size:15px; color:#F00;">5%</label>作为平台服务费。
          </div>
       </div>
     </div>
     <script type="text/javascript">
		function click4ApplyExchangeRmb(){
			var isBl = "${customer.isVerifyAlipay}";
			if(isBl == "false"){
				alert("你的支付宝帐号尚未通过验证!");
				return;
			}
			
			var data = {};
			data.reason = $("#apply_reason").val();
			data.monyeApply = $("#apply_money").val();
			data.monyeApply = parseInt(data.monyeApply,10);
			
			if(isNaN(data.monyeApply)){
				alert("请输入正确的申请金额！");
				$("#apply_money").focus();
				return;
			}
			
			if(data.monyeApply < 20 || data.monyeApply > 300){
				alert("申请金额必须大于等于20小于等于300！");
				$("#apply_money").focus();
				return;
			}
			
			
			if(confirm("确定申请提取现金"+data.monyeApply+"元吗?")){
				var url = '<%=basePath%>'+"sigle/accAppayExchangeRmb";
				var infunCallBack = function(back){
					alert(back.msg);
					if(back.status == 1){
						window.location.reload();
					}
				};
				$.post(url,data,infunCallBack,"json");
			};
		};
	</script>
</body>
</html>