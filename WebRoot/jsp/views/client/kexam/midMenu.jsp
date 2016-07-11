<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 试卷目录结构(中间的顶部信息) -->
<div class="mk_itms_top">
	<div class="mk_tims_topcont">
		<div class="mk_tims_toptitle">${kind.nmProduct }</div>
		<c:if test="${!isBuy }">
		<div class="mk_tims_topbottom"
			onmouseout="this.className='mk_tims_topbottom'"
			onmouseover="this.className='mk_tims_topbottom_hover'"
			onclick="click2BuyKind(${kind.id});">
		</div>
		<form action="client/buyLessonsPage" method="post" id="fm_2buy_kind">
			<input type="hidden" id="kind_kindId" name="kind_kindId"/>
		</form>
		</c:if>
	</div>
</div>
<div class="mk_tims_dbuttom">
	<div class="mk_tims_dbuttom_cont">
		<div class="mk_tims_dbuttom_div">
			<a href="client/knowledgePoints"> <span class="mk_tims_list_div"
				style="margin-left:0px;"
				typeMenu="1">知识要点</span> </a>
			<a href="client/practiceChapters"> <span
				class="mk_tims_list_div_a"
				typeMenu="2">章节练习</span>
			</a> <a href="client/historyTopics"><span class="mk_tims_list_div_b"
				typeMenu="3">历年真题</span>
			</a> <a href="client/realSimulations"><span
				class="mk_tims_list_div_c"
				typeMenu="4">全真模拟</span>
			</a> <a href="client/winTopics"><span class="mk_tims_list_div_d"
				typeMenu="5">绝胜压题</span>
			</a> 
			<c:if test="${kind.isHasITMS}">
			<a href="javascript:void(0);"><span class="mk_tims_list_div_e"
				typeMenu="6"
				onclick="clickGo2ITMS();">ITMS辅助</span>
			</a>
			</c:if>
		</div>
	</div>
</div>
<script type="text/javascript">
	function clickGo2ITMS(){
		var isLogin = ("${sessionScope.Customer != null}" == "true");
		if(isLogin){
			var isHas = "${kind.isHasITMS}";
			if(isHas == "true"){
				window.location.href = '<%=basePath%>' + "client/assistITMS"
			}else{
				alert("该套餐不包含ITMS功能!!");
			};
		}else{
			getLoginHtml("${sessionScope.UrlPre}");
		}
	}
	
	$(document).ready(function(){
		var curType = "${curMenu4kexam}";
		curType = parseInt(curType,10);
		if(isNaN(curType))
			curType = 1;
		var jqCurSpan = $("span[typeMenu="+curType+"]");
		jqCurSpan.removeClass();
		switch(curType){
		case 1:
			jqCurSpan.addClass("mk_tims_list_div_hover");	
			break;
		case 2:
			jqCurSpan.addClass("mk_tims_list_div_ahover");
			break;
		case 3:
			jqCurSpan.addClass("mk_tims_list_div_bhover");
			break;
		case 4:
			jqCurSpan.addClass("mk_tims_list_div_chover");
			break;
		case 5:
			jqCurSpan.addClass("mk_tims_list_div_dhover");
			break;
		case 6:
			jqCurSpan.addClass("mk_tims_list_div_ehover");
			break;
		}
		
		var jqOthSpan = $("span[typeMenu][typeMenu!="+curType+"]");
		jqOthSpan.mouseout(function(){
			var jqThat = $(this);
			var type = jqThat.attr("typeMenu");
			type = parseInt(type,10);
			jqThat.removeClass();
			switch(type){
			case 1:
				jqThat.addClass("mk_tims_list_div");
				break;
			case 2:
				jqThat.addClass("mk_tims_list_div_a");
				break;
			case 3:
				jqThat.addClass("mk_tims_list_div_b");
				break;
			case 4:
				jqThat.addClass("mk_tims_list_div_c");
				break;
			case 5:
				jqThat.addClass("mk_tims_list_div_d");
				break;
			case 6:
				jqThat.addClass("mk_tims_list_div_e");
				break;
			}
		});
		
		jqOthSpan.mouseover(function(){
			var jqThat = $(this);
			var type = jqThat.attr("typeMenu");
			type = parseInt(type,10);
			jqThat.removeClass();
			switch(type){
			case 1:
				jqThat.addClass("mk_tims_list_div_hover");	
				break;
			case 2:
				jqThat.addClass("mk_tims_list_div_ahover");
				break;
			case 3:
				jqThat.addClass("mk_tims_list_div_bhover");
				break;
			case 4:
				jqThat.addClass("mk_tims_list_div_chover");
				break;
			case 5:
				jqThat.addClass("mk_tims_list_div_dhover");
				break;
			case 6:
				jqThat.addClass("mk_tims_list_div_ehover");
				break;
			};
		});
	});
</script>