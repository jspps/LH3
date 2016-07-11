<%@ page language="java" pageEncoding="UTF8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>个人中心 - 设置--基本资料</title>
<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/onesigle_style.css" />
<link rel="stylesheet" type="text/css" href="jsp/css/usermanager.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="jsp/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="jsp/js/onesigle/common.js"></script>
<script type="text/javascript" src="jsp/js/onesigle/user_common.js"></script>
</head>

<body class="user_bg">
	<!--头部-->
	<jsp:include page="top/head.jsp" />

	<!--内容-->
	<div class="user_content">
		<div class="user_chlidmenu">
			<a href="sigle/setBinfo" class="current">基本资料</a> <a href="sigle/setPwd">密码设置</a> <a href="sigle/setAccount">修改账号</a>
			<i class="clear"></i>
		</div>
	</div>
	<div class="user_content bg_fff">
		<form action="sigle/doChangeBInfo" method="post" id="fm_changeBaseInfo">
		<table class="f_st person_table1" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<th>帐号：</th>
				<td><input type="text" class="p_input_1 p_w1" name="accountlgid" value="${account.lgid }" disabled="disabled"/></td>
			</tr>
			<tr>
				<th>姓名：</th>
				<td><input type="text" class="p_input_1 p_w1" name="cname" value="${customer.name }"/></td>
				<td rowspan="4" class="">
					<div class="phone_info">
						<span> 
							<!-- jsp/imgs/onesigle/usermanange/bg.jpg -->
							<img src="${headImg }" width="180" height="181" id="info_img" />
							<span style="color: rgba(47, 170, 212,255);">单击下面按钮选取新头像</span>
							<input type="file" name="uploadImg" id="up_img4file" style="width: 200px;height: 26px;"/>
						</span>
						<a href="javascript:void(0);" class="sctx" onclick="click2Upfile();"></a>
						<input type="hidden" name="cicon" id="img_url" value="${headImg }"/>
					</div>
				</td>

			</tr>
			<tr>
				<th>邮箱：</th>
				<td><input type="text" class="p_input_1 p_w1" name="cemail" value="${customer.email }"/></td>
			</tr>
			<tr>
				<th>省：</th>
				<td colspan="2">
				<select class="dlszc_left_xlcd" name="cprovince" id="sel_province">
					<option>${customer.province}</option>
					<c:forEach items="${provinces }" var="item">
						<option value="${item.provinceName }" parID="${item.id }">${item.provinceName }</option>
					</c:forEach>
				</select> 
				<label>城市</label>
				<select class="dlszc_left_xlcd" name="ccity" id="sel_city">
					<option>${customer.city}</option>
				</select>
				</td>
			</tr>

			<tr>
				<th>详细地址：</th>
				<td colspan="2"><input type="text" class="p_input_1 p_w1" name="cseat" value="${customer.seat }"/></td>
			</tr>
			<tr>
				<th>简介：</th>
				<td colspan="2"><textarea class="p_text" name="cdescr" style="resize: none;" value="${customer.descr }"></textarea>
				</td>
			</tr>

			<tr>
				<th></th>
				<td style="text-align:right;" colspan="2"><input type="button"
					class="btn_yes mt_10" value="确定" onclick="click2ChangeBaseInfo();" /></td>
			</tr>
		</table>
		</form>
	</div>
	<script type="text/javascript">
		function click2Upfile(){
			var jqFile = $("#up_img4file");
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
            	databackImg(data);
            };

			//xml
	        $.ajaxFileUpload({
	            "url":urlPost, 
	            "secureuri":false, 
	            "fileElementId":"up_img4file", 
	            "data":{"cmd":"baseInfoHead"},
	            "dataType": "json",
	            "success": backFun,
	            "error": backFun
	        }); 
		};
		
		function databackImg(back){
			var val = back.responseText;
			var aa= 5;
			if(window.navigator.userAgent.indexOf("Chrome") !== -1){
				aa = 59;
			}
			back = val.substring(aa,val.length-6);
			back = jQuery.parseJSON(back);
			if(back.status==1){
		         var imgUrl = back.msg;
		         $("#img_url").val(imgUrl);
		         $("#info_img").attr("src",imgUrl);
		         alert("成功");
		    }else{
		        alert(back.msg);
		    }
		}

		function click2Changefile() {
			var jqFile = $("#up_img4file");
			jqFile.click();
		};
		
		function click2ChangeBaseInfo(){
			var jqFm = $("#fm_changeBaseInfo");
			var url = jqFm.attr("action");
			var fmEJq = jqFm[0];
			var data = jqFm.serialize();
			
			if(fmEJq.cname.value == ""){
				alert('请输入名字!');
				return;
			};
			
			if(fmEJq.cicon.value == ""){
				alert('请选择头像!');
				return;
			};
			if(fmEJq.cemail.value == ""){
				alert('请输入正确的邮箱!');
				return;
			};
			
			if(fmEJq.cprovince.value == ""){
				alert('请选择省份!');
				return;
			};
			
			if(fmEJq.ccity.value == ""){
				alert('请选择城市!');
				return;
			};
			
			if(fmEJq.cseat.value == ""){
				alert('请输入详细地址!');
				return;
			};
			
			var callBackFun = function(backData){
				alert(backData.msg);
			};
			
			$.post(url,data,callBackFun,"json");
		};
		
		$(document).ready(function(){
			$("#sel_province").change(function() {
				var optSel = $(this).children("option:selected"); 
				var parID = optSel.attr("parID");
				var text = optSel.text();
				var url = '<%=basePath%>' + "getCityJson";
				var callBackFun = function(backData){
					if(backData.status == 1 && backData.data){
						var jqCity = $("#sel_city"); 
						jqCity.empty();
						jqCity.append($("<option>",{"text":"${customer.city}","selected":"selected"}));
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