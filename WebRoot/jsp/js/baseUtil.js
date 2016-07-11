function delMp34(delId,fileId,hiddenId,mp34,uploadUrl){
	$("#"+delId).click(function(){
		if($("#"+hiddenId).val()==""|| $("#"+hiddenId).val()==null){
			alert("上未上传，或已经删除！");
		}else{
			$("#"+hiddenId).val("");
			$("#"+mp34).attr("href","javascript:void(0);");
			$("#"+mp34).html("");
			alert("成功！");
		}
	});
}

function uploadMp34(fileId,hiddenId,mp34,uploadUrl){
  	    //图片上传
  	    $(document).on("change","#"+fileId,function(){
  	        var $this=$(this);
  	        $.ajaxFileUpload({
  	            url:uploadUrl, 
  	            secureuri:false, 
  	            fileElementId:$this.attr("id"), 
  	            dataType: 'xml',
  	            success: function (data, status){
  	            	 databack(data,fileId,hiddenId,mp34,uploadUrl);
  	            },
  	            error: function (data, status, e){
  	            	 databack(data,fileId,hiddenId,mp34,uploadUrl);
  	            }
  	        });    
  	    });
  	} 
function databack(data,fileId,hiddenId,mp34,uploadUrl){
	var val = data.responseText;
	var aa= 5;
	if(window.navigator.userAgent.indexOf("Chrome") !== -1){
		aa = 59;
	}
	data = val.substring(aa,val.length-6);
	data = jQuery.parseJSON(data);
	if(data.status==1){
        var url=data.msg;
         $("#"+hiddenId).val(url);
         $("#"+mp34).attr("href",url);
         if("center/uploadMp3"==uploadUrl){
        	 $("#"+mp34).html("音频文件");
         }else{
        	 $("#"+mp34).html("视频文件");
         }
         alert("成功");
    }else{
        alert(data.msg);
    }
}



function jiaoyan(id){
	var bool = true;
	jQuery(id).each(function(){
		   var msg = $(this).attr("nullmsg");
		   var val = $(this).val();
		   if(msg!="false" && msg!=undefined){
			   if(val=="" && bool){
				   alert(msg); 
				   $(this).focus(); 
				   bool = false;
			   }
		   }
		   var zz =  $(this).attr("zz");
		   if(zz!="" && zz!=undefined && val!=""  && bool){
			   if(zz=="比例"  && bool){
				   if(val.indexOf(".")>=0){
					   alert("请输入正确的比例,不能为小数"); 
					   $(this).focus(); 
					   bool = false;
				   }else{
					   val = parseFloat(val); 
					   if(val>=0 && val<=100){}else{
						   alert("请输入正确的比例,比例在0-100之间！"); 
						   $(this).focus(); 
						   bool = false;
					   }
				   }
			   }
			   if(zz=="价格"  && bool){
				   val = parseFloat(val); 
				   if(val>=0 && val<=10000000){}else{
					   var patrn = /^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/;
			    		if(!patrn.exec(val)){
						   alert("请输入正确的价格,比例在0-1亿之间！"); 
						   $(this).focus(); 
						   bool = false;
			         }
				   }
			   }
			   if(zz=="正数"  && bool){
				   val = parseFloat(val); 
				   if(val>=1 && val<=1000000){}else{
					   alert("请输入数值,在1-1百亿之间！"); 
					   $(this).focus(); 
					   bool = false;
				   }
			   }
			   if(zz=="电话"  && bool){
				   if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(val)){
					   alert("请输入正确的电话号码！"); 
					   $(this).focus(); 
					   bool = false;
				   }
			   }
			  
		   }
	   }); 
	return bool;
}