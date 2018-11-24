<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="xq" uri="http://www.xiqing.info/tags"%>
<!DOCTYPE html>
<head>
<jsp:include page="../include/header2.jsp" flush="true"></jsp:include>
</head>
<body class="p-activity p-activity-cgz">
<div class="wrapper">
    <jsp:include page="../include/top.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/left.jsp" flush="true"></jsp:include>

    <div class="content">
        <div class="crumb">
            <h5>基础功能 &#187; 消息管理</h5>
        </div>
        <div class="content-container">
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>消息管理</span></h3>
                    <div class="row area-list-dec">
                        <div class="area-search">
                            <form class="form-inline" role="form" method="post"
                                    id="searchForm" name="searchForm"  action="index.do">
                                <div class="form-input" style="margin-left: 20px">
                                    <input class="form-control"  id="realname"  name="realName" placeholder="用户名" type="text"  value="${vo.realName}">
                                </div>
                                <div class="form-input" style="margin-left: 20px">
                                    <select id="status" name="status"  class="form-control select2-input select2-offscreen" style="width:150px;" >
                                        <option value=""  <c:if test="${empty vo.status}"> selected="selected" </c:if> >消息分类</option>
                                        <option value="1" <c:if test="${vo.status ==1}"> selected="selected" </c:if> >发送信息</option>
                                        <option value="2" <c:if test="${vo.status ==2}"> selected="selected" </c:if> >接受信息</option>
                                    </select>
                                </div>
                                <div class="form-search-sub" style="margin-left:20px;">
                                    <button type="submit" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                    <a href="#insert_edit" data-toggle="modal" onclick="initAdd('')" class="btn btn-default" data-backdrop="static" style="margin-left:30px;"><i class="ico-add"></i>发送信息</a>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table id="mymenutable"  class="table table-striped table-bordered table-hover table-highlight table-checkable order-column" data-provide="datatable" data-display-rows="10" data-info="true" data-length-change="true" data-paginate="true">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>用户名</th>
                                <th width="30%">内容</th>
                                <th>消息分类</th>
                                <th>消息时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.data}" var="item">
                                <input type="hidden" id="content_${item.id}" value="${item.content}"/>
                                <input type="hidden" id="imagecontent_${item.id}" value="${item.imageId}"/>
                                <input type="hidden" id="headimage_${item.id}" value="${item.headImage}"/>
                                <input type="hidden" id="status_${item.id}" value="${item.status}"/>
                                <input type="hidden" id="contenttype_${item.id}" value="${item.contentType}"/>
                                <input type="hidden" id="title1_${item.id}" value=""${item.imageTitle}"/>
                                <input type="hidden" id="content1_${item.id}" value="${item.content}"/>
                                <input type="hidden" id="image1_${item.id}" value="${item.image}"/>
                                <input type="hidden" id="author_${item.id}" value="${item.author}"/>
                                <input type="hidden" id="url_${item.id}" value="${item.url}"/>
                                <input type="hidden" id="appendix_${item.id}" value="${item.appendix}"/>
                                <input type="hidden" id="userid_${item.id}" value="${item.userId}"/>
                                <input type="hidden" id="realname_${item.id}" value="${tem.realName}"/>
                                <input type="hidden" id="areaname_${item.id}" value="${item.areaName}"/>
                                <input type="hidden" id="smallname_${item.id}" value="${item.smallName}"/>
                                <input type="hidden" id="sellcode_${item.id}" value="${item.sellCode}"/>
                                <input type="hidden" id="servecode_${item.id}" value="${item.serveCode}"/>
                                <input type="hidden" id="position_${item.id}" value="${item.position}"/>
                                <tr>
                                    <td>${item.id}</td>
                                    <td><a href="#insert_edit" data-toggle="modal" onclick="initAdd('${item.id}','${item.realName}');">${item.realName}</a></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.contentType==6}">
                                                图文信息：请查看详情
                                            </c:when>
                                            <c:otherwise>
                                                 文本: ${item.content}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${item.status==1}">
                                            发送消息(点击用户名可直接回复此员工)
                                        </c:if>
                                        <c:if test="${item.status!=1}">
                                            接收消息(点击用户名可继续发信息)
                                        </c:if>
                                    </td>
                                    <td><fmt:formatDate value="${item.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>
                                        <c:if test="${item.contentType==6}">
                                            <a href="#save_edit" onclick="EditInfo('${item.id}','${item.imageId}')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>查看详情</a>
                                        </c:if>
                                        <a href="#deleteTip" onclick="deleteData('${item.id}')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>删除</a>
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

    $(function(){
        $("#mymenutable tr").each(function(){
            $(this).children().eq(2).html($(this).children().eq(2).text());
        });

        $("#delegate_btn_upload_image").click(function(){

            // 上传方法
            $.upload({
                // 上传地址
                url: lion.util.context+'/wechat/media/upload.do',
                // 文件域名字
                fileName: 'files',
                // 其他表单数据
                params: {dir:'image'},
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
                    if(data['error']==1){
                        lion.util.warning("提示",data['message']);
                        return;
                    }
                    $('#imageSrc').val(data['filePath']);
                    $('#upload_image').attr('src',lion.util.context+data['filePath']);
                    lion.util.success('','上传图片成功');
                }
            });
        });
        $("#btnc01").hide();
    });


    function deleteData(id){
        bootbox.confirm('注意,您确定要删除该条数据吗?此操作不可恢复!',function(confirm){
            if(confirm){
                lion.util.post('delete.do',{id:id},function(data){
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

    function initAdd(id,realName){
        console.log(realName);
        $('#sendmessage').show();
        if(id!=''){
            $('#bigarea',$('#insertEditForm')).select2("val",$('#areaname_'+id).val());
            $('#smarea',$('#insertEditForm')).text($('#smallname_'+id).val());
            $('#servecode',$('#insertEditForm')).val($('#servecode_'+id).val());
            $('#sellcode',$('#insertEditForm')).val($('#sellcode_'+id).val());
            $('#position',$('#insertEditForm')).select2("val",$('#position_'+id).val());
            $('#employee',$('#insertEditForm')).html("val", "");
            $('#employee',$('#insertEditForm')).append("<option value='"+$('#userid_'+id).val()+"'>"+realName+"</option>");
            $('#employee',$('#insertEditForm')).select2("val", $('#userid_'+id).val());
        }
        else{
            $('#bigarea',$('#insertEditForm')).select2("val","");
            $('#smarea',$('#insertEditForm')).text("");
            $('#servecode',$('#insertEditForm')).val("");
            $('#sellcode',$('#insertEditForm')).val("");
            $('#position',$('#insertEditForm')).select2("val","");
            $('#employee',$('#insertEditForm')).html("val", "");
            $('#employee',$('#insertEditForm')).append("<option value=''>全部员工</option>");
            $('#employee',$('#insertEditForm')).select2("val", "");
        }
        $('#txt',$('#insertEditForm')).text("");
        $('#messageid',$('#insertEditForm')).val("");
    }


    $(function(){

        $('#showMyImg',$('#insertEditForm')).click(function(){
            $('#imgurl').trigger("click");
        });
        $('#showMyImg1',$('#insertEditForm')).click(function(){
            $('#imgurl').trigger("click");
        });
        changeArea();
    });
    function textaction(id){
        if(id=="1"){

            $('#textactive').addClass('active');
            $('#textactive1 #txt').attr("name","content");
            $('#textactive1').show();
            $('#contenttype').val('1');

        }else{
            $('#textactive').removeClass('active');
            $('#textactive1 #txt').attr("name","");
            $('#textactive1').hide();


        }
        if(id=="2"){
            $('#imageactive').addClass('active');
            $('#imageactive1 #headimage').attr("name","content");
            $('#imageactive1').show();
            $('#contenttype').val('2');
        }else{
            $('#imageactive').removeClass('active');
            $('#imageactive1 #headimage').attr("name","")
            $('#imageactive1').hide();

        }
        if(id=="3"){
            $('#vioceactive').addClass('active');
            $('#vioceactive1 #vioce').attr("name","content");
            $('#vioceactive1').show();
            $('#contenttype').val('3');
        }else{
            $('#vioceactive').removeClass('active');
            $('#vioceactive1 #vioce').attr("name","")
            $('#vioceactive1').hide();
        }
        if(id=="4"){
            $('#videoactive').addClass('active');
            $('#vioceactive1 #video').attr("name","content");
            $('#videoactive1').show();
            $('#contenttype').val('4');
        }else{
            $('#videoactive').removeClass('active');
            $('#vioceactive1 #video').attr("name","")
            $('#videoactive1').hide();
        }
        if(id=="5"){
            $('#fileactive').addClass('active');
            $('#vioceactive1 #file').attr("name","content");
            $('#fileactive1').show();
            $('#contenttype').val('5');
        }else{
            $('#fileactive').removeClass('active');
            $('#vioceactive1 #file').attr("name","")
            $('#fileactive1').hide();
        }
        if(id=="6"){
            $('#imagecontentactive').addClass('active');
            $('#imagecontentactive1').show();
            $('#contenttype').val('6');
        }else{
            $('#imagecontentactive').removeClass('active');
            $('#imagecontentactive1').hide();
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


    function sendMessage(){
        var contenttype=$("#contenttype").val();
        if(contenttype=="1"){
            if($("#txt").val()==""){
                lion.util.info('请输入消息内容!');
                return ;
            }

        }else if(contenttype=="2"){
            if($("#imageSrc").val()==""){
                lion.util.info('请输上传图片!');
                return ;
            }
        }

        var form=$('#insertEditForm');
        lion.util.post('send.do',form.serialize(),function(data){
            if(data==1){
                lion.util.success('提示','操作成功!');
                lion.util.reload();
            }
            lion.util.warning('提示','操作成功');
        },function(xhr,status,error){
            console.log(status);
            console.log(error);
        });

    }

</script>


<!-- 添加 -->
<div id="save_edit" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title">图文详情</h3>
            </div>
            <form id="saveEditForm" class="form-horizontal parsley-form" method="post" namespace="/backer" role="form"  action="Basic!editMessage.action"  data-validate="parsley">
                <div class="modal-body">
                    <div class="form-group" style="margin-top: 30px" >
                        <label class="col-md-3 control-label"><i class="required">*</i>标题</label>
                        <div class="col-md-7" style="width:388px;">
                            <input id="title1" name="pvMap.title" type="text" class="form-control parsley-validated">
                        </div>
                    </div>
                    <br/>
                    <div class="form-group" style="margin-top: 30px" >
                        <label class="col-md-3 control-label">作者</label>
                        <div class="col-md-7" style="width:388px;">
                            <input id="author1" name="author" type="text" class="form-control parsley-validated">
                        </div>
                    </div>
                    <br/>
                    <div class="form-group" style="margin-top: 30px" >
                        <label class="col-md-3 control-label"><i class="required">*</i>封面图片</label>
                        <div class="col-md-7" style="width:388px;">
                            <img id="showMyImg2" alt="" src="${pageContext.request.contextPath}/" class="form-control" class="form-control" style="height:280px">
                            <font color="red">分辨率：300 * 400</font>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group" style="margin-top: 30px" >
                        <label class="col-md-3 control-label"><i class="required">*</i>内容</label>
                        <div class="col-md-7" style="width:388px;">
			        <textarea rows="10" cols=""  class="form-control parsley-validated"
                              id="content" name="imagecontent"></textarea>
                            <br/><br/>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group" style="margin-top: 30px" >
                        <label class="col-md-3 control-label"><i class="required">*</i>原文链接</label>
                        <div class="col-md-7" style="width:388px;">
                            <input id="url1" name="pvMap.url" type="text" class="form-control parsley-validated">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 30px" >
                        <label class="col-md-3 control-label">附件</label>
                        <div class="col-md-7" style="width:388px;">
                            <a id="appendix1"></a>
                        </div>
                    </div>
                </div>
            </form>
        </div><%--
        <div class="modal-footer">
            <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-save"><i class="ico-ok"></i>保 存</button>
        </div>
        --%>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 添加 -->
<div id="insert_edit" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title">发送消息给用户</h3>
            </div>

            <form id="insertEditForm" class="form-horizontal parsley-form" method="post"  role="form"  action="#"  data-validate="parsley">
                <input type="hidden"   id="messageId" name="id" />
                <div class="modal-body">
                    <div class="row">
                            <div class="col-md-6">
                                 <div class="form-group">
                                    <label class="col-md-4 control-label">部门</label>
                                    <div class="col-md-8">
                                        <select id="departId" name="departId"  class="form-control select2-input select2-offscreen" style="width:120px;margin-left:10px" >
                                            <option value="">请选择部门</option>
                                            <c:forEach items="${departments}" var="item">
                                                <option value="${item.id}" >${item.departName}</option>
                                            </c:forEach>
                                        </select>
                                     </div>
                                 </div>
                             </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4 control-label">员工</label>
                                    <div class="col-md-8">
                                        <select id="employee" name="userId"  class="form-control select2-input select2-offscreen">
                                            <option value="" selected="selected">全部员工</option>
                                            <c:forEach items="${users}" var="item">
                                                <option value="${item.id}" >${item.realName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-4 control-label">标签</label>
                                <div class="col-md-8">
                                    <select id="mark" name="markId"  class="form-control select2-input select2-offscreen" style="width:120px;margin-left:10px" >
                                        <option value="">全部标签</option>
                                        <c:forEach items="${marks}" var="item">
                                            <option value="${item.id}" >${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group" >
                                <label class="col-md-4 control-label">职位</label>
                                <div  class="col-md-8">
                                    <select id="position" name="position" class="form-control select2-input select2-offscreen" style="width:120px;">
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
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-4 control-label">服务代码</label>
                                <div  class="col-md-8">
                                    <input id="servecode" name="serveCode" type="text" class="form-control parsley-validated"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-4 control-label">销售代码</label>
                                <div class="col-md-8">
                                    <input id="sellcode" name="sellCode" type="text" class="form-control parsley-validated"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                         <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-4 control-label">大区</label>
                                <div class="col-md-8">
                                    <select id="bigarea" onchange="changeArea()" name="areaName" class="form-control select2-input select2-offscreen" style="width:120px;" >
                                        <option value="">请选择大区</option>
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
                                <label class="col-md-2 control-label">小区</label>
                                <div id="smarea" class="col-md-10"  style="height:70px;"> </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group" >
                                <label class="col-md-2 control-label">内容</label>
                                <div class="col-md-10" style="width:390px;">
                                    <ul class="nav nav-tabs nav-tabs-pills">
                                        <li class="active" onclick="textaction('1');" id="textactive"><a style="cursor: pointer;">文本</a></li>
                                        <%-- onclick="textaction('2');" --%>
                                        <li  id="imageactive" onclick="textaction('2');" ><a style="cursor: pointer;">图片</a></li>
                                        <%-- onclick="textaction('3');" --%>
                                        <li id="vioceactive"><a style="cursor: pointer;">音频</a></li>
                                        <%-- onclick="textaction('4');" --%>
                                        <li   id="videoactive"><a style="cursor: pointer;">视频</a></li>
                                        <%-- onclick="textaction('5');" --%>
                                        <li   id="fileactive"><a style="cursor: pointer;">文件</a></li>
                                        <%-- onclick="textaction('6');" --%>
                                        <li   id="imagecontentactive" ><a style="cursor: pointer;">图文</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                        <div id="textactive1">
                            <div class="form-group" >
                                <label class="col-md-2 control-label">文本</label>
                                <div class="col-md-10" style="width:388px;height:100px;">
                                    <textarea rows="5" cols=""  class="form-control" id="txt" name="content"   ></textarea>
                                </div>
                            </div>
                        </div>
                        <div  id="imageactive1" style="display:none">
                            <div class="form-group" >
                                <label class="col-md-2 control-label">图片</label>
                                <div class="col-md-10">
                                    <div   style="width:388px;height:100px;">
                                        <input class="btn btn-default"  type="button" id="delegate_btn_upload_image" value="上传图片" />
                                        <input type="hidden" name="imageSrc" id="imageSrc" />
                                        <a href="javascript:;" class="thumbnail">
                                            <img   alt="" id="upload_image" />
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div  id="vioceactive1" style="display:none">
                            <div class="form-group">
                                <br/>
                                <label class="col-md-3 control-label">音频</label>
                                <div class="col-md-7" style="width:388px;">
                        <textarea rows="5" cols=""  class="form-control parsley-validated"
                                  id="vioce" name="paramMap.content"></textarea>
                                </div>
                            </div>
                        </div>
                        <div  id="videoactive1" style="display:none">
                            <div class="form-group">
                                <br/>
                                <label class="col-md-3 control-label">视频</label>
                                <div class="col-md-7" style="width:388px;">
                        <textarea rows="5" cols=""  class="form-control parsley-validated"
                                  id="video" name="paramMap.content"></textarea>
                                </div>
                            </div>
                        </div>
                        <div id="fileactive1" style="display:none">
                            <div class="form-group"  >
                                <br/>
                                <label class="col-md-3 control-label">文件</label>
                                <div class="col-md-7" style="width:388px;">
                                     <textarea rows="10" cols=""  class="form-control parsley-validated"
                                               id="file" name="paramMap.content"></textarea>
                                </div>
                            </div>
                        </div>
                        <div id="imagecontentactive1" style="display:none">
                            <div class="form-group" >
                                <label class="col-md-3 control-label">标题</label>
                                <div class="col-md-7" style="width:388px;">
                                    <input id="title" name="pvMap.title" type="text" class="form-control parsley-validated">
                                </div>
                            </div>
                            <br/>
                            <div class="form-group">
                                <label class="col-md-3 control-label">作者</label>
                                <div class="col-md-7" style="width:388px;">
                                    <input id="title" name="pvMap.author" type="text" class="form-control parsley-validated">
                                </div>
                            </div>
                            <br/>
                            <div class="form-group" >
                                <label class="col-md-3 control-label">封面图片</label>
                                <div class="col-md-7" style="width:388px;">
                                    <input type="hidden" id="image" name="pvMap.image">
                                    <img id="showMyImg1" alt=""  class="form-control" class="form-control" style="height:280px">
                                    <font color="red">分辨率：300 * 400</font>

                                </div>
                            </div>
                            <br/>
                            <div class="form-group" >
                                <label class="col-md-3 control-label">内容</label>
                                <div class="col-md-7" style="width:388px;">
                                    <textarea rows="10" cols=""  class="form-control parsley-validated"
                                              id="content" name="pvMap.imagecontent"></textarea>
                                    <br/><br/>
                                </div>
                            </div>
                            <br/>
                            <div class="form-group" >
                                <label class="col-md-3 control-label">原文链接</label>
                                <div class="col-md-7" style="width:388px;">
                                    <input id="url" name="pvMap.url" type="text" class="form-control parsley-validated">
                                </div>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="hidden" id="contenttype" name="contentType" value="1"/>
                    <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
                    <button type="button" onclick="sendMessage();" id="sendmessage" class="btn btn-save"><i class="ico-ok"></i>发送</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="btnc01">
    <input type="button" value="上传图片" id="btn_upload_image" >
    <input type="hidden"  id="upload_url" value="${pageContext.request.contextPath}/wechat/media/uploadQyMedia.action?dir=" />
    <input type="hidden" id="basePath" value="${pageContext.request.contextPath}">
</div>
<form action="Basic!uploadEmployeeImg.action" id="imgUploadForm" name="imgUploadForm"
        enctype="multipart/form-data" theme="simple" method="post"
        namespace="/backer"  target="imgFrame">
    <input type="file" style="width:0px;height:0px;"  name="imgurl" id="imgurl" onchange="document.getElementById('imgUploadForm').submit();"  />
</form>
<iframe id="imgFrame"  name="imgFrame" style="display:none;width:0px;height:0px;"></iframe>
</body>
</html>