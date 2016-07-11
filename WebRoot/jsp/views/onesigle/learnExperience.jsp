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
<title>个人中心 - 学习--已购课程</title>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/onesigle_style.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/usermanager.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/onesigle/common.js"></script>
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/onesigle/user_common.js"></script>
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
							<th width="350">名称</th>
							<th width="80">RMB</th>
							<th width="80">套餐考币</th>
							<th width="100">截止期限</th>
							<th width="70">进入考场</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageEnt.listPages }" var="item">
							<tr>
								<td>${item.kindEn.nmProduct}${item.kindEn.nmKClass}</td>
								<td>${item.kindEn.discount}元</td>
								<td>${item.kindEn.kbi}</td>
								<td>
									<c:choose>
										<c:when test="${item.status == 1 }">
											${item.validity}天
										</c:when>
										<c:otherwise>
											<p:fmtDate parttern="yyyy-MM-dd" value="${item.validtime}" />
										</c:otherwise>
									</c:choose>
								</td>
								<td><a href="javascript:void(0)" class="jrkc jrkc_1"
									onclick="click2TestExam(${item.kindEn.id});">进入考场</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<p:pageTag name="pageEnt" action="sigle/learnExperience" />
		</div>
		<form action="client/knowledgePoints" method="post"
			id="fm_lb2examKind">
			<input type="hidden" value="0" id="examin_kindId"
				name="examin_kindId" />
		</form>
	</div>
	<script type="text/javascript">
		function click2TestExam(kindId) {
			$("#examin_kindId").val(kindId);
			$("#fm_lb2examKind").submit();
		};
	</script>
</body>
</html>