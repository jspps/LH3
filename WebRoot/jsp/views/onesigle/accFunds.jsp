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
<title>个人中心 - 帐户--资金流水</title>
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
	<jsp:include page="top/account_menus.jsp" />
	
	<div class="user_content bg_fff">
		<div class="preson_info">
			<p class="wdkb_p">如果您想要再购买其他课程或者在线提问、加入交友圈……这些服务是需要使用现金另外支付的。</p>
			<div class="wdlb_info">
				<ul>
					<c:choose>
					<c:when test="${type == 1 }">
					<li class="li4" onclick="click4Type(1);" style="cursor: pointer;">
					<span>收入</span> <label style="cursor: pointer;">合计${cus.moneyAll }元</label>
					</li>
					<li class="li5" onclick="click4Type(2);" style="cursor: pointer;">
					<span>支出</span> <label style="cursor: pointer;">合计${cus.moneyAll - cus.moneyCur }元</label>
					</li>
					</c:when>
					<c:otherwise>
					<li class="li5" onclick="click4Type(1);" style="cursor: pointer;">
					<span>收入</span> <label style="cursor: pointer;">合计${cus.moneyAll }元</label>
					</li>
					<li class="li4" onclick="click4Type(2);" style="cursor: pointer;">
					<span>支出</span> <label style="cursor: pointer;">合计${cus.moneyAll - cus.moneyCur }元</label>
					</li>
					</c:otherwise>
					</c:choose>
				</ul>
			</div>

			<div class="div_table f_st  tjh_info">
				<table cellspacing="0" cellpadding="0" border="0" class="table">
					<thead>
						<tr>
							<th width="250">时间</th>
							<th width="">摘要</th>
							<th width="250">金额</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageEnt.listPages }" var="item">
							<tr>
								<td><p:fmtDate parttern="yyyy-MM-dd" value="${item.createtime }" /> </td>
								<td>${item.cont }</td>
								<td>${item.val }元</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<p:pageTag name="pageEnt" action="sigle/accFunds?type=${type }" />
		</div>
	</div>
	<script type="text/javascript">
		function click4Type(type){
			window.location.href = '<%=basePath%>' + "sigle/accFunds?type="+type;
		};
	</script>
</body>
</html>