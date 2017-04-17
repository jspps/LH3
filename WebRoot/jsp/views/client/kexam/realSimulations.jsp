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
<title>体验模考主页_全真模拟</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="体验模考主页_全真模拟" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" language="JavaScript">
	function click2BuyKind(kindId){
		$("#kind_kindId").val(kindId);
		$("#fm_2buy_kind").submit();
	}
	
	function intoExamRoom(examid) {
		$("#examid").val(examid);
		$("#fm_examId").submit();
	};
	
	function OnClickPrintView(id){
		$("#fm_printView_"+id).submit();
	};
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="../top/head.jsp"></jsp:include>

	<!--内容-->
	<jsp:include page="midMenu.jsp"></jsp:include>
	<div>
		<div class="mk_tims_dbuttom_cont">
			<div class="mk_tims_nr">
				<span class="mk_zsyd_title">${kind.nmProduct }<br />
					<label style="color:#939393; font-size:15px;">全真模拟</label> </span>

				<c:choose>
				<c:when test="${isBuy }">
					<c:forEach items="${pageEnt.listPages }" var="item">
						<div class="mk_qzmn_cont"
							onmouseout="this.className='mk_qzmn_cont'"
							onmouseover="this.className='mk_qzmn_cont_hover'"
							onclick="intoExamRoom(${item.id})">
							<span class="mk_lnzt_jrkc">
								<a href="javascript:void(0);">进入考场</a>
							</span>
							<span class="mk_lnzt_title">${item.name }</span> 
							<c:set var="numQues" value="0"></c:set>
							<c:forEach items="${map4num}" var="itmap">
								<c:if test="${itmap.key == item.id }">
									<c:set var="numQues" value="${itmap.value }"></c:set>
								</c:if>
							</c:forEach>
							<span class="mk_lnzt_list">总题量：${numQues}</span> 
							<span class="mk_lnzt_list">总分数：${item.score}</span>
							<!--  
							<span class="mk_lnzt_list">
								我的分数：<label style="color:#0060ff;">未做</label>
							</span>
							<span class="mk_lnzt_list">
								正确率：<label style="color:#0060ff;">未做</label>
							</span>
							 -->
							 <c:if test="${isBuy == true}">
							 <div class="mk_lnzt_list" style="margin: 10px 0 0 88px;cursor: pointer;padding-left:0;">
								<a class="a_add" href="javascript:OnClickPrintView(${item.id});">打印试卷</a>
								<form action="client/printView" name="fm_printView" id="fm_printView_${item.id}" method="post" target="_blank">
								<input name="unqid" value="${item.id}" type="hidden" />
								</form>
							</div>
							</c:if>
						</div>
					</c:forEach>
					
					<form action="client/examing" method="post" id="fm_examId">
						<input type="hidden" id="examid" name="examid"/>
					</form>
				</c:when>
				<c:otherwise>
					<span class="mk_zsyd_img"> <span class="mk_zsyd_ico">尚未购买，暂无法使用</span></span>
				</c:otherwise>
				</c:choose>
			</div>
			<div class="mk_tims_db">
				<img src="jsp/imgs/client/115.jpg" />
			</div>

			<div style="clear:both;"></div>
			<c:choose>
			<c:when test="${isBuy }">
			<!--分页-->
			<p:pageTag name="pageEnt" action="client/realSimulations" />
			</c:when>
			</c:choose>
		</div>
	</div>

	<!--底部-->
	<jsp:include page="../top/bot.jsp"></jsp:include>
</body>
</html>
