var context=function(){
    //获取应用的上下文根路径
    var pathname=window.location.pathname;
    var indexNext= pathname.indexOf('/',1);
    if(pathname.substr(0,indexNext)!='fute') {
        return '';
    }
    return pathname.substr(0,indexNext);
}();

$(function(){
    $('#showMyImg').on('click',function(){
        // 上传方法
        $.upload({
            // 上传地址
            url: context+'/image/upload.do',
            // 文件域名字
            fileName: 'files',
            // 其他表单数据
            params: {name: 'pxblog'},
            // 上传完成后, 返回json, text
            dataType: 'json',
            // 上传之前回调,return true表示可继续上传
            onSend: function() {
                return true;
            },
            // 上传之后回调
            onComplate: function(data) {
                $('#headImage').val(data['filePath']);
                $('#showMyImg').attr('src',context+data['filePath']);
            }
        });
    });

    $('.btnAdd').on('click',function(){
         initAdd();
    });

    $("#btn_query").click(function(){
        $("#searchForm").attr("action","index.do");
        $("#searchForm").submit();
    });
    $("#btn_export").click(function(){
        $("#searchForm").attr("action","export.do");
        $("#searchForm").submit();
    });
});

function deleteData(id){
    $('#deleteId',$('#deleteForm')).val(id);
}
//点击编辑，初始化form表单
function EditInfo(id){
    $('#userpass').hide();
    $('#realname',$('#saveEditForm')).val($('#realname_'+id).val());
    $('#username',$('#saveEditForm')).val($('#username_'+id).val());
    $('#position',$('#saveEditForm')).select2("val",$('#position_'+id).val());
    $('#phone',$('#saveEditForm')).val($('#phone_'+id).val());
    $('#email',$('#saveEditForm')).val($('#email_'+id).val());
    $('#wxnum',$('#saveEditForm')).val($('#wxnum_'+id).val());
    $('#departid',$('#saveEditForm')).select2("val", $('#departid_'+id).val());
    var sex=$('#sex_'+id).val();
    if(sex=="1"){
        $('#sex',$('#saveEditForm')).select2("val", "1");
    }else{
        $('#sex',$('#saveEditForm')).select2("val", "2");
    }
    $('#headImage',$('#saveEditForm')).val($('#headimage_'+id).val());
    var defaultimg=context+ '/basic/upload/nopic.jpg';
    if(!hhutil.isEmpty($('#headimage_'+id).val())){
        defaultimg =context+$('#headimage_'+id).val();
    }
    $('#showMyImg',$('#saveEditForm')).attr("src",defaultimg);

    $('#id',$('#saveEditForm')).val(id);
}
function initAdd(){

    $('#realname',$('#saveEditForm')).val("");
    $('#username',$('#saveEditForm')).val("");
    $('#position',$('#saveEditForm')).val("");
    $('#phone',$('#saveEditForm')).val("");
    $('#email',$('#saveEditForm')).val("");
    $('#wxnum',$('#saveEditForm')).val("");
    $('#departid',$('#saveEditForm')).select2("val","");
    $('#headimage',$('#saveEditForm')).val("");
    $('#showMyImg',$('#saveEditForm')).attr("src","");
    $('#id',$('#saveEditForm')).val("");
}
function markData(id){
    $('input[name="markIds"]:checked').each(function(){
        $(this).iCheck('uncheck');

    });

    var params={userId:id};
    $.ajax({url:context+'/mark/user.do',data:params,type:"post",success:function(data){
        data=JSON.parse(data);
        if(data){
            setMarkData(data);
        }
    }});


    function setMarkData(data){
        $('#marknames',$('#markEditForm')).html("");
        for(var i = 0;i<data.length;i++){
            $('#marknames',$('#markEditForm')).append("<input type='button' class='but' value='"+data[i].name+"'/>&nbsp;&nbsp;<input  class='markid' type='hidden' value='"+data[i].name+"'>");
            $('input[name="markIds"]').each(function(){
                var $this=$(this);
                if($this.val()==data[i].id) {
                    $(this).iCheck('check');
                }
            });
        }
    }
    $('#userId',$('#markEditForm')).val(id);
}


$(function(){
    //保存标签
    $('#saveUserMark').on('click',function(){
        var params=$('#markEditForm').serialize();
        $.ajax({url:'mark.do',data:params,type:"post",success:function(data){
            if(data==1){
                alert("设置用户标签成功");
                $('#mark_edit').modal('hide');
            }else{
                alert("设置用户标签失败");
            }
        }});
    });
    //保存用户角色信息
    //保存标签
    $('#saveUserRole').on('click',function(){
        var params=$('#roleEditForm').serialize();
        $.ajax({url:'role.do',data:params,type:"post",success:function(data){
            if(data==1){
                alert("设置用户角色成功");
                $('#role_edit').modal('hide');
            }else{
                alert("设置用户角色失败");
            }
        }});
    });
});
function roleData(id){
    $('input[name="roleIds"]:checked').each(function(){
        $(this).iCheck('uncheck');

    });

    var params={userId:id};
    $.ajax({url:context+'/role/user.do',data:params,type:"post",success:function(data){
        data=JSON.parse(data);
        if(data){
            setRoleData(data);
        }
    }});

    function setRoleData(data){
        $('#rolenames',$('#roleEditForm')).html("");
        for(var i = 0;i<data.length;i++){
            $('#rolenames',$('#roleEditForm')).append("<input type='button' class='but' value='"+data[i].name+"'/>&nbsp;&nbsp;<input  class='markid' type='hidden' value='"+data[i].name+"'>");
            $('input[name="roleIds"]').each(function(){
                var $this=$(this);
                if($this.val()==data[i].id) {
                    $(this).iCheck('check');
                }
            });
        }
    }
    $('#id',$('#roleEditForm')).val(id);
}
function uploadSuccess(path){
    var obj = document.getElementById('imgurl') ;
    obj.outerHTML=obj.outerHTML;
    $('#showMyImg',$('#saveEditForm')).attr("src",lcontext);
    $('#headimage',$('#saveEditForm')).val(path);
}
$(function(){
    $('#showMyImg',$('#saveEditForm')).click(function(){
        $('#imgurl').trigger("click");
    });
    var state=$('#state').val();
    if(state=="1"){
        $('#statea').click();
        $('#st').text("经销商中");
    }
    else if(state=="2"){
        $('#statea').click();
        $('#st').text("员工信息中");
    }
});


function submitRole(){
    $("#roleEditForm").submit();
}