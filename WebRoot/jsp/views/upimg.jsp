<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<form action="${upUrl}" enctype="multipart/form-data"  method="post" target="hidden_frame_4up" onsubmit="return uploadImage();">
	<input type="file" id="uploadFile" name="uploadImg" /> <input type="submit"
		id="btnUpload" value="确定" />
	<iframe name='hidden_frame_4up' id="hidden_frame_4up" style='display:none'></iframe>
</form>
<script type="text/javascript">
	function uploadImage() {
		//判断是否有选择上传文件
		var imgPath = $("#uploadFile").val();
		if (imgPath == "") {
			alert("请选择上传图片！");
			return false;
		}

		//判断上传文件的后缀名
		var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
		if (strExtension != 'jpg' && strExtension != 'gif'
				&& strExtension != 'png' && strExtension != 'bmp') {
			alert("请选择图片文件");
			return false;
		}
		
		return true;
	}
	
	function callFun4Loaded(map){
		alert(map);
	};
</script>
