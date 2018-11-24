<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-cn" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/hhutil.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/basic/ztree/css//demo.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/basic/ztree/css/zTreeStyle/zTreeStyle.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/basic/ztree//jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/basic/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/basic/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/basic/ztree/jquery.ztree.exedit-3.5.js"></script>
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" />
	
	<script type="text/javascript">
		var setting = {
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false,
				showIcon: false
			},

			edit: {
				enable: true,
				editNameSelectAll: true,
				showRemoveBtn: showRemoveBtn,
				showRenameBtn: true
			},
			data: {
				key:{
					name: "treeName"
				},
				simpleData: {
					enable: true,
					idKey: "treeId",
					pIdKey: "treePid"
				}
			},
			callback: {
				beforeClick: beforeClick,
				beforeDrag: beforeDrag,
				beforeEditName: beforeEditName,
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				onRemove: onRemove,
				onRename: onRename,
				onDragMove: onDragMove
			}
		};
		$(function(){
			//"/backer/Basic!getDepartTree.action"
			hhutil.ajax("<%=request.getContextPath() %>/backer/Basic!getDepartTree.action",null,function(data){
				$.fn.zTree.init($("#treeDemo"), setting, data.data);
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					var node=treeObj.getNodeByParam("treeId",$('#departid',window.parent.document).val(),null);
					treeObj.selectNode(node);
			});
		});
		var log, className = "dark";
		function beforeClick(treeId, treeNode, clickFlag){
			$('#departid',window.parent.document).val(treeNode.treeId);
			$('#searchForm',window.parent.document).submit();
			return true;
		}
		//拖动节点
		function beforeDrag(treeId, treeNodes) {
			alert(treeNodes[0].treeId);
			return true;
		}
		function beforeEditName(treeId, treeNode) {
			//className = (className === "dark" ? "":"dark");
			//showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
			//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			//zTree.selectNode(treeNode);
			return true;
		}
		function showRemoveBtn(treeId, treeNode) {
			var roleid=<%=request.getSession().getAttribute("roleid")==null?"":request.getSession().getAttribute("roleid")%>;
			if(roleid=="1"){
				return true;
			}
			else{
				return false;
			}
		}
		function beforeRemove(treeId, treeNode) {
			$("#deletede",window.parent.document).click();
			$("#deletedepartId",window.parent.document).val(treeNode.treeId);
				//className = (className === "dark" ? "":"dark");
				//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				//zTree.selectNode(treeNode);
				//删除
				//var url = "<%=request.getContextPath() %>/backer/Basic!editDepart.action";
				//url += "?paramMap.op=2&paramMap.departId="+treeNode.treeId;			
				//hhutil.ajax(url,null,function(item){});
			return false;
		}
		function onRemove(e, treeId, treeNode) {
			//showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
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
			//修改
			var url = "<%=request.getContextPath() %>/backer/Basic!editDepart.action";
			url += "?paramMap.op=1&paramMap.departId="+treeNode.treeId+"&paramMap.departName="+treeNode.treeName;			
			hhutil.ajax(url,null,function(item){});
		}
		function onDragMove(event, treeId, treeNodes) {
			alert(treeId);
			alert(treeNodes);
		}
		
		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				//添加
				var url = "<%=request.getContextPath() %>/backer/Basic!editDepart.action";
				url += "?paramMap.op=0&paramMap.departId="+treeNode.treeId+"&paramMap.departName=newdepart";			
				hhutil.ajax(url,null,function(item){
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					zTree.addNodes(treeNode, {treeId:item.data.departId, treePid:treeNode.treeId, treeName:"newdepart"});			
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					var node=treeObj.getNodeByParam("treeId",item.data.departId,null);
					treeObj.selectNode(node);
					treeObj.editName(node);	
					location.href.reload();
				});
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		function validateDepart(){
			var departname=$('#departname').val();
			var url = "<%=request.getContextPath() %>/backer/Basic!validateDepart.action";
			url += "?paramMap.departname="+departname;
			hhutil.ajax(url,null,function(item){
				if(item.data!=null){
				alert("您输入的部门名称已存在，请重新输入！");
				}
				else{
					$('#departform').submit();
				}
			});
		}
		//function selectAll() {
			//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			//zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
		//}
		</script>
		<style type="text/css">
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
		</style>
<body>

<input type="hidden" value="" id="departid">
	<%String roleid=(String)request.getSession().getAttribute("roleid");%>
	<%if(roleid.equals("1")){%>
	<form id="departform" action="<%=request.getContextPath() %>/backer/Basic!insertMaxDepart.action" method="post">
	<input style="position:relative;display:inline-block;padding:6px 8px;height:30px;border-radius:3px;-moz-border-radius:3px;-webkit-border-radius:3px;" class="form-control"  id="departname"   name="pvMap.departname" placeholder="一级部门" type="text" />
	<button type="button" onclick="validateDepart()" class="btn btn-default" style="margin-top:5px;float:right;height:30px;background:#1badf2;color:#fff;border:1px solid #129ada">添加</button>
	</form>
	<%} %>
	<div style="width: 110%;border: 0px solid #d3d7d4;border-right:0px; height: 595px;float: left;SCROLLING:no;overflow:hidden">
		<ul id="treeDemo" class="ztree" style="overflow:hidden;SCROLLING:no;border:0px;height: 570px;width:90% ;background: #ffffff;font-family:verdana,'宋体','Microsoft Yahei',Tahoma,Arial;font-size: 15px"></ul>
		<style type="text/css">
			.ztree li{
				border：1px red solid;
				margin-top: 10px;
				font-size: 16px;
			}
		</style>
	</div>
</body>
</html>