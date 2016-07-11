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
<title>全部课程分类</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="全部课程分类" />
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

	<!--标题图片-->
	<div class="qbkcfl_title">
		<div class="qbkcfl_title_img">
			<img src="jsp/imgs/client/39.jpg" />
		</div>
	</div>
	<div style="clear:both;"></div>

	<!--内容-->
	<div class="qbkcfl_cont">
		<ul class="qbkcfl_ul">
			<c:forEach items="${departs}" var="item" varStatus="itStatus">
				<c:set value="${itStatus.index == 0 ? 'style=\"margin-left:0px;\"' :''}" var="c_style"></c:set>
				<li ${c_style} onclick="clickLi(${item.did})">
					<span class="${item.class1 }" 
					 onmouseout="this.className='${item.class1 }'"
					 onmouseover="this.className='${item.class2 }'"> 
						<a	href="javascript:void(0);">${item.name }</a> 
					</span>
				</li>
			</c:forEach>

		</ul>

		<!---切换内容-->
		<div class="qbkcfl_qh_cont" id="qbkcfl_qh_cont" style="display:none">
			<div class="qbkcfl_qh_xt" id="qbkcfl_qh_xt"></div>
			<div class="qbkcfl_qh_style" id="qbkcfl_qh_style">
			</div>
			<div style="clear:both;"></div>
		</div>

		<!--底部-->
		<jsp:include page="top/bot.jsp"></jsp:include>

		<form action="client/chooseLessons" id="fm_majoy" method="post">
			<input type="hidden" id="fm_departid" name="departid" value="0" />
			<input type="hidden" id="fm_nmMajor" name="nmMajor" value="0" />
		</form>

		<script type="text/javascript">
		var arrs = new Array(${json});
		var list = arrs[0];
		function clickLi(departid) {
			document.getElementById("qbkcfl_qh_cont").style.display="";
			var jqRtCont = $("#qbkcfl_qh_style");			
			var index = departid - 1;		
			var first = list[index];
			var listChild = first.childs;
			var lens = listChild.length;
			var s = "";
			for (var i = 0; i < lens; i++) {
				var en = listChild[i];
				var nmMajor = en.nmMajor;
				s +=  '<a href="javascript:void(0)" onclick="click2majoy( ' + departid + ',\''+nmMajor+'\')">' + nmMajor + '</a>' + '|';
			}
			
			if(s != ""){
				s = s.substring(0, s.length - 1);
			}
			jqRtCont.html(s);
			
			// 设置线的位置
			var margin_left = index * 118;
			$("#qbkcfl_qh_xt").attr("style","margin-left:"+margin_left+"px;");
		}
		
		function click2majoy(departid,nmMajor) {
			$("#fm_departid").val(departid);
			$("#fm_nmMajor").val(nmMajor);
			$("#fm_majoy").submit();
		}
	</script>
	</div>
</body>
</html>
