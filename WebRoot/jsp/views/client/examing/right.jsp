<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div style="word-wrap:break-word;">
<c:choose>
	<c:when test="${curOpt.type==1}">
		<jsp:include page="question/radio.jsp" />
	</c:when>
	<c:when test="${curOpt.type==2}">
		<jsp:include page="question/checkbox.jsp" />
	</c:when>
	<c:when test="${curOpt.type==3}">
		<jsp:include page="question/judge.jsp" />
	</c:when>
	<c:when test="${curOpt.type==4}">
		<jsp:include page="question/fill.jsp" />
	</c:when>
	<c:when test="${curOpt.type==5}">
		<jsp:include page="question/brief.jsp" />
	</c:when>
	<c:when test="${curOpt.type==6}">
		<jsp:include page="question/discuss.jsp" />
	</c:when>
	<c:when test="${curOpt.type==7}">
		<c:choose>
		<c:when test="${curOpt.gid == 1}">
			<jsp:include page="question/radio.jsp" />
		</c:when>
		<c:when test="${curOpt.gid == 2}">
			<jsp:include page="question/checkbox.jsp" />
		</c:when>
		<c:when test="${curOpt.gid == 3}">
			<jsp:include page="question/judge.jsp" />
		</c:when>
		<c:when test="${curOpt.gid == 4}">
			<jsp:include page="question/fill.jsp" />
		</c:when>
		<c:when test="${curOpt.gid == 5}">
			<jsp:include page="question/brief.jsp" />
		</c:when>
		<c:when test="${curOpt.gid == 6}">
			<jsp:include page="question/discuss.jsp" />
		</c:when>
		</c:choose>
	</c:when>
</c:choose>

<input type="hidden" id="curOptId" value="${curOpt.optid}" />
<input type="hidden" id="curOptType" value="${curOpt.type}" />

<script type="text/javascript">
	mapAnswer = ${json};
	mapScores = ${json4score};
	
	// 弹出纠错界面
	function getCorrectHtml(questid){
		var url = '<%=basePath%>'+"client/correct4Alert";
		var data = {};
		data.questid = questid;
		var jqAlert = $("#div_4_alert_correct");
		if(jqAlert.length == 0){
			jqAlert = $("<div id='div_4_alert_correct'>");
			$(document.body).append(jqAlert);
		};
		var callFun = function(back){
			jqAlert.html(back);
		};
		$.post(url,data,callFun);
	};
	
	// 弹出提示框是否花费考币考试
	function getCostKbiHtml(){
		var url = '<%=basePath%>'+"client/costKbi4Alert";
		var data = {"name":"${name}"};
		var jqAlert = $("#div_4_alert_costKbi");
		if(jqAlert.length == 0){
			jqAlert = $("<div id='div_4_alert_costKbi'>");
			$(document.body).append(jqAlert);
		};
		var callFun = function(back){
			jqAlert.html(back);
		};
		$.post(url,data,callFun);
	};
	
	var costStatus = "${sessionScope.Status4Examing}";
	costStatus = parseInt(costStatus,10);
	if(isNaN(costStatus) || costStatus != 1){
		getCostKbiHtml();
	};
	
	
		
	// 弹出我要提问界面
	function getPutQuesHtml(questid){
		var blStr = "${sessionScope.Customer != null}";
		var url = '<%=basePath%>'+"client/getPutQuesHtml";
		if (blStr == "true") {
			var data = {};
			data.questid = questid;
			var jqAlert = $("#div_4_alert_putques");
			if(jqAlert.length == 0){
				jqAlert = $("<div id='div_4_alert_putques'>");
				$(document.body).append(jqAlert);
			};
			var callFun = function(back){
				jqAlert.html(back);
			};
			$.post(url,data,callFun);
		}else{
			alert("你没登录不能提交问题!");
			// getLoginHtml(url+"?questid="+questid);
		}
	};
	
	$(document).ready(function(){
		$("span.tymk_right_jcwz a").click(function(){
			getPutQuesHtml($(this).attr("title"));
		});
	});
</script>

<div style="clear:both;"></div>
</div>