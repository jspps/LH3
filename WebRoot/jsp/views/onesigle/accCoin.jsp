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
<title>个人中心 - 帐户--我的考币</title>
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
			<p class="wdkb_p">您购买学习账号后系统将自动为您充入相应数量的学币，主要用于在线学习，每种学习课程对学币有不同的要求，如果您账户里有足够的学币，就可以开始学习，否则需要购买。</p>
			<div class="wdlb_info">
				<ul>
					<c:choose>
					<c:when test="${type == 1 }">
					<li class="li1" onclick="click4Type(1);" style="cursor: pointer;">
						<span>购买记录</span> <label style="cursor: pointer;">${cus.kbiAll }</label>
					</li>
					<li class="li2" onclick="click4Type(2);" style="cursor: pointer;">
						<span>使用记录</span> <label style="cursor: pointer;">${cus.kbiAll - cus.kbiUse }</label>
					</li>
					</c:when>
					<c:otherwise>
					<li class="li2" onclick="click4Type(1);" style="cursor: pointer;">
						<span>购买记录</span> <label style="cursor: pointer;">${cus.kbiAll }</label>
					</li>
					<li class="li1" onclick="click4Type(2);" style="cursor: pointer;">
						<span>使用记录</span> <label style="cursor: pointer;">${cus.kbiAll - cus.kbiUse }</label>
					</li>
					</c:otherwise>
					</c:choose>
					<li class="li3" onclick="click3BuyKbi();">
					<a href="javascript:void(0);" class="ljgm"></a>
					<label>您还剩【${cus.kbiUse }】个考币</label>
					</li>
				</ul>
			</div>

			<div class="div_table f_st  tjh_info">
				<table cellspacing="0" cellpadding="0" border="0" class="table">
					<thead>
						<tr>
							<th width="300">时间</th>
							<th width="350">描述</th>
							<th width="160">考币数量</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageEnt.listPages }" var="item">
							<tr>
								<td><p:fmtDate parttern="yyyy-MM-dd" value="${item.createtime }" /> </td>
								<td>${item.cont }</td>
								<td>${item.val }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<p:pageTag name="pageEnt" action="sigle/accCoin?type=${type}" />
		</div>
	</div>
	<script type="text/javascript">
		function click4Type(type){
			window.location.href = '<%=basePath%>' + "sigle/accCoin?type="+type;
		};
		
		function click3BuyKbi(){
			var isLogin = ("${sessionScope.Customer != null}" == "true");
			var url = '<%=basePath%>';
			if(isLogin){
				url += "client/req4BuyKbi";
				var data = {};
				var callBack = function(backData){
					var jqAlert = $("#div_4_kbi");
					if(jqAlert.length == 0){
						jqAlert = $("<div id='div_4_kbi'>");
						$(document.body).append(jqAlert);
					};
					jqAlert.html(backData);
				};
				$.post(url,data,callBack);
			}else{
				getLoginHtml("/");
			};
		};
	</script>
</body>
</html>