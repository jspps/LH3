<%@ page language="java" pageEncoding="UTF8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>个人中心 - 学习--已购课程</title>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/onesigle_style.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/usermanager.css" />
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/onesigle/common.js"></script>
<script type="text/javascript" language="JavaScript" charset="utf-8"
	src="jsp/js/onesigle/user_common.js"></script>
</head>

<body class="user_bg">
	<!--头部-->
	<jsp:include page="top/head.jsp" />

	<!--内容-->
	<jsp:include page="top/learn_menus.jsp" />
	
	<div class="user_content bg_fff">
		<div class="preson_info">
			<div class="div_table f_st xxmk_info">
				<table cellspacing="0" cellpadding="0" border="0" class="table">
					<thead>
						<tr>
							<th width="350">课程名称</th>
							<th width="80">RMB</th>
							<th width="80">考币数量</th>
							<th width="100">截止期限</th>
							<th width="70">充值</th>
							<th width="70">进入考场</th>
							<th width="70">点评</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageEnt.listPages }" var="item">
							<tr>
								<td>${item.name}</td>
								<td>${item.price}元</td>
								<td>${item.kbi}</td>
								<td><p:fmtDate parttern="yyyy-MM-dd"
										value="${item.validtime}" />
								</td>
								<td><a href="javascript:void(0)" class="jrkc gmkb"
									onclick="click2BuyKBi(${item.kindid});">买考币</a></td>
								<td><a href="javascript:void(0)" class="jrkc jrkc_1"
									onclick="click2TestExam(${item.kindid});">进入考场</a></td>
								<td><a href="javascript:void(0)" class="jrkc dp" onclick="click2Appraise(${item.kindid},'${item.name}',${item.lhubid});">点评</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<p:pageTag name="pageEnt" action="sigle/learnBuy" />
		</div>
		<form action="client/knowledgePoints" method="post"
			id="fm_lb2examKind">
			<input type="hidden" value="0" id="examin_kindId"
				name="examin_kindId" />
		</form>
	</div>
	<!--弹出层-->
	<div class="tab_zz hide"></div>
	<div class="tab hide" id="hfyj">
		<div class="tab_info tab_info1">
			<div class="hfyj hfyj_1">
				<h2>
					<a href="javascript:void(0)" class="t_close"></a>
				</h2>
				<div class="jinrlc">
					<p>您购买的套餐不含此部分试题，您有2种解决方案：</p>
					<p>
						<strong>方案一：</strong>付费购买
					</p>
					<p>
						<span><input type="checkbox" />知识要点10元 </span><span><input
							type="checkbox" />全真模拟10元 </span>
					</p>
					<p>
						<span><input type="checkbox" />ITMS辅助20元 </span><span><input
							type="checkbox" />绝胜押题98元 </span>
					</p>
					<div class="tab_btn_content tab_btn_content_1">
						<input class="tab_b1" type="button" value="付款" />
					</div>
				</div>
				<div class="jinrlc_c">
					<p>
						<strong>方案二：</strong>邀请好友购买任一科目的任何套餐后您就可以免费 获取该模考模块。
					</p>
					<p>
						<span><input type="checkbox" />知识要点，邀请1个朋友购买。 </span>
					</p>
					<p>
						<span><input type="checkbox" />全真模拟，邀请1个朋友购买。 </span>
					</p>
					<p>
						<span><input type="checkbox" />ITMS辅助20元，邀请2个朋友购买。 </span>
					</p>
					<p>
						<span><input type="checkbox" />绝胜押题98元，邀请10个朋友购买。 </span>
					</p>
					<p class="jin_p2">
						把推荐号<label class="phone_c">【13812345678】</label>发给您的朋友吧！ <br />告诉TA购买时一定要输入这个推荐号，您的朋友也可以享受到一定的优惠哦！
					</p>
					<p class="jin_p">合计：需要邀请3个朋友购买</p>

					<div class="tab_btn_content tab_btn_content_1">
						<input class="tab_b1" type="button" value="确定" onclick="click2Sure();"/>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="tab hide" id="dp">
		<div class="tab_info tab_info2">
			<div class="hfyj">
				<h2>
					<a href="javascript:void(0)" class="t_close"></a>
				</h2>
				<p>我有话说：</p>
				<div class="text_div">
					<textarea id="txt4Appraise" name="txt4Appraise" style="resize:none;"></textarea>
				</div>
				<div class="div_radio" style="margin: 10px 0px 5px 0px;">
					<input type="radio" value="1" name="score_4_kind" checked="checked"/>1分 &nbsp;&nbsp;
					<input type="radio" value="2" name="score_4_kind"/>2分 &nbsp;&nbsp;
					<input type="radio" value="3" name="score_4_kind"/>3分 &nbsp;&nbsp;
					<input type="radio" value="4" name="score_4_kind"/>4分 &nbsp;&nbsp;
					<input type="radio" value="5" name="score_4_kind"/>5分 &nbsp;&nbsp;
				</div>
				<input type="hidden" name="kindid4Appraise" id="kindid4Appraise"/>
				<input type="hidden" name="kindname4Appraise" id="kindname4Appraise"/>
				<input type="hidden" name="lhubid4Appraise" id="lhubid4Appraise"/>
				<div class="tab_btn_content tab_btn_content_2">
					<input class="tab_b1" type="button" value="提交" onclick="click2SetAppraise();"/>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function click2TestExam(kindId) {
			$("#examin_kindId").val(kindId);
			$("#fm_lb2examKind").submit();
		};
		
		// 单击点评
		function click2Appraise(kindId,name,lhubid) {
			var h = 352;
			var top = $(window).scrollTop() + ($(window).height() - h) / 2;
			if (top <= 0) {
				top = 0;
			}
			$(".tab_zz").removeClass("hide");
			$("#dp").css("top", top);
			$("#dp").removeClass("hide");
			$("#kindid4Appraise").val(kindId);
			$("#kindname4Appraise").val(name);
			$("#lhubid4Appraise").val(lhubid);
		};
		
		// 确定点评
		function click2SetAppraise() {
			var txt4Appraise = $("#txt4Appraise").val();
			var kindId = $("#kindid4Appraise").val();
			var name = $("#kindname4Appraise").val();
			var lhubid = $("#lhubid4Appraise").val();
			var score_4_kind = $(":radio:checked").val();
			var url = '<%=basePath%>' + "sigle/appraise4Kind";
			var data={"kindid":kindId,"txt4Appraise":txt4Appraise,"name":name,"lhubid":lhubid,"score_4_kind":score_4_kind};
			var callFun = function(back){
				alert(back.msg);
				if(back.status == 1){
					$("#txt4Appraise").val("");
					click2CloseTab();
				}
			};
			$.post(url,data,callFun,"json");
		};

		// 单击购买考币
		function click2BuyKBi(kindId){
			var isLogin = ("${sessionScope.Customer != null}" == "true");
			var url = '<%=basePath%>';
			if(isLogin){
				url += "client/req4BuyKbi";
				var data = {};
				var callBack = function(backData){
					var jqAlert = $("#div_4_kbi");
					if(jqAlert.length == 0){
						jqAlert = $("<div id='div_4_kbi'>");
						$(document.body).append(jqAlert);
					};
					jqAlert.html(backData);
				};
				$.post(url,data,callBack);
			}else{
				getLoginHtml("/");
			};
		};
		
		function click2Sure(){
		};
	</script>
</body>
</html>