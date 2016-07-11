<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 级联选择课程 -->
<select name="departid" id="departid" onchange="changeDepart();">
	<option value="-1">请选择</option>
	<c:forEach items="${adqdepartments}" var = "ent">
	<option value="${ent.did}">${ent.name}</option>
	</c:forEach>
</select>
<select name="nmMajor" id="nmMajor" onchange="changeMajor();">
	<option value="-1">专业</option>
</select>
<select name="nmLevel" id="nmLevel" onchange="changeLevel();">
	<option value="-1">层次</option>
</select>
<select name="nmSub" id="nmSub" onchange="changeSub();">
	<option value="-1">科目</option>
</select>
<select name="nmArea" id="nmArea">
	<option value="-1">考试范围</option>
</select>

<script type="text/javascript">
	function resetSelVal(type){
		if(type <= 1){
			$("#nmMajor").html('<option value="-1">专业</option>');
		}
		
		if(type <= 2){
			$("#nmLevel").html('<option value="-1">层次</option>');
		}
		
		if(type <= 3){
			$("#nmSub").html('<option value="-1">科目</option>');
		}
		
		if(type <= 4){
			$("#nmArea").html('<option value="-1">考试范围</option>');
		}
	}
	
	function changeDepart() {
		var val = $("#departid").val();
		resetSelVal(1);
		if(val == "-1"){
			return;
		}
		var url = '<%=basePath%>' + "getNmMajors";
		var data = {"departid":val};
		var callBackInFun = function(back){
			var list = back.data;
			if(list && list.length > 0){
				var lens = list.length;
				for(var i = 0; i < lens;i++){
					var mapMajor = list[i];
					$("#nmMajor").append($("<option>",{"text":mapMajor.nmMajor}));
				};
			};
		};
		$.post(url,data,callBackInFun,"json");
	};
	
	function changeMajor() {
		resetSelVal(2);
		var val = $("#departid").val();
		var nmMajor = $("#nmMajor").val();
		if(val == "-1" || nmMajor == "-1"){
			return;
		}
		
		var url = '<%=basePath%>' + "getNmLevels";
		var data = {"departid":val,"nmMajor":nmMajor};
		var callBackInFun = function(back){
			var names = back.data;
			if(names && names.length > 0){
				var lens = names.length;
				for(var i = 0; i < lens;i++){
					$("#nmLevel").append($("<option>",{"text":names[i]}));
				};
			};
		};
		$.post(url,data,callBackInFun,"json");
	};
	
	function changeLevel() {
		resetSelVal(3);
		if(val == -1){
			return;
		}
		
		var val = $("#departid").val();
		var nmMajor = $("#nmMajor").val();
		var nmLevel = $("#nmLevel").val();
		if(val == "-1" || nmMajor == "-1" || nmLevel == "-1"){
			return;
		}
		
		var url = '<%=basePath%>' + "getNmSubs";
		var data = {"departid":val,"nmMajor":nmMajor,"nmLevel":nmLevel};
		var callBackInFun = function(back){
			var names = back.data;
			if(names && names.length > 0){
				var lens = names.length;
				for(var i = 0; i < lens;i++){
					$("#nmSub").append($("<option>",{"text":names[i]}));
				};
			};
		};
		$.post(url,data,callBackInFun,"json");
	};
	
	function changeSub() {
		resetSelVal(4);
		if(val == -1){
			return;
		}
		
		var val = $("#departid").val();
		var nmMajor = $("#nmMajor").val();
		var nmLevel = $("#nmLevel").val();
		var nmSub = $("#nmSub").val();
		if(val == "-1" || nmMajor == "-1" || nmLevel == "-1" || nmSub == "-1"){
			return;
		}
		
		var url = '<%=basePath%>' + "getNmAreas";
		var data = {"departid":val,"nmMajor":nmMajor,"nmLevel":nmLevel,"nmSub":nmSub};
		var callBackInFun = function(back){
			var names = back.data;
			if(names && names.length > 0){
				var lens = names.length;
				for(var i = 0; i < lens;i++){
					$("#nmArea").append($("<option>",{"text":names[i]}));
				};
			};
		};
		$.post(url,data,callBackInFun,"json");
	};
</script>