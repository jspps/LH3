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
<title>在线模考体验</title>
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
		<div class="tymk_top_title" style="width: 800px;">${name }</div>
		<div id="sucai">
			试卷说明：<br />
			${sessionScope.CurExam.descstr }
			<p />
		</div>
	</div>

	<div class="tymk_cont_style">
		<div class="tymk_left_cont">
			<div class="tymk_left_topimg">
				<span class="tymk_sj_div" id="timer_lab_val"></span> 
				<span class="tymk_zt_div">未答</span>
				<span class="tymk_zt_div_yd">已答</span><span class="tymk_zt_div_dd">待定</span>
			</div>
			<div class="tymk_left_dtk" id="left_question_titles">
				<!-- 左边试卷题目号 -->
				<jsp:include page="left.jsp" flush="true"></jsp:include>
			</div>
			
			
			<div class="tymk_left_bottom"
				onmouseout="this.className='tymk_left_bottom'"
				onmouseover="this.className='tymk_left_bottom_hover'"
				id="submitAnswer">
			</div>
			
		</div>

		<div class="tymk_right_cont" id="tymk_right_cont_html">
			<!-- 右边当前题的内容 -->
			<jsp:include page="right.jsp" flush="true"></jsp:include>
		</div>
		
		<div style="clear:both;"></div>
	</div>
	<!--底部-->
	<jsp:include page="../top/bot.jsp"></jsp:include>
	
	<script type="text/javascript">
		function hrefUrl(titNum, curOptid,curType,isAnswer) {
			var nxtOptId = 0;
			isAnswer = !!isAnswer;
			var parOptId = curOptid;
			var parType = curType;
			var parIsAnswer = isAnswer;
			if(!isAnswer){
				curOptid = $("#curOptId").val();
				curType = $("#curOptType").val();
				if(curType == "7"){
					curType = $("#curOptGid").val();	
				}
				isAnswer = true;
			}
			
			nxtOptId = $("#tymk_zt_div_" + curOptid).next().attr('npid');
			if (nxtOptId == null || nxtOptId == '' || nxtOptId == undefined) {
				nxtOptId = $("#tymk_zt_div_" + curOptid).next().next().attr('npid');
			}
			
			if (nxtOptId == null || nxtOptId == '' || nxtOptId == undefined) {
				nxtOptId = 0;
			}
			
			var datedaan = "";
			var type = parseInt(curType,10);
			switch(type){
				case 1:
					// 单选题答案
					$(".tymk_right_xx_radio").each(function() {
						if ($(this).is(":checked")) {
							datedaan = $(this).val();
						}
					});
					break;
				case 2:
					// 多选题答案
					$(".tymk_right_xx_checkbox").each(function(){
					     if($(this).is(":checked")) {
					    	 datedaan += $(this).val()+",";
					     }
					 });
					break;
				case 3:
					// 判断题答案
					$(".tymk_right_xx_panduan").each(function(){
					     if($(this).is(":checked")) {
					    	 datedaan = $(this).val();
					     }
					 });
					break;
				case 4:
					// 填空题答案
					datedaan = $(".tiankongdaan").val();
					break;
				case 5:
					// 简答题答案
					datedaan = $(".jiandadaan").val();
					break;
				case 6:
					// 论述题答案
					datedaan = $(".jiandadaan").val();
					break;
			};
			
			jQuery.post("client/optquestionDes", {
				'curOptId' : curOptid,
				'curType' : type,
				'titNum' : titNum,
				'nxtOptId' : nxtOptId,
				'isAnswer':isAnswer,
				'curExamLen':'${curExamLen}',
				'datedaan' : datedaan
			}, function(data) {
				if(parIsAnswer){
					$("#tymk_right_cont_html").html(data);
					exceCallBack();
				}else{
					getRtCont(parOptId, parType, titNum, nxtOptId, parIsAnswer, datedaan);
				}
			});
		};

		function getRtCont(curOptid,curType,titNum,nxtOptId,isAnswer,datedaan){
			var data = {
				'curOptId' : curOptid,
				'curType' : curType,
				'titNum' : titNum,
				'nxtOptId' : nxtOptId,
				'isAnswer':isAnswer,
				'curExamLen':'${curExamLen}',
				'datedaan' : datedaan
			};
			jQuery.post("client/optquestionDes",data, function(data) {
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
			var val = "";
			if(curAnswer != null){
				var curType = $("#curOptType").val();
				curType = parseInt(curType,10);
				switch(curType){
					case 1:
						// 单选题答案
						$(".tymk_right_xx_radio").each(function() {
							if($(this).val() == curAnswer){
								$(this).attr("checked",true);
							}
						});
						break;
					case 2:
						// 多选题答案
						var arry = curAnswer.split(",");
						$(".tymk_right_xx_checkbox").each(function(){
							if(arry != null && arry.length > 0){
								val = $(this).val();
								for(var i = 0; i < arry.length;i++){
									var en = arry[i];
									if(en == val){
										$(this).attr("checked",true);
									}
								}
							}
						 });
						break;
					case 3:
						// 判断题答案
						$(".tymk_right_xx_panduan").each(function(){
						    if($(this).val() == curAnswer){
								$(this).attr("checked",true);
							}
						 });
						break;
					case 4:
						// 填空题答案
						$(".tiankongdaan").val(curAnswer);
						break;
					case 5:
						// 简答题答案
						$(".jiandadaan").val(curAnswer);
						break;
				};
			}
		};
		
		/*** 考试时间倒计时 **/
		var maxtime = "${examTime * 60}"; // 时间按秒 7200s
		var tmpMaxTime = parseInt(maxtime,10);
		var timerId = -1;// 考试定时器ID
		function CountDown(){
			maxtime = parseInt(maxtime,10);
			if(maxtime>=0){  
				var minutes = Math.floor(maxtime/60);  
			 	var seconds = Math.floor(maxtime%60);  
			 	var msg = "还剩"+minutes+"分"+seconds+"秒";  
			 	$("#timer_lab_val").html(msg);  
			 	if(minutes == 5 && seconds == 0) 
			 		alert('注意，还有5分钟!');
			 	else if(minutes == 0 && seconds==30)
			 		alert('注意，还有30秒!');
			 	  
			 	--maxtime;  
			 }else{  
			 	clearInterval(timerId);
			 	if(tmpMaxTime > 0){  
				 	alert("时间到，结束!");  
				 	judgeAnswers();
			 	}
			 }  
		}
		
		function judgeAnswers(){
			var usetime = (tmpMaxTime-maxtime);
			jQuery.post('<%=basePath%>'+"client/judgeAnswers", {'curExamLen':'${curExamLen}',"timediff":maxtime}, function(back) {
				// console.info(back);
				if (back.status) {
					if (back.status == 1) {
						var url = '<%=basePath%>'+"client/saveAnswers?curExamLen=${curExamLen}&timers="+usetime;
						window.location.href = url;
					}else{
						var url = '<%=basePath%>'+"client/judgePage4Examing";
						var data = {"curExamLen":"${curExamLen}","timers":usetime,"url":"client/saveAnswers"};
						var callBack4Page = function (jugdeBack) {
							var jqAlert = $("#div_4_jugde4examing");
							if(jqAlert.length == 0){
								jqAlert = $("<div id='div_4_jugde4examing'>");
								$(document.body).append(jqAlert);
							};
							jqAlert.html(jugdeBack);
						};
						$.post(url,data,callBack4Page);
					}
				}
			}, "json");
		}
		
		$(document).ready(function(){
		  	exceCallBack();
		  	var usetime = (tmpMaxTime-maxtime);
		  	$("#submitAnswer").click(function(){
		  		judgeAnswers();		  		
		  	});
		});
	</script>
</body>
</html>
