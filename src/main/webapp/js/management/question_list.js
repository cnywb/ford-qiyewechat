$(function(){


	$('#startDate',$('#searchForm')).dateRangePicker ({
        language: 'cn',
        singleDate : true,
        showShortcuts: false,
        format: 'YYYY-MM-DD ',
        time: {
            enabled: false
        }
    });
	$('#endDate',$('#searchForm')).dateRangePicker ({
        language: 'cn',
        singleDate : true,
        showShortcuts: false,
        format: 'YYYY-MM-DD ',
        time: {
            enabled: false
        }
    });

	$('#btn-nav-tab-1').click(function(){
		$('#contentType').val(1);
	});

	$('#btn-nav-tab-2').click(function(){
		$('#contentType').val(2);
	});
	
	$("#btn_query").click(function(){
		$("#searchForm").attr("action","index.do");
		$("#searchForm").submit();
	});
	
	$("#btn_export").click(function(){
		$("#searchForm").attr("action","export.do");
		$("#searchForm").submit();
	});

	$("#delegate_btn_upload_image").click(function(){

		// 上传方法
		$.upload({
			// 上传地址
			url: lion.util.context+'/wechat/media/upload.do',
			// 文件域名字
			fileName: 'files',
			// 其他表单数据
			params: {dir: 'image'},
			// 上传完成后, 返回json, text
			dataType: 'json',
			// 上传之前回调,return true表示可继续上传
			onSend: function() {

				lion.util.loading().show();
				return true;
			},
			// 上传之后回调
			onComplate: function(data) {
				var filePath=$('input[type=file][name=files]').val();
				lion.util.loading().hide();
				if(data['error']==1){
					lion.util.warning("提示",data['message']);
					return;
				}
				$('#imageSrc').val(data['filePath']);
				$('#filePath').val(data['filePath']);
				$('#upload_image').attr('src',lion.util.context+data['filePath']);
				lion.util.success('','上传图片成功');
			}
		});
 	});
	$("#btnc01").hide();
	
	$("#treeArea").click(function(){
		$("#treeModal").modal("show");
	});
	initTree();
	initClearAreaEvent();
}); 


function answerData(id){
	initReplyData(id);
	$("#reply_depart_id").attr("data-required","false");
	$("#transform_to_department").val("");
	$("#transform_to_department").hide();
}

function setReplyAndTransferData(id){
	initReplyData(id);
	$("#reply_depart_id").attr("data-required","true");
	$("#transform_to_department").show();
}

function initReplyData(id){
	$('#quname').html($('#quname_'+id).val());
	$('#telnum').html($('#phone_'+id).val());
	$('#questionname').html($('#qucontent_'+id).val());
	$('#userid',$('#saveEditForm')).val($('#quizid_'+id).val());
	$('#quid').val(id);
}

function transferData(id){
	$('#name').html($('#quname_'+id).val());
	$('#tel').html($('#phone_'+id).val());
	$('#content').html($('#qucontent_'+id).val());
	$('#quids').val(id);
}
function replycontent(id){
	$('#replycontent').html($('#answercontent_'+id).val());
	$('#replyname').val($('#answername_'+id).val());
	$('#departname').val($('#departname_'+id).val());
}

function shareData(id){
	$('input[name="departIds"]:checked').each(function(){
		$(this).iCheck('uncheck');

	});
	$('#share_name').html($('#quname_'+id).val());
	$('#share_tel').html($('#phone_'+id).val());
	$('#share_content').html($('#qucontent_'+id).val());
	$('#share_quids').val(id);
}

function splitCheckBoxValue(checkBoxName) {
	var temp=0;
	var ids="";
	var o = document.getElementsByName(checkBoxName);
	for(var i = 0; i < o.length ; i ++){
	        if(o[i].checked == true){
	        	
	        	if(temp!=0){
	        		ids=ids+",";
	        	}
	        	ids=ids+o[i].value;
	        	temp++;
	        	}    
	}
	return ids;
}

function showAlertModal(title,content){
	$("#alertModal").find('h4').html(title);
	$("#alertModal").find('p').html(content);
	$("#alertModal").modal('show');
}
function shareQuestion(){
	 var departIds=$('input[name="departIds"]:checked');
     if(departIds.length==0){
		 lion.util.info('提示','请选择部门!');
		 return;
	 }
	 var data= {questionId:$('#share_quids').val(),departIds:departIds.val()};
	 lion.util.post('share.do',data,function(result){
		if(result==1) {
			lion.util.success('提示', '操作成功!');
			lion.util.reload();
		}else{
			lion.util.warning('提示','系统异常');
		}
	},function(xhr,status,error){
		 return;
	 });
	return;
}

function deleteQuestionAndAnswer(questionId){
	bootbox.confirm('注意,您确定要删除该条数据吗?此操作不可恢复!',function(confirm){
		if(confirm){
			lion.util.post('delete.do',{questionId:questionId},function(data){
				if(data==1) {
					lion.util.success('提示', '操作成功!');
					lion.util.reload();
				}else{
					lion.util.warning('提示','系统异常');
				}
			});
		}
	});
}

function closeQuestion(questionId){

	bootbox.confirm('注意,您确定要关闭该条数据吗?此操作不可恢复!',function(confirm){
		 if(confirm){
				lion.util.post('close.do',{questionId:questionId},function(data){
					if(data==1) {
						lion.util.success('提示', '操作成功!');
						lion.util.reload();
					}else{
						lion.util.warning('提示','系统异常');
					}
				})
		 }
	});
}




function getTextParams(){
	var retVal={};
	var text={};
	text["content"]=$("#msg_content").val();
	retVal["text"]=text;
	retVal["msgtype"]="text";
	retVal["touser"]=$("#msg_touser").val();
	retVal["safe"]="0";
	retVal["agentid"]=$("#agentid").val();
	return retVal;
}


/**
 * {
   "touser": "UserID1|UserID2|UserID3",
   "toparty": " PartyID1 | PartyID2 ",
   "msgtype": "image",
   "agentid": "1",
   "image": {
       "media_id": "MEDIA_ID"
   },
   "safe":"0"
}
 * @returns {___anonymous3094_3095}
 */
function getImageParams(){
	var retVal={};
	var image={};
	image["media_id"]=$("#media_image_id").val();
	retVal["image"]=image;
	retVal["msgtype"]="image";
	retVal["touser"]=$("#msg_touser").val();
	retVal["safe"]="0";
	retVal["agentid"]=$("#agentid").val();
	return retVal;
}

function initTree(){
	$.ajax( {
		url :'../area/all.do',
		type : 'post',
		dataType:'json',
		data: '',
		success : function(result) {
			initZTree(result);
        },error:function(){
			alert('注意,系统错误！');
     }
	});
}

var setting = {
		view: {
			dblClickExpand: false
		},data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick
		}
	};


function initZTree(data){
$.fn.zTree.init($("#treeDemo"),setting,data);
}

function beforeClick(treeId,treeNode) {
	doTreeClick(treeNode);
	return check;
}




function doTreeClick(treeNode){
	if(treeNode.pId==null){
		$("#areaname").val(treeNode.id);
		$("#samllname").val("");
	}else{
		$("#areaname").val("");
		$("#samllname").val(treeNode.id);
	}
	$("#treeArea").val(treeNode.name);
	$("#treeModal").modal("hide");
}

function initClearAreaEvent(){
	$("#btn_clear_area").click(function(){
		$("#areaname").val("");
		$("#samllname").val("");
		$("#treeArea").val("");
		$("#treeModal").modal("hide");
	});
}
