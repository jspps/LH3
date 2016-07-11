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
<title>体验模考主页_错题训练</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="体验模考主页_题型练习" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" language="JavaScript">	
	function click2BuyKind(kindId){
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
	<jsp:include page="../../top/head.jsp" />

	<!--内容-->
	<jsp:include page="../midMenu.jsp" />
	<div>
		<div class="mk_tims_dbuttom_cont">
			<div class="mk_tims_nr">
				<a href="client/practiceTopics"> <span class="mk_tims_list_xxk">题型练习</span>
					</a> <a href="client/errorTrains"><span
					class="mk_tims_list_xxkhover">错题训练</span> </a> <a
					href="client/autoSelecTopics"><span class="mk_tims_list_xxk">智能组选</span>
				</a> <a href="client/doNewTopics"><span class="mk_tims_list_xxk">只做新题</span>
				</a> <a href="client/trainKnowledges"><span class="mk_tims_list_xxk">知识点训练</span>
				</a> <a href="client/analysisDatas"><span class="mk_tims_list_xxk"
					style="border-right:1px solid #cdcdcd;">数据分析</span> </a>
				<div class="mk_ctxl_nr" id="div_cont4txxl">
					<c:forEach items="${list }" var="item">
						<div class="mk_ctxl_listcont">
							<div class="mk_ctxl_list_nr">
								<span class="mk_ctxl_title">${item.serial}、${item.bigtypes }</span> <span class="mk_ctxl_list_cs"
									style="width:100px;"> <label class="mk_ctxt_cs_text">总题量：${item.val}</label> </span> <span class="mk_ctxl_list_cs" style="width:170px;">
									<label class="mk_ctxt_cs_text">设置数量：</label> <input type="text"
									class="mk_ctxl_input"
									onchange="inp4Change(this,${item.val },'${item.inpId}')" /> </span>
								<!--  
							<span class="mk_ctxl_list_cs" style="width:80px;">
								<label class="mk_ctxt_cs_text">小计：26分</label>
							</span>
							 -->
							</div>
							<div class="mk_ctxl_list_bottom">
								<img src="jsp/imgs/client/121.jpg" />
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="mk_tims_szzql mk_ctxl_zgts">
					共选择<label style="color:#ff9600; font-size:24px;" id="num_total">0</label>道题
				</div>
				<div class="mk_tims_bottom" onclick="click4Cancel();">取消</div>
				<div class="mk_tims_bottom_a"
					onmouseout="this.className='mk_tims_bottom_a'"
					onmouseover="this.className='mk_tims_bottom_a_hover'"
					onclick="click4Sure();">确定</div>
			</div>
			<div class="mk_tims_db">
				<img src="jsp/imgs/client/115.jpg" />
			</div>
			<div style="clear:both;"></div>
		</div>
	</div>

	<jsp:include page="newQuestion4Exam.jsp" />

	<!--底部-->
	<jsp:include page="../../top/bot.jsp" />
	<script type="text/javascript" language="JavaScript">
		function inp4Change(that,max,id) {
			var val = that.value;
			val = parseInt(val,10);
			if(isNaN(val)){
				alert("请输入数值");
				return;
			};
			val = val <= 0 ? 0 : val;
			val = val > max ? max : val;
			that.value = val;
			$("#"+id).val(val);
			recordNum();
		};
		
		function click4Cancel(){
			$("#div_cont4txxl :text").val(0);
			$("#num_total").html(0);
		};
		
		function click4Sure(){
			$("#go2Examing").submit();
		};
	</script>
</body>
</html>