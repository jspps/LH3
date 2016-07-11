<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="buyKbi_wrap">
	<div style="z-index: 11000;opacity:0.6;filter : alpha(opacity=60);background:url(jsp/imgs/client/01.jpg) repeat; width:100%;height:100%;position:fixed;left:0px;top:0px;"></div>
	<div style="width:100%;height:100%;position:absolute;left:0px;top:0px;z-index: 12050;">
		<div class="tck_cont_style" style="position:relative;float: left;top: 50%;left: 50%;margin:-175px -280px;">
			<div class="tck_cont_left"><img src="jsp/imgs/gmkb_ico.jpg" /></div>
		    <div class="tck_cont_right">
		    	<div class="tck_cont_close" onmouseout="this.className='tck_cont_close'" onmouseover="this.className='tck_cont_close_hover'" onclick="click2CloseVI();"></div>
		        <div class="tck_cont_nr">
		        	<span class="tck_cont_title" style="margin-top:15px;">
		        		您还剩<label style="color:#F30;">【${kbi }】</label>个考币
		        	</span>
		        	<span class="tck_cont_title" style="margin-top:10px;">
		        	考试币：
		        	<select id="kbi4buyui" onchange="change4SelKbi();">
		        	<c:forEach items="${mapRmbKbi }" var="item">
		        	  <option value="${item.key }">${item.value }</option>
		        	</c:forEach>
		       	  </select>&nbsp;&nbsp;个
		          </span>
		            <span class="tck_cont_title" style="margin-top:10px;">售&nbsp;&nbsp;价：<label style="color:#F30;" id="moneycost4buyui"></label></span>
		            <span class="tck_cont_title" style="color:#9d9d9d; margin-top:10px;">考试币可以用来做更多的试题以提升您的成绩！</span>
		            <span class="tck_cont_bottom" onclick="click2SubmitBuyKbi();"><a href="javascript:void(0);">购买</a></span>
		        </div>
		    </div>
		</div>
	</div>
	<div id="buy_kbi_4_rmb"></div>
	<script type="text/javascript">
		function change4SelKbi(){
			var mm = $("#kbi4buyui").val();
			$("#moneycost4buyui").html(mm+"元");
		};
		
		function click2SubmitBuyKbi(){
			var rmb = $("#kbi4buyui").val();
			var url = '<%=basePath%>';
			url += "client/buyKbi";
			var data = {"rmb":rmb};
			var callBack = function(backData){
				if(backData.status == 1){
					if(backData.data){
						$("#buy_kbi_4_rmb").html(backData.data);
					}else{
						click2CloseVI();
					}
				}else{
					alert(backData.msg);
				}
			};
			$.post(url,data,callBack,"json");
		};
		
		function click2CloseVI(){
			$("#buyKbi_wrap").parent().empty();
		};
		
		$(document).ready(function(){
			change4SelKbi();
		});
	</script>
</div>