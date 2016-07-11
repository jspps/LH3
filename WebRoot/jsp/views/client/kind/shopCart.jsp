<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>购物车</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="购买课程" />
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
	<jsp:include page="../top/head.jsp"></jsp:include>

	<!--内容-->
	<div class="zxbd_cont">
		<div class="zxbd_mbx">
			<a href="client/home">首页</a>&nbsp;/&nbsp;购物车
		</div>
		<div class="gmkc_nr_cont">
			<div class="gmkc_jdt">
				<img src="jsp/imgs/client/89.jpg" />
			</div>
			<div class="gmkc_nr_div">
				<div class="gmkc_zt_div">
					<c:forEach items="${sessionScope.ShopCutMap }" var="entryMap">
						<c:set value="${entryMap.value }" var="item"></c:set>
						<c:set value="${entryMap.key }" var="shopcartid"></c:set>
						<div>
							<div class="gmkc_list_div">
								<div class="gmkc_list_img">
									<a href="javascript:void(0);"><img
										src="${item.product.imgurl}" width="123" height="96" /> </a>
								</div>
								<div class="gmkc_list_right">
									<span class="gmkc_list_name">
										<a href="javascript:void(0);">${item.nmKind }</a> 
									</span> 
									<span class="gmkc_list_cj">层次：${item.course.nmLevel }</span> 
									<span class="gmkc_list_km">科目：${item.course.nmSub }</span>
									<span class="gmkc_list_jg" style="color:#979797;">
										价格${item.price}元										
									</span> 
									<!-- 
									<span class="gmkc_list_jg">
										现价${item.discount}元&nbsp;&nbsp;
										<label style="text-decoration:line-through; color:#979797;">原价${item.price}元</label>
									</span>
									 -->
									<span class="gmkc_list_cj" style="width:80px; color:#979797;">【${item.course.nmArea}】</span>
									<div class="gmkc_list_bhlb">
										<span class="gmkc_list_bhlbcont">
											<input name=""	type="checkbox" value="" checked="checked" disabled="disabled" />&nbsp;${item.nmKClass }
										</span>
										<span class="gmkc_list_jg" style="float: right;margin-right: 55px;">
											推荐号优惠价:${item.discount}元
										</span>
									</div>
									<div class="sckc_list_tjico gmkc_list_ico">
										<span class="sckc_list_icogz">${item.visit}</span> <span
											class="sckc_list_icodz">${item.praise}</span> <span
											class="sckc_list_icofx">${item.buycount}</span>
									</div>
									<div class="gmkc_list_bottomcont">
										<div class="gmkc_list_bottom"
											onmouseout="this.className='gmkc_list_bottom'"
											onmouseover="this.className='gmkc_list_bottom_hover'"
											onclick="delShopCart(${shopcartid})">删除</div>
									</div>
								</div>
							</div>
							<div style="clear:both;"></div>
						</div>
					</c:forEach>

				</div>
				<div class="gmkc_sytjh_div">
					<div class="gmkc_sythh_style" style="cursor: pointer;border: 1px dashed #0089f2;">
						&nbsp;
						<c:choose>
						<c:when test="${sessionScope.ShopCartCode != null && sessionScope.ShopCartCode != '' }">
							<input name="recommendCode" type="checkbox" checked="checked" disabled="disabled" style="vertical-align:middle;"/>
						</c:when>
						<c:otherwise>
							<input name="recommendCode" type="checkbox" disabled="disabled" style="vertical-align:middle;"/>
						</c:otherwise>
						</c:choose>
						<label style="color:#ff0609;">推荐号优惠价:${sumDis}元</label>
					</div>
					<div class="gmkc_jgsm_style">
						<span style="float:left;">
							<label style="color:#F00;">${lens}</label>件商品&nbsp;&nbsp;&nbsp;共计：${sumPri}元
						</span>
						<span class="mgkc_fk_div">
							<label style="font-size:14px; color:#616161;">实付款：</label>
							<label id="cur_price_4_pay">
							<c:choose>
							<c:when test="${sessionScope.ShopCartCode != null && sessionScope.ShopCartCode != '' }">
								${sumDis }
							</c:when>
							<c:otherwise>
								${sumPri }
							</c:otherwise>
							</c:choose>
							</label>
							元
						</span>
					</div>
				</div>
			</div>
			<div class="mgkc_fk_bottom"
				onmouseout="this.className='mgkc_fk_bottom'"
				onmouseover="this.className='mgkc_fk_bottom_hover'"
				id="submint_shopcut">提交订单</div>

			<div style="clear:both;"></div>
		</div>
	</div>

	<!--底部-->
	<jsp:include page="../top/bot.jsp"></jsp:include>

	<script type="text/javascript">
		// 设置事件不冒泡
		function setEventBubbling(event){
			if(!event || typeof event != 'object' || event == null){return;}
			if(event.stopPropagation){event.stopPropagation();}
			else if(event.bubbles){event.bubbles = false;}
		}
		
		// 取消与事件关联的默认动作
		function cancelEventDefaultAction(event){
			if(!event || typeof event != 'object' || event == null){return;}
			if(event.preventDefault){event.preventDefault();}
			else if(event.cancelable ){event.cancelable = false}
		}
		
		//删除购物车物品
		function delShopCart(shopcartid) {
			var r = window.confirm("确定要删除吗？");
	        if(r) {
	        	window.location.href = '<%=basePath%>'+"client/deleteShopCart?shopcartid="+shopcartid;
	        };
		};
		
		function click4Recommend(boxCheck){
			var shopcartid = boxCheck.val();
			var isChecked = boxCheck.is(":checked");
			isChecked = !isChecked;
			if(isChecked){
				getRecommendCodeHtml(shopcartid);
			}else{
				cancelRecommendCode(shopcartid);
			};
		}
		
		$(document).ready(function() {
			$("#submint_shopcut").click(function() {
				window.location.href = '<%=basePath%>'+"client/submitShopCart2Buy";
			});
			
			$("div.gmkc_sythh_style :checkbox").click(function(event){
				var that = $(this);
				click4Recommend(that);
				// cancelEventDefaultAction(event);
			});
			
			$("div.gmkc_sythh_style").click(function() {
				var that = $(this);
				var box = that.children(":checkbox");
				click4Recommend(box);
			});
		});
		
		// 取得推荐界面
		function getRecommendCodeHtml(shopcartid){
			var url = '<%=basePath%>'+"client/goRecommendCodeUI";
			var data = {"shopcartid":shopcartid};
			var jqAlert = $("#div_4_recommend_code");
			if(jqAlert.length == 0){
				jqAlert = $("<div id='div_4_recommend_code'>");
				$(document.body).append(jqAlert);
			};
			var callFun = function(back){
				jqAlert.html(back);
			};
			$.post(url,data,callFun);
		};
		
		function cancelRecommendCode(shopcartid){
			var url = '<%=basePath%>'+"client/modifyShopCartRdCode";
			var data = {};
			data.shopcartid = shopcartid;
			var callBack = function(back){
				$("#cur_price_4_pay").html("${sumPri }");
			};
			$.post(url,data,callBack,"json");
		}
	</script>
</body>
</html>
