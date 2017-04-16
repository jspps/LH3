<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="db_div_style">
	<div class="db_div_cont">
		<div class="db_div_top">
			<span class="db_list_cont">
				<a href="help"><span class="db_list_ico"> <!--底部图标--> </span> </a> 
				<span class="db_list_text"><a href="help">新手指引</a> </span> 
			</span>
			<span class="db_list_cont">
				<a href="ensure"> <span class="db_list_ico_a"> <!--底部图标--> </span> </a> 
				<span class="db_list_text"><a href="ensure">学习保障</a> </span>
			</span>
			<span class="db_list_cont">
				<a href="helpPay"> <span class="db_list_ico_b"> <!--底部图标--> </span></a>
				<span class="db_list_text"><a href="helpPay">支付方式</a> </span>
			</span>
			<span class="db_list_cont">
				<a href="clause"><span class="db_list_ico_c"> <!--底部图标--> </span> </a>
				<span class="db_list_text"><a href="clause">关于我们</a> </span> 
			</span>
			<div class="db_dz_cont">
				<!-- 
				电话：028-66753505&nbsp;&nbsp;&nbsp;QQ：636544785<br />地址：成都市金牛区华侨城徐州路1栋3单元8010
				 QQ：2951309817
				 -->
			</div>
			<div class="dh_ewm_div">
				<!-- 
					<img src="jsp/imgs/client/30.jpg" />
				 -->
				<img width="68" height="68" id="qrcode_img"/>
			</div>
		</div>
		<div class="dh_div_bottom">成都尚学在线网络科技有限公司版权所有&nbsp;&nbsp;&nbsp;蜀ICP备15011601</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#qrcode_img").attr("src","getQRCode");
});
</script>