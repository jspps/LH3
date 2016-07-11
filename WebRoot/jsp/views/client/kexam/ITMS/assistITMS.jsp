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
<title>体验模考主页_ITMS辅助</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="体验模考主页_ITMS辅助" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" language="JavaScript">
	window.onload = function() {
		var obj = document.getElementById("gotoBuy");
		obj.onclick = function() {
			window.location.href = "client/buyLessonsPage";	
		};
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
	<div class="mk_itms_top">
		<div class="mk_tims_topcont">
			<div class="mk_tims_toptitle">企业人力资源管理师一级基础知识VIP套餐</div>
			<div class="mk_tims_topbottom"
				onmouseout="this.className='mk_tims_topbottom'"
				onmouseover="this.className='mk_tims_topbottom_hover'" id="gotoBuy">
			</div>
		</div>
	</div>
	<div class="mk_tims_dbuttom">
		<div class="mk_tims_dbuttom_cont">
			<div class="mk_tims_dbuttom_div">
				<a href="client/knowledgePoints"> <span class="mk_tims_list_div"
					style="margin-left:0px;"
					onmouseout="this.className='mk_tims_list_div'"
					onmouseover="this.className='mk_tims_list_div_hover'">知识要点</span> </a>
				<a href="client/practiceChapters"> <span
					class="mk_tims_list_div_a"
					onmouseout="this.className='mk_tims_list_div_a'"
					onmouseover="this.className='mk_tims_list_div_ahover'">章节练习</span>
				</a> <a href="client/historyTopics"><span class="mk_tims_list_div_b"
					onmouseout="this.className='mk_tims_list_div_b'"
					onmouseover="this.className='mk_tims_list_div_bhover'">历年真题</span>
				</a> <a href="client/realSimulations"><span
					class="mk_tims_list_div_c"
					onmouseout="this.className='mk_tims_list_div_c'"
					onmouseover="this.className='mk_tims_list_div_chover'">全真模拟</span>
				</a> <a href="client/winTopics"><span class="mk_tims_list_div_d"
					onmouseout="this.className='mk_tims_list_div_d'"
					onmouseover="this.className='mk_tims_list_div_dhover'">绝胜压题</span>
				</a> <a href="client/assistITMS"><span class="mk_tims_list_div_e"
					onmouseout="this.className='mk_tims_list_div_e'"
					onmouseover="this.className='mk_tims_list_div_ehover'">ITMS辅助</span>
				</a>
			</div>
			<div class="mk_tims_nr">
				<a href="client/assistITMS"><span class="mk_tims_list_xxkhover">通关模考</span></a> 
				<a href="client/practiceTopics"><span class="mk_tims_list_xxk">题型练习</span></a> 
				<a href="client/errorTrains"><span class="mk_tims_list_xxk">错题训练</span></a>
				<a href="client/autoSelecTopics"><span class="mk_tims_list_xxk">智能组选</span></a> 
				<a href="client/doNewTopics"><span class="mk_tims_list_xxk">只做新题</span></a> 
				<a href="client/trainKnowledges"><span class="mk_tims_list_xxk">知识点训练</span></a>
				<a href="client/analysisDatas"><span class="mk_tims_list_xxk" style="border-right:1px solid #cdcdcd;">数据分析</span></a>
				<textarea name="" cols="" rows="" class="mk_tims_list_wbk"></textarea>
				<div class="mk_tims_szzql">
					<span class="mk_tims_szzql_title">设置每关的通关目标（正确率）：</span> <span
						class="mk_tims_szzql_list"> <label class="mk_tims_name">章节练习：</label>
						<label class="mk_tims_srk"> <input type="text"
							class="mk_tims_srk_style" /> </label> </span> <span class="mk_tims_szzql_list"
						style="margin-left:221px;"> <label class="mk_tims_name">模拟考试：</label>
						<label class="mk_tims_srk"> <input type="text"
							class="mk_tims_srk_style" /> </label> </span> <span class="mk_tims_szzql_list"
						style="margin-left:221px;"> <label class="mk_tims_name">历年真题：</label>
						<label class="mk_tims_srk"> <input type="text"
							class="mk_tims_srk_style" /> </label> </span>
				</div>
				<div class="mk_tims_bottom">取消</div>
				<div class="mk_tims_bottom_a"
					onmouseout="this.className='mk_tims_bottom_a'"
					onmouseover="this.className='mk_tims_bottom_a_hover'">确定</div>

			</div>
			<div class="mk_tims_db">
				<img src="jsp/imgs/client/115.jpg" />
			</div>






			<div style="clear:both;"></div>
		</div>
	</div>
	<!--底部-->
	<jsp:include page="../../top/bot.jsp"></jsp:include>
</body>
</html>
