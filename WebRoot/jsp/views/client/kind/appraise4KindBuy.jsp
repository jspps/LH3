<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!--累计评价-->
<div class="kczy_ljpj_cont">
<div class="kczy_ljpj_div">
	<div class="kczy_ljpj_fs">
		<span class="kczy_ljpj_sz">${score4Appraise }</span> 
		<span style="float:left; width:auto; margin-left:6px;"> 
			<c:if test="${star > 0 }">
			<c:forEach begin="1" end="${star }" step="1">
			<span class="kczy_ljpj_xx">
				<img src="jsp/imgs/client/78.jpg" />
			</span> 
			</c:forEach>
			</c:if>
			<c:if test="${half_star > 0 }">
			<c:forEach begin="1" end="${half_star }" step="1">
			<span class="kczy_ljpj_xx">
				<img src="jsp/imgs/client/80.jpg" />
			</span> 
			</c:forEach>
			</c:if>
			<c:if test="${no_star > 0 }">
			<c:forEach begin="1" end="${no_star }" step="1">
			<span class="kczy_ljpj_xx">
				<img src="jsp/imgs/client/79.jpg" />
			</span> 
			</c:forEach>
			</c:if>
		</span>
	</div>
	<div class="kczy_ljpj_img">
		<div class="kczy_ljpj_dw" style="padding-left: ${rate4Appraise * 857}px;">
			<img src="jsp/imgs/client/77.png" />
		</div>
	</div>
</div>
<c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
<div class="kczy_ljpj_list">
	<span class="kczy_ljpj_name">
		<label style="font-weight:bold;">${ent.custname}</label>
		<br />
		<label style="color:#9c9c9c;">
			<p:fmtDate parttern="yyyy-MM-dd HH:mm:ss" value="${ent.createtime}"/>
		</label> 
	</span> 
	<span class="kczy_ljpj_name" style="color:#333333; margin-top:5px;">${ent.appraisetext}</span>
		<!-- 
	<span class="kczy_ljpj_name" style="color:#af874d; margin-top:5px;">回复："感谢亲亲对我们的支持，我们是天猫旗舰店，售出的商品都是经过严格检验的呢，亲亲可以放心哦。
		这款宝贝无论是做工还是舒适度都是非常不错的，这个价格可是非常超值的哦。我们活动都是不定期举行的，一般情况下活动前都是有预热活动的，亲亲说的情况我们会与相关部门进行核实的，没有给您带来便利真的非常抱歉，亲亲也可以收藏我们的店铺，加入我们的微淘，随时关注活动的第一手信息呢。
	</span>
	 -->
</div>   
</c:forEach> 
</div>
<p:pageTag name="pageEnt" action="client/getDiscuss4Kind?kind_kindId=${kind_kindId}" wrapid="mid_cont_product"/>