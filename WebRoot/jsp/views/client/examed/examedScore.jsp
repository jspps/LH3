<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>成绩统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="成绩统计" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="../top/head.jsp"></jsp:include>

	<!--内容-->
	<div class="cjtj_cont_div">
		<div class="cjtj_top_div">
			<div class="cjtj_top_title">${examed.nmExam }</div>
			<div class="cjtj_top_yssm">
				用时：<p:fmtDate parttern="HH时mm分ss秒" value="${examed.costTimes }"/> <br />
				<!-- 
				 <a href="javascript:void(0);">历史成绩</a>
				  -->
			</div>
		</div>
		<div class="cjtj_nr_div">
			<div class="cjtj_nr_top">
				<span class="cjtj_nr_top_title">得分：<label
					style="color:#ff4800; font-size:24px;">${examed.score }分</label>&nbsp;&nbsp;&nbsp;&nbsp;正确率：<label
					style="color:#ff4800; font-size:24px;">${examed.avecorrectrate }%</label> </span> 
					<!-- 
					<span class="cjtj_nr_pm">本期学员中您的成绩位列第<label style="color:#ff4800;">14位</label>，您是考神哦，我好崇拜您！</span>
					 -->
			</div>
			<c:forEach items="${reckon }" var="item" varStatus="itemStatus">
			<c:set var="index" value="${ itemStatus.index + 1}"></c:set>
			<c:set var="imgIndex" value="${141+item.type }"></c:set>
			<div class="cjtj_list_cont">
				<span class="cjtj_list_ico">
					<img src="jsp/imgs/client/${imgIndex}.jpg" /> 
				</span> 
				<span class="cjtj_list_nr">
					<span class="cjtj_list_nr_text">${item.serial }、${item.bigtypes }（${item.scoreRight }分），每题${item.everScore }分，共${item.lensAll }题</span> 
					<span class="cjtj_list_nr_zqs">做对：${item.lensRight }题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;做错：${item.lensAll - item.lensRight }题 </span> 
				</span>
				<span class="cjtj_list_df">【得分${item.scoreRight }分】</span>
			</div>
			</c:forEach>
			<!-- 
			<div class="cjtj_list_cont">
				<span class="cjtj_list_ico">
					<img src="jsp/imgs/client/143.jpg" /> 
				</span> 
				<span class="cjtj_list_nr">
					<span class="cjtj_list_nr_text">二、多项选择题（${checkScoreAll }分），每题2分，共${checkLenAll }题</span> 
					<span class="cjtj_list_nr_zqs">做对：${checkLenRight }题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;做错：${checkLenAll - checkLenRight }题</span> 
				</span> 
				<span class="cjtj_list_df">【得分${checkScoreRight }分】</span>
			</div>
			<div class="cjtj_list_cont">
				<span class="cjtj_list_ico">
					<img src="jsp/imgs/client/144.jpg" /> 
				</span> 
				<span class="cjtj_list_nr">
					<span class="cjtj_list_nr_text">三、判断题（${jugdeScoreAll }分），每题2分，共${jugdeLenAll }题</span> 
					<span class="cjtj_list_nr_zqs">做对：${jugdeLenRight }题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;做错：${jugdeLenAll - jugdeLenRight }题</span>
				</span> 
				<span class="cjtj_list_df">【得分${jugdeScoreRight }分】</span>
			</div>
			 -->
			<div class="mk_tims_bottom_a_hover" style="margin-left:55px;"
				onmouseout="this.className='mk_tims_bottom_a_hover'"
				onmouseover="this.className='mk_tims_bottom_a'"
				onclick="click2SeeAnswer();">查看答卷</div>
			<div class="mk_tims_bottom_a"
				onmouseout="this.className='mk_tims_bottom_a'"
				onmouseover="this.className='mk_tims_bottom_a_hover'"
				onclick="click2RedoErrors();">重做错题</div>
			<div class="mk_tims_bottom_a"
				onmouseout="this.className='mk_tims_bottom_a'"
				onmouseover="this.className='mk_tims_bottom_a_hover'"
				onclick="click2DoNewExam();">另做新题</div>
			<div class="mk_tims_bottom_a"
				onmouseout="this.className='mk_tims_bottom_a'"
				onmouseover="this.className='mk_tims_bottom_a_hover'"
				onclick="click2ReExaming();">再考一次</div>
			<div class="mk_tims_bottom_a"
				onmouseout="this.className='mk_tims_bottom_a'"
				onmouseover="this.className='mk_tims_bottom_a_hover'"
				onclick="OnClickPrintView(${examed.examid});">
				<span>打印试卷</span>
				<form action="client/printView" name="fm_printView" id="fm_printView_${examed.examid}" method="post" target="_blank">
				<input name="unqid" value="${examed.examid}" type="hidden" />
				<input name="answerid" value="${examed.id}" type="hidden" />
				</form>
			</div>
		</div>
		<div class="cjtj_nr_buttom"></div>
		<div style="clear:both;"></div>
	</div>

	<!--底部-->
	<jsp:include page="../top/bot.jsp"></jsp:include>
	<script type="text/javascript">
		function click2SeeAnswer(){
			window.location.href = '<%=basePath%>'+"client/seeAnswer?reqType=1";
		};
		function click2ReExaming(){
			window.location.href = '<%=basePath%>'+"client/examing";
		};
		function click2RedoErrors(){
			window.location.href = '<%=basePath%>'+"client/redoErrors";
		};
		function click2DoNewExam(){
			window.location.href = '<%=basePath%>'+"client/reExaming";
		};
		function OnClickPrintView(id){
			$("#fm_printView_"+id).submit();
		};
	</script>
</body>
</html>
