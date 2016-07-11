<%@ page language="java" pageEncoding="UTF8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>个人中心 - 学习--模考记录</title>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/onesigle_style.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/usermanager.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="jsp/js/onesigle/common.js"></script>
<script type="text/javascript" src="jsp/js/onesigle/user_common.js"></script>
</head>

<body class="user_bg">
	<!--头部-->
	<jsp:include page="top/head.jsp" />
	
	<div>
		<!--内容-->
		<jsp:include page="top/learn_menus.jsp" />
		
		<div class="user_content bg_fff">
			<div class="preson_info">
				<div class="div_table f_st xxmk_info">
					<table cellspacing="0" cellpadding="0" border="0" class="table">
						<thead>
							<tr>
								<th width="150">考题名字</th>
								<th width="85">考试时间</th>
								<th width="80">模考次数</th>
								<th width="80">平均正确率</th>
								<th width="85">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageEnt.listPages }" var="item">
							<tr>
								<td>${item.nmExam }</td>
								<td><p:fmtDate parttern="yyyy-MM-dd" value="${item.lasttime }"/></td>
								<td>${item.num }次</td>
								<td>${item.avecorrectrate }%</td>
								<td><a href="javascript:void(0);" class="jrkc" onclick="click2IntoExam(${item.kindid},${item.examid });">进入考场</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<p:pageTag name="pageEnt" action="sigle/examRecord" />

			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
	<form action="sigle/go2Exam" method="post" id="fm_lb2examKind">
		<input type="hidden" value="0" id="examid" name="examid" />
		<input type="hidden" value="0" id="examin_kindId" name="examin_kindId" />
	</form>
	<script type="text/javascript">
		function click2IntoExam(kindid,examid){
			$("#examid").val(examid);
			$("#examin_kindId").val(kindid);
			$("#fm_lb2examKind").submit();
		};
	</script>
</body>
</html>