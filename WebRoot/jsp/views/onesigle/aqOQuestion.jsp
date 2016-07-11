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
<title>个人中心 - 问答--同学有问</title>
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
				<a type="1" href="sigle/question?isMyQuestion=false&type=1"><i class="xwt"></i>新问题</a>
				<a type="2" href="sigle/question?isMyQuestion=false&type=2"><i class="yhf"></i>已回复</a> 
				<i class="clear"></i>
			</div>

			<div class="myhd">
				<ul>
					<c:forEach items="${pageEnt.listPages }" var="item">
					<li>
						<div class="myhd_w">
							<div class="myhd_w_content myhd_w_content_1">
								<span class="fr">【${item.tag }】&nbsp;&nbsp;<p:fmtDate parttern="yyyy-MM-dd" value="${item.createtime }" /></span>
								 <a href="javascript:void(0);">
								 	<c:if test="${item.rewardamount > 0}">
								 	<span>【悬赏${item.rewardamount }元】 </span>
								 	</c:if>
								 	${item.title }
								 </a>
								 <i class="clear"></i>
								 <p class="wd_tx">
								 	【结束时间<p:fmtDate parttern="yyyy-MM-dd" value="${item.expirationtime }" />
								 	<c:if test="${item.rewardamount > 0}">
								 	，您的答案如被采纳将获得${item.rewardamount * 0.7}元的酬劳（10%返还悬赏者，20%作为网站服务费，70%为回复奖励）
								 	</c:if>
								 	】
							 	 </p>
								 <div class="wd_text"><textarea style="resize:none" name="answer" pars="${item.id }">${item.answer }</textarea></div> 
                              	 <div class="wd_btn">
                              	 	<c:choose>
                              	 	<c:when test="${item.answer != null}"><input type="button" value="修改" onclick="click2ModifyAnswer(this);" name="${item.id }" /></c:when>
                              	 	<c:otherwise><input type="button" value="提交" onclick="click2ModifyAnswer(this);" name="${item.id }"/></c:otherwise>
                              	 	</c:choose>
                              	 </div>
							</div>
							<i class="clear"></i>
						</div>
					</li>
					</c:forEach>
				</ul>
			</div>
			
			<p:pageTag name="pageEnt" action="sigle/question?isMyQuestion=false&type=${type}" />
		</div>
	</div>
	<script type="text/javascript">
		var qtype = "${type}";
		qtype = parseInt(qtype, 10);
		if (isNaN(qtype)){
			qtype = 1;
		}
		
		function click2ModifyAnswer(that){
			that = $(that);
			var askid = that.attr("name");
			var answer = $("textarea[name='answer'][pars='"+askid+"']").val();
			var url = '<%=basePath%>'+"sigle/doAnswer";
			var data = {};
			data.answer=answer;
			data.questionid = askid;
			var infunCallBack = function(back){
				alert(back.msg);
				if(qtype == 1 && back.status == 1){
					window.location.reload();
				}
			};
			$.post(url,data,infunCallBack,"json");
		};
		$(document).ready(function() {
			$("div.wd_tit a[type]").each(function(){
				var that = $(this);
				that.removeClass();
				var tpVal = that.attr("type");
				tpVal = parseInt(tpVal, 10);
				if (tpVal == qtype) {
					that.addClass("current");
				};
			});
		});
	</script>
</body>
</html>