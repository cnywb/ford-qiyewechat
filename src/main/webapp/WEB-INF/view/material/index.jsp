<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="xq" uri="http://www.xiqing.info/tags"%>
<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-inte p-inte-gain">
<div class="wrapper">
    <jsp:include page="../include/top.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/left.jsp" flush="true"></jsp:include>

    <div class="content">
        <div class="crumb">
            <h5>业务管理 &#187; 物料下发</h5>
        </div>
        <div class="content-container">
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>物料管理</span></h3>
                    <div class="row area-list-dec">
                        <div class="area-search">
                            <form class="form-inline" role="form"  method="post"
                                    id="searchForm" name="searchForm"  action="index.do">
                                <div class="form-input">
                                    <input class="form-control"  id="name" value="${vo.name}"  name="name" placeholder="关键字" type="text"/>
                                </div>

                                <div class="form-date-single-time" style="margin-left:20px;">
                                    <input class="form-control date-picker-single-time" id="startDate" type="text"
                                           name="startDate" placeholder="开始时间"
                                           value="${vo.startDate}" readonly>
                                    <span class="date-picker-addon"><i class="ico-calendar"></i></span>
                                </div>
                                <div class="form-date-single-time" style="margin-left:20px;">
                                    <input class="form-control date-picker-single-time" id="endDate" type="text"
                                           name="endDate" placeholder="结束时间"
                                           value="${vo.endDate}" readonly>
                                    <span class="date-picker-addon"><i class="ico-calendar"></i></span>
                                </div>
                                <div class="form-select" style="margin-left:30px;">
                                    <select name="status" class="form-control select2-input select2-offscreen" style="width:90px;" >
                                            <option value=""  <c:if test="${empty vo.status}">  selected="selected" </c:if> >全部</option>
                                            <option value="0" <c:if test="${vo.status ==0}">  selected="selected" </c:if> >启用中</option>
                                            <option value="1" <c:if test="${vo.status ==1}">  selected="selected" </c:if> >未启用</option>
                                    </select>
                                </div>

                                <div class="form-search-sub" style="margin-left:20px;">
                                    <button type="submit" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                    <a href="#save_edit" data-toggle="modal" onclick="initAdd();" class="btn btn-default" data-backdrop="static" style="margin-left:30px;"><i class="ico-add"></i>添加</a>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table id="mymenutable"  class="table table-striped table-bordered table-hover table-highlight table-checkable order-column" data-provide="datatable" data-display-rows="10" data-info="true" data-length-change="true" data-paginate="true">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>下发物料</th>
                                <th>内容摘要</th>
                                <th>下发时间</th>
                                <th>被下载次数</th>
                                <th>下发人</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.data}"  var="item">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td>${item.content}</td>
                                    <td><fmt:formatDate value="${item.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>${item.download}</td>
                                    <td>${item.realName}</td>
                                    <td>
                                        <c:if test="${item.status == 0}">启用中</c:if>
                                        <c:if test="${item.status == 1}">停用中</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${item.status ==0}">
                                            <a  href="javascript:void(0)"   onclick="editStatus('${item.id}','1')"
                                               data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>停用</a>
                                        </c:if>
                                        <c:if test="${item.status == 1}">
                                            <a  href="javascript:void(0)"  onclick="editStatus('${item.id}','0')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>启用</a>
                                        </c:if>
                                        &nbsp;&nbsp;<a href="detail.do?materialId=${item.id}">下发详情</a>
                                        &nbsp;&nbsp;<a href="download.do?materialId=${item.id}">下载详情</a>
                                        &nbsp;&nbsp;<a href="downloadFile.do?id=${item.id}" target="_blank" >下载</a>
                                    </td>
                                </tr>
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
    $(function() {
        changeArea();

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

        //更新状态
        $('#btnUpdateStatus').on('click',function(){
            var params={id:$('#materialid',$('#deleteForm')).val(),status:$('#status',$('#deleteForm')).val()}

            $.ajax({url:"status.do",data:params,type:"post",success:function(data){

                if(data==1){
                   location.reload();
                    return;
                }
                alert("修改状态失败,请重试");
                return;
            }});
            return;
        });
        //发送消息
        $('#btnSaveEdit').on('click',function(){
            var params=$('#saveEditForm').serialize();
            $.ajax({url:"handler.do",data:params,type:"post",success:function(data){
                console.log(data);
                if(data==1){
                    $('#save_edit').modal('hide');
                    location.reload();
                    return;
                }
                alert("修改状态失败,请重试");
            }});
        });
        //更新
        $('#uploadFile').on('click',function(){

            var $this=$(this),$filePath=$('#filePath');
            // 上传方法
            $.upload({
                // 上传地址
                url: lion.util.context+'/image/uploadFile.do',
                // 文件域名字
                fileName: 'files',
                // 其他表单数据
                params: {name: ''},
                // 上传完成后, 返回json, text
                dataType: 'json',
                // 上传之前回调,return true表示可继续上传
                onSend: function() {
                    lion.util.loading().show();
                    return true;
                },
                // 上传之后回调
                onComplate: function(data) {
                    lion.util.loading().hide();
                    $this.val(data['fileName']);
                    $filePath.val(data['filePath']);
                }
            });
        });
    });


    function editStatus(id,status){
        var msg='启用后可以下载物料资源';
        if(status ==1){
            msg='停用后无法再下载物料资源';
        }
        bootbox.confirm(msg,function(confirm){
            if(confirm){
                var params={id:id,status:status};
                console.log(params);
                lion.util.post('status.do',params,function(data){
                    if(data==1){
                        lion.util.success('提示','操作成功');
                        lion.util.reload();
                    }
                    lion.util.success('提示','操作失败');
                });
            }
        });
    }

    function initAdd(){
        $('#materialcontent',$('#saveEditForm')).val("");
    }

    function upload(){
        var file = document.getElementById('fileToUpload').files[0];
        if (file) {
            var fileSize = 0;
            fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString();
            if(fileSize<500)
            {
                $('#filesize').text("文件大小："+fileSize+"M");
            }
            else{
                alert("上传文件不能超过500M!");
                $('#fileToUpload').val("");
            }
        }

    }

    function changeArea(){
        var $smallArea= $('#smarea'),$bigArea=$('#bigarea');
        $smallArea.html('');
        if($bigArea.val()!=''){
            var url='${pageContext.request.contextPath}/area/small.do',params={parentId:$bigArea.val()};
            lion.util.post(url,params,function(data){
                var htmlCheckBox="";
                for(var i=0;i<data.length;i++){
                    htmlCheckBox+='<label title="'+data[i].name+'" class="icheck-inline">';
                    htmlCheckBox+='<input name="smallAreaIds"  type="checkbox" class="icheck-input fundidmain" value="'+data[i].id+'"/>';
                    htmlCheckBox+=data[i].name;
                    htmlCheckBox+='</label>&nbsp;&nbsp;&nbsp;';
                }
                $smallArea.append(htmlCheckBox);
                $smallArea.find('input').iCheck('check');
                $smallArea.find('input').iCheck({checkboxClass: 'ui-checkbox  icheck-input'});
            });
        }
    }

    function tijiao(){
        var servecode=$('#servecode').val();
        var sellcode=$('#sellcode').val();
        var reg = /^[0-9a-zA-Z\;]+$/;
        var s=reg.test(servecode);
        var s1=reg.test(sellcode);
        if(servecode!="" && servecode!=null && sellcode!="" && sellcode!=null){
            if(s && s1){
                $('#saveEditForm').submit();
            }
            else{
                alert("服务代码和销售代码只能输入数字、字母和分号!");
            }
        }else if(servecode!="" && servecode!=null){
            if(s){
                $('#saveEditForm').submit();
            }
            else{
                alert("服务代码只能输入数字、字母和分号!");
            }
        }else if(sellcode!="" && sellcode!=null){
            if(s1){
                $('#saveEditForm').submit();
            }
            else{
                alert("销售代码只能输入数字、字母和分号!");
            }
        }else{
            $('#saveEditForm').submit();
        }
    }
