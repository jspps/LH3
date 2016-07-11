<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 开始考试，提示扣费 -->
<div id="tip4examing">
	<div style="z-index: 11000;opacity:0.6;filter : alpha(opacity=60);background:url(jsp/imgs/client/01.jpg) repeat; width:100%;height:100%;position:fixed;left:0px;top:0px;"></div>
	<div style="width:100%;height:100%;position:absolute;left:0px;top:0px;z-index: 12050;">
		<div class="tck_cont_style" style="position:relative;float: left;top: 50%;left: 50%;margin:0px -280px;">
			<div class="tck_cont_left"><img src="jsp/imgs/client/173.jpg" /></div>
		    <div class="tck_cont_right">
		    	<div class="tck_cont_close" onmouseout="this.className='tck_cont_close'" onmouseover="this.className='tck_cont_close_hover'" onclick="click2CloseTip4Examing();"></div>
		        <div class="tck_cont_nr" style="padding-top: 30px;">
		        	<div class="tck_cont_title" style="text-align:center; height:160px; font-size:15px; padding-top: 20px;">
		        		<span style="font-weight:bold;">温馨提示：</span><br /><br />
		        		<c:choose>
		        		<c:when test="${sessionScope.Customer == null}">
		        			<label style="color:#F00; font-weight:bold;">您目前是体验试卷，单击开始考试，即可继续考试。体验试卷，只能做前5到题.</label>
		        		</c:when>
		        		<c:otherwise>
		        			本次考试共需消耗您<label style="color:#F00; font-weight:bold;">${ndKbi}</label>个考币，您目前有<label style="color:#F00; font-weight:bold;">${hasKbi}</label>个考币。
		        		</c:otherwise>
		        		</c:choose>
		        	</div>
		        	<span class="tck_cont_bottom_qx" style="margin-left:0px;" onclick="click2CloseTip4Examing();"><a href="javascript:void(0);">取消</a></span>
		            <span class="tck_cont_bottom" onclick="click2SureExaming();" style="margin-left: 10px;"><a href="javascript:void(0);">开始考试</a></span>
		        </div>
		    </div>
		</div>
	</div>
	<script type="text/javascript">
		function click2SureExaming(){
			var url = '<%=basePath%>' + "client/judgeCostKbi4Examing";
			var data = {"name":"${name}"};
			var callBack4SureExaming = function(backData){
				if(backData.status == 1){
					$("#tip4examing").parent().empty();
					timerId = setInterval("CountDown()",1000);
				}else{
					alert(backData.msg);
				}
			};
			$.post(url,data,callBack4SureExaming,"json");
		};
		
		function click2CloseTip4Examing(){
			var url = "${sessionScope.UrlPre}";
			var intdex = url.indexOf("/");
			if(intdex == 0){
				url = url.substring(1);
			};
			if(url == "")
				url = "client";
			url ='<%=basePath%>'+url;
			window.location.href = url;
		};
	</script>
</div>