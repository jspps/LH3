<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
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
<title>学习中心首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="学习中心首页" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="../top/head.jsp"></jsp:include>

	<!--内容-->
	<div class="sxkc_cont" style="padding-top:0px; padding-bottom:0px;">
		<div class="xxzx_qbkc" onclick="go2LhubKinds();">
			<div class="sxkc_mbx" style="margin-top:10px;">
				<a href="javascript:void(0);">${lhub.name }首页</a>
			</div>
			<div class="xxzx_qbkc_logo">
				<div>
					<a href="javascript:void(0);">
						<img src="${lhub.imgr4Cover}" width="136" height="84" />
					</a>
				</div>
			</div>
			<div class="xxzx_name_title">
				<a href="javascript:void(0);">官方旗舰店</a>
			</div>
			<div class="zxsy_ico_div">
				<a href="javascript:void(0);">
					<img src="jsp/imgs/client/84.jpg" />
				</a>
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
	<c:set value="${fn:length(imgs) == 0 ? 'jsp/imgs/client/88.jpg' : imgs[0] }" var="def_img"></c:set>
	<div class="zxsy_banner_cont">
		<div class="zxsy_banner_zj">
			<div class="zxsy_banner_div">
				<img src="${def_img}" id="cur_img" width="983px" height="445px"/>
			</div>
			<div class="zxsy_banner_qh">
				<c:forEach var="imgUrl" items="${imgs }" varStatus="itStatus">
				<span class="zxsy_banner_list" onclick="click2ChangeUrl(this)" img="${imgUrl}" index="${itStatus.index}">
					<a href="javascript:void(0);">
						<c:choose>
							<c:when test="${itStatus.index != 0 }">
								<img src="jsp/imgs/client/89.png" />
							</c:when>
							<c:otherwise>
								<img src="jsp/imgs/client/89_hover.png" />
							</c:otherwise>
						</c:choose>
					</a>
				</span> 
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div class="zxbd_title_div">
		<div class="zxbd_title_style" style="margin-top:-2px;">
			<img src="jsp/imgs/client/87.jpg" />
		</div>
	</div>
	<div style="clear:both;"></div>
	<div class="zxsy_cont_style">
		<p>
			${lhub.descr }
		</p>
	</div>
	<!--底部-->
	<jsp:include page="../top/bot.jsp"></jsp:include>
	<form action="client/kind4Lhub" id="fm_go2lhubkinds" method="post">
		<input type="hidden" value="${lhub.lhid }" id="lhubid" name="lhubid" />
	</form>
	<script type="text/javascript">
		var index = 0;
		function click2ChangeUrl(that){
			var jqThat = $(that);
			
			var imgUrl = jqThat.attr("img");
			if(!imgUrl || imgUrl == ""){
				return;
			}
			
			index = jqThat.attr("index");
			index = parseInt(index);
			if(isNaN(index)){
				index = 0;
			}
			
			$("#cur_img").attr("src",imgUrl);
			var jqImgs = $("div.zxsy_banner_qh img");
			jqImgs.attr("src","jsp/imgs/client/89.png");
			var lens = jqImgs.length;
			if(index < lens){
				var curImg = jqImgs.get(index); 
				curImg.src = "jsp/imgs/client/89_hover.png";
			};
		}
		
		function invoke4ChangeImg(){
			var jqSpans = $("div.zxsy_banner_qh span.zxsy_banner_list");
			var lens = jqSpans.length;
			index++;
			index %= lens;
			click2ChangeUrl(jqSpans.get(index));
		}
		
		setInterval("invoke4ChangeImg()",3000);
		
		function go2LhubKinds(){
			$("#fm_go2lhubkinds").submit();
		}
	</script>
</body>
</html>
