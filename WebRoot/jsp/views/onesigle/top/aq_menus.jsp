<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="user_content">
	<div class="user_chlidmenu">
		<a href="javascript:void(0);" class="wytw">我要提问</a> 
		<a type="4" href="sigle/info4question">尚学提问</a> 
		<a type="1" href="sigle/question?isMyQuestion=true&type=1">我的提问</a> 
		<a type="2" href="sigle/question?isMyQuestion=false&type=1">同学有问</a>
		<a type="3" href="sigle/answer4Satisfied">满意回答</a>
		<i class="clear"></i>
	</div>
</div>
<script type="text/javascript">
	var menuChild = "${menuChild}";

	$(document).ready(function() {
		menuChild = parseInt(menuChild, 10);
		if (isNaN(menuChild))
			menuChild = 1;
		
		var list = $("div.user_content a[type]");
		
		list.each(function() {
			var that = $(this);
			that.removeClass();
			var tpVal = that.attr("type");
			tpVal = parseInt(tpVal, 10);
			if (tpVal == menuChild) {
				that.addClass("current");
			};
		});
			
		$(".wytw").click(function(){
			var h = 414;
			var top = $(window).scrollTop()+($(window).height()-h)/2;
			if(top<=0){top=0;}
			$("#tab_zz").removeClass("hide");
			$("#gmkb").css("top",top);
			$("#gmkb").removeClass("hide");
		});
	});
	
</script>

<div style="clear:both;"></div>
<div class="tab_zz hide" id="tab_zz" style="z-index: 1000;"></div>
<div class="tab hide" id="gmkb" style="z-index: 1050;">
	<div class="tab_info tab_info5">
		<div class="hfyj">
			<h2>
				<a href="javascript:void(0)" class="t_close"></a>
			</h2>
			<p class="gmkb_p3">
				<jsp:include page="../../stepCourses.jsp" />
			</p>
			<p class="gmkb_p3">
				<textarea class="gmkb_p_text" style="resize:none;"
					id="question_title"></textarea>
			</p>
			<p class="gmkb_p4">说明：提交问题后如果7天之内还没有答复，您可以选择放弃
				悬赏求助，悬赏金将在次日返回您的账户。您也可以增加悬 赏金额重新悬赏，有助于尽快得到最佳答案。</p>
			<p class="gmkb_p5">
				设置悬赏金额： <select id="sel_money">
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="0">免费提问</option>
				</select> 元
			</p>
			<div class="tab_btn_content tab_btn_content_2">
				<input class="tab_b1" type="button" value="提交"
					onclick="click2Quesition();" />
			</div>
		</div>
	</div>
	<div id="pay_money_4_ask"></div>
</div>
<script type="text/javascript">
	function click2Quesition(){
		var depart = $("#departid option:selected").html();
		var major = $("#nmMajor option:selected").html();
		var lev = $("#nmLevel option:selected").html();
		var sub = $("#nmSub option:selected").html();
		
		var tag = depart+major+lev+sub;
		var tit = $("#question_title").val();
		var money = $("#sel_money").val();
		var url = '<%=basePath%>'+"sigle/doSendQuestion";
		var data = {};
		data.tag = tag;
		data.tit = tit;
		data.money = money;
		var callBack = function(back){
			if(back.status){
				alert(back.msg);
				if(back.status == 1){
					if(back.data){
						$("#pay_money_4_ask").html(back.data);
					}else{
						window.location.reload();
					}
				}
			}
		};
		$.post(url,data,callBack,"json");
	};
</script>