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
<title>个人中心 - 学习--推荐课程</title>
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

	<!--内容-->
	<jsp:include page="top/learn_menus.jsp" />
	
	<div class="user_content bg_fff">
		<div class="preson_info">
			<div class="div_table f_st xxmk_info">
				<table cellspacing="0" cellpadding="0" border="0" class="table">
					<thead>
						<tr>
							<th width="">分类</th>
							<th width="200">专业</th>
							<th width="100">层次</th>
							<th width="180">科目</th>
							<th width="140">购买</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageEnt.listPages}" var="item">
						<tr>
							<td>${item.coures.adqdepartmentFkDepartid.name}</td>
							<td>${item.coures.nmMajor}</td>
							<td>${item.coures.nmLevel}</td>
							<td>${item.coures.nmSub}</td>
							<td><a href="javascript:void(0);" class="jrkc" val="${item.kindid}" onclick="click2BuyKind(this);">购买</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<p:pageTag name="pageEnt" action="sigle/learnRecommend" />
		</div>
	</div>
	<form action="client/buyLessonsPage" id="fm_kind" method="post">
		<input type="hidden" value="0" id="kind_kindId" name="kind_kindId" />
	</form>
	<script type="text/javascript">
		function click2BuyKind(that){
			var val = that.getAttribute("val");
			val = parseInt(val,10);
			if(isNaN(val)){
				alert("该课程没有响应的套餐!");
				return;
			}
			$("#kind_kindId").val(val);
			$("#fm_kind").submit();
		};
	</script>
</body>
</html>