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
            name: "departName"
        },
        simpleData: {
            enable: true,
            idKey: "departId",
            pIdKey: "departParentId"
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
    $.ajax({url:"tree.do",data:null,success:function(data){
        data=JSON.parse(data);
        $.fn.zTree.init($("#treeDemo"), setting, data);
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var node=treeObj.getNodeByParam("treeId",$('#departid',window.parent.document).val(),null);
        treeObj.selectNode(node);
    }});

});
var log, className = "dark";
function beforeClick(id, treeNode, clickFlag){
    $('#queryDepartId',window.parent.document).val(treeNode.departId);
    $('#searchForm',window.parent.document).submit();
    return true;
}
//拖动节点
function beforeDrag(id, treeNodes) {
    alert(treeNodes[0].treeId);
    return true;
}
function beforeEditName(id, treeNode) {


}
function showRemoveBtn(id, treeNode) {
        var roleid=1;
    if(roleid=="1"){
        return true;
    }
    else{
        return false;
    }
}
function beforeRemove(treeId, treeNode) {

}
function onRemove(e, treeId, treeNode) {
    var params={id:treeNode.id}
    $.ajax({url:'delete.do',data:params,type:'post',dataType: "json",success:function(data){
        if(data==1){
            return;
        }
        alert("删除部门失败");
    }});
    return true;
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
function onRename(e, id, treeNode, isCancel) {
    //修改
    var params={id:treeNode.departId,departName:treeNode.departName};
    $.ajax({url:'handler.do',data:params,type:'post',success:function(data){
            console.log(data);
    }});
}
function onDragMove(event, treeId, treeNodes) {

}


function addHoverDom(treeId, treeNode) {

    var spanId=treeNode.tId+'_span';
    var btnId=treeNode.tId+'_btn';
    var sObj = $('#'+spanId);
    if (treeNode.editNameFlag || $('#'+btnId).length>0) return;
    var addStr = "<span class='button add' id='"+btnId+ "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $('#'+btnId);
    if (btn){
        btn.bind("click", function(){
            //添加
            var params={departName:'新部门'+treeNode.id,parentId:treeNode.id};
            $.ajax({url:'handler.do',data:params,type:'post',dataType: "text",success:function(data){
                if(data>0){
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    treeObj.addNodes(treeNode,{departId:data,departParentId:treeNode.id,departName:params.departName});
                    treeObj.reAsyncChildNodes(null,'refresh');
                    var node=treeObj.getNodeByParam("departId",data);
                    treeObj.selectNode(node);
                    treeObj.editName(node);
                    return;
                }else if(data==-1){
                    alert('该部门已经存在');
                }

            }});
            return false;
        });
    }
};
function removeHoverDom(treeId, treeNode) {

    $('#'+treeNode.tId+'_btn').unbind().remove();
};

$(function(){
    $('#addDepart').bind('click',function(){
        var departName=$("#departname").val();
        if(departName==''){
            alert('请输入一级部门名称');
            return;
        }
        var params={departName:departName,parentId:1};
        $.ajax({url:'handler.do',data:params,type:'post',success:function(data){
            if(data>1){
                alert("添加一级部门成功");
                location.reload();
                return;
            }
            alert('该部门已存在!');
        }});
    });

});