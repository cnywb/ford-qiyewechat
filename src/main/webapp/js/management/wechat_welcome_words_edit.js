$(function(){
initLeftMenu();
initEvent();

});

function initLeftMenu(){
	$(".nav-dashboard").removeClass("active");
	$(".nav-activity-wechatwelcomewordsedit").addClass("active");
	$(".nav-activity-wechatwelcomewordsedit").parent().css({"display":"block"});
}

function initEvent(){
	$("#btn_save").click(function(){
		update();
	});
}



function update(){
	var paramValue=$("#paramValue").val();
	if(paramValue==""){
		 showAlertModal('注意',"操作失败!欢迎词不能为空!");
		 return;
	}
	var submitData={"welcome":paramValue};
	 $.ajax( {//提交数据
			url : 'welcome.do',
			type : 'post',
			dataType:'text',
			data:submitData,
			success : function(result) {
			       if(result=="1"){
				    	 showAlertModal('注意',"操作成功!");
				    }else{
				    	 showAlertModal('注意',"操作失败!系统异常!");
				    }
		  
	        },error:function(){
	        	showAlertModal('注意',"网络异常！");
	       }
		});
}


function showAlertModal(title,content){
	$("#alertModal").find('h4').html(title);
	$("#alertModal").find('p').html(content);
	$("#alertModal").modal('show');
}