<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<c:forEach items="${pageEnt.listPages }" var="item">
<c:choose>
<c:when test="${type ==3  }">
	<div class="myhd_d myhd_d_4">
	<span class="fl mdleft"></span>
    <div class="fl mdcenter"><span class="jt"></span>
    	${item.content}
    </div>
    <a href="javascript:void(0);" class="fr yhf_cn" onclick="click2Adopt(${quesid},${item.id})">采纳</a>
	<i class="clear"></i>
</div>
</c:when>
<c:when test="${type ==4  }">
	<c:choose>
	<c:when test="${answerid == item.id  }">
		<div class="myhd_d myhd_d_3">
			<span class="fl mdleft"></span>
		    <div class="fl mdcenter"><span class="jt"></span>
		    	${item.content}
		    </div>
			<i class="clear"></i>
		</div>
	</c:when>
	<c:otherwise>
		<div class="myhd_d myhd_d_2">
			<span class="fl mdleft"></span>
		    <div class="fl mdcenter"><span class="jt"></span>
		    	${item.content}
		    </div>
			<i class="clear"></i>
		</div>
	</c:otherwise>
	</c:choose>
</c:when>
</c:choose>

</c:forEach>
<p:pageTag name="pageEnt" action="sigle/getAnswers?type=${type}&questionid=${quesid}" wrapid="cell_id_${quesid}" />

<script type="text/javascript">
	function click2Adopt(quesid,itemid){
		var url = "sigle/doAdopt";
		var data = {};
		data.answerid = itemid;
		data.quesid = quesid;
		var callBack = function(back){
			if(back.status){
				alert(back.msg);
				if(back.status == 1){
					window.location.reload();
				}
			}
		};
		$.post(url,data,callBack,"json");
	};
</script>
<div style="clear:both;"></div>