<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 登录内嵌弹出框 -->
<div id="login4alert_wrap">
	<div style="clear:both;"></div>
	<div style="z-index: 11000;opacity:0.6;filter : alpha(opacity=60);background:url(jsp/imgs/client/01.jpg) repeat; width:100%;height:100%;position:fixed;left:0px;top:0px;"></div>
	<div style="width:100%;height:100%;position:absolute;left:0px;top:0px;z-index: 12050;">
		<div class="login_cont_style" style="position:relative;float: left;top: 50%;left: 50%;margin:-200px -204px;">
			<div class="login_top_close"
				onmouseout="this.className='login_top_close'"
				onmouseover="this.className='login_top_close_hover'" 
				onclick="click2Close();">
			</div>
			
			<div class="login_top_title">登录！</div>
		
			<div class="login_cont_nr">
				<div class="xylogo_zh"
					style="background:url(jsp/imgs/client/166.jpg) no-repeat;">
					<!-- <input type="text" style="color:gray" value="请输入用户名" onfocus="if(this.value=='请输入用户名'){this.value=''};this.style.color='black';" onblur="if(this.value==''||this.value=='请输入用户名'){this.value='(测试)';this.style.color='gray';}">  -->
					<input type="text" class="input_zh" id="login_id" name="login_id" style="color:gray" value="请输入用户名" onfocus="if(this.value=='请输入用户名'){this.value=''};this.style.color='black';" onblur="if(this.value==''||this.value=='请输入用户名'){this.value='请输入用户名';this.style.color='gray';}" />
				</div>
				<div class="xylogo_mm" style="border-left:1px solid #d3d3d3; margin-top:22px;">
					<input type="password" class="input_zh" id="login_pwd" name="login_pwd" style="display:none"/>
					<input type="text" class="input_zh" id="login_pwd1" name="login_pwd" value="请输入密码" style="color:gray"/>
				</div>
				<div class="xylogo_wjmm_div"
					style="width:343px; text-align:left; color:#f68909;">
					<span id="login_error_tip" style="display: none;">请输入正确的用户名和密码</span>
				</div>
				<div class="login_zt_div">
					<span class="login_zt_span"><input type="checkbox"
						style="float:left; margin-right:7px; margin-top:2px;" />记住我的登录状态</span>
					<span class="login_wjmi_span"><a href="javascript:void(0);" onclick="forgetPswd()">忘记密码？</a>
					</span>
				</div>
				<div>
					<input type="button" value="登录" class="login_bottom_style"
						onmouseout="this.className='login_bottom_style'"
						onmouseover="this.className='login_bottom_style_hover'"
						style="border: 0px;"
						onclick="click2DoLogin();" />
				</div>
			</div>
		</div>
		<script type="text/javascript">
			
			$(function() {
				var pwd = $("#login_pwd1");
				var password = $("#login_pwd");
				pwd.focus(function() {
					pwd.hide();
					password.show().focus();
				});
				password.focusout(function() {
					if (password.val() == "") {
						password.hide();
						pwd.show();
					}
				});
			});

			function click2DoLogin() {
				var url = '<%=basePath%>'+"sigle/doLogin";
				var data = {};
				data.login_id = $("#login_id").val();
				data.login_pwd = $("#login_pwd").val();
				var callBack = function(back){
					//alert(back.msg);
					if(back.status == 1){
						// click2Close();
						var tmpUrl = "${sessionScope.UrlGo2}";
						if(tmpUrl.indexOf("/") == 0){
							tmpUrl = tmpUrl.substring(1);
						}
						window.location.href = '<%=basePath%>'+tmpUrl;
					}else{
						$("#login_error_tip").show();
					}
				};
				$.post(url,data,callBack,"json");
			};
			
			function click2Close(){
				$("#login4alert_wrap").parent().empty();
			};
			
			
			function forgetPswd(){
				window.location.href = '<%=basePath%>'+"client/forgetPswd";
			}
		</script>
	</div>
</div>