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
<title>体验模考主页_智能组选</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="体验模考主页_智能组选" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" language="JavaScript">
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
				<a href="client/assistITMS"><span class="mk_tims_list_xxk">通关模考</span>
				</a> <a href="client/practiceTopics"><span class="mk_tims_list_xxk">题型练习</span>
				</a> <a href="client/errorTrains"><span class="mk_tims_list_xxk">错题训练</span>
				</a> <a href="client/autoSelecTopics"><span
					class="mk_tims_list_xxkhover">智能组选</span> </a> <a
					href="client/doNewTopics"><span class="mk_tims_list_xxk">只做新题</span>
				</a> <a href="client/trainKnowledges"><span class="mk_tims_list_xxk">知识点训练</span>
				</a> <a href="client/analysisDatas"><span class="mk_tims_list_xxk"
					style="border-right:1px solid #cdcdcd;">数据分析</span> </a>
				<div class="mk_ctxl_nr">
					<div class="mk_znzx_top">系统已按真题题型及题量自动生成一份模考试题</div>
					<div class="mk_znzx_cxzx"
						onmouseout="this.className='mk_znzx_cxzx'"
						onmouseover="this.className='mk_znzx_cxzx_hover'">
						<a href="javascript:void(0);">重新组选</a>
					</div>
					<div class="mk_znzx_nr">
						<div class="mk_znzx_right">
							<span class="mk_znzx_list">1、单项选择题（共${auto.num4radio }题）</span> <span
								class="mk_znzx_list_d">2、多项选择题（共${auto.num4chbox }题）</span> <span
								class="mk_znzx_list_p">3、判断题（共${auto.num4judge }题）</span> <span
								class="mk_znzx_list_p">4、填空题（共${auto.num4fill }题）</span> <span
								class="mk_znzx_list_p">5、简答题（共${auto.num4jd }题）</span> <span
								class="mk_znzx_list_l">6、论述题（共${auto.num4luns }题）</span> <span
								class="mk_znzx_list_a">7、案例分析题（共0题）</span>
						</div>
					</div>






				</div>
				<div class="mk_tims_szzql mk_ctxl_zgts">
					共选择<label style="color:#ff9600; font-size:24px;">${all}</label>
				</div>
				<div class="mk_tims_bottom">取消</div>
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
	<!--底部-->
	<jsp:include page="../../top/bot.jsp"></jsp:include>
	<jsp:include page="newQuestion4Exam.jsp" />
	<script type="text/javascript" language="JavaScript">
		function click4Sure(){
			$("#numRadio2Exam").val("${auto.num4radio}");
			$("#numChbox2Exam").val("${auto.num4chbox}");
			$("#numJudge2Exam").val("${auto.num4judge}");
			$("#numFill2Exam").val("${auto.num4fill}");
			$("#numJDa2Exam").val("${auto.num4jd}");
			$("#numLunsu2Exam").val("${auto.num4luns}");
			$("#go2Examing").submit();
		};
	</script>
</body>
</html>
