<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="tymk_right_djjx" style="overflow-x:hidden;">
	<!-- <span class="tymk_right_nr_title">二、多项选择题</span> -->
	<span class="tymk_right_nr_title" style="display: block;height:auto;line-height: 24px;">${examcatalog.serial} ${examcatalog.title }</span>
	<span class="tymk_right_nr_tm" style="font-size: 14px;">
		<pre>${num}.${curOpt.content}</pre>
		<c:if test="${curOpt.imgPic != ''}">
		<img src="${curOpt.imgPic}" />
		</c:if>
	</span> 
	<span class="tymk_right_nr_tm"> 
		<label class="tymk_right_icobc" onclick="getCorrectHtml(${curOpt.optid});"><a href="javascript:void(0);">报错</a></label> 
		<c:if test="${curOpt.videourl != ''}">
		<label class="tymk_right_ico"><a href="${curOpt.videourl}">视频</a> </label>
		</c:if>
		<c:if test="${curOpt.voiceurl != ''}">
		<label class="tymk_right_icoyp"><a href="${curOpt.voiceurl}">音频</a> </label>
		</c:if>
	</span>
	<div style="clear: both;"></div>
</div>
<div style="clear: both;"></div>
<c:choose>
<c:when test="${see_dj != null}">
<div style="margin:10px 10px;">
	<span class="tymk_right_fx_table">
   		<table width="735" height="100" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td style="text-align:right; width: 200px;">教材位置</td>
           <td>
           	<span style="float:left; padding-left:10px;">${curOpt.position}</span>
               <span class="tymk_right_jcwz"><a href="javascript:void(0);" title="${curOpt.optid}">我要提问</a></span>
           </td>
         </tr>
         <tr>
           <td style="text-align:right; width: 200px;">解析</td>
           <td style="padding:0px 10px 0px 10px;">${curOpt.analyse}</td>
         </tr>
       </table>
   </span>
</div>
<div class="tymk_right_wb">
	<span class="mk_ctxl_list_cs" style="margin-top:9px; width:auto; margin-right:40px; padding-left:0px;">
		<label class="mk_ctxt_cs_text" style="color:#F00; float:left;">得分：20分</label>
	</span>
	
	<span onclick="hrefUrl('${num+1}','${curOpt.optid}','${curOpt.type}');" class="tymk_right_bottom" onmouseout="this.classNma='tymk_right_bottom'" onmouseover="this.calssName='tymk_right_bottom_hover'">
		<c:choose>
			<c:when test="${isOver != 0}">下一题</c:when>
			<c:otherwise>完成</c:otherwise>
		</c:choose> 
	</span>
</div>
<div class="tymk_right_wb" style="margin-top:2px; height:auto;">
	<span class="tymk_djjx_zqda" style="width:740px;">
		<label class="tymk_djjx_nd_style">您未作答</label>
		<label class="tymk_djjx_style" style="width:580px; margin-bottom:20px; padding:10px 15px 10px 15px; position:absolute; text-align:left; height:auto; line-height:20px;">正确答案:<br/>${curOpt.right_2 }</label>
	</span>
</div>
</c:when>
<c:otherwise>
<div class="tymk_right_wb" style="margin-top: 25px;">
	<span class="tymk_right_xx"> 
		<label style="float:left; margin-right:20px;">
        	<span style="font-size:20px; float:left; margin:0px 10px 0px 0px;">A</span> <input type="checkbox" class="tymk_right_xx_checkbox" value="A"  style="width:18px; float:left; height:18px;"/> 
        </label>
        <label style="float:left; margin-right:20px;">
        	<span style="font-size:20px; float:left; margin:0px 10px 0px 0px;">B</span> <input type="checkbox" class="tymk_right_xx_checkbox" value="B"  style="width:18px; float:left; height:18px;"/> 
        </label>
        <label style="float:left; margin-right:20px;">
        	<span style="font-size:20px; float:left; margin:0px 10px 0px 0px;">C</span> <input type="checkbox" class="tymk_right_xx_checkbox" value="C"  style="width:18px; float:left; height:18px;"/> 
        </label>
        <label style="float:left; margin-right:20px;">
        	<span style="font-size:20px; float:left; margin:0px 10px 0px 0px;">D</span> <input type="checkbox" class="tymk_right_xx_checkbox" value="D"  style="width:18px; float:left; height:18px;"/> 
        </label>
        <c:if test="${curOpt.answernum >= 5}">
			<label style="float:left; margin-right:20px;">
	        	<span style="font-size:20px; float:left; margin:0px 10px 0px 0px;">E</span> <input type="checkbox" class="tymk_right_xx_checkbox" value="E"  style="width:18px; float:left; height:18px;"/> 
	        </label>
		</c:if> 
		<c:if test="${curOpt.answernum >= 6}">
		 	<label style="float:left; margin-right:20px;">
	        	<span style="font-size:20px; float:left; margin:0px 10px 0px 0px;">F</span> <input type="checkbox" class="tymk_right_xx_checkbox" value="F"  style="width:18px; float:left; height:18px;"/> 
	        </label>
		</c:if> 
		<c:if test="${curOpt.answernum >= 7}">
		 	<label style="float:left; margin-right:20px;">
	        	<span style="font-size:20px; float:left; margin:0px 10px 0px 0px;">G</span> <input type="checkbox" class="tymk_right_xx_checkbox" value="G"  style="width:18px; float:left; height:18px;"/> 
	        </label>
		</c:if>
	</span>
	<span class="tymk_right_bottom"
			onmouseout="this.classNma='tymk_right_bottom'"
			onmouseover="this.calssName='tymk_right_bottom_hover'"
			onclick="hrefUrl('${num+1}','${curOpt.optid}','${curOpt.type}',true);">
		<c:choose>
		<c:when test="${isOver != 0}">下一题</c:when>
		<c:otherwise>完成</c:otherwise>
		</c:choose>
	</span>
</div>
</c:otherwise>
</c:choose>