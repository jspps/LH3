<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="top_style">
	<div class="top_center">
		<div class="logo_div">
			<a href="client/home"><img src="jsp/imgs/client/logo.jpg" /> </a>
		</div>
		<div class="dhl_div">
			<ul class="dhl_ul" style="color: rgb(180, 180, 180);">
				<li type="1">首页</li>
				<li type="2" style="margin-right:40px;">考试榜</li>
				<li type="3">全部课程</li>
			</ul>
		</div>
		<div class="login_cont" style="width: auto;">
			<div class="login_cont_ico">
				<img src="jsp/imgs/client/02.jpg" />
			</div>
			<div class="login_cont_text" style="width: auto;">
				<a href="javascript:void(0);" onclick="click2JugdeLogin();" id="a_4_lg_info">
				</a>
			</div>
		</div>
	</div>
</div>
<div class="drift">
	<!--清除浮动-->
</div>

<script type="text/javascript">
	var curTop = "${sessionScope.CurTop}";

	$(document).ready(function() {
		curTop = parseInt(curTop, 10);
		if (isNaN(curTop))
			curTop = 1;
		
		var ullis = $("div.dhl_div ul.dhl_ul li[type]");
		ullis.mouseover(function() {
			$(this).css("cursor", "pointer");
		});
		
		ullis.each(function() {
			var that = $(this);
			that.mouseover(function() {
				$(this).css({
					"cursor" : "pointer",
					"color" : "#f56120"
				});
			});

			that.mouseout(function() {
				$(this).css({
					"cursor" : "pointer",
					"color" : "rgb(180, 180, 180)"
				});
			});

			that.removeClass();
			var tpVal = that.attr("type");
			tpVal = parseInt(tpVal, 10);
			if (tpVal == curTop) {
				that.addClass("dhl_li_hover");
			}
			;

			that.click(function() {
				switch (tpVal) {
				case 2:
					window.location.href = '<%=basePath%>'+"client/newTestList";
					break;
				case 3:
					window.location.href = '<%=basePath%>'+"client/allClass";
					break;
				default:
					window.location.href = '<%=basePath%>'+"client/home";
					break;
				}
			});
		});
		
		reset4LoginInfo();
	});
	
	function click2JugdeLogin(){
		var blStr = "${sessionScope.Customer != null}";
		if (blStr == "true") {
			window.location.href = '<%=basePath%>'+"sigle/examRecord";
		}else{
			getLoginHtml();
		}
	};
	
	function reset4LoginInfo(){
		var blStr = "${sessionScope.Customer != null}";
		if (blStr == "true") {
			var name = "${sessionScope.Customer.name}";
			var len4name = name.length;
			if(len4name == 32){
				name = name.substring(0, 16);
			}else if(len4name > 8){
				name = name.substring(0, 8);
			}
			$("#a_4_lg_info").html(name);
		}else{
			$("#a_4_lg_info").html("登陆");
		}
	};
	
	function getLoginHtml(loginedUrl){
		var url = '<%=basePath%>'+"client/login4Alert";
		if(!loginedUrl)
			loginedUrl = "sigle/examRecord";
		var data = {"loginedUrl":loginedUrl};
		var jqAlert = $("#div_4_alert");
		if(jqAlert.length == 0){
			jqAlert = $("<div id='div_4_alert'>");
			$(document.body).append(jqAlert);
		};
		var callFun = function(back){
			jqAlert.html(back);
		};
		$.post(url,data,callFun);
	};
</script>