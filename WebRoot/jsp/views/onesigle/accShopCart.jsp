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
<title>个人中心 - 帐户--我的购物车</title>
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
			<div class="div_table tjh_info" style="margin-top:80px;">
				<table cellspacing="0" cellpadding="0" border="0" class="table">
					<thead>
						<tr>
							<th width="300">待付款课程</th>
							<th width="150">数量</th>
							<th width="180">价格</th>
							<th width="180">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sessionScope.ShopCutMap }" var="entryMap">
						<c:set value="${entryMap.value }" var="item"></c:set>
						<c:set value="${entryMap.key }" var="shopcartid"></c:set>
						<tr>
							<td><a href="javascript:void(0);" class="jrkc">${item.nmKind } </a></td>
							<td>1</td>
							<td>现价${item.discount}元</td>
							<td>
								<a class="zh_del" href="sigle/deleteShopCart?shopcartid=${shopcartid}">删除</a>
								<a class="zh_fk" href="javascript:void(0);" onclick="click2PayOneShopCart(${shopcartid});">付款</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4"><a class="wdgwc_info" href="client/home"> 选购更多课程
									&gt;&gt; </a></td>
						</tr>
					</tfoot>
				</table>
			</div>
			
		</div>
	</div>
	<div id="go_2_shop_buy_one"></div>
	<script type="text/javascript">
		function click2PayOneShopCart(shopcartid){
			var url = '<%=basePath%>'+"sigle/subOneShopCart";
			var data = {"shopcartid":shopcartid};
			var callBack = function(back){
				if(back.status == 1){
					if(back.data){
						$("#go_2_shop_buy_one").html(back.data);
					}else{
						window.location.reload();
					}
				}else{
					alert(back.msg);
				}
			};
			$.post(url,data,callBack,"json");
		};
	</script>
</body>
</html>