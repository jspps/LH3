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
<title>个人中心 - 问答--满意答案</title>
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
			<div class="myhd">
				<ul>
					<c:forEach items="${pageEnt.listPages }" var="item">
					<li>
						<div class="myhd_w" dataId="${item.id }">
							<div class="myhd_w_content">
								<span class="fr">【${item.tag }】&nbsp;&nbsp;<p:fmtDate parttern="yyyy-MM-dd" value="${item.createtime }"/></span>
								 <a href="javascript:void(0);">
								 	<span>【悬赏${item.rewardamount }元】 </span>${item.title }
								 </a>
							</div>
							<i class="clear"></i>
						</div>
						<div class="myhd_d">
	                    	<span class="fl mdleft"></span>
	                        <div class="fl mdcenter"><span class="jt"></span>
	                        	<span name="${item.answerid }">
	                        		<c:choose>
									<c:when test="${item.answer != null}">${item.answer}</c:when>
									<c:otherwise>只需要10个考币就可以看到答案了！您的账户目前还剩${kbi }个考币。</c:otherwise>
									</c:choose>
	                        	</span> 
	                        </div>
							<c:if test="${item.answer == null}"><a href="javascript:void(0);" class="fr mdright" name="${item.answerid }" pars="${item.id }">查看答案</a></c:if>
	                    	<i class="clear"></i>
	                    </div>
					</li>
					</c:forEach>
				</ul>
			</div>
			
			<p:pageTag name="pageEnt" action="sigle/answer4Satisfied" />
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("div.myhd_d a.mdright[name]").click(function(){
				var that = $(this);
				var answerid = that.attr("name");
				var askid = that.attr("pars");
				var url = '<%=basePath%>' + "sigle/doSeeStatisfiedAnswer";
				var data = {"answerid":answerid,"askid":askid};
				var callBackInfun = function(back){
					if(back.status == 1){
						$("div.myhd_d div.mdcenter span[name='"+answerid+"']").html(back.data);
						that.hide();
					}else{
						alert(back.msg);
					}
				};
				$.post(url,data,callBackInfun,"json");
			});
		});
	</script>
</body>
</html>