<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>注册学习中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学了,尚学,3学了,3学" />
<meta http-equiv="description" content="注册学习中心" />
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_client.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="jsp/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="jsp/js/baseUtil.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<!--导航条-->
	<jsp:include page="../top/head_dls_xxzx.jsp" />

	<!--内容-->
	<form action="client/saveRegLhub" id="saveRegLhub">
	<div class="dlszc_cont">
		<div class="xxzx_top_div">
			<span class="dlsz_top_text">第三方专业在线模考平台，布局全国性网格化营销推广，助推您的事业腾飞！</span>
		</div>
		<div class="xxzx_left">
			<div class="xxzx_left_title">录入基本信息</div>

			<!--基本信息-->
			<div class="xxzx_left_cont">
				<div class="xxzx_left_border" style="border: 0px;">
					<div class="dlszc_left_list" style="width:480px;">
						<span class="dlszc_left_name">学习中心名称：</span> 
						<!-- ent -->
						<input type="text" class="dlszc_input"  nullmsg="学习中心名称不能为空" name="name"/>
					</div>
					<div class="dlszc_left_list" style="width:480px;">
						<span class="dlszc_left_name">学习中心类型：</span> 
						<!-- ent -->
						<input name="type" type="radio" value="1" style="float:left; margin-top:9px;" checked="checked" /> 
						<span class="dlszc_left_cs" style="width:40px; margin-right:20px;">个人</span>
						<input name="type" type="radio" value="2" style="float:left; margin-top:9px;" /> 
						<span class="dlszc_left_cs" style="width:40px;">机构</span>
					</div>
					<div class="dlszc_left_list" style="width:530px;display: none;" id="div_img_4_jg">
						<span class="dlszc_left_name">上传机构图片：</span> 
						<select class="dlszc_left_xlcd" style="height:42px; margin-right:25px; width:160px; line-height:24px;">
							<option value="1">正面</option>
							<!-- 
							<option value="2">反面</option>
							 -->
						</select> 
						<!-- nullmsg="请上传机构正反面" -->
						<input type="hidden" name="img4jg" id="img4jg" />
						<input type="file" style="margin:10px 25px 0 0;float:left;width:70px;height:25px;" name="uploadImg" id="file_img_4_jg" value="请选择上传图片"/>
						<a href="javascript:void(0);" class="xxzx_sfz_bottom" onmouseout="this.className='xxzx_sfz_bottom'" onmouseover="this.className='xxzx_sfz_bottom_hover'" onclick="uploadImg4prove('file_img_4_jg')">确定</a>
					</div>
					<div class="dlszc_left_list" style="width:480px;">
						<span class="dlszc_left_name">身份证号码：</span> 
						<!-- ent -->
						<input type="text" name="codeid" nullmsg="身份证号码不能为空" class="dlszc_input" />
					</div>
					<div class="dlszc_left_list" style="width:530px;" id="div_img_4_idcode">
						<span class="dlszc_left_name">上传身份证：</span> 
						<select id="sel_face_back" class="dlszc_left_xlcd" style="height:42px; margin-right:25px; width:160px; line-height:24px;">
							<option value="face">正面</option>
							<option value="back">反面</option>
						</select>
						<input type="hidden" name="img4idface" id="img4idface" nullmsg="请上传身份证正反面" />
						<input type="hidden" name="img4idback" id="img4idback" nullmsg="请上传身份证正反面" />
						<input type="file" style="margin:10px 25px 0 0;float:left;width:70px;height:25px;" name="uploadImg" id="file_img_4_idcode" value="请选择上传图片" />
						<a href="javascript:void(0);" class="xxzx_sfz_bottom" onmouseout="this.className='xxzx_sfz_bottom'" onmouseover="this.className='xxzx_sfz_bottom_hover'" onclick="uploadImg4prove('file_img_4_idcode')">确定</a>
					</div>
				</div>
				
				<div class="xxzx_right_border" id="div_img_face_jg" style="display: none;border: 0px;margin-top: 15px;padding-bottom: 5px">
					<div class="xxzx_right_sfz_cont">
						<span class="xxzx_right_sfz_img">
							<img src="jsp/imgs/client/d06.jpg"  class="hide" id="img_url_id_img4jg" width="195" height="123"/> 
						</span> 
						<span class="xxzx_right_zm" style="float: right;margin-right: 30px;">机构正面</span>
						<span class="xxzx_right_zmbottm" style="float: right;margin-right: 10px;">
							<a href="javascript:void(0);"  onclick="delImg('jg');">删除</a> 
						</span>
					</div>
				</div>
				
				<div class="xxzx_right_border" style="border: 0px;margin-top: 15px;padding-bottom: 10px">
					<div class="xxzx_right_sfz_cont">
						<span class="xxzx_right_sfz_img">
							<img src="jsp/imgs/client/d06000.jpg"  class="hide" id="img_url_id_img4idface" width="195" height="123"/> 
						</span> 
						<span class="xxzx_right_zm" style="float: right;margin-right: 30px;">身份证正面</span>
						<span class="xxzx_right_zmbottm" style="float: right;margin-right: 10px;">
							<a href="javascript:void(0);"  onclick="delImg('idface');">删除</a> 
						</span>
					</div>
					<div class="xxzx_right_sfz_cont">
						<span class="xxzx_right_sfz_img">
							<img src="jsp/imgs/client/d07.jpg"  class="hide" id="img_url_id_img4idback" width="195" height="123"/> 
						</span> 
						<span class="xxzx_right_zm" style="float: right;margin-right: 30px;">身份证背面</span>
						<span class="xxzx_right_zmbottm" style="float: right;margin-right: 10px;">
							<a href="javascript:void(0);"  onclick="delImg('idback');">删除</a> 
						</span>
					</div>
				</div>
			</div>
			
			<div class="xxzx_left_cont">
				<div class="dlszc_left_list">
					<span class="dlszc_left_name">省：</span> 
					<!-- ent -->
					<select name="province" nullmsg="省份不能为空" class="dlszc_left_xlcd" id="province">
						<option>选择省份</option>
						<c:forEach items="${provinces }" var="item">
							<option value="${item.provinceName }" parID="${item.id }">${item.provinceName }</option>
						</c:forEach>
					</select> 
					<span class="dlszc_left_cs">城市</span> 
					<!-- ent -->
					<select name="city" nullmsg="城市不能为空" class="dlszc_left_xlcd" id="city">
						<option>选择城市</option>
					</select>
				</div>
				<div class="dlszc_left_list">
					<span class="dlszc_left_name">详细地址：</span> 
					<!-- ent -->
					<input  name="seat" nullmsg="详细地址不能为空"  type="text" class="dlszc_input" />
				</div>
				<div class="dlszc_left_list">
					<span class="dlszc_left_name">QQ邮箱：</span> 
					<!-- ent -->
					<input name="qq"  nullmsg="qq邮箱不能为空" type="text" class="dlszc_input" />
				</div>
				
				<div class="dlszc_left_bjys" style="width:998px;">
					<div class="dlszc_left_list">
						<span class="dlszc_left_name">联系人：</span> 
						<!-- ent -->
						<input type="text" nullmsg="联系人不能为空"  class="dlszc_input" name="uname" />
					</div>
					<div class="dlszc_left_list">
						<span class="dlszc_left_name">联系电话：</span>
						<!-- ent -->
						<input name="phone" nullmsg="联系电话不能为空" type="text" class="dlszc_input"/>
						<span class="dlszc_left_name" style="width: auto;color:rgba(200,20,20,255);margin-left: 15px;">联系电话即登录用户名</span>
					</div>
					<div class="dlszc_left_list">
						<span class="dlszc_left_name">登录密码：</span> 
						<!-- ent -->
						<input type="password" id="mima1" nullmsg="登录密码不能为空" class="dlszc_input" name="lgpwd"/>
					</div>
					<div class="dlszc_left_list">
						<span class="dlszc_left_name">再次输入密码：</span> 
						<input type="password" id="mima2" nullmsg="再次输入密码不能为空"  class="dlszc_input"/>
					</div>
				</div>

			</div>


			<!--选择课程销售模式：-->
			<div class="dlszc_left_title" style="width:998px;">选择课程销售模式：</div>
			<input name="salesmode" type="radio" value="1" style="float:left; margin-top:9px;" checked="checked"/>
			<span class="dlszc_left_cs"	style="width:auto; margin-right:20px;">代理销售（由尚学在线代理团队负责销售）</span>
			<input name="salesmode" type="radio" value="2" style="float:left; margin-top:9px;" />
			<span class="dlszc_left_cs" style="width:auto; margin-right:20px;">自行销售（由自己负责销售，课程购买页不显示"使用推荐号"）</span>


			<!--录入收款账户信息-->
			<div class="dlszc_left_title_zhxx" style="width:998px;">录入收款账户信息</div>
			<div class="dlszc_left_list" style="width:499px;">
				<span class="dlszc_left_name">支付宝账号：</span> 
				<input type="text" id="alipay1" class="dlszc_input" nullmsg="支付宝账号不能为空"  name="alipay" />
			</div>
			<div class="dlszc_left_list" style="width:499px;">
				<span class="dlszc_left_name">再次输入账号：</span> 
				<input type="text"  id="alipay2" class="dlszc_input"  nullmsg="再次输入账号不能为空"/>
			</div>
			<div class="dlszc_tjxx_buttom" style="margin-left:350px;"
				onmouseout="this.className='dlszc_tjxx_buttom'"
				onmouseover="this.className='dlszc_tjxx_buttom_hover'"
				onclick="adduser('saveRegLhub');">提交信息</div>
		</div>
		<div style="clear:both;"></div>
	</div>
	</form>
	<!--底部-->
	<jsp:include page="../top/bot.jsp"></jsp:include>
	
	<script type="text/javascript">
	function delImg(uniq){
		if(uniq=='jg'){
			$("#img4jg").val("");
			$("#img_url_id_img4jg").attr("src","jsp/imgs/client/d06.jpg");
		}else if(uniq == "idface"){
			$("#img4idface").val("");
			$("#img_url_id_img4idface").attr("src","jsp/imgs/client/d06000.jpg");
		}else if(uniq == "idback"){
			$("#img4idback").val("");
			$("#img_url_id_img4idback").attr("src","jsp/imgs/client/d07.jpg");
		}
		alert("成功");
	}
	  
   function uploadImg4prove(fileId){
   		var jqFile = $("#"+fileId);
		var fileName = jqFile.val();
		if(fileName == ""){
			alert("请选择图片头像!");
			return;
		};

		//upload
		var urlPost = "";
		urlPost = '<%=basePath%>' + "client/uploadImg";
		// urlPost = "client/uploadImg";
		
		var backFun = function (data, status, e){
           	databackImg(data,fileId);
        };
        
		//xml
        $.ajaxFileUpload({
            "url":urlPost, 
            "secureuri":false, 
            "fileElementId":fileId, 
            "dataType": "json",
            "success": backFun,
            "error": backFun
        });
	}
	   
   function databackImg(data,fileid){
		var val = data.responseText;
		var aa= 5;
		if(window.navigator.userAgent.indexOf("Chrome") !== -1){
			aa = 59;
		}
		data = val.substring(aa,val.length-6);
		data = jQuery.parseJSON(data);
		if(data.status==1){
			var url=data.msg;
			if(fileid == "file_img_4_idcode"){
				var zf = $("#sel_face_back").val();
				if(zf == "face"){
					$("#img4idface").val(url);
					$("#img_url_id_img4idface").attr("src",url);
					$("#img_url_id_img4idface").show();
				}else{
					$("#img4idback").val(url);
					$("#img_url_id_img4idback").attr("src",url);
					$("#img_url_id_img4idback").show();
				}
			}else{
				$("#img4jg").val(url);
				$("#img_url_id_img4jg").attr("src",url);
				$("#img_url_id_img4jg").show();
			}
			alert("成功");
	    }else{
	        alert(data.msg);
	    }
	}
	   
	var savebool = true;
	function adduser(formId){
		var bool = true;
		if(bool && savebool && jiaoyan('#'+formId+' :input')) {
			var ma1 =  $("#mima1").val();
			var ma2 = $("#mima2").val();
			if(bool && ma1!=ma2){
				alert("两次密码不一致！");
				bool = false;
			} 
			
			if(bool && ma1.length<6 || ma1.length>20){
				alert("密码在6-20位之间！");
				bool = false;
			}
			var alipay1 =  $("#alipay1").val();
			var alipay2 = $("#alipay2").val();
			if(bool && alipay1!=alipay2){
				alert("两次支付宝帐号不一致！");
				bool = false;
			};
			var province = $("#province").val();
			if(province == ""){
				alert("请选择省份！");
				bool = false;
			}
			var city = $("#city").val();
			if(province == ""){
				alert("请选择城市！");
				bool = false;
			}
			if(bool){
				savebool = false;
				jQuery.post($("#"+formId).attr('action'), $("#"+formId).serialize(), function(data) {
					
					savebool = true;
					alert(data.msg);
					if(data.status==1){
						  window.location.href = '<%=basePath%>'+"xxzx";
					}
				}, "json");
			}
			
		
			}
	};
	
	$(function(){
		$(":radio[name='type']").change(function(){
			var val = $(this).val();
			var div1 = $("#div_img_4_jg");
			var div2 = $("#div_img_face_jg");
			if(val == 1){
				div1.hide();
				div2.hide();
			}else{
				div1.show();
				div2.show();
			}
		});
		
		$("#province").change(function() {
			var optSel = $(this).children("option:selected"); 
			var parID = optSel.attr("parID");
			var text = optSel.text();
			var url = '<%=basePath%>' + "getCityJson";
			var callBackFun = function(backData){
				if(backData.status == 1 && backData.data){
					var jqCity = $("#city"); 
					jqCity.empty();
					jqCity.append($("<option>",{"text":"选择城市","selected":"selected"}));
					var lens = backData.data.length;
					for(var i = 0; i < lens; i++){
						var tmpEn = backData.data[i];
						var jqOption = $("<option>").text(tmpEn.cityName);
						jqOption.val(tmpEn.cityName);
						jqOption.attr("provincid",tmpEn.pid);
						jqCity.append(jqOption);
					};
				};
			};
			$.post(url,{"provincid":parID},callBackFun,"json");
		});
	});
	
	</script>
</body>
</html>
