<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 推荐号弹出框 -->
<div id="correct4recommend">
	<div style="z-index: 11000;opacity:0.6;filter : alpha(opacity=60);background:url(jsp/imgs/client/01.jpg) repeat; width:100%;height:100%;position:fixed;left:0px;top:0px;"></div>
	<div style="width:100%;height:100%;position:absolute;left:0px;top:0px;z-index: 12050;">
		<div class="tck_cont_style" style="position:relative;float: left;top: 50%;left: 50%;margin:0px -280px;">
			<div class="tck_cont_left"><img src="jsp/imgs/client/173.jpg" /></div>
		    <div class="tck_cont_right">
				<div class="tck_cont_close"
					onmouseout="this.className='tck_cont_close'"
					onmouseover="this.className='tck_cont_close_hover'"
					onclick="click2CloseRecommendCode()"></div>
				<div class="tck_cont_nr">
					<span class="tck_cont_title">输入推荐号</span> 
					<input type="text" class="tck_cont_ipnut" id="recommend_code_val" />
					<span class="tck_cont_title" style="color:#9d9d9d;">请核对推荐号是否正确</span> 
					<span class="tck_cont_bottom">
						<a href="javascript:void(0);" onclick="click2SubmitRcomdCode()">提交</a>
					</span>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function click2SubmitRcomdCode(){
				var url = '<%=basePath%>'+"client/modifyShopCartRdCode";
				var data = {};
				data.code = $("#recommend_code_val").val();
				data.shopcartid = "${shopcartid}";
				data.isSet = true;
				var callBack = function(back){
					alert(back.msg);
					if(back.status == 1){
						// click2CloseRecommendCode();
						window.location.reload();
					}
				};
				$.post(url,data,callBack,"json");
			};
			
			function click2CloseRecommendCode(){
				$("#correct4recommend").parent().empty();
			};
		</script>
	</div>
</div>
