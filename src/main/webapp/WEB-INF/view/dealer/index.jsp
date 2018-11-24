<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="xq" uri="http://www.xiqing.info/tags"%>
<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-activity p-activity-wilm">
<div class="wrapper">
    <jsp:include page="../include/top.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/left.jsp" flush="true"></jsp:include>

    <div class="content">
        <div class="crumb">
            <h5>经销商管理 &#187; 经销商信息管理</h5>
        </div>

        <div class="content-container" >
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>经销商信息管理</span></h3>
                    <div class="row area-list-dec">
                        <div class="area-search">
                            <form class="form-inline form-horizontal" role="form"  id="searchForm" method="post" name="searchForm" action="index.do">
                            <div class=" col-md-2 form-input">
                                <input class=" form-control input-sm"  id="realName"   name="realName" placeholder="姓名" type="text" size="20"   value="${vo.realName}">
                            </div>
                            <div class="form-input col-md-2 form-input">
                                <input class="form-control input-sm"   name="phone" placeholder="手机" type="text"  size="20"  value="${vo.phone}">
                            </div>
                            <input type="hidden" name="departId" id="departId" value="${vo.departId}">
                            <div class="form-input col-md-2">
                                <input class="form-control"  name="serveCode" placeholder="服务代码" type="text"  value="${vo.serveCode}">
                            </div>
                            <div class="form-input col-md-2">
                                <input class="form-control input-sm"  name="sellCode" placeholder="销售代码" type="text"  value="${vo.sellCode}">
                            </div>
                            <div class="form-input col-md-2">
                                <input class="form-control input-sm"  name="userName" placeholder="账号" type="text"  value="${vo.userName}">
                            </div>
                            <div class="form-input col-md-2">
                                <input class="form-control input-sm"  name="email" placeholder="邮箱" type="text"  value="${vo.email}">
                            </div>
                            <div class="form-input col-md-2">
                                <select id="queryPosition" name="position" class="form-control select2-input select2-offscreen" >
                                    <option <c:if test="${vo.position==''}">selected="selected"</c:if> value="">请选择</option>
                                    <option <c:if test="${vo.position=='总经理'}">selected="selected"</c:if> value="总经理" >总经理</option>
                                    <option <c:if test="${vo.position=='销售经理'}">selected="selected"</c:if> value="销售经理">销售经理</option>
                                    <option <c:if test="${vo.position=='服务经理'}">selected="selected"</c:if> value="服务经理">服务经理</option>
                                    <option <c:if test="${vo.position=='DCRC经理'}">selected="selected"</c:if> value="DCRC经理">DCRC经理</option>
                                    <option <c:if test="${vo.position=='零件经理'}">selected="selected"</c:if> value="零件经理">零件经理</option>
                                    <option <c:if test="${vo.position=='索赔员'}">selected="selected"</c:if> value="索赔员">索赔员</option>
                                    <option <c:if test="${vo.position=='市场经理'}">selected="selected"</c:if> value="市场经理">市场经理</option>
                                </select>
                            </div>
                            <div class="form-search-sub" style="margin-top:5px;">
                                <button type="submit" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                <a href="#save_edit" data-toggle="modal" onclick="initAdd();" class="btn btn-default" data-backdrop="static" style="margin-left:20px;"><i class="ico-add"></i>添加</a>
                            </div>
                        </form>
                    </div>
                </div>


                <div class="table-responsive"  style="width: 100%;float: left">
                    <table id="mymenutable"  class="table table-striped table-bordered table-hover table-highlight table-checkable order-column" data-provide="datatable" data-display-rows="10" data-info="true" data-length-change="true" data-paginate="true">
                        <thead>
                        <tr>
                            <th>缩略图</th>
                            <th>姓名</th>
                            <th>帐号</th>
                            <th>职位</th>
                            <th>手机</th>
                            <th>邮箱</th>
                            <th>大区</th>
                            <th>小区</th>
                            <th>服务代码</th>
                            <th>销售代码</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.data}"  var="item">
                            <input type="hidden" id="realname_${item.id}" value="${item.realName}"/>
                            <input type="hidden" id="username_${item.id}" value="${item.userName}"/>
                            <input type="hidden" id="position_${item.id}" value="${item.position}"/>
                            <input type="hidden" id="phone_${item.id}" value="${item.phone}"/>
                            <input type="hidden" id="email_${item.id}" value="${item.email}"/>
                            <input type="hidden" id="headimage_${item.id}" value="${item.headImage}"/>
                            <input type="hidden" id="sex_${item.id}" value="${item.sex}"/>
                            <input type="hidden" id="wxnum_${item.id}" value="${item.wxNum}"/>
                            <input type="hidden" id="areaname_${item.id}" value="${item.areaId}"/>
                            <input type="hidden" id="smallname_${item.id}" value="${item.smallAreaId}"/>
                            <input type="hidden" id="servecode_${item.id}" value="${item.serveCode}"/>
                            <input type="hidden" id="sellcode_${item.id}"N value="${item.sellCode}"/>
                            <c:if test="${not empty item.id}">
                                <tr>
                                    <td><img src="${pageContext.request.contextPath}${item.headImage}" width="40" height="40"/></td>
                                    <td>${item.realName}</td>
                                    <td>${item.userName}</td>
                                    <td>${item.position}</td>
                                    <td>${item.phone}</td>
                                    <td>${item.email}</td>
                                    <td>${item.areaName}</td>
                                    <td>${item.smallName}</td>
                                    <td>${item.serveCode}</td>
                                    <td>${item.sellCode}</td>
                                    <td>
                                        <a href="#save_edit" onclick="EditInfo('${item.id}')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>编辑</a>
                                        <a href="#deleteTip" onclick="deleteData('${item.id}')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>删除</a>
                                        <a href="#mark_edit" onclick="markData('${item.id}')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>标签设置</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <xq:page pagination="${page}" formId="searchForm"/>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    function deleteData(id){
        $('#id',$('#deleteForm')).val(id);
    }
    //点击编辑，初始化form表单
    function EditInfo(id){
        $('#userpass').hide();
        $('#realName',$('#saveEditForm')).val($('#realname_'+id).val());
        $('#userName',$('#saveEditForm')).val($('#username_'+id).val());

        $('#phone',$('#saveEditForm')).val($('#phone_'+id).val());
        $('#email',$('#saveEditForm')).val($('#email_'+id).val());
        $('#wxNum',$('#saveEditForm')).val($('#wxnum_'+id).val());


        $('#positionTemp').select2("val",$('#position_'+id).val());
        var areaId=$('#areaname_'+id).val();

        $("#smarea").html("");
        $.ajax({url:"${pageContext.request.contextPath}/area/small.do",data:{parentId:areaId},type:"post",success:function(data){
            data=JSON.parse(data)
            for(var i=0;i<data.length;i++){

                $('#smarea').append('<option value="'+ data[i].id +'">' + data[i].name +'</option>');

            }
            var smallAreaId=$('#smallname_'+id).val();
            $('#smarea').select2('val',smallAreaId);
            $('#areaName').select2('val',areaId);
        }});
        $('#serveCode',$('#saveEditForm')).val($('#servecode_'+id).val());
        $('#sellCode',$('#saveEditForm')).val($('#sellcode_'+id).val());
        var sex=$('#sex_'+id).val();
        if(sex=="1"){
            $('#sex',$('#saveEditForm')).select2("val", "1");
        }else{
            $('#sex',$('#saveEditForm')).select2("val", "2");
        }
        $('#headImage',$('#saveEditForm')).val($('#headimage_'+id).val());
        var defaultimg ="${pageContext.request.contextPath}/basic/upload/nopic.jpg";
        if(!hhutil.isEmpty($('#headimage_'+id).val())){
            defaultimg = "${pageContext.request.contextPath}"+$('#headimage_'+id).val() ;
        }
        $('#showMyImg',$('#saveEditForm')).attr("src",defaultimg);
        $('#id',$('#saveEditForm')).val(id);

    }

    function deleteConfirm(){
        if(confirm("注意:您正在删除当前查询条件下的所有经销商,删除经销商会删除该经销商相关的所有咨询与解答数据,您确认要进行此操作吗?")==false){
            return ;
        }
        $("#searchForm").submit();
    }
    function initAdd(){
        $('#userpass').show();
        $('#saveEditForm').reset();
        $('#id',$('#saveEditForm')).val("");
    }
    function markData(id){

        $('input[name="markIds"]:checked').each(function(){
            $(this).iCheck('uncheck');

        });

        var params={userId:id};
        $.ajax({url:'${pageContext.request.contextPath}/mark/user.do',data:params,type:"post",success:function(data){
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
        $('#id',$('#markEditForm')).val(id);
    }

    function uploadSuccess(path){
        var obj = document.getElementById('imgurl') ;
        obj.outerHTML=obj.outerHTML;
        $('#showMyImg',$('#saveEditForm')).attr("src","${pageContext.request.contextPath}"+path);
        $('#headimage',$('#saveEditForm')).val(path);
    }
    $(function(){
        var id= $('#areaName').val();
        changeArea();

        $('#showMyImg',$('#saveEditForm')).click(function(){
            // 上传方法
            $.upload({
                // 上传地址
                url: '${pageContext.request.contextPath}/image/upload.do',
                // 文件域名字
                fileName: 'files',
                // 其他表单数据
                params: {name: 'pxblog'},
                // 上传完成后, 返回json, text
                dataType: 'json',
                // 上传之前回调,return true表示可继续上传
                onSend: function() {
                    console.log(this);
                    return true;
                },
                // 上传之后回调
                onComplate: function(data) {
                    $('#headImage').val(data['filePath']);
                    $('#showMyImg').attr('src','${pageContext.request.contextPath}'+data['filePath']);
                }
            });
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
    function changeArea(){
        $("#smarea").html("");
        $.ajax({url:"${pageContext.request.contextPath}/area/small.do",data:{parentId:$('#areaName').val()},type:"post",success:function(data){
            data=JSON.parse(data);
            console.log(data);
            for(var i=0;i<data.length;i++){
                $("#smarea").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
            }
        }});
    }

   $(function(){
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
   });
</script>
<a href="#error" id="statea" data-toggle="modal"  style="display: none"><span>提示</span></a>
<input type="hidden" id="state" value="${state}"/>
<div id="error" class="modal fade tips-modal tips-modal-warning">
    <div class="modal-dialog">
        <div class="modal-content">
            <form  theme="simple" method="post" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title">提示</h3>
                </div>
                <div class="modal-body">
                    <i class="ico-tips-warning"></i>
                    <h4>您输入的手机号已存在<span id="st"></span>，添加不成功！</h4>
                    <p class="tips-modal-sub"></p>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-close" data-dismiss="modal">确定</button>
                </div> 
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div id="deleteTip" class="modal fade tips-modal tips-modal-warning">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="delete.do" id="deleteForm" name="deleteForm"
                    theme="simple" method="post"
                    namespace="/backer" >
                <input type="hidden" id="id" name="id" />
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title">提示</h3>
                </div>
                <div class="modal-body">
                    <i class="ico-tips-warning"></i>
                    <h4>确定删除？</h4>
                    <p class="tips-modal-sub">员工删除之后无法恢复</p>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
                    <button type="submit" class="btn btn-save"><i class="ico-ok"></i>确 定</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 添加 -->
<div id="save_edit" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title">经销商信息编辑</h3>
            </div>
            <form id="saveEditForm" class="form-horizontal parsley-form" method="post"  role="form"  action="handler.do"  data-validate="parsley">
                <input type="hidden" id="id" name="id" />
                <input id="departId" name="departId" type="hidden" value="1" class="form-control parsley-validated" >
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label"><i class="required">*</i>姓名</label>
                            <div class="col-md-8">
                                <input id="realName" name="realName" type="text" class="form-control parsley-validated" data-required="true">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label"><i class="required">*</i>帐号名称</label>
                            <div class="col-md-8">
                                <input id="userName" name="userName" type="text" class="form-control parsley-validated" data-required="true">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label">性别</label>
                            <div class="col-md-8">
                                <select id="sex" name="sex" class="form-control select2-input select2-offscreen" style="width:150px;" >
                                    <option value="1" >男</option>
                                    <option value="2" >女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label">职位</label>
                            <div  class="col-md-8">
                                <select id="positionTemp" name="position" class="form-control select2-input select2-offscreen" style="width:150px;" >
                                    <option value="">请选择</option>
                                    <option value="总经理">总经理</option>
                                    <option value="销售经理">销售经理</option>
                                    <option value="服务经理">服务经理</option>
                                    <option value="DCRC经理">DCRC经理</option>
                                    <option value="零件经理">零件经理</option>
                                    <option value="索赔员">索赔员</option>
                                    <option value="市场经理">市场经理</option>
                                    <option value="技术主管">技术主管</option>
                                </select>
                            </div>
                        </div>
                     </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label"><i class="required">*</i>手机</label>
                            <div class="col-md-8">
                                <input id="phone" name="phone" type="text" class="form-control parsley-validated" data-required="true">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label">微信号</label>
                            <div class="col-md-8">
                                <input id="wxNum" name="wxNum" type="text" class="form-control parsley-validated" >
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6"  >
                            <label class="col-md-4 control-label">邮箱</label>
                            <div class="col-md-8">
                                <input id="email" name="email" type="text" class="form-control parsley-validated" data-type="email">
                            </div>
                        </div>
                        <div class="form-group col-md-6" >
                            <label class="col-md-4 control-label"><i class="required">*</i>大区</label>
                            <div  class="col-md-8">
                                <select id="areaName" onchange="changeArea()" name="areaName" class="form-control select2-input select2-offscreen" style="width:150px;"  data-required="true" >
                                    <c:forEach items="${areas}" var="item">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                     </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label"><i class="required">*</i>小区</label>
                            <div  class="col-md-8">
                                <select id="smarea"  name="smallName" class="form-control select2-input select2-offscreen" style="width:150px;"  data-required="true">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label"><i class="required">*</i>服务代码</label>
                            <div  class="col-md-8">
                                <input id="serveCode" name="serveCode" type="text" class="form-control parsley-validated" data-required="true" >
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label"><i class="required">*</i>销售代码</label>
                            <div class="col-md-8">
                                <input id="sellCode" name="sellCode" type="text" class="form-control parsley-validated" data-required="true" >
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label">头像</label>
                            <div class="col-md-8">
                                <input type="hidden" id="headImage" name="headImage">
                                <img id="showMyImg" alt="" src="${pageContext.request.contextPath}/" class="form-control" class="form-control" style="width:120px;height:160px">
                                <span style="color:red">分辨率：300 * 400</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-save"><i class="ico-ok"></i>保 存</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--标签设置 -->
<div id="mark_edit" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title">标签设置</h3>
            </div>
            <form id="markEditForm" class="form-horizontal parsley-form" method="post"  role="form"  action="mark.do"  data-validate="parsley" >
                <input type="hidden" id="id" name="userId"/>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label"><i class="required">*</i>用户当前标签</label>
                        <div class="col-md-7" id="marknames">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top:20px;padding-top:20px;">
                        <label class="col-md-3 control-label"><i class="required">*</i>所有标签</label>
                        <div class="col-md-7">
                            <c:forEach items="${marks}" var="item">
                                <label title="${item.name}" class="icheck-inline">
                                    <input type="checkbox"   name="markIds" class="icheck-input" value="${item.id}"/>${item.name}
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-save" id="saveUserMark"><i class="ico-ok"></i>保 存</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- 角色 -->
</body>
</html>