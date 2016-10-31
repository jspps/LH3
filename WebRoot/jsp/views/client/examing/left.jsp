<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set var="radio4left" value="-1"></c:set>
<c:set var="chbox4left" value="-1"></c:set>
<c:set var="judge4left" value="-1"></c:set>
<c:set var="fill4left" value="-1"></c:set>
<c:set var="jianda4left" value="-1"></c:set>
<c:set var="lunsu4left" value="-1"></c:set>
<c:set var="anlifx4left" value="-1"></c:set>
<c:set var="isShowTit" value="true"></c:set>

<!--  <div style="width: 180px;margin-left: 18px;"> -->
<div id="sucai1">
	<div>
		<c:forEach items="${listLeft}" var="ent" varStatus="status">			
			<c:if test="${!sessionScope.IsTestExam || (sessionScope.IsTestExam && (num < 6)) }">
			<c:set var="isShowTit" value="false"></c:set>
			
			<c:choose>
			<c:when test="${ent.type==1 && radio4left != ent.examcatalogid}">
				<c:set var="radio4left" value="${ent.examcatalogid}"></c:set>
				<c:set var="isShowTit" value="true"></c:set>
			</c:when>
			<c:when test="${ent.type==2 && chbox4left != ent.examcatalogid}">
				<c:set var="chbox4left" value="${ent.examcatalogid}"></c:set>
				<c:set var="isShowTit" value="true"></c:set>
			</c:when>
			<c:when test="${ent.type==3 && judge4left != ent.examcatalogid}">
				<c:set var="judge4left" value="${ent.examcatalogid}"></c:set>
				<c:set var="isShowTit" value="true"></c:set>
			</c:when>
			<c:when test="${ent.type==4 && fill4left != ent.examcatalogid}">
				<c:set var="fill4left" value="${ent.examcatalogid}"></c:set>
				<c:set var="isShowTit" value="true"></c:set>
			</c:when>
			<c:when test="${ent.type==5 && jianda4left != ent.examcatalogid}">
				<c:set var="jianda4left" value="${ent.examcatalogid}"></c:set>
				<c:set var="isShowTit" value="true"></c:set>
			</c:when>
			<c:when test="${ent.type==6 && lunsu4left != ent.examcatalogid}}">
				<c:set var="lunsu4left" value="${ent.examcatalogid}"></c:set>
				<c:set var="isShowTit" value="true"></c:set>
			</c:when>
			<c:when test="${ent.type==7 && anlifx4left != ent.examcatalogid}">
				<c:set var="anlifx4left" value="${ent.examcatalogid}"></c:set>
				<c:set var="isShowTit" value="true"></c:set>				
			</c:when>
			<c:otherwise></c:otherwise>
			</c:choose>
			
			<c:if test="${isShowTit}">
			<span class="tymk_zm_div">${ent.serial} ${ent.titleEllipsis}</span>
			</c:if>
			
			<span id="tymk_zt_div_${ent.optid}" npid='${ent.optid}'
				onclick="hrefUrl('${num}','${ent.optid}','${ent.type}',false,true);"
				class="tymk_list_ts_wd">${num}</span>
			<c:set var="num" value="${num + 1}"></c:set>
			</c:if>
		</c:forEach>
		<div style="clear: both;"></div>
	</div>
</div>
<!-- <div class="tymk_wdtts_div">您还有10题没做答</div> -->
<script type="text/javascript">
	$(document).ready(function(){
		$("#sucai1").niceScroll({
			cursorcolor : "#FFF",
			cursoropacitymax : 1,	
			touchbehavior : true,
			cursorwidth : "5px",
			cursorborder : "1px",
			cursorborderradius : "5px"
		});
	});
</script>