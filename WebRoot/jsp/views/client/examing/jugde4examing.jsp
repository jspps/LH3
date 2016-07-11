<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 提交试卷，的提示信息 -->
<div id="jugde4examing">
	<div style="z-index: 11000;opacity:0.6;filter : alpha(opacity=60);background:url(jsp/imgs/client/01.jpg) repeat; width:100%;height:100%;position:fixed;left:0px;top:0px;"></div>
	<div style="width:100%;height:100%;position:absolute;left:0px;top:0px;z-index: 12050;">
		<div class="tck_cont_style" style="position:relative;float: left;top: 50%;left: 50%;margin:0px -280px;">
			<div class="tck_cont_left"><img src="jsp/imgs/client/173.jpg" /></div>
		    <div class="tck_cont_right">
		    	<div class="tck_cont_close" onmouseout="this.className='tck_cont_close'" onmouseover="this.className='tck_cont_close_hover'" onclick="click2CloseJugde();"></div>
		        <div class="tck_cont_nr margin_top">
		        	<span class="tck_cont_title" style="text-align:center; margin-bottom:50px; font-size:15px;">您还有${num}道题没有完成！</span>
		            <span class="tck_cont_bottom_qx btn_bom_1" onclick="click2SubmitAnswers();"><a href="javascript:void(0);">确认交卷</a></span>
		            <span class="tck_cont_bottom btn_bom_2" style="margin-left:0px;" onclick="click2CloseJugde();"><a href="javascript:void(0);">继续考试</a></span>
		        </div>
		    </div>
		</div>
	</div>
	<script type="text/javascript">
		function click2SubmitAnswers(){
			isCanShowConfirm = false;
			var url = '<%=basePath%>'+"${url}?curExamLen=${curExamLen}&timers=${timers}";
			window.location.href = url;
		};
		
		function click2CloseJugde(){
			$("#jugde4examing").parent().empty();
		};
	</script>
</div>