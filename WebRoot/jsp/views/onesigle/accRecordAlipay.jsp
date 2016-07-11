<%@ page language="java" pageEncoding="UTF8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>
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
<title>个人中心 - 帐户-登记支付宝账号</title>
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
        	<p class="tkj_cont">请输入您的支付宝账号并验证，验证过后才能提现你拥有的资金。</p>
        	<div class="div_table tjh_info" style="border:0px;">
                <table border="0" cellspacing="0" cellpadding="30" style="line-height:50px;">
                  <tr>
                    <td height="71">支付宝账号：</td>
                    <td><input type="text" class="p_input_1 p_w1" value="${customer.alipay }"  id="alipay_4_cust" name="alipay" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>真实姓名：</td>
                    <td><input type="text" class="p_input_1 p_w1" value="${customer.alipayRealName }"  id="alipay_name_4_cust" name="alipay_name" /></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>支付宝状态：</td>
                    <td style="text-align: left;">
                   		<c:choose>
                   		<c:when test="${customer.isVerifyAlipay}"><label style="font-weight:bold; color:#090;margin-left: 26px;">验证通过</label></c:when>
                   		<c:otherwise><label style="font-weight:bold; color:#F00;margin-left: 26px;">尚未通过验证</label></c:otherwise>
                   		</c:choose>
					</td>
					<td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="3">
                    	<div style="width:500px; float:left; margin-top:30px;">
                        	验证支付金额：<label style="font-weight:bold; color:#F00;">0.10元</label>
                        	<input type="hidden" value="0.1" id="alipay_money" name="alipay_money" />
                            <input type="button"  value="马上验证支付宝" style="margin-top:0px; margin-left:20px; font-size:15px; background:#F60; border:0px; color:#FFF; cursor:pointer; height:40px; width:180px;" onclick="click4VerifyAlipay();"/>
                        </div>
                    </td>
                  </tr>
                </table>

            </div>
        	<div style="width:99%; float:left; text-align:left; margin-bottom:30px; margin-top:30px; border-top:1px solid #CCC; padding-top:20px;">
            	<label style="color:#F00;">注意一下几点：</label><br />
            	&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#F10;">1.支付宝帐号是提取现金的重要帐号，必须是您个人的支付宝帐号。该支付宝帐号必须实名认证的！</span><br />
            	&nbsp;&nbsp;&nbsp;&nbsp;<span>2.真实姓名是指经过了实名认证的支付宝帐号,支付宝帐号实名认证时,会输入你的身份以及你身份证上的姓名,此处就是该身份证上的姓名.</span><br />
            	&nbsp;&nbsp;&nbsp;&nbsp;<span>3.请务必进行验证，如果成功通过验证，系统会显示“验证通过”。</span><br />
            	&nbsp;&nbsp;&nbsp;&nbsp;<span>4.您在线悬赏提问或回复其他学员的问题时候如有收益，“验证通过”的支付宝就可以提取现金了!</span><br />
            	&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#F10;">5.请务必注意“验证通过”的支付宝帐号,不需要再次验证,如果你确定再次验证,请完成验证流程,否则你的状态将会变成“尚未验证”!</span><br />
            	&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#F10;">6.请务必注意以上几点，否则您提取现金出现错误，我们将不会做任何处理!</span>
            </div>
            <i class="clear"></i>
       </div>
       <div id="valide_alipay_4_customer"></div>
    </div>
	<script type="text/javascript">
		function click4VerifyAlipay(){
			var isBl = "${customer.isVerifyAlipay}";
			var data = {};
			data.alipay = $("#alipay_4_cust").val();
			data.alipay_name = $("#alipay_name_4_cust").val();
			data.alipay_money = $("#alipay_money").val();
			if(data.alipay.length <= 0){
				alert("请输入正确的支付宝帐号！");
				$("#alipay_4_cust").focus();
				return;
			}
			
			if(data.alipay_name.length <= 0){
				alert("请输入真实姓名！");
				$("#alipay_name_4_cust").focus();
				return;
			}
			
			var desc = "确定花费0.1元进行验证?";
			if(isBl == "true"){
				desc = "通过了验证的,想换支付宝号再次验证?";
			}
			
			if(confirm(desc)){
				var url = '<%=basePath%>'+"sigle/accVerifyAliapy";
			
				var infunCallBack = function(back){
					if(back.status == 1){
						if(back.data){
							$("#valide_alipay_4_customer").html(back.data);
						}else{
							window.location.reload();
						}
					}else{
						alert(back.msg);
					}
				};
				$.post(url,data,infunCallBack,"json");
			}
		};
	</script>
</body>
</html>