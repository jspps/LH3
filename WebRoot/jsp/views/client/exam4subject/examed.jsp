<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>在线模考</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="在线模考体验" /> 
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="jsp/js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="jsp/js/lock.js"></script>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="../top/head.jsp"></jsp:include>

	<!--内容-->
	<div id="tymk_cont_top">
		<img src="jsp/imgs/client/164.jpg" />
	</div>

	<div class="tymk_top_div">
		<div class="tymk_top_title" style="width: 800px;">${sessionScope.CurExam.name }</div>
		<div id="sucai">
			试卷说明：<br />
			${sessionScope.CurExam.descstr }
			<p />
		</div>
	</div>

	<div class="tymk_cont_style">
		<div class="tymk_left_cont">
			<div class="tymk_left_topimg">
	        	<span class="tymk_djjx_div">答卷解析</span>
	            <span class="tymk_zt_div">未答</span>
	            <span class="tymk_zt_div_yd">已答</span>
	            <span class="tymk_zt_div_dd">待定</span>
	        </div>
			<div class="tymk_left_djjx" id="left_question_titles">
				<!-- 左边试卷题目号 -->
				<jsp:include page="../examing/left.jsp" flush="true"></jsp:include>
			</div>
			<div id="submitAnswer" class="tymk_left_bottom_ck" onmouseout="this.className='tymk_left_bottom_ck'" onmouseover="this.className='tymk_left_bottom_ck_hover'"></div>
		</div>

		<div class="tymk_right_ajjxcont" style="width:800px;" id="tymk_right_cont_html">
			<!-- 右边当前题的内容 -->
			<jsp:include page="../examing/right.jsp" flush="true"></jsp:include>
		</div>
		<div style="clear:both;"></div>
	</div>
	<div style="clear:both;"></div>
	<!--底部-->
	<jsp:include page="../top/bot.jsp"></jsp:include>
	
	<script type="text/javascript">
		function hrefUrl(titNum, curOptid,curType,isAnswer,isLeft) {
			var nxtOptId = 0;
			isAnswer = false;
			isLeft = !!isLeft;
			
			nxtOptId = $("#tymk_zt_div_" + curOptid).next().attr('npid');
			if (nxtOptId == null || nxtOptId == '' || nxtOptId == undefined) {
				nxtOptId = $("#tymk_zt_div_" + curOptid).next().next().attr('npid');
			}
			
			if (nxtOptId == null || nxtOptId == '' || nxtOptId == undefined) {
				nxtOptId = 0;
			}
			
			if(!isAnswer && isLeft){
				var parOptId = curOptid;
				nxtOptId = curOptid;
				curOptid = $("#curOptId").val();
				curType = $("#curOptType").val();
			}
			
			var type = parseInt(curType,10);
			var score = 0;
			if(curType > 3){
				score = $("#self_score").val();
			}
			
			jQuery.post("client/seeQuestion4Answer", {
				'curOptId' : curOptid,
				'curType' : type,
				'titNum' : titNum,
				'nxtOptId' : nxtOptId,
				'curExamLen':'${curExamLen}',
				'isLeft':isLeft,
				'isAnswer':isAnswer,
				"reqType":0,
				"selfScore":score
			}, function(data) {
				$("#tymk_right_cont_html").html(data);
				exceCallBack();
			});
		};
		
		function exceCallBack(){
			var jqWrapLt = $("#left_question_titles");
			jqWrapLt.find("span[npid]").removeClass().addClass("tymk_list_ts_wd");
			for(var key in mapAnswer){
				var val = mapAnswer[key];
				var tit = jqWrapLt.find("span[npid='"+key+"']");
				if(val != ""){
					tit.removeClass().addClass("tymk_list_ts");
				}else{
					tit.removeClass().addClass("tymk_list_ts_dd");
				};
			};
			
			var curOptid = $("#curOptId").val();
			var curAnswer = mapAnswer[curOptid];
			if(curAnswer != null && curAnswer != ""){
				var val = "您的答案: "+curAnswer;
				$("label.tymk_djjx_nd_style").html(val);
			}else{
				$("label.tymk_djjx_nd_style").html("您未作答!");
			}
			
			var curScore = mapScores[curOptid];
			if(curScore != null && curScore != ""){
				var val = "得分："+curScore+"分";
				$("span.mk_ctxl_list_cs label.mk_ctxt_cs_text").html(val);	
			}else{
				$("span.mk_ctxl_list_cs label.mk_ctxt_cs_text").html("得分：0分");
			}
			
			var jqInpSeflScore = $("#self_score");
			jqInpSeflScore.css({"width":"100px","height":"30px","padding-left":"10px"});
			jqInpSeflScore.change(function(){
		  		var that = $(this); 
		  		var min = that.attr("min");
		  		var max = that.attr("max");
		  		var cur = that.val();
		  		cur = parseInt(cur,10);
		  		if(isNaN(cur))
		  			cur = 0;
		  		
		  		if(cur < min){
		  			that.val(min);
		  		}else if(cur > max){
		  			that.val(max);
		  		};
		  		
		  		var val = "得分："+(that.val())+"分";
				$("label.mk_ctxt_cs_text").html(val);
		  	});
		};
		
		$(document).ready(function(){
		  	exceCallBack();
		  	$("#submitAnswer").click(function(){
		  		window.location.href = '<%=basePath%>'+"client/reckonExam";
		  	});
		});
	</script>
</body>
</html>
