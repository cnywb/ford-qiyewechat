<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<!DOCTYPE html>
<head>
<jsp:include page="../include/header2.jsp" flush="true"></jsp:include>
<link rel="stylesheet" href="<s:url value='/plugin/kindeditor/themes/default/default.css' />" />
<link rel="stylesheet" href="<s:url value='/plugin/kindeditor/plugins/code/prettify.css' />" />
<script charset="utf-8" src="<s:url value='/plugin/kindeditor/kindeditor.js' />"></script>
<script charset="utf-8" src="<s:url value='/plugin/kindeditor/lang/zh_CN.js' />"></script>
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
                            <s:form class="form-inline" role="form" namespace="/backer"
								id="searchForm"  action="Basic!getMessageList.action">
								<div class="form-input" style="margin-left: 20px">
                                   <input class="form-control"  id="realname"  name="pvMap.realname" placeholder="用户名" type="text"  value="<s:property value='#request.pvMap.realname' />">
                                </div> 
                                <div class="form-input" style="margin-left: 20px">
                                    <select id="status" name="pvMap.status"  class="form-control select2-input select2-offscreen" style="width:150px;" >
                                    		<option value="" selected="selected">消息分类</option>
											<s:if test="#request.pvMap.status==1">
												<option value="1" selected="selected">发送信息</option>
								       			<option value="2">接受信息</option>
								       			</s:if>
								       		<s:elseif test="#request.pvMap.status==2">
								       			<option value="1" >发送信息</option>
								       			<option value="2" selected="selected">接受信息</option>
								       		</s:elseif>
								       		<s:else>
								       			<option value="1" >发送信息</option>
								       			<option value="2" >接受信息</option>
								       		</s:else>
                                    </select>
                                 </div> 
                                <div class="form-search-sub" style="margin-left:20px;">
                                    <button type="submit" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                    <a href="#insert_edit" data-toggle="modal" onclick="initAdd('')" class="btn btn-default" data-backdrop="static" style="margin-left:30px;"><i class="ico-add"></i>发送信息</a>
                                </div>
                            </s:form>
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
                            <s:iterator value="#request.dataList" status="st" id="item">
                            <input type="hidden" id="content_<s:property value='#item.messageid' />" value="<s:property value='#item.content' />"/> 
                            <input type="hidden" id="imagecontent_<s:property value='#item.messageid' />" value="<s:property value='#item.imagecontent' />"/>  
                            <input type="hidden" id="headimage_<s:property value='#item.messageid' />" value="<s:property value='#item.headimage' />"/>
                            <input type="hidden" id="status_<s:property value='#item.messageid' />" value="<s:property value='#item.status' />"/>
                            <input type="hidden" id="contenttype_<s:property value='#item.messageid' />" value="<s:property value='#item.contenttype' />"/>
                           	<input type="hidden" id="title1_<s:property value='#item.messageid' />" value="<s:property value='#item.title1' />"/>
                           	<input type="hidden" id="content1_<s:property value='#item.messageid' />" value="<s:property value='#item.content1' />"/>
                           	<input type="hidden" id="image1_<s:property value='#item.messageid' />" value="<s:property value='#item.image1' />"/>
                           	<input type="hidden" id="author_<s:property value='#item.messageid' />" value="<s:property value='#item.author' />"/>
                           	<input type="hidden" id="url_<s:property value='#item.messageid' />" value="<s:property value='#item.url' />"/>
                           	<input type="hidden" id="appendix_<s:property value='#item.messageid' />" value="<s:property value='#item.appendix' />"/>
                           	<input type="hidden" id="userid_<s:property value='#item.messageid' />" value="<s:property value='#item.id' />"/>
                           	<input type="hidden" id="realname_<s:property value='#item.messageid' />" value="<s:property value='#item.realname' />"/>
                           	<input type="hidden" id="areaname_<s:property value='#item.messageid' />" value="<s:property value='#item.areaname' />"/>
                           	<input type="hidden" id="smallname_<s:property value='#item.messageid' />" value="<s:property value='#item.smallname' />"/>
                           	<input type="hidden" id="sellcode_<s:property value='#item.messageid' />" value="<s:property value='#item.sellcode' />"/>
                           	<input type="hidden" id="servecode_<s:property value='#item.messageid' />" value="<s:property value='#item.servecode' />"/>
                           	<input type="hidden" id="position_<s:property value='#item.messageid' />" value="<s:property value='#item.position' />"/>
                            	<tr>
                                    <td><s:property value="#item.messageid"/></td>
                                    <td><a href="#insert_edit" data-toggle="modal" onclick="initAdd('<s:property value="#item.messageid"/>');"><s:property value="#item.realname"/></a></td>
                                    <td>
                                    <s:if test="#item.contenttype==1">
                                    	 文本：<s:property value="#item.content"/>
                                    </s:if>
                                  	<s:elseif test="#item.contenttype==2">
                                    	图片：<s:property value="#item.content"/>
                                    </s:elseif>
                                    <s:elseif test="#item.contenttype==3">
                                    	音频：<s:property value="#item.content"/>
                                    </s:elseif>
                                    <s:elseif test="#item.contenttype==4">
                                    	视频：<s:property value="#item.content"/>
                                    </s:elseif>
                                    <s:elseif test="#item.contenttype==5">
                                    	文件：<s:property value="#item.content"/>
                                    </s:elseif>
                                    <s:elseif test="#item.contenttype==6">
                                    	图文信息：请查看详情
                                    </s:elseif>
                                   </td>
                                    <td>
										<s:if test="#item.status==1">
											发送消息(点击用户名可直接回复此员工)
										</s:if>
										<s:else>
											接收消息(点击用户名可继续发信息)
										</s:else>
									</td>
									<td><s:date name="#item.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>
                                    <s:if test="#item.contenttype==6">
										<a href="#save_edit" onclick="EditInfo('<s:property value='#item.messageid'/>','<s:property value='#item.imagecontent'/>')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>查看详情</a>
									</s:if>
									<a href="#deleteTip" onclick="deleteData('<s:property value='#item.messageid' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>删除</a>
									</td> 
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>         
                    </div>
                    ${pager}
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