</script>
<!-- 添加 -->
<div id="save_edit" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                 <h3 class="modal-title">下发物料</h3>
            </div>
            <form id="saveEditForm" class="form-horizontal parsley-form"   method="post"  role="form"  action="#"  data-validate="parsley">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-md-6">
                            <div class="form-group">
                                <label class="col-md-4 control-label">内容摘要</label>
                                <div class="col-md-8">
                                    <input id="materialcontent" name="content" type="text" class="form-control parsley-validated" >
                                </div>
                            </div>
                        </div>
                        <div class=" col-md-6">
                            <div class="form-group">
                                <label class="col-md-4 control-label"><i class="required">*</i>文件</label>
                                <div id="url_file" class="col-md-8">
                                    <input id="uploadFile"  name="fileName"  type="text" class="form-control parsley-validated" data-required="true"  readonly="true" style="cursor:pointer">
                                    <input type="hidden" id="filePath" name="filePath">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class=" col-md-6">
                            <div class="form-group">
                                <label class="col-md-4 control-label">服务代码</label>
                                <div  class="col-md-8">
                                    <input id="servecode" name="serveCode" type="text" class="form-control parsley-validated" >
                                </div>
                            </div>
                        </div>
                        <div class=" col-md-6">
                            <div class="form-group">
                                <label class="col-md-4 control-label">销售代码</label>
                                <div class="col-md-8">
                                    <input id="sellcode" name="sellCode" type="text" class="form-control parsley-validated" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-4 control-label">职位</label>
                                <div  class="col-md-8">
                                    <select id="position" name="position" class="form-control select2-input select2-offscreen" style="width:130px;">
                                        <option value="">请选择</option>
                                        <option value="总经理">总经理</option>
                                        <option value="销售经理">销售经理</option>
                                        <option value="服务经理">服务经理</option>
                                        <option value="DCRC经理">DCRC经理</option>
                                        <option value="零件经理">零件经理</option>
                                        <option value="索赔员">索赔员</option>
                                        <option value="市场经理">市场经理</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-4 control-label">大区</label>
                                <div  class="col-md-8">
                                    <select id="bigarea" onchange="changeArea()" name="areaName" class="form-control select2-input select2-offscreen" style="width:130px;" >
                                        <option value="">请选择</option>
                                        <c:forEach items="${areas}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-2 control-label" >小区</label>
                                <div id="smarea" class="col-md-10"  style="height:70px;"></div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
                    <button type="button"  id="btnSaveEdit"  class="btn btn-save"><i class="ico-ok"></i>发送</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>