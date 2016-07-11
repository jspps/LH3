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
<title>筛选课程</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="筛选课程" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body style="background:#efeff1;">
	<!--导航条-->
	<jsp:include page="top/head.jsp"></jsp:include>

	<!--内容-->
	<c:set value="-1" var="chooseLhubId" />
	
	<c:set value="-1" var="chooseLevId" />
	<c:set value="-1" var="chooseSubId" />
	<c:set value="-1" var="chooseArearid" />
	
	<c:set value="-1" var="curpriceid" />
	<c:set value="-1" var="curbuycountid" />
	<c:set value="-1" var="curvisitid" />
	<c:set value="-1" var="curpraiseid" />
	
	<c:forEach items="${mapFiterPars }" var="item">
		<c:if test="${item.key == 'lhubid' }">
			<c:set value="${item.value }" var="chooseLhubId" />
		</c:if>
		<c:if test="${item.key == 'nmLevel' }">
			<c:set value="${item.value }" var="chooseLevId" />
		</c:if>
		<c:if test="${item.key == 'nmSub' }">
			<c:set value="${item.value }" var="chooseSubId" />
		</c:if>
		<c:if test="${item.key == 'nmArea' }">
			<c:set value="${item.value }" var="chooseArearid" />
		</c:if>
		<c:if test="${item.key == 'buycount' }">
			<c:set value="${item.value }" var="curbuycountid" />
		</c:if>
		<c:if test="${item.key == 'visit' }">
			<c:set value="${item.value }" var="curvisitid" />
		</c:if>
		<c:if test="${item.key == 'price' }">
			<c:set value="${item.value }" var="curpriceid" />
		</c:if>
		<c:if test="${item.key == 'praise' }">
			<c:set value="${item.value }" var="curpraiseid" />
		</c:if>
	</c:forEach>
	<div class="sxkc_cont" style="width: 975px;">
		<div class="sxkc_mbx">
			<a href="client/home">首页</a>&nbsp;/&nbsp;<a href="client/home">${nmDepart}</a>&nbsp;/&nbsp;${nmMajor}
		</div>
		<div class="sxkc_banner">
			<a href="javascript:void(0);"><img src="jsp/imgs/client/50.jpg" />
			</a>
		</div>
		<div class="sxkc_title_style">${nmMajor}</div>
		<div class="sxkc_xs_list">
			<div class="sxkc_xs_title">学习中心：</div>
			<div class="sckc_xs_nr">
				<a href="javascript:void(0);" onclick="chooseByLhub(-1)">全部</a>
				<c:forEach items="${mapLhub }" var="itemLhub">
					<span class="sckc_gf">|</span>
					<c:choose>
					<c:when test="${chooseLhubId ==  itemLhub.key}">
						<a href="javascript:void(0);" style="color:red">${itemLhub.value }</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0);" onclick="chooseByLhub(${itemLhub.key})">${itemLhub.value}</a>
					</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<!-- <div class="sxkc_xs_list">
			<div class="sxkc_xs_title">价格区间：</div>
			<div class="sckc_xs_nr">
				<a href="javascript:void(0);">全部</a><span class="sckc_gf">|</span><a
					href="javascript:void(0);">20元以下</a><span class="sckc_gf">|</span><a
					href="javascript:void(0);">20-60元</a><span class="sckc_gf">|</span><a
					href="javascript:void(0);">60-100元</a><span class="sckc_gf">|</span><a
					href="javascript:void(0);">101-1000元</a><span class="sckc_gf">|</span><a
					href="javascript:void(0);">1000元以上</a>
			</div>
		</div> -->
		<div class="sxkc_xs_list">
			<div class="sxkc_xs_title">专业层次：</div>
			<div class="sckc_xs_nr">
				<a href="javascript:void(0);" onclick="chooseByLev(-1)">全部</a>
				<c:forEach items="${mapLev }" var="itemLev">
					<span class="sckc_gf">|</span>
					<c:choose>
						<c:when test="${chooseLevId == itemLev.key}">
							<a href="javascript:void(0);" style="color:red">${itemLev.value }</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0);" onclick="chooseByLev('${itemLev.key}')">${itemLev.value }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<div class="sxkc_xs_list" style="border-bottom:0px;">
			<div class="sxkc_xs_title">学科科目：</div>
			<div class="sckc_xs_nr">
				<a href="javascript:void(0);" onclick="chooseBySub(-1)">全部</a>
				<c:forEach items="${mapSub }" var="itemSub">
					<span class="sckc_gf">|</span>
					<c:choose>
						<c:when test="${chooseSubId == itemSub.key}">
							<a href="javascript:void(0);" style="color:red">${itemSub.value }</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0);" onclick="chooseBySub('${itemSub.key}')">${itemSub.value }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		
		<div class="sckc_ss_cont">
			<div class="sckc_ss_text" style="width: 400px">
				<c:choose>
					<c:when test="${curbuycountid == 1}">
						<a href="javascript:void(0);" onclick="chooseByBuycount(2)">销量↓</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0);" onclick="chooseByBuycount(1)">销量↑</a>
					</c:otherwise>
				</c:choose>
				<span class="sckc_ss_style">|</span>
				<c:choose>
					<c:when test="${curvisitid == 1}">
						<a href="javascript:void(0);" onclick="chooseByVisit(2)">人气↓</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0);" onclick="chooseByVisit(1)">人气↑</a>
					</c:otherwise>
				</c:choose>
				<span class="sckc_ss_style">|</span>
				<c:choose>
					<c:when test="${curpriceid == 1}">
						<a href="javascript:void(0);" onclick="chooseByPrice(2)">价格↓</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0);" onclick="chooseByPrice(1)">价格↑</a>
					</c:otherwise>
				</c:choose>
				<span class="sckc_ss_style">|</span>
				<c:choose>
					<c:when test="${curpraiseid == 1}">
						<a href="javascript:void(0);" onclick="chooseByPraise(2)">口碑↓</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0);" onclick="chooseByPraise(1)">口碑↑</a>
					</c:otherwise>
				</c:choose>
				<span class="sckc_ss_style">|</span>
			</div>
			
			<div style="float:left;">
				<select id="chooseArea" class="sckc_select">
					<option id="allArea" value="-1">考试范围</option>
					<c:forEach items="${mapArea }" var="itemArea">
						<option value="${itemArea.key}">${itemArea.value}</option>
					</c:forEach>
				</select>
			</div>
			<!-- <div class="sckc_sx_cont">
				<input type="text" class="sckc_sx_input" />
				<div class="sckc_sx_bottom"
					onmouseout="this.className='sckc_sx_bottom'"
					onmouseover="this.className='sckc_sx_bottom_hover'"> -->
					<!--搜索按钮-->
				<!-- </div>
			</div>
			 -->
			
		</div>
		<c:forEach items="${pageEnt.listPages }" var="item">
			<div class="sckc_list_div">
				<div class="sckc_list_img">
					<a href="javascript:void(0);">
						<img src="${item.product.imgurl}" width="270" height="211" />
					</a>
				</div>
				<div class="sckc_list_text">
					<a href="javascript:void(0);">${item.course.nmMajor} ${item.course.nmLevel} ${item.course.nmSub} ${item.nmKClass}</a>
				</div>
				<div class="sckc_list_jg">
					${item.discount }元<span class="sckc_list_jg_style">|&nbsp;&nbsp;<label
						style="text-decoration:line-through">${item.price}元</label>
					</span>
				</div>
				<div class="sckc_list_text">
					<span class="sckc_list_bh">课程编号：SB1248</span> <span
						class="sckc_list_lb">【${item.course.nmArea }】</span>
				</div>
				<div class="sckc_list_tjico">
					<span class="sckc_list_icogz" onmouseout="this.className='sckc_list_icogz'" onmouseover="this.className='sckc_list_icogz_hover'">
						${item.visit}
					</span> 
					<span class="sckc_list_icodz" onmouseout="this.className='sckc_list_icodz'" onmouseover="this.className='sckc_list_icodz_hover'"
						onclick="click2Praise(${item.id });">
						${item.praise}
					</span>
					<span class="sckc_list_icofx" onmouseout="this.className='sckc_list_icofx'" onmouseover="this.className='sckc_list_icofx_hover'">
						${item.buycount}
					</span>
				</div>
				<div class="sckc_list_buttom" type="opt_product">
					<div class="sckc_list_buttongm"
						onmouseout="this.className='sckc_list_buttongm'"
						onmouseover="this.className='sckc_list_buttongm_hover'"
						onclick="clickProduct2Kind(${item.id })">
						<span class="sckc_list_buttomgmtext"><a
							href="javascript:void(0);">购买</a> </span>
					</div>
					<div class="sckc_list_buttonty"
						onmouseout="this.className='sckc_list_buttonty'"
						onmouseover="this.className='sckc_list_buttonty_hover'"
						onclick="clickProduct2Examine(${item.id })">
						<span class="sckc_list_buttomgmtext"><a
							href="javascript:void(0);">体验</a> </span>
					</div>
				</div>
			</div>
		</c:forEach>

		<div style="clear: both;"></div>
		<!--分页-->
		<p:pageTag name="pageEnt" action="client/chooseLessons"/>
		<div style="clear:both;"></div>
		<form action="client/historyTopics" id="fm_examine" method="post">
			<input type="hidden" value="0" id="examin_kindId" name="examin_kindId" />
		</form>
		
		<form action="client/buyLessonsPage" id="fm_kind" method="post">
			<input type="hidden" value="0" id="kind_kindId" name="kind_kindId" />
		</form>
		<form action="client/chooseLessons" id="fm_fitler" method="post">
			<input type="hidden" value="${chooseLhubId}" id="kind_lhubid" name="kind_lhubid" />
			<input type="hidden" value="${chooseLevId}" id="kind_levid" name="kind_levid" />
			<input type="hidden" value="${chooseSubId}" id="kind_subid" name="kind_subid" />
			<input type="hidden" value="${chooseArearid}" id="kind_areaid" name="kind_areaid" />
			<input type="hidden" value="${curpriceid}" id="kind_priceid" name="kind_priceid" />
			<input type="hidden" value="${curbuycountid}" id="kind_countid" name="kind_countid" />
			<input type="hidden" value="${curvisitid}" id="kind_visitid" name="kind_visitid" />
			<input type="hidden" value="${curpraiseid}" id="kind_praiseid" name="kind_praiseid" />
		</form>
		
	</div>
	<!--底部-->
	<jsp:include page="top/bot.jsp"></jsp:include>
	
	<script type="text/javascript">	
		function clickProduct2Kind(kindId){
			$("#kind_kindId").val(kindId);
			$("#fm_kind").submit();
		};
		
		function clickProduct2Examine(kindId){
			$("#examin_kindId").val(kindId);
			$("#fm_examine").submit();
		};
		
		// 筛选条件 -- 学习中心id
		function chooseByLhub(lhubid){
			$("#kind_lhubid").val(lhubid);
			$("#fm_fitler").submit();
		};
		
		// 筛选条件 -- 专业层次id
		function chooseByLev(levid){
			$("#kind_levid").val(levid);
			$("#fm_fitler").submit();
		};
		
		// 筛选条件 -- 学科科目id
		function chooseBySub(subid){
			$("#kind_subid").val(subid);
			$("#fm_fitler").submit();
		};
		
		// 筛选条件 -- 考试范围id
		function chooseByArea(areaid){
			$("#kind_areaid").val(areaid);
			$("#fm_fitler").submit();
		};
		
		// 口碑排序
		function chooseByPraise(praiseid) {
			$("#kind_praiseid").val(praiseid);
			$("#fm_fitler").submit();
		}
		
		// 价格排序
		function chooseByPrice(priceid) {
			$("#kind_priceid").val(priceid);
			$("#fm_fitler").submit();
		}
		
		// 人气排序
		function chooseByVisit(visitid) {
			$("#kind_visitid").val(visitid);
			$("#fm_fitler").submit();
		}
		
		// 销量排序
		function chooseByBuycount(buycountid) {
			$("#kind_countid").val(buycountid);
			$("#fm_fitler").submit();
		}
		
		$(document).ready(function(){
			var jqArear = $("#chooseArea"); 
			jqArear.change(function(){
				var arearID = $(this).val();
				chooseByArea(arearID);
			});
			
			jqArear.children("option[value='${chooseArearid}']").attr("selected","selected");
			
			var optPros = $('div[type="opt_product"]');
			var cellDivs = $("div.sckc_list_div");
			optPros.hide();
			cellDivs.mouseover(function(){
				$(this).addClass("sckc_list_div_hover");
				$(this).find('div[type="opt_product"]').show();
			});
			
			cellDivs.mouseout(function(){
				$(this).removeClass("sckc_list_div_hover");
				$(this).find('div[type="opt_product"]').hide();
			});
		});
		
		// 点赞
		function click2Praise(kindId){
			var url = '<%=basePath%>'+"client/praiseKind";
			var data = {"kindid":kindId};
			var callBack4Praise = function (back) {
				alert(back.msg);
				if(back.status == 1){
					window.location.reload();
				}
			};
			$.post(url,data,callBack4Praise,"json");
		}
	</script>
</body>
</html>