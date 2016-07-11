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
<title>个人中心 - 问答--尚学问答</title>
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
        	<div class="wd_tit" style="text-align:left;">
				你问我答，学习难题，轻松解决！<br/><br/>
				使用说明：<br/><br/>
				<span style="font-weight:bold;">使用问答前请务必点击导航”账户”进行支付宝账号登记和测试，否则您将无法使用问答功能。</span><br/><br/>
				<span style="font-weight:bold;">——如果您要提问：提问分为免费提问和悬赏提问，悬赏金由您自由选择，悬赏期为7天。</span><br/>
	       		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果7天内有满意答案，请务必采纳，成功采纳后您的悬赏金将返还<span style="color:#F00; font-weight:bold;">10%，70%</span>作为对回答问题者进行奖励，网站留存<span style="color:#F00; font-weight:bold;">20%</span>服务费；<br/>
	       		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果超过7天仍不采纳您将法获取<span style="color:#F00; font-weight:bold;">10%</span>的悬赏金返还，也无法退回悬赏金；<br/> 
	       		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果7天后您的提问没有一个回复，您可以取消悬赏（悬赏金将在3个工作日后退回到您的支付宝账户，您可以选择继续悬赏或追加悬赏金悬赏。<br/><br/> 
				<span style="font-weight:bold;">——如果您要回答问题：您可以回复任何其他同学提交的问题，请务必保证您的回复的正确性。</span><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您回复的是悬赏提问，一经提问同学的采纳，您将获得70%的悬赏金，作为对您真诚帮助的回报。<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您回复的是免费提问，网站及提问同学将感谢您的无私奉献！<br/><br/>
				<span style="font-weight:bold;">——您也可以在“满意问答"处查询是否有和您的问题一致的答案，如果有，只需支付少量的考币就可查看了。</span>
				<div style="height:50px; float:;"></div>
	            <i class="clear"></i>
            </div>
     	</div>
     </div>
</body>
</html>