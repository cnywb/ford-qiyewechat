$(function(){
	initLeftMenu();
	initTree();
	initEvent();
});

function initLeftMenu(){
	$(".nav-dashboard").removeClass("active");
	$(".nav-activity-qygl").addClass("active");
	$(".nav-activity-qygl").parent().css({"display":"block"});
}

function initEvent(){
	$("#btn_add").click(function(){
		$("#parentId").val("0");
		$("#addModal").modal("show");
	});

	$('#addArea').reset();
}

function initTree(){
	$.ajax( {
		url :'all.do',
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
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showAddBtn: false,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onRename: onRename
		}
	};

var  className = "dark";
function initZTree(data){
$.fn.zTree.init($("#treeDemo"),setting,data);
}

var updateTreeNode;
function beforeEditName(treeId, treeNode) {
	className = (className === "dark" ? "":"dark");
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	updateTreeNode=treeNode;
	setEditParams('editModal', treeNode);
	$('#editModal').modal('show');
	return false;//false表示不显示编辑状态
}
var removeTreeNode;
function beforeRemove(treeId, treeNode) {
	className = (className === "dark" ? "":"dark");
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	removeTreeNode=treeNode;
	if(confirm("确认删除 " + treeNode.name + "节点 吗？")){
		doDelete(treeNode.id);
	}
	return false;
}
function onRemove(e, treeId, treeNode) {
	//alert("remove finished");
}
function beforeRename(treeId, treeNode, newName, isCancel) {
	className = (className === "dark" ? "":"dark");
	if (newName.length == 0) {
		alert("节点名称不能为空.");
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		setTimeout(function(){zTree.editName(treeNode)}, 10);
		return false;
	}
	return true;
}
function onRename(e, treeId, treeNode, isCancel) {
	
}
/**
 * 是否显示删除按钮
 * @param treeId
 * @param treeNode
 * @returns {Boolean}
 */
function showRemoveBtn(treeId, treeNode) {
	
	return true;
}
/**
 * 是否显示编辑按钮
 * @param treeId
 * @param treeNode
 * @returns {Boolean}
 */
function showRenameBtn(treeId, treeNode) {
	return true;
}


var addParentTreeNode;
function addHoverDom(treeId, treeNode) {

	$('#addArea').reset();

	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId+ "' title='add node' onfocus='this.blur();'></span>";
	
	
	if(treeNode.pId==null){
		sObj.after(addStr);
	}
	
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		addParentTreeNode=treeNode;
		 $("#parentId").val(treeNode.id); 
		 $('#addModal').modal('show');
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};


function save(){

	var frm=$('#addArea');
	var params=frm.serialize();
	if(params['parentId']==''){
		params['parentId']='0';
	}
	params['ifActive']=1;

	$.ajax( {//提交数据
			url : 'handler.do',
			type : 'post',
			dataType:'text',
			data:params,
			success : function(result) {
			       if(result=="1"){
				    	 initTree();
				    	 showAlertModal('注意',"操作成功!");
				    	 $("#addModal").modal("hide"); 
				    }else if(result=="0"){
				    	 showAlertModal('注意',"操作失败!经销商代码重复,请重新填写!");
				    }else{
				    	 showAlertModal('注意',"操作失败!系统异常!");
				    }
	        },error:function(){
	        	showAlertModal('注意',"网络异常！");
	       }
		});
}


function update(){
	var frm=$('#editArea');
	var params=frm.serialize();
	 
	 $.ajax( {//提交数据
			url : 'handler.do',
			type : 'post',
			dataType:'text',
			data:params,
			success : function(result) {
			       if(result=="1"){
				    	 initTree();
				    	 showAlertModal('注意',"操作成功!");
				    	 $("#editModal").modal("hide"); 
				    }else if(result=="0"){
				    	 showAlertModal('注意',"操作失败!经销商代码重复,请重新填写!");
				    }else{
				    	 showAlertModal('注意',"操作失败!系统异常!");
				    }
		  
	        },error:function(){
	        	showAlertModal('注意',"网络异常！");
	       }
		});
}

function doDelete(id){
	$.ajax( {//提交数据
		url : 'delete.do',
		type : 'post',
		dataType:'text',
		data:'id='+id,
		success : function(result) {
			 if(result=="0"){
				 showAlertModal('注意',"操作失败!该区域存在子区域，请先删除子区域再操作!");
		    }else if(result=="1"){
		    	 showAlertModal('注意',"操作失败!该区域已经被用户引用，无法删除!");
		    }else if(result=="2"){
		       	 initTree();
		    	 showAlertModal('注意',"操作成功!");
		    }
       },error:function(){
    	   showAlertModal('注意',"网络异常！");
      }
	});
}

function setEditParams(containerId,obj){
	console.dir(obj);
	var editAreaForm=$('#editArea');
	editAreaForm.reset();
	editAreaForm.fill(obj);
}


function showAlertModal(title,content){
	$("#alertModal").find('h4').html(title);
	$("#alertModal").find('p').html(content);
	$("#alertModal").modal('show');
}