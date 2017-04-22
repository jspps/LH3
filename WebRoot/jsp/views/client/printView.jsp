<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/tags" prefix="tag"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>打印界面</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<style type="text/css" media=print>
		.noprint{display : none }
	</style>
	
	<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
	<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
	<script type="text/javascript" charset="utf-8" src="jsp/js/jquery-1.11.1.js"></script>
	
	<link rel="stylesheet" type="text/css" href="jsp/css/base_print.css" />
	
	<style type="text/css">
	.break_word,pre{
		white-space:pre-wrap;
		white-space:-moz-pre-wrap;
		white-space:-pre-wrap;
		white-space:-o-pre-wrap;
		word-wrap:break-word;
		overflow: auto;
	}
	</style>

  </head>
  
  <body>
    <div id="noprint_menu" class="noprint">
    	<!-- <object classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" width="0" id="WebBrowser"></object> -->
    			
		<!-- 打印预览 -->
		<div class="print-box">
			<span class="print-title">
				打印预览
			</span>
			<span class="select-list">
				<span class="mechanism">
					<input type="checkbox" id="mechanism-logo" class="regular-checkbox" name="is_printLogo" id="is_printLogo" onchange="OnChangeLogo(this.checked)">
					<label for="mechanism-logo">
						<i>机构LOGO</i>
					</label>
				
				</span>
	
				<span>
					<input type="checkbox" id="contain-answer" class="regular-checkbox" name="is_printAnswer" id="is_printAnswer" onchange="OnChangeAnswer(this.checked)">
					<label for="contain-answer">
						<i>包含答案</i>
					</label>
				</span>
				
				
			</span>
			
			<span class="print-button-box t-center">
				<!-- <input type="button" class="print-button" value="预览" onclick="pagePreview()"/>  --> 
				<input type="button" class="print-button" value="打印" onclick="pagePrint()"/>
				<!-- <input type="button" class="print-button" value="页面设置" onclick="pageSetup()"/>  -->
			</span>
			<div style="padding: 10px 0 0 0"></div>
		</div>
    </div>
	<div>
		<div class="p-container persen-index">
			<div class="logo-box">
				<a href="javascript:void(0)">
					<img src="${lhub.img4logo}" width="136" height="84" id="lh_logo" />
				</a>
			</div>

			<div class="persen-content">
				<h1 class="test-title t-center">${exam.name }</h1>
				<p class="test-dec t-center">
					<span class="time">考试时间：${exam.examtime }分钟</span>
					<span class="mark">总分：${exam.score }分</span>
				</p>

				<!-- 考生信息 -->
				<div class="examinee-info">
					<span class="name">
						<label>姓名：</label>
						<input type="text" class="examinee-input" name="">
					</span>
					<span class="school">
						<label>学校：</label>
						<input type="text" class="examinee-input" name="">
					</span>
					<span class="class-name">
						<label>班级：</label>
						<input type="text" class="examinee-input" name="">
					</span>
				</div>

				<!-- 试题说明 -->
				<dl class="question-explain">
					<dt>试题说明：</dt>
					<dd>${exam.descstr }</dd>
				</dl>

				<!-- 题号分值 -->
				<table class="score">
					<col width="100px" />
					<c:forEach begin="0" end="${lens - 1}" step="1">
					<col width="136px" />
					</c:forEach>
					<col width="136px" />
					<thead>
						<tr>
							<th>题号</th>
							<c:forEach items="${list}" var="item">
								<th>${item.serial }（${item.everyScore * item.num}分）</th>
							</c:forEach>
							<th>总分（${exam.score}分）</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td>得分</td>
							<c:forEach items="${list}" var="item">
							<td>
							<c:if test="${isShowAnswer}">
								<tag:map key="${item.serial}" data="${statistics}" defVal="0"></tag:map>
							</c:if>
							</td>
							</c:forEach>
							<td>
								<c:if test="${isShowAnswer}">
								<tag:map key="all" data="${statistics}" defVal="0"></tag:map>
								</c:if>
							</td>
						</tr>
					</tbody>
				</table>

				<!-- 测试题列表	 -->
				<div class="question-box">
