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
	<!-- <span class="tymk_right_nr_title">四、填空题</span> --> 
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
	<c:if test="${see_dj != '1'}">
	<span style="float:left; margin-top:9px;">
		<label class="mk_ctxt_cs_text" style="color:#F00; float:left;">自我评分：</label>
		<input type="text" style="width:80px; float:left; height:25px; line-height:25px; border:1px solid #dddddd;" 
			value="" id="self_score" name = "self_score" min="0" max="${examcatalog.everyScore }" />
		<label class="mk_ctxt_cs_text" style="color:#F00; float:left;">可评最高分：${examcatalog.everyScore }</label>
	</span>
	</c:if>
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
	<textarea class="tiankongdaan"></textarea>
	
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