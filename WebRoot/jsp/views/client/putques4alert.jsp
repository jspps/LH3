<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 前台提问 -->
<div id="putnew4question">
	<div
		style="z-index: 11000;opacity:0.6;filter : alpha(opacity=60);background:url(jsp/imgs/client/01.jpg) repeat; width:100%;height:100%;position:fixed;left:0px;top:0px;"></div>
	<div
		style="width:100%;height:100%;position:absolute;left:0px;top:0px;z-index: 12050;">
		<div class="tck_cont_style"
			style="position:relative;float: left;top: 50%;left: 50%;margin:0px -280px;">
			<div class="tck_cont_left">
				<img src="jsp/imgs/twico.jpg" />
			</div>
			<div class="tck_cont_right">
				<div class="tck_cont_close"
					onmouseout="this.className='tck_cont_close'"
					onmouseover="this.className='tck_cont_close_hover'"
					onclick="click2ClosePutQues();"></div>
				<div class="tck_cont_nr">
					<span>${tag}</span>
					<span class="tck_cont_title"> 设置悬赏金额： <select id="sel_money">
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="50">50</option>
							<option value="100">100</option>
							<option value="0">免费提问</option>
					</select> 元 </span>
					<textarea name="description" cols="" rows="" class="tck_cont_wbqy"
						style="resize:none;" id="description4putques"></textarea>
					<span class="tck_cont_bottom_qx" onclick="click2CancelPutQues();"><a
						href="javascript:void(0);">取消</a>
					</span> <span class="tck_cont_bottom" style="margin-left:0px;"
						onclick="click2PutNewQues();"><a href="javascript:void(0);">提交</a>
					</span>
				</div>
			</div>
		</div>
		<div id="pay_money_4_ask_put_alert"></div>
		<script type="text/javascript">
			function click2PutNewQues(){
				var tag = "${tag}";
				var tit = $("#description4putques").val();
				var money = $("#sel_money").val();
				var url = '<%=basePath%>' + "sigle/doSendQuestion";
				var data = {};
				data.tag = tag;
				data.tit = tit;
				data.money = money;
				var callBack = function(back) {
					if (back.status) {
						alert(back.msg);
						if (back.status == 1) {
							if(back.data){
								$("#pay_money_4_ask_put_alert").html(back.data);
							}else{
								click2ClosePutQues();
							}
						}
					}
				};
				$.post(url, data, callBack, "json");
			};

			function click2ClosePutQues() {
				$("#putnew4question").parent().empty();
			};

			function click2CancelPutQues() {
				$("#description4putques").val("");
				click2ClosePutQues();
			};
		</script>
	</div>
</div>