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
<title>课程主页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="课程主页" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="jsp/js/dateEx.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="../top/head.jsp"></jsp:include>

	<!--内容-->
	<div class="sxkc_cont" style="padding-top:0px; padding-bottom:0px;">
		<div class="xxzx_qbkc">
			<div class="sxkc_mbx" style="margin-top:10px;">
				<a href="client/home">首页</a>&nbsp;/&nbsp;<a href="client/home">${nmDepart}</a>&nbsp;/&nbsp;${nmMajor}
			</div>
			<div onclick="go2Lhub();">
				<div class="xxzx_qbkc_logo">
					<div>
						<a href="javascript:void(0);">
							<img src="${lhlogo}" width="136" height="84" />
						</a>
					</div>
				</div>
				<div class="xxzx_name_title">
					<a href="javascript:void(0);">官方旗舰店</a>
				</div>
				<form action="client/home4Lhub" method="post" id="fm_go2lhub">
					<input type="hidden" value="${lhubid}" name="lhubid"/>
				</form>
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
	<div class="kczy_title_div">
		<div class="kczy_title_text">
			<span class="kczy_title_span">${nmProduct }</span> <span
				class="kczy_title_fw">考试范围【${nmArear }】</span>
		</div>
	</div>
	<div style="clear:both;"></div>
	<div class="kczy_cont_style">
		<div class="kczy_lb_cont">
			<c:forEach items="${list}" var="item" varStatus="itemStatus">
				<c:choose>
				<c:when test="${item.kclassid == 1}">
				<div class="kczy_lb_tca" parsKindId="${item.id}">
				</c:when>
				<c:when test="${item.kclassid == 2}">
				<div class="kczy_lb_tcb" parsKindId="${item.id}">
				</c:when>
				<c:otherwise>
				<div class="kczy_lb_tcc" parsKindId="${item.id}">
				</c:otherwise>
				</c:choose>
					<div class="kczy_lb_tcatext">${item.nmKClass }</div>
				</div>
				<!--展开内容-->
				<c:choose>
				<c:when test="${item.id == curKindId}">
				<div class="kczy_lb_nr" contId="${item.id}"">
				</c:when>
				<c:otherwise>
				<div class="kczy_lb_nr"  style="display:none;"  contId="${item.id}"">
				</c:otherwise>
				</c:choose>
					<div class="kczy_nr_cont">
						<div class="kczy_list_div" style="height:107px;">
							<c:forEach items="${item.clist }" var="mapVal">
								<span class="${mapVal.clzz }">
									${mapVal.name }
									<c:if test="${mapVal.vals != null }">
									(${mapVal.vals }套)
									</c:if>
								</span>
							</c:forEach>
						</div>
						<div class="kczy_list_div">
							<span class="kczy_jg_span">
								价格：<label style="font-size:26px; color:#ff7200; font-weight:bold;">￥${item.price }元</label>
								<label style="font-size:12px;color:#ff7200;">(使用推荐号价格：￥${item.discount }元)</label>
							</span>
							
						</div>
						<div class="sckc_list_tjico"
							style="width:377px; border:0px; height:24px;">
							<span class="sckc_list_icogz" style="margin-left:20px;"
								onmouseout="this.className='sckc_list_icogz'"
								onmouseover="this.className='sckc_list_icogz_hover'">${item.visit }</span>
							<span class="sckc_list_icodz" style="margin-left:20px;"
								onmouseout="this.className='sckc_list_icodz'"
								onmouseover="this.className='sckc_list_icodz_hover'"
								onclick="click2Praise();">${item.praise }</span>
							<span class="sckc_list_icofx" style="margin-left:20px;"
								onmouseout="this.className='sckc_list_icofx'"
								onmouseover="this.className='sckc_list_icofx_hover'">${item.buycount }</span>
						</div>
						<div class="kczy_bottom_div"
							onmouseout="this.className='kczy_bottom_div'"
							onmouseover="this.className='kczy_bottom_div_hover'"
							onclick="onclick2ShopCart(${item.id});" >
							<a href="javascript:void(0);">放入购物车</a>
						</div>
						<div class="kczy_bottom_div_hover"
							onmouseout="this.className='kczy_bottom_div_hover'"
							onmouseover="this.className='kczy_bottom_div'"
							onclick="onclick2FastBuy(${item.id});" >
							<a href="javascript:void(0);">立刻购买</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<!-- 广告 -->
		<div class="kczy_gg_div">
			<a href="javascript:void(0);"><img src="jsp/imgs/client/71.jpg" />
			</a>
		</div>

		<div class="kczy_js_cont">
			<div onclick="productselect(1);" id="kczy_js_cont_1" class="kczy_js_list_hover">课程详情</div>
			<div onclick="productselect(2);" id="kczy_js_cont_2"  class="kczy_js_list">累计评价（${lens4Appraise}）</div>
			<div onclick="productselect(3);" id="kczy_js_cont_3" class="kczy_js_list">成交记录（${lens4Order}）</div>
			<div onclick="productselect(4);" id="kczy_js_cont_4" class="kczy_js_list">消费者保障</div>
		</div>

		<div class="kczy_ljpj_cont"  id="mid_cont_product">
			 ${product.descr} 
		</div>
		<div id="product_desc" style="display: none;">
			 ${product.descr} 
		</div>
	</div>
	<!--底部-->
	<jsp:include page="../top/bot.jsp"></jsp:include>
	
	<form action="client/login4OldNew" id="fm_kind" method="post">
		<input type="hidden" id="fm_kind_id" name="kindid" value="0" />
	</form>
	
	<form action="client/shopCart" id="fm_kind2buy" method="post">
		<input type="hidden" id="kindid4buy" name="kindid" value="0" />
		<input type="hidden" id="ses4ShopCart" name="ses4ShopCart" value="0" />
	</form>
		
	<script type="text/javascript">
		function click2Jugde4IsLogined(loginUrl) {
			getLoginHtml(loginUrl);
		};
		
		function onclick2ShopCart(kindId){
			var bl = "${sessionScope.Customer == null}";
			if (bl == "true") {
				$("#fm_kind_id").val(kindId);
				$("#fm_kind").submit();
			} else {
				$("#kindid4buy").val(kindId);
				$("#ses4ShopCart").val(getTimeMSecond());
				$("#fm_kind2buy").submit();
				//click2JugdeLogin();
			}
		};
		
		function onclick2FastBuy(kindId){
			onclick2ShopCart(kindId);
		};
		
		function req4Orders() {
			var url = '<%=basePath%>' + "client/getOrders4Kind";
			var data = {"kind_kindId":"${kind_kindId}"};
			var callBack = function(back){
				$("#mid_cont_product").html(back);
			};
			$.post(url,data,callBack);
		};
		
		function req4Appraise() {
			var url = '<%=basePath%>' + "client/getDiscuss4Kind";
			var data = {"kind_kindId":"${kind_kindId}"};
			var callBack = function(back){
				$("#mid_cont_product").html(back);
			};
			$.post(url,data,callBack);
		};
		
		function req4Protection() {
			var url = '<%=basePath%>' + "client/getProtection";
			var data = {"kind_kindId":"${kind_kindId}"};
			var callBack = function(back){
				$("#mid_cont_product").html(back);
			};
			$.post(url,data,callBack);
		};
		
		function productselect(type){
			$("div.kczy_js_cont div").removeClass();
			$("div.kczy_js_cont div[id='kczy_js_cont_"+type+"']").addClass("kczy_js_list_hover");
			$("div.kczy_js_cont div[id!='kczy_js_cont_"+type+"']").addClass("kczy_js_list");
			switch(type){
			case 1:
				var txt = $("#product_desc").html();
				$("#mid_cont_product").html(txt);
			break;
			case 2:
				req4Appraise();
				break;
			case 3:
				req4Orders();
				break;
			case 4:
				req4Protection();
			break;
			};
		};

		$(document).ready(function(){
			$("div[parsKindId]").click(function(){
				var kindId = $(this).attr("parsKindId");
				$("div[contId][contId!="+kindId+"]").hide();
				$("div[contId="+kindId+"]").show(100);
			});
		});
		
		// 点赞
		function click2Praise(){
			var url = '<%=basePath%>'+"client/praiseKind";
			var data = {"kindid":"${kind_kindId}"};
			var callBack4Praise = function (back) {
				alert(back.msg);
				if(back.status == 1){
					window.location.reload();
				}
			};
			$.post(url,data,callBack4Praise,"json");
		}
		
		function go2Lhub(){
			$("#fm_go2lhub").submit();
		}
	</script>
</body>
</html>
