<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 购买记录 -->
<div class="kczy_cjjl_cont">
<table width="1000" border="0" cellspacing="0" cellpadding="0" style="color:#636363; text-align:center; line-height:24px;">
	<tr>
		<th width="200" scope="col">成交时间</th>
		<th width="350" scope="col">课程名称</th>
		<th width="300" scope="col">学员</th>
		<th width="150" scope="col">状态</th>
	</tr>
	 <c:forEach items="${pageEnt.listPages}" var = "ento" varStatus="">
	 <tr>
		<td>
		<p:fmtDate parttern="yyyy-MM-dd HH:mm:ss" value="${ento.createtime}"/>
		</td>
		<td>${ento.name}</td>
		<td>${ento.nmMaker}</td>
		<td>成交</td>
	</tr>
	 </c:forEach>
</table>
</div>
<!--分页-->
<p:pageTag name="pageEnt" action="client/getOrders4Kind?kind_kindId=${kind_kindId}" wrapid="mid_cont_product"/>