<c:forEach items="${details}" var="ent">
<c:set var="num" value="1"></c:set>
<div class="question-type">
	<c:choose>
	<c:when test="${ent.catalogType == 7 && ent.gid > 0}">
	<div class="question-type-title">${ent.title }</div>
	</c:when>
	<c:otherwise>
	<div class="question-type-title">${ent.serial}、${ent.title }</div>
	</c:otherwise>
	</c:choose>
	<c:forEach items="${ent.listChild}" var = "child">
	<div class="question-list">
		<div class="question-title" style="font-size: 18px;">
		<c:choose>
		<c:when test="${child.isOldContent }">
<pre>${num}.${child.content}</pre>
		</c:when>
		<c:otherwise>
		<tag:content index="${num}" content="${child.content}"></tag:content>
		</c:otherwise>
		</c:choose>
		</div>
		<c:if test="${child.imgPic != null && child.imgPic != '' }">
		<br/>
		<img src="${child.imgPic}" />
		</c:if>
		<div class="answer-box">
			<span class="l">
				<i class="">正确答案：</i>
				<em class="right">【${child.right_2}】</em>
			</span>
			<span>
				<i class="">教材位置：</i>
				<em>${child.position}</em>
			</span>
		</div>

		<div class="result-box">
			<span class="l">
				<i class="">答题结果：</i>
				<em class="error">
				<tag:map key="${child.optid}" data="${answers }" defVal="未答"></tag:map>
				</em>
			</span>
			<span>
				<i class="">得分：</i>
				<em class="error">【<tag:map key="${child.optid}" data="${scores }" defVal="0"></tag:map>分】</em>
			</span>
		</div>

		<p class="analysis">
			<em>本题解析：</em>${child.analyse}
		</p>
		<div style="padding: 8px 0 0 0"></div>	
	</div>
	<c:set var="num" value="${num + 1}"></c:set>
	</c:forEach>
</div>
</c:forEach>
				</div>

			</div>

		</div>
	</div>
	<script type="text/javascript">
	function OnChangeLogo(isShow){
		isShow = !!isShow;
		if(isShow){
			$("#lh_logo").show();
		}else{
			$("#lh_logo").hide();
		}
	}
	
	function OnChangeAnswer(isShow){
		isShow = !!isShow;
		if(isShow){
			$(".answer-box").show();
			var isShowAnswer = ${isShowAnswer};
			if(isShowAnswer)
				$(".result-box").show();
			$(".analysis").show();
		}else{
			$(".answer-box").hide();
			$(".result-box").hide();
			$(".analysis").hide();
		}
	}
	
	/***
	// 这个只支持IE浏览器
	var WebBrowser = document.getElementById("WebBrowser");
	function pagePreview()  
    {  
        WebBrowser.ExecWB(7,1);
    }  
  
    function pagePrint()  
    {  
        WebBrowser.ExecWB(6,1);
    }  
  
    function pageSetup()  
    {  
        WebBrowser.ExecWB(8,1);  
    }  
    */
    
    function pagePrint()  
    {  
        prePagePrint(11);
    }
    
    function prePagePrint(page){
    	if(page < 10){
	    	var bdhtml = window.document.body.innerHTML;//获取当前页的html代码
	    	var sprnstr = "<!--startprint"+oper+"-->";//设置打印开始区域
			var eprnstr = "<!--endprint"+oper+"-->";//设置打印结束区域
			var prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html
			
			var prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
			window.document.body.innerHTML = prnhtml;
			window.print();
			window.document.body.innerHTML = bdhtml;
		}else{
			window.print();
		}
    }
	
	$(function(){
		OnChangeLogo();
		OnChangeAnswer();
	});
	</script>
  </body>
</html>
