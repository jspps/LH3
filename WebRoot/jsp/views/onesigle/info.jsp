<%@ page language="java" pageEncoding="UTF8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="page" %>

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
<title>个人中心 - 消息</title>
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
	<div class="user_content bg_fff f_st">
		<div class="kcdp_1 preson_xx">
			<div class="kcdp_research">
				<span class="kcdp_star"></span> <i class="clear"></i>
			</div>
			<c:forEach items="${pageEnt.listPages}" var="ent" varStatus="">
				<div class="kcdp_list">
					<span class="fl kcdp_time f_st f_w">尚学在线</span>
					<div class="kcdp_right">
						<div class="kcdp_info">
							<span class="jj"></span>
							<h2><page:fmtDate parttern="yyyy-MM-dd HH:mm:ss" value="${ent.createtime}" /> </h2>
							<p>${ent.description}</p>
						</div>
						<img width="100%" style="display:block;" src="jsp/imgs/onesigle/kc_bor.png" />
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script>
		$(function() {
			$(".kcdp_list").hover(function() {
				$(this).addClass("kcdp_list_hover");
			}, function() {
				$(this).removeClass("kcdp_list_hover");
			});
		});
	</script>
</body>
</html>