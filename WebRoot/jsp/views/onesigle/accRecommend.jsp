<%@ page language="java" pageEncoding="UTF8"%>
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
<title>个人中心 - 帐户--推荐号成交</title>
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
	<jsp:include page="top/account_menus.jsp" />
	
	<div class="user_content bg_fff">
		<div class="preson_info">
			<p class="tkj_cont">
				您想开通的模考模块是[全真模拟、ITMS辅助]，共需推荐13个朋友购买模考课程，您需要给朋友发送的课程推荐号是： <strong>13812345678</strong>
			</p>
			<div class="div_table tjh_info">
				<table cellspacing="0" cellpadding="0" border="0" class="table">
					<thead>
						<tr>
							<th width="180">成交时间</th>
							<th width="">购买课程</th>
							<th width="180">购买人电话</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2012-12-12</td>
							<td>企业人力资源管理师 一级理论[VIP套餐]</td>
							<td>13589477562</td>
						</tr>
						<tr>
							<td>2012-12-12</td>
							<td>企业人力资源管理师 一级理论[VIP套餐]</td>
							<td>13589477562</td>
						</tr>

						<tr>
							<td>2012-12-12</td>
							<td>企业人力资源管理师 一级理论[VIP套餐]</td>
							<td>13589477562</td>
						</tr>

						<tr>
							<td>2012-12-12</td>
							<td>企业人力资源管理师 一级理论[VIP套餐]</td>
							<td>13589477562</td>
						</tr>

						<tr>
							<td>2012-12-12</td>
							<td>企业人力资源管理师 一级理论[VIP套餐]</td>
							<td>13589477562</td>
						</tr>

						<tr>
							<td>2012-12-12</td>
							<td>企业人力资源管理师 一级理论[VIP套餐]</td>
							<td>13589477562</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3"><p class="zh_info">合计成交2个课程，只差11个模考课程的销售就可以免费给您开通您想要的模考账号了。
								</p></td>
						</tr>
					</tfoot>
				</table>
			</div>

			<div class="page">
				<a href="" class="fy">&lt;</a> <a href="#">1</a> <a href="#">2</a> <a
					href="#">3</a> <a href="#">4</a> <a href="#">5</a> <span>6</span> <a
					href="#">8</a> <a href="#">9</a> <a href="#">...</a> <a href=""
					class="fy">&gt;</a>
			</div>
		</div>
	</div>
</body>
</html>