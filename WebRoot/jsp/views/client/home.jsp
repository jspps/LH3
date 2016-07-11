<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<title>尚学在线 - 首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学"/>
<meta http-equiv="description" content="首页"/>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--背景图片-->
	<div id="index_bg">
		<img src="jsp/imgs/client/00.jpg" />
	</div>
	<!--导航条-->
	<jsp:include page="top/head.jsp"></jsp:include>

	<!--页面内容-->
	<div class="overall">
		<div class="ss_cont">
			<div class="ss_ssk">
				<input type="text" class="ssk_text" id="inp_ssk" name="inp_ssk"/>
			</div>
			<div class="search_button"
				onmouseout="this.className='search_button'"
				onmouseover="this.className='search_button_hover'" 
				onclick="click2SearhSubName();">搜索</div>
		</div>

		<!--左边分类-->
		<div class="fdh_div">
			<div class="fdh_cont">
				<c:forEach items="${departs}" var="item" varStatus="itemStatus">
					<c:set var="indexType" value="${itemStatus.index}"></c:set>
					<div class="fdh_list" type="${indexType}">
						<span class="span_ico">
							<img src="jsp/imgs/client/${item.icon}" /> 
						</span> 
						<span class="span_text">
							<a href="javascript:void(0);">${item.name}</a>
						</span>
					</div>
				</c:forEach>
			</div>

			<!--买考币按钮-->
			<div class="fdh_mkb" onmouseout="this.className='fdh_mkb'"
				onmouseover="this.className='fdh_mkb_hover'"
				onclick="click2BuyKbi();">
				<a href="javascript:void(0)"></a>
			</div>
		</div>

		<!--右边列表-->
		<div class="right_list">
			<div class="right_list_top">
				<span class="right_title_text" id="rt_cont_tit"></span>
				<div class="right_qh_div">
					<span class="right_list_ico"
						onmouseout="this.className='right_list_ico'"
						onmouseover="this.className='right_list_ico_hover'"
						onclick="clickPreView();"> <!--左右切换--> </span> <span
						class="right_list_ico_a"
						onmouseout="this.className='right_list_ico_a'"
						onmouseover="this.className='right_list_ico_a_hover'"
						onclick="clickNextView();"> <!--左右切换--> </span>
				</div>
			</div>
			<div class="right_list_bottom" id="rt_list_childs"></div>
		</div>
		<div class="drift">
			<!--清除浮动-->
		</div>
	</div>

	<!--底部-->
	<jsp:include page="top/bot.jsp"></jsp:include>

	<form action="client/chooseLessons" id="fm_majoy" method="post"
		onsubmit="return true;">
		<input type="hidden" id="nmMajor" name="nmMajor" value="0" />
		<input type="hidden" id="departid" name="departid" value="0" />
	</form>
	
	<form action="client/chooseLessons" id="fm_search_sub" method="post"
		onsubmit="return true;">
		<input type="hidden" id="subname" name="subname" value="" />
	</form>
	<script type="text/javascript">
		var arrs = new Array(${json});
		var list = arrs[0];
		var star = 0;
		var end = 0;
		var size = 8;
		var curSize = 8;
		var allSize = 0;
		var listChild = null;
		function firstInit() {
			var index = 4;
			var first = list[index];
			if(first){
				$("#rt_cont_tit").html(first.name);
				$("div.fdh_list[type="+index+"]").addClass("fdh_list_hover");
				showInitView(first.childs);
			}else{
				$("#rt_cont_tit").html("");
			};
		}

		function showInitView(listTmp) {
			star = 0;
			end = star + size;
			curSize = 8;
			if (listTmp == null || listTmp.length <= 0) {
				var jqRtCont = $("#rt_list_childs");
				jqRtCont.empty();
				return;
			}

			allSize = listTmp.length;
			listChild = listTmp;
			showChildsView();
		}

		function clickPreView() {
			if (star <= 0)
				return;
			if (curSize < size) {
				end = end - curSize;
				curSize = size;
			}

			star = star - size;
			if (star < 0)
				star = 0;

			showChildsView();
		}

		function clickNextView() {
			if (star < 0)
				return;
			star = star + size;
			if (star > allSize) {
				star = star - size;
				return;
			}

			if (allSize - end > size) {
				curSize = size;
			} else {
				curSize = allSize - end;
			}
			end = end + curSize;
			showChildsView();
		}

		function showChildsView() {
			var jqRtCont = $("#rt_list_childs");
			jqRtCont.empty();
			var isNull = (!listChild);
			if (isNull)
				return;
			var len = listChild.length;
			if (star > len)
				return;
			if (end > len)
				end = len;
			var curList = listChild.slice(star, end);
			var curLen = end - star;
			for ( var i = 0; i < curLen; i++) {
				var en = curList[i];
				var div = $("<div>", {
					"class" : "kc_list_div"
				});
				div.append($("<span>", {
					"class" : "kc_list_title"
				}).append($("<a>", {
					"href" : "javascript:void(0);",
					"text" : en.nmMajor
				})), $("<span>").append($("<a>", {
					"href" : "javascript:void(0);"
				}).append($("<img>", {
					"src" : en.imgurl4major
				}))));

				jqRtCont.append(div);

				(function bindClick(li, jqdiv) {
					jqdiv.click(function click2major() {
						// admajor对象li数据
						click2majoy(li.departid,li.nmMajor);
					});
				})(en, div);
			};
		};

		function click2majoy(departid,nmMajor) {
			// admajor对象li数据
			var jqfm = $("#fm_majoy");
			$("#nmMajor").val(nmMajor);
			$("#departid").val(departid);
			jqfm.submit();
		}

		$(document).ready(
		function() {
			firstInit();
			$("div.fdh_list[type]").click(
					function() {
						var val = $(this).attr("type");
						$("div.fdh_list[type]").removeClass(
								"fdh_list_hover");
						$('div.fdh_list[type="' + val + '"]').addClass(
								"fdh_list_hover");

						var en = list[val];
						$("#rt_cont_tit").html(en.name);
						showInitView(en.childs);
					});
		});
		
		function click2BuyKbi(){
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
				getLoginHtml("client/home");
			};
		}
		
		function click2Searh(){
			var inpVal = $("#inp_ssk").val();
			var url = '<%=basePath%>'+"client/req4Search";
			var data = {"name":inpVal};
			var callBack = function(backData){
				if(backData.data){
					showInitView(backData.data);
				}
			};
			$.post(url,data,callBack,"json");
		}
		
		function click2SearhSubName(){
			var inpVal = $("#inp_ssk").val();
			$("#subname").val(inpVal);
			$("#fm_search_sub").submit();
		}
	</script>
</body>
</html>
