<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<form action="client/initITMSQues?itmsType=${itmsType }" method="post" id="go2Examing">
	<input id="numRadio2Exam" type="hidden" value="0" name="numRadio2Exam"/>
	<input id="numChbox2Exam" type="hidden" value="0" name="numChbox2Exam"/>
	<input id="numJudge2Exam" type="hidden" value="0" name="numJudge2Exam"/>
	<input id="numFill2Exam" type="hidden" value="0" name="numFill2Exam"/>
	<input id="numJDa2Exam" type="hidden" value="0" name="numJDa2Exam"/>
	<input id="numLunsu2Exam" type="hidden" value="0" name="numLunsu2Exam"/>
</form>
<script type="text/javascript">
function recordNum(){
	var numRadio2Exam = $("#numRadio2Exam").val();
	numRadio2Exam = parseInt(numRadio2Exam,10);
	var numChbox2Exam = $("#numChbox2Exam").val();
	numChbox2Exam = parseInt(numChbox2Exam,10);
	var numJudge2Exam = $("#numJudge2Exam").val();
	numJudge2Exam = parseInt(numJudge2Exam,10);
	var numFill2Exam = $("#numFill2Exam").val();
	numFill2Exam = parseInt(numFill2Exam,10);
	var numJDa2Exam = $("#numJDa2Exam").val();
	numJDa2Exam = parseInt(numJDa2Exam,10);
	var numLunsu2Exam = $("#numLunsu2Exam").val();
	numLunsu2Exam = parseInt(numLunsu2Exam,10);
	var all = numChbox2Exam + numFill2Exam + numJDa2Exam +numJudge2Exam + numLunsu2Exam + numRadio2Exam;
	$("#num_total").html(all);
}
</script>