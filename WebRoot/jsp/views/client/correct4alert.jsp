<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 错题反馈 -->
<div id="correct4question">
	<div style="z-index: 11000;opacity:0.6;filter : alpha(opacity=60);background:url(jsp/imgs/client/01.jpg) repeat; width:100%;height:100%;position:fixed;left:0px;top:0px;"></div>
	<div style="width:100%;height:100%;position:absolute;left:0px;top:0px;z-index: 12050;">
		<div class="tck_cont_style" style="position:relative;float: left;top: 50%;left: 50%;margin:0px -280px;">
			<div class="tck_cont_left"><img src="jsp/imgs/jcico.jpg" /></div>
		    <div class="tck_cont_right">
		    	<div class="tck_cont_close" onmouseout="this.className='tck_cont_close'" onmouseover="this.className='tck_cont_close_hover'" onclick="click2CloseCorrect();"></div>
		        <div class="tck_cont_nr">
		        	<span class="tck_cont_title">非常感谢您的指正，请写出本题的错误之处，谢谢！</span>
		            <textarea name="description" cols="" rows="" class="tck_cont_wbqy" style="resize:none;" id="description4correct"></textarea>
		            <span class="tck_cont_bottom_qx" onclick="click2CancelCorrect();"><a href="javascript:void(0);">取消</a></span>
		            <span class="tck_cont_bottom" style="margin-left:0px;" onclick="click2UpCorrect();"><a href="javascript:void(0);">提交</a></span>
		        </div>
		    </div>
		</div>
		<script type="text/javascript">
			function click2UpCorrect(){
				var url = '<%=basePath%>'+"client/correct4Feedback";
				var data = {};
				data.descr = $("#description4correct").val();
				data.questid = "${questid}";
				var callBack = function(back){
					alert(back.msg);
					if(back.status == 1){
						click2CloseCorrect();
					}else{
						$("#login_error_tip").show();
					}
				};
				$.post(url,data,callBack,"json");
			};
			
			function click2CloseCorrect(){
				$("#correct4question").parent().empty();
			};
			
			function click2CancelCorrect(){
				$("#description4correct").val("");
				click2CloseCorrect();
			};
		</script>
	</div>
</div>