<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>最新模考榜单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="最新模考榜单" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="top/head.jsp"></jsp:include>

	<!--内容-->
	<div class="zxbd_cont" >
		<!-- <div class="zxbd_mbx"><a href="javascript:void(0);">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0);">职业类</a>&nbsp;/&nbsp;人力资源</div> -->
		<div class="zxbd_title_div">
			<div class="zxbd_title_style">
				<img src="jsp/imgs/client/81.jpg" />
			</div>
		</div>
		<div class="zxbd_ico_div" style="margin-bottom:30px;">
			<div class="zxbd_xlcd_div">
				<form action="client/newTestList" method="post">
				<jsp:include page="../stepCourses.jsp" />
				<input type="submit" value="查询" />
				</form>
			</div>
		</div>
	</div>

		<div class="zxdb_bd_cont">
 			<div class="zxbd_zt_div">
				<c:forEach items="${getList }" var="item" varStatus="s">
					<div class="zxbd_list_div"
						onmouseout="this.className='zxbd_list_div'"
						onmouseover="this.className='zxbd_list_div_hover'">
						<div class="zxbd_nr_div" style="width: 170px;margin-left: 10px;">
						 	<div style="height: 30px;">${item.custName }</div>
							<div style="height: 60px;">${item.exTpName }</div> 
							<div style="color:#ff4701;"> ${item.score } </div>
							<div>${item.lastime }</div>
						</div>
						<div class="zxbd_pm_sz" style="margin-top: 33px;"> ${s.count } </div>
					</div>
				</c:forEach>
			</div>
			<div style="clear:both;"></div>
		</div>

	<script type="text/javascript">
		$(document).ready(function(){
			$("select").css({"width":"100px", "height":"24px"," color":"#6b6b6b"});
		});
	</script>
	<!--底部-->
	<jsp:include page="top/bot.jsp"></jsp:include>
</body>
</html>
