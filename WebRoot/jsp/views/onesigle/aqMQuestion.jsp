<%@ page language="java" pageEncoding="UTF8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>个人中心 - 问答--我的提问</title>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/onesigle_style.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/usermanager.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="jsp/js/onesigle/common.js"></script>
<script type="text/javascript" src="jsp/js/onesigle/user_common.js"></script>
</head>

<body class="user_bg">
	<!--头部-->
	<jsp:include page="top/head.jsp" />

	<!--内容-->
	<jsp:include page="top/aq_menus.jsp" />

	<div class="user_content bg_fff">
		<div class="preson_info">
			<div class="wd_tit">
				<a type="1" href="sigle/question?isMyQuestion=true&type=1"><i class="xwt"></i>新问题</a>
				<a type="2" href="sigle/question?isMyQuestion=true&type=2"><i class="ygq"></i>已过期</a> 
				<a type="3" href="sigle/question?isMyQuestion=true&type=3"><i class="yhf"></i>已回复</a> 
				<a type="4" href="sigle/question?isMyQuestion=true&type=4"><i class="ycn"></i>已采纳</a> 
				<i class="clear"></i>
			</div>

			<div class="myhd">
				<ul>
					<c:forEach items="${pageEnt.listPages }" var="item">
					<li>
						<div class="myhd_w" dataId="${item.id }">
							<div class="myhd_w_content">
								<span class="fr">【${item.tag }】&nbsp;&nbsp;<p:fmtDate parttern="yyyy-MM-dd" value="${item.createtime }"/></span>
								 <a href="javascript:void(0);">
								 	<c:if test="${item.rewardamount > 0}">
								 	<span>【悬赏${item.rewardamount }元】 </span>
								 	</c:if>
								 	${item.title }
								 </a>
								 <c:if test="${type==2}">
									 <i class="clear"></i>
		                             <p class="wd_tx wgygq_a">
		                             	<a href="javascript:void(0)" class="a2">增加悬赏</a>
		                             	<a href="javascript:void(0)" class="a1">放弃求助</a>
		                             	已超过7天没有得到答复了，您可以选择增加悬赏金或放弃求助，您的5元悬赏金将返回您的账户。 
		                             </p> 	
								 </c:if>
							</div>
							<i class="clear"></i>
						</div>
						 <c:choose>
						 <c:when test="${type==3 || type == 4}">
						 	<div id="cell_id_${item.id }" name="cell_id"></div> 	
						 </c:when>
                         </c:choose>
					</li>
					</c:forEach>
				</ul>
			</div>
			
			<p:pageTag name="pageEnt" action="sigle/question?isMyQuestion=true&type=${type}" />
		</div>
	</div>
	<script type="text/javascript">
		var qtype = "${type}";
		$(document).ready(function() {
			qtype = parseInt(qtype, 10);
			if (isNaN(qtype))
				qtype = 1;
			$("div.wd_tit a[type]").each(function(){
				var that = $(this);
				that.removeClass();
				var tpVal = that.attr("type");
				tpVal = parseInt(tpVal, 10);
				if (tpVal == qtype) {
					that.addClass("current");
				};
			});
			if(qtype == 3 || qtype == 4){
				$("div.myhd_w").each(function(){
					$(this).mouseover(function() {
						$(this).css("cursor", "pointer");
					});
					var quesid = $(this).attr("dataId");
					$(this).click(function(){
						$("div[name='cell_id']").empty();
						var data = {questionid:quesid};
						var callBack = function(bdata) {
							$("#cell_id_"+quesid).html(bdata);
						};
						var url = "sigle/getAnswers?type="+qtype;
						$.post(url,data,callBack);
					});
				});
			}
		});
	</script>
</body>
</html>