$(document).ready(function(){
	$("#mymenutable tr").each(function(){
		$(this).children().eq(2).html($(this).children().eq(2).text());
	});
	initEditor();
	$("#delegate_btn_upload_image").click(function(){
		$("#btnc01 input[type='file']").each(function(){
			if($(this).hasClass("ke-upload-file")){
				$(this).click();
			}
		});
	});
	$("#btnc01").hide();
});
function initEditor(){
	KindEditor.ready(function(K) {
		initUploadBtn(K,'image');
	});
}
function initUploadBtn(K,type){
	var uploadbutton = K.uploadbutton({
		button : K('#btn_upload_'+type)[0],
		fieldName : 'imgFile',
		url : $("#upload_url").val()+type,
		afterUpload : function(data) {
			if (data.error === 0) {
				    $("#upload_image").attr("src",$('#basePath').val()+data.url);
				    $("#imageSrc").val(data.url);
			}else{
				alert("上传文件出错");
			}
		},
		afterError : function(str) {
			alert(str);
		}
	});
	uploadbutton.fileBox.change(function(e) {
		uploadbutton.submit();
	});
}

function deleteData(id){
	$('#messageid',$('#deleteForm')).val(id);
}
function initAdd(id){
	$('#sendmessage').show();
	if(id!=''){
		$('#bigarea',$('#insertEditForm')).select2("val",$('#areaname_'+id).val());
		$('#smarea',$('#insertEditForm')).text($('#smallname_'+id).val());
		$('#servecode',$('#insertEditForm')).val($('#servecode_'+id).val());
		$('#sellcode',$('#insertEditForm')).val($('#sellcode_'+id).val());
		$('#position',$('#insertEditForm')).select2("val",$('#position_'+id).val());
		$('#employee',$('#insertEditForm')).html("val", "");
		$('#employee',$('#insertEditForm')).append("<option value='"+$('#userid_'+id).val()+"'>"+$('#realname_'+id).val()+"</option>");
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
function getNextDepart(id){
	var departid=$('#departid'+id).val();
	$('#departid'+(id-1)).attr("name","");
	var url='<%=request.getContextPath() %>/backer/Basic!getNextDepart.action?paramMap.departid='+departid;

	if(departid!=''){
		hhutil.ajax(url,null,function(data){
			if(data.data.departlist!=''){
				id++;
				$('#departid'+id).remove();
				$('#depart').append("<select id='departid"+id+"' name='paramMap.departid' class='form-control select2-input' style='font-size:12px;width:100px;height:30px;margin-left:10px' onchange='getNextDepart("+id+")'>"+
	        	"<option value='' selected='selected'>全部部门</option>"+
				"</select>");
	    		$.each(data.data.departlist,function(i,item){
		       		$('#departid'+id).append("<option value='"+item.departid+"'>"+item.departname+"</option>");
	    		}); 
			}
			else{
				var a=$('#depart select').length;
				var b=parseInt(id);
				for(var i=b+1;i<(b+a);i++){
					$('#departid'+i).remove();
				}
			}
			$('#employee').html("<option value=''>全部员工</option>");
			$.each(data.data.employeelist,function(i,it){
				$('#employee').append("<option value='"+it.userid+"'>"+it.realname+"</option>");
			}); 
		});
	}
}
function uploadSuccess(path){
	var obj = document.getElementById('imgurl') ;
	obj.outerHTML=obj.outerHTML;
	$('#showMyImg',$('#insertEditForm')).attr("src","<%=request.getContextPath()%>"+path);
	$('#headimage',$('#insertEditForm')).val(path);
	$('#showMyImg1',$('#insertEditForm')).attr("src","<%=request.getContextPath()%>"+path);
	$('#image',$('#insertEditForm')).val(path);
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
		$('#textactive1 #txt').attr("name","paramMap.content");
		$('#textactive1').show();
		$('#contenttype').val('1');
		
	}else{
		$('#textactive').removeClass('active');
		$('#textactive1 #txt').attr("name","");
		$('#textactive1').hide();
		
		
	}
	if(id=="2"){
		$('#imageactive').addClass('active');
		$('#imageactive1 #headimage').attr("name","paramMap.content");
		$('#imageactive1').show();
		$('#contenttype').val('2');
	}else{
		$('#imageactive').removeClass('active');
		$('#imageactive1 #headimage').attr("name","")
		$('#imageactive1').hide();
		
	}
	if(id=="3"){
		$('#vioceactive').addClass('active');
		$('#vioceactive1 #vioce').attr("name","paramMap.content");
		$('#vioceactive1').show();
		$('#contenttype').val('3');
	}else{
		$('#vioceactive').removeClass('active');
		$('#vioceactive1 #vioce').attr("name","")
		$('#vioceactive1').hide();
	}
	if(id=="4"){
		$('#videoactive').addClass('active');
		$('#vioceactive1 #video').attr("name","paramMap.content");
		$('#videoactive1').show();
		$('#contenttype').val('4');
	}else{
		$('#videoactive').removeClass('active');
		$('#vioceactive1 #video').attr("name","")
		$('#videoactive1').hide();
	}
	if(id=="5"){
		$('#fileactive').addClass('active');
		$('#vioceactive1 #file').attr("name","paramMap.content");
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
	$("#smarea").html("");
	if($('#bigarea').val()!=""){
	    $.ajax({url:"<%=request.getContextPath()%>/backer/system!ajaxArea.action",data:{parentid:$("#bigarea").val()},type:"post",success:function(data){
	    var dataList=JSON.parse(data);
	    dataList=dataList.data;
	    for(var i=0;i<dataList.length;i++){
	    	$("#smarea").append("<label title='"+dataList[i].name+"' class='icheck-inline'><input name='ids'  type='checkbox'class='icheck-input fundidmain' value='"+dataList[i].id+"' />"+dataList[i].name+"</label>");
	        }
		    $("#smarea").find("input").iCheck("check");
		}});
	}
}


function sendMessage(){
	var contenttype=$("#contenttype").val();
	if(contenttype=="1"){
		if($("#txt").val()==""){
			alert("请输入消息内容!");
			return ;
		}
		
	}else if(contenttype=="2"){
		if($("#imageSrc").val()==""){
			alert("请输上传图片!");
			return ;
		}
	}
	$('#sendmessage').hide();
	$("#insertEditForm").submit();
}

</script>
<div id="deleteTip" class="modal fade tips-modal tips-modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
	<s:form action="Basic!deleteMessage.action" id="deleteForm" name="deleteForm"
			 theme="simple" method="post"
			namespace="/backer" >
	 <s:hidden  id="messageid" name="paramMap.messageid" ></s:hidden>
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title">提示</h3>
      </div>
      <div class="modal-body">
        <i class="ico-tips-warning"></i>
        <h4>确定删除？</h4>
        <p class="tips-modal-sub">删除之后无法恢复</p>
      </div>
		
      <div class="modal-footer">
        <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
        <button type="submit" class="btn btn-save"><i class="ico-ok"></i>确 定</button>
      </div>
      </s:form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 添加 -->
<div id="save_edit" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">图文详情</h3>
        </div>
        <s:form id="saveEditForm" class="form-horizontal parsley-form" method="post" namespace="/backer" role="form"  action="Basic!editMessage.action"  data-validate="parsley">
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
                  <input id="author1" name="pvMap.author" type="text" class="form-control parsley-validated">
                </div>
            </div>
            <br/>
            <div class="form-group" style="margin-top: 30px" >
                <label class="col-md-3 control-label"><i class="required">*</i>封面图片</label>
                <div class="col-md-7" style="width:388px;">
		               <img id="showMyImg2" alt="" src="<%=request.getContextPath() %>/" class="form-control" class="form-control" style="height:280px">
		             <font color="red">分辨率：300 * 400</font>
                </div>
            </div>
            <br/>
            <div class="form-group" style="margin-top: 30px" >
                <label class="col-md-3 control-label"><i class="required">*</i>内容</label>
                <div class="col-md-7" style="width:388px;">
			        <textarea rows="10" cols=""  class="form-control parsley-validated" 
	                id="content" name="pvMap.imagecontent"></textarea>
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
            </s:form>
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
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">发送消息给用户</h3>
        </div>

        <form id="insertEditForm" class="form-horizontal parsley-form" method="post"  role="form"  action="<%=request.getContextPath() %>/backer/Basic!sendMessage.action"  data-validate="parsley">
        <s:hidden  id="messageid" name="paramMap.messageid" ></s:hidden>
        <div class="modal-body">
            <div class="form-group">
                <label class="col-md-3 control-label">发送给 </label>
                <div class="col-md-7" style="width:390px;">
                <div id="depart"><%--
                	<select id="departid1" name="paramMap.departid"  class="form-control select2-input" style="width:100px;height:35px;" onchange="getNextDepart('1')">
                                    	<option value="" selected="selected">全部部门</option>
										<s:iterator value="#request.departList" var="item">
											<s:if test="#item.parentid==1">
								       		<option value="<s:property value='#item.departid' />"><s:property value='#item.departname' /></option>
								       		</s:if>
								       </s:iterator>
                  	</select>
                  	--%>
                  	  <select id="bigarea" onchange="changeArea()" name="paramMap.areaname" class="form-control select2-input select2-offscreen" style="width:150px;" >
							  <option value="">请选择</option>
							  <s:iterator value="#request.dictTypeList" var="item">
								 <option value="<s:property value='#item.id' />"><s:property value='#item.name' /></option>
							  </s:iterator>
                       </select>
                     <select id="mark" name="paramMap.markid"  class="form-control select2-input select2-offscreen" style="width:150px;margin-left:10px" >
                             <option value="">全部标签</option>
			       		  <s:iterator value="#request.marklist" var="item">
			       			<option value="<s:property value='#item.markid' />"><s:property value='#item.markname' /></option>
			      		  </s:iterator>
                      </select>
                      
                  	</div>
                  	<span style="font-size:12px;"></span><select id="employee" name="paramMap.employeeid"  class="form-control select2-input select2-offscreen" style="display:none" >
                            <option value="" selected="selected">全部员工</option>
                               <s:iterator value="#request.empList" var="item">
                                    <option value="<s:property value="#item.id"/>" ><s:property value="#item.realname"/></option>
                               </s:iterator></select>
                       
                 </div>
            </div>
             <br/>
                 <div class="form-group">
		                <label class="col-md-3 control-label">小区</label>
		                <div id="smarea" class="col-md-7">
					       
                   </div>
               </div>
             <br/>
               <div class="form-group">
		                <label class="col-md-3 control-label">服务代码</label>
		                <div  class="col-md-7">
		                <input id="servecode" name="paramMap.servecode" type="text" class="form-control parsley-validated" >
                   </div>
               </div>
               <br/>
               <div class="form-group" >
		                <label class="col-md-3 control-label">销售代码</label>
		                <div class="col-md-7">
		                <input id="sellcode" name="paramMap.sellcode" type="text" class="form-control parsley-validated" >
                   </div>
               </div>
                 <div class="form-group" >
		                <label class="col-md-3 control-label">职位</label>
		                <div  class="col-md-7">
		                <select id="position" name="paramMap.position" class="form-control select2-input select2-offscreen" style="width:150px;">
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
                
            <div class="form-group" >
                <label class="col-md-3 control-label">内容</label>
                <div class="col-md-7" style="width:390px;">
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
            <div id="textactive1">
				<div class="form-group" >
				 <br/>
	                <label class="col-md-3 control-label">文本</label>
	                <div class="col-md-7" style="width:388px;">
	                <textarea rows="10" cols=""  class="form-control" id="txt" name="paramMap.content"   ></textarea>
	                  </div>
	            </div>
            </div>
            <div  id="imageactive1" style="display:none">
	            <div class="form-group" >
	            <br/>
	                <label class="col-md-3 control-label">图片</label>
	                <div class="col-md-3" >
	                	     <input class="btn btn-default"  type="button" id="delegate_btn_upload_image" value="上传图片" />
                             <input type="hidden" name="paramMap.imageSrc" id="imageSrc" />
                           
	                </div>
	                 <div class="col-md-4" >
	                	      <a href="javascript:;" class="thumbnail">
                                  <img src="" alt="" id="upload_image" />
                               </a>
	                </div>
	                
	            </div>
          	</div>
          	<div  id="vioceactive1" style="display:none">
	            <div class="form-group">
	              <br/>
	                <label class="col-md-3 control-label">音频</label>
	                <div class="col-md-7" style="width:388px;">
	                <textarea rows="10" cols=""  class="form-control parsley-validated"
	                id="vioce" name="paramMap.content"></textarea>
	                  </div>
	            </div>
           </div>
           <div  id="videoactive1" style="display:none">
	            <div class="form-group">
	             <br/>
	                <label class="col-md-3 control-label">视频</label>
	                <div class="col-md-7" style="width:388px;">
	                <textarea rows="10" cols=""  class="form-control parsley-validated"
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
            <br/>
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
		               <img id="showMyImg1" alt="" src="<%=request.getContextPath() %>/" class="form-control" class="form-control" style="height:280px">
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
        <div class="modal-footer">
       		<input type="hidden" id="contenttype" name="paramMap.contenttype" value="1"/>
            <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
            <button type="button" onclick="sendMessage();" id="sendmessage" class="btn btn-save"><i class="ico-ok"></i>发送</button>
        </div>
        </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="btnc01">
<input type="button" value="上传图片" id="btn_upload_image" >
<input type="hidden"  id="upload_url" value="<s:url value='/wechat/media/uploadQyMedia.action?dir=' />" />
<input type="hidden" id="basePath" value="<%=basePath%>">
</div>


<s:form action="Basic!uploadEmployeeImg.action" id="imgUploadForm" name="imgUploadForm"
	enctype="multipart/form-data" theme="simple" method="post"
	namespace="/backer"  target="imgFrame">
<s:file style="width:0px;height:0px;"  name="imgurl" id="imgurl" onchange="document.getElementById('imgUploadForm').submit();"  ></s:file>
</s:form>
<iframe id="imgFrame"  name="imgFrame" style="display:none;width:0px;height:0px;"></iframe>
</body>
</html>