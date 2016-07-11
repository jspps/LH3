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
<title>注册为代理商</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学"/>
<meta http-equiv="description" content="注册为代理商"/>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<!--
	<script type="text/javascript" src="jsp/js/dateEx.js"></script>
	<link rel="stylesheet" type="text/css" href="styles.css">
-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="../top/head_dls_xxzx.jsp" />

	<!--内容-->
	<div class="dlszc_cont">
		<div class="dlszc_top_div">
			<span class="dlsz_top_text">第三方专业在线模考平台，布局全国性网格化营销推广，助推您的事业腾飞！</span>
		</div>
		<div class="dlszc_left">
			<form action="client/newAgent" method="post" onsubmit="return false;" id="fm_new_agent">
			<div class="dlszc_left_title">录入基本信息</div>

			<!--基本信息-->
			<div class="dlszc_left_cont">
				<div class="dlszc_left_list">
					<span class="dlszc_left_name">联系人：</span> 
					<input type="text" class="dlszc_input" name="agent_uname"/>
				</div>
				<div class="dlszc_left_list">
					<span class="dlszc_left_name">联系电话：</span> 
					<input type="text" class="dlszc_input" name="agent_phone" onchange="change4AgentCode(this.value);"/>
				</div>
				<div class="dlszc_left_list" style="margin-top:8px;">
					<span class="dlszc_left_name">代理号：</span>
					<span class="dlszc_left_sm">
						<input type="hidden" class="dlszc_input" name="agent_code" id="agent_code"/>
						<span id="agent_code_span"></span><br />
						<label style="color:#f2452d;">代理号即推荐号，请将此号码告知您的学生，让他（她）在购买时使用此推荐号，否则您将无法获取到代理佣金！请勿随意更改手机号码！</label>
					</span>
				</div>
				<div class="dlszc_left_list">
					<span class="dlszc_left_name">省：</span> 
					<select class="dlszc_left_xlcd" name="agent_province" id="agent_province">
						<option>选择省份</option>
						<c:forEach items="${provinces }" var="item">
							<option value="${item.provinceName }" parID="${item.id }">${item.provinceName }</option>
						</c:forEach>
						
					</select> 
					<span class="dlszc_left_cs">城市</span> 
					<select class="dlszc_left_xlcd" name="agent_city" id="agent_city">
						<option>选择城市</option>
					</select>
				</div>
				<div class="dlszc_left_list">
					<span class="dlszc_left_name">详细地址：</span> 
					<input type="text" class="dlszc_input" name="agent_seat"/>
				</div>
				<div class="dlszc_left_list">
					<span class="dlszc_left_name">QQ邮箱：</span> 
					<input type="text" class="dlszc_input" name="agent_qq"/>
				</div>

				<div class="dlszc_left_bjys">
					<div class="dlszc_left_list">
						<span class="dlszc_left_name">登录账号：</span> 
						<input type="text" class="dlszc_input" name="agent_lgid" id="agent_lgid" readonly="readonly"/>
					</div>
					<div class="dlszc_left_list">
						<span class="dlszc_left_name">登录密码：</span> 
						<input type="password" class="dlszc_input" name="agent_lgpwd" id="agent_lgpwd"/>
					</div>
					<div class="dlszc_left_list">
						<span class="dlszc_left_name">再次输入密码：</span> 
						<input type="password" class="dlszc_input" name="agent_lgpwd2" id="agent_lgpwd2" />
					</div>
				</div>
			</div>

			<!--请填写你代理课程销售方面的优势-->
			<div class="dlszc_left_title">请填写你代理课程销售方面的优势</div>
			<textarea name="agent_goodness" cols="" rows="" class="dlszc_left_ys" style="resize:none;"></textarea>

			<!--录入收款账户信息-->
			<div class="dlszc_left_title_zhxx">录入收款账户信息</div>
			<div class="dlszc_left_list">
				<span class="dlszc_left_name">支付宝账号：</span> 
				<input type="text" class="dlszc_input" name="agent_alipay" id="agent_alipay"/>
			</div>
			<div class="dlszc_left_list">
				<span class="dlszc_left_name">再次输入账号：</span> 
				<input type="text" class="dlszc_input" name="agent_alipay2" id="agent_alipay2"/>
			</div>
			<div style="color:red;">注:请确认您提交的信息是否正确,本网客服将在三个工作日内审核,通过审核后,才可以推荐您的学生购买题库!</div>
			<div class="dlszc_tjxx_buttom"
				onmouseout="this.className='dlszc_tjxx_buttom'"
				onmouseover="this.className='dlszc_tjxx_buttom_hover'"
				onclick="click2NewAgent();">提交信息</div>
			</form>
		</div>
		
		<div class="dlszc_right">
			<div class="dlszc_rihgt_title">${rnkDate}年代理商业绩排行榜</div>
			<div class="dlszc_right_cont">
				<div class="dlszc_right_ico">奖金总额：${sumBonus}</div>
				<div class="dlszc_right_list">
					<span class="dlszc_right_list_mc">排名</span> <span
						class="dlszc_right_list_name">姓名</span> <span
						class="dlszc_right_list_name">成交金额</span> <span
						class="dlszc_right_list_name">代理收益</span> <span
						class="dlszc_right_list_name">预计奖金</span>
				</div>
				<c:forEach items="${rnkList}" var="item">
				<div class="dlszc_right_list_style">
					<span class="dlszc_right_list_aa">${item.indexs}</span> <span
						class="dlszc_right_list_b">${item.name}</span> <span
						class="dlszc_right_list_b">${item.money}</span> <span
						class="dlszc_right_list_b">${item.royalty}</span> <span
						class="dlszc_right_list_b">${item.bonus}</span>
				</div>
				</c:forEach>
				<div class="dlszc_left_buttom_hover"
					style="float:left; margin-left:17px;"
					onmouseout="this.className='dlszc_left_buttom_hover'"
					onmouseover="this.className='dlszc_left_buttom'"
					onclick="click2ShowRewardRule();">奖励规则</div>
			</div>
		</div>

		<div style="clear:both;"></div>
	</div>
	<!--底部-->
	<jsp:include page="../top/bot.jsp"></jsp:include>
	
	<!-- 奖励规则 -->
	<div id="reward_rule_4_agent" style="display: none" onclick="this.style.display='none'">
		<div style="z-index: 10000;opacity:0.6;filter : alpha(opacity=60);background:url(jsp/imgs/client/01.jpg) repeat; width:100%;height:100%;position:fixed;left:0px;top:0px;"></div>
		<div style="width:100%;height:100%;position:absolute;left:0px;top:0px;z-index: 10050;">
			<div style="position:relative;top: 50%;left: 50%;width:800px;min-height:600px; margin:-300px -400px;background-color: white;text-align: center;">
				<div style="color: red;width: 98%;text-align: right;">关闭</div>
				<div>
					<h3 style="margin: 5px;">奖励规则 </h3>
				</div>
				<jsp:include page="reward_rule_agent.jsp" />
				<span style="display: inline-block;margin: 5px;"></span>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function validatemobile(mobile){
	        if(!mobile || mobile.length==0)
	        {
	           return false;
	        };
	        if(mobile.length != 11)
	        {
	            // document.form1.mobile.focus();
	            return false;
	        };
	        
	        var myreg = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
	        if(!myreg.test(mobile))
	        {
	            // document.form1.mobile.focus();
	            return false;
	        };
	        return true;
	    };
	    
	    function change4AgentCode(mobile){
	    	var isValid = validatemobile(mobile);
			if(!isValid){
				alert("请正确输入手机号码！");
				return;
			};
	    	$("#agent_code").val(mobile);
	    	$("#agent_code_span").html(mobile);
	    	$("#agent_lgid").val(mobile);
	    };
	    
		function click2NewAgent() {
			var jqForm = $("#fm_new_agent");
			var fmEJq = jqForm[0];
			var data = jqForm.serialize();
			var isValid = validatemobile(fmEJq.agent_phone.value);
			if(!isValid){
				alert("请输入手机号码！");
				return;
			}
			
			if(fmEJq.agent_province.value == ""){
				alert("请选择省份!");
				return;
			};
			
			if(fmEJq.agent_city.value == ""){
				alert("请选择城市!");
				return;
			};
			
			if(fmEJq.agent_lgid.value == ""){
				alert("登录帐号为空!");
				return;
			};
			
			if(fmEJq.agent_lgpwd.value == ""){
				alert("登录密码为空!");
				return;
			};
			if(fmEJq.agent_lgpwd.value != fmEJq.agent_lgpwd2.value){
				alert("两次密码不同!");
				return;
			};
			
			if(fmEJq.agent_alipay.value == ""){
				alert("支付宝帐号为空!");
				return;
			};
			if(fmEJq.agent_alipay.value != fmEJq.agent_alipay2.value){
				alert("两次支付宝帐号不同!");
				return;
			};
			
			var url = jqForm.attr("action");
			var callFun = function(backData){
				alert(backData.msg);
				if(backData.status==1){
					  window.location.href = '<%=basePath%>'+"dls";
				}
			};
			$.post(url,data,callFun,"json");
		}
		
		function click2ShowRewardRule(){
			$("#reward_rule_4_agent").show();
		}
		
		$(function(){
			$("#agent_province").change(function() {
				var optSel = $(this).children("option:selected"); 
				var parID = optSel.attr("parID");
				var text = optSel.text();
				var url = '<%=basePath%>' + "getCityJson";
				var callBackFun = function(backData){
					if(backData.status == 1 && backData.data){
						var jqCity = $("#agent_city"); 
						jqCity.empty();
						jqCity.append($("<option>",{"text":"选择城市","selected":"selected"}));
						var lens = backData.data.length;
						for(var i = 0; i < lens; i++){
							var tmpEn = backData.data[i];
							var jqOption = $("<option>").text(tmpEn.cityName);
							jqOption.val(tmpEn.cityName);
							jqOption.attr("provincid",tmpEn.pid);
							jqCity.append(jqOption);
						};
					};
				};
				$.post(url,{"provincid":parID},callBackFun,"json");
			});
		});
	</script>
</body>
</html>
