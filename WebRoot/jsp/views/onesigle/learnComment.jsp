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
<title>个人中心 - 学习--我的点评</title>
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
		<div class="kcdp_1 xxwddp">
			<div class="kcdp_research">
				<span class="kcdp_star"></span> <i class="clear"></i>
			</div>
			<c:forEach items="${pageEnt.listPages }" var="item">
			<div class="kcdp_list">
				<span class="fl kcdp_time f_st f_w">
					<p:fmtDate parttern="yyyy-MM-dd" value="${item.createtime }" />
				</span>
				<div class="kcdp_right">
					<div class="kcdp_info">
						<span class="jj"></span>
						<h2>
							<a href="javascript:void(0);">${item.kindname }</a>
						</h2>
						<p>
							${item.appraisetext}
						</p>
					</div>
					<img width="100%" style="display:block;" src="jsp/imgs/onesigle/kc_bor.png" />
					<c:if test="${item.reback != '' }">
					<div class="kcdp_hf">
						<span class="jj"></span>
						<h2>回复：</h2>
						<div class="hfinfo">${item.reback}</div>
					</div>
					</c:if>
				</div>
			</div>
			</c:forEach>
			
			<p:pageTag name="pageEnt" action="sigle/comment" />
		</div>
	</div>
</body>
</html>