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
<title>体验模考主页_数据分析</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="体验模考主页_数据分析" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	function click2BuyKind(kindId) {
		$("#kind_kindId").val(kindId);
		$("#fm_2buy_kind").submit();
	}

	function intoExamRoom(examid) {
		$("#examid").val(examid);
		$("#fm_examId").submit();
	};
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="../../top/head.jsp"></jsp:include>

	<!--内容-->
	<jsp:include page="../midMenu.jsp"></jsp:include>
	<div>
		<div class="mk_tims_dbuttom_cont">
			<div class="mk_tims_nr">
				<div class="mk_sjfx_top">
					<span class="mk_sjfx_text">选择分析类型：</span> 
					<span class="mk_sjfx_text" style="margin-right:25px;">
						<input name="reckon" type="radio" value="1" style="margin-right:5px;" checked="checked" />模考类型
					</span> 
					<span class="mk_sjfx_text" style="margin-right:25px;">
						<input name="reckon" type="radio" value="2" style="margin-right:5px;" />考试类型
					</span> 
					
					<!-- 
					<span class="mk_sjfx_text">
						<input name="reckon" type="radio" value="3" style="margin-right:5px;" />进步曲线
					</span> 
					<span class="mk_sjfx_zql">
						综合正确率：<label style=" font-size:24px; color:#ff9600;">100</label>%
					</span>
					 -->
				</div>

				<!--模块类型-->
				<div class="mk_sjfx_cont" id="div_reckon_type">
					<div class="mk_sjfx_list">
						<span class="mk_sjfx_list_sm">章节练习</span> 
						<span style="width: 68px; height: 450px;border:1px solid #547f25;display: inline-block;">
							<span style="width: 68px; height: ${zzlxH }px;background-color: #547f25;display: inline-block;"></span>
						</span>
						<span class="mk_sjfx_list_text">正确率${zzlx }%</span>
					</div>
					<div class="mk_sjfx_list">
						<span class="mk_sjfx_list_sm">历年真题</span> 
						<span style="width: 68px;height: 450px;border:1px solid #b7881c;display: inline-block;">
							<span style="width: 68px;height: ${lnztH }px;background-color:#b7881c;display: inline-block;"></span> 
						</span> 
						<span class="mk_sjfx_list_text mk_sjfx_list_zq">正确率${lnzt }%</span>
					</div>
					<div class="mk_sjfx_list">
						<span class="mk_sjfx_list_sm">全真模拟</span> 
						<span style="width: 68px;height: 450px;border:1px solid #bf524f;display: inline-block;">
							<span style="width: 68px;height: ${qzmlH }px;background-color:#bf524f;display: inline-block;"></span> 
						</span>
						<span class="mk_sjfx_list_text mk_sjfx_list_qzmn">正确率${qzml }%</span>
					</div>
					<div class="mk_sjfx_list" style="margin-right:0px;">
						<span class="mk_sjfx_list_sm">绝胜押题</span> 
						<span style="width: 68px;height: 450px;border:1px solid #254899;display: inline-block;">
							<span style="width: 68px;height: ${jsytH }px;background-color: #254899;display: inline-block;"></span>
						</span>
						<span class="mk_sjfx_list_text mk_sjfx_list_zql">正确率${jsyt }%</span>
					</div>
				</div>

				<!--考试类型-->
				<div class="mk_kslxdiv_cont" style="display:none;" id="div_reckon_catalogtype">
					<div class="mk_kslx_cont">
						<span class="mk_kslx_text">单项选择题</span> 
						<span style="width: 570px;float: left;height: 35px;border: 1px solid #5dc2f6;display: inline-block;">
							<label style="width: ${danxw}px;float: left;height: 35px;background:#5dc2f6;display: inline-block;"></label> 
						</span> 
						<span class="mk_kslx_bfb">${danx}%</span>
					</div>
					<div class="mk_kslx_cont">
						<span class="mk_kslx_text">多项选择题</span> 
						<span style="width: 570px;float: left;height: 35px;border: 1px solid #ee7ba4;display: inline-block;">
							<label style="width: ${duoxw}px;float: left;height: 35px;background:#ee7ba4;display: inline-block;"></label> 
						</span> 
						<span class="mk_kslx_bfb_a">${duox}%</span>
					</div>
					<div class="mk_kslx_cont">
						<span class="mk_kslx_text">判断题</span> 
						<span style="width: 570px;float: left;height: 35px;border: 1px solid #c9b89c;display: inline-block;">
							<label style="width: ${jugdew}px;float: left;height: 35px;background:#c9b89c;display: inline-block;"></label> 
						</span> 
						<span class="mk_kslx_bfb_b">${jugde}%</span>
					</div>
					<div class="mk_kslx_cont">
						<span class="mk_kslx_text">填空题</span> 
						<span style="width: 570px;float: left;height: 35px;border: 1px solid #76cb28;display: inline-block;">
							<label style="width: ${fillw}px;float: left;height: 35px;background: #76cb28;display: inline-block;"></label> 
						</span> 
						<span class="mk_kslx_bfb_c">${fill}%</span>
					</div>
					<div class="mk_kslx_cont">
						<span class="mk_kslx_text">简答题</span> 
						<span style="width: 570px;float: left;height: 35px;border: 1px solid #d66be6;display: inline-block;">
							<label style="width: ${jdw}px;float: left;height: 35px;background: #d66be6;display: inline-block;"></label> 
						</span> 
						<span class="mk_kslx_bfb_d">${jd}%</span>
					</div>
				</div>

				<!--进步曲线-->
				<div class="mk_jbqx_cont" style="display:none;" id="div_reckon_day">
					<div class="mk_jdqx_img">
						<img src="jsp/imgs/client/133.jpg" />
					</div>
				</div>

			</div>
			<div class="mk_tims_db">
				<img src="jsp/imgs/client/115.jpg" />
			</div>

			<div style="clear:both;"></div>
		</div>
	</div>
	<!--底部-->
	<jsp:include page="../../top/bot.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			$(":radio").change(function(){
				var val = $(this).val();
				$("#div_reckon_type").hide();
				$("#div_reckon_catalogtype").hide();
				$("#div_reckon_day").hide();
				if(val == 1){
					$("#div_reckon_type").show();
				}else if(val == 2){
					$("#div_reckon_catalogtype").show();
				}else{
					$("#div_reckon_day").show();
				}
			});
		});
	</script>
</body>
</html>
