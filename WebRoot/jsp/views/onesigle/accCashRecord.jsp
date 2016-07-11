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
<title>个人中心 - 帐户--提现记录</title>
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
	<jsp:include page="top/account_menus.jsp" />
	<div class="user_content bg_fff">
     	<div class="preson_info">
        	<p class="tkj_cont">
          		<a href="sigle/accCash" style="width:100px; height:35px; border:0px; background:#999; color:#FFF; font-size:15px; cursor:pointer; margin-right:30px;padding: 8px;">申请提现</a>
                <a href="javascript:void(0);" style="width:100px; height:35px; border:0px; background:#F60; color:#FFF; font-size:15px; cursor:pointer;padding: 8px;">提现记录</a>
         	</p>
        	<div class="div_table tjh_info" style="margin-top:10px;">
                <table cellspacing="0" cellpadding="0" border="0" class="table">
                    <thead>
                    <tr>
                        <th width="20%">申请时间</th>
                        <th width="20%">支付宝帐号</th>
                        <th width="20%">真实姓名</th>
                        <th width="10%">提现金额(元)</th>
                        <th width="15%">处理状态</th>
                        <th width="15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${pageEnt.listPages }" var="item">
                        <tr>
                            <td><p:fmtDate parttern="" value="${item.createtime}" /> </td>
                            <td>${item.alipay } </td>
                            <td>${item.alipayName } </td>
                            <td>${item.monyeApply } </td>
                            <td>
	                            <c:choose>
	                            <c:when test="${item.statusOpt == 0 }">审核中</c:when>
	                            <c:when test="${item.statusOpt == 1 }">取消中</c:when>
	                            <c:when test="${item.statusOpt == 2 }">已取消</c:when>
	                            <c:when test="${item.statusOpt == 3 }">被拒绝</c:when>
	                            <c:otherwise>已转账</c:otherwise>
	                            </c:choose>
                            </td>
                            <td>
	                            <c:if test="${item.statusOpt == 0 }">
	                            <input type="button" value="申请取消提现" style="width:100px; height:30px; border:0px; background:#F60; color:#FFF; cursor:pointer; margin-left:10px;" pars="${item.id}" />
	                            </c:if>
                            </td>
                        </tr>
                        </c:forEach>
                      </tbody>
                </table>
            </div>
            <p:pageTag name="pageEnt" action="sigle/accCashRecord" />
        </div>
     </div>
     <script type="text/javascript">
     $(document).ready(function(){
     	$("div.tjh_info table :button").click(function(){
     		var val = $(this).attr("pars");
     		val = parseInt(val,10);
     		if(isNaN(val)){
     			alert("申请取消提现失败!");
     			return;
     		}
     		
			if(confirm("确定取消申请提取现金吗?")){
				var data = {};
				data.pars_id = val;
				var url = '<%=basePath%>'+"sigle/cancelAppayERmb";
				var infunCallBack = function(back){
					alert(back.msg);
					if(back.status == 1){
						window.location.reload();
					}
				};
				$.post(url,data,infunCallBack,"json");
			};
     	});
     });
     </script>
</body>
</html>