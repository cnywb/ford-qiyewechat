<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

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
                            <s:form class="form-inline" role="form" namespace="/backer"
								id="searchForm" action="system!getMaterialList.action">
                                <div class="form-input">
                                 	<input class="form-control"  id="materialname" value="<s:property value="#request.pvMap.materialname"/>"  name="pvMap.materialname" placeholder="关键字" type="text"  value="<s:property value='#request.pvMap.markname' />">
                                </div>
                                
                                 <div class="form-date-single-time" style="margin-left:20px;">
                                    <input class="form-control date-picker-single-time" id="startDate" type="text" 
										name="pvMap.gainstarttime" placeholder="开始时间"
										value="<s:property value='#request.pvMap.gainstarttime' />" readonly>
                                    <span class="date-picker-addon"><i class="ico-calendar"></i></span>
                                </div>
                                <div class="form-date-single-time" style="margin-left:20px;">
                                    <input class="form-control date-picker-single-time" id="endDate" type="text" 
										name="pvMap.gainendtime" placeholder="结束时间"
										value="<s:property value='#request.pvMap.gainendtime' />" readonly>
                                    <span class="date-picker-addon"><i class="ico-calendar"></i></span>
                                </div>
                                	<div class="form-select" style="margin-left:30px;">
                                    <select name="pvMap.status" class="form-control select2-input select2-offscreen" style="width:90px;" >
                                    		<s:if test="#request.pvMap.status==0">
											<option value="" >全部</option>
					                		<option value="0" selected="selected">启用中</option>
					                		<option value="1">未启用</option>
					                		</s:if>
					                		<s:elseif test="#request.pvMap.status==1">
					                		<option value="" >全部</option>
					                		<option value="0">启用中</option>
					                		<option value="1"  selected="selected">未启用</option>
					                		</s:elseif>
					                		<s:elseif test="#request.pvMap.status==3">
					                		<option value=""  selected="selected">全部</option>
					                		<option value="0">启用中</option>
					                		<option value="1" >未启用</option>
					                		</s:elseif>
					                		<s:else>
					                		<option value="" selected="selected">全部</option>
					                		<option value="1" >启用中</option>
					                		<option value="0" >未启用</option>
					                		</s:else>
                                    </select>
                                </div>
                                
                                <div class="form-search-sub" style="margin-left:20px;">
                                    <button type="submit" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                    <a href="#save_edit" data-toggle="modal" onclick="initAdd();" class="btn btn-default" data-backdrop="static" style="margin-left:30px;"><i class="ico-add"></i>添加</a>
                                </div>
                            </s:form>
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
                            <s:iterator value="#request.dataList" status="st" id="item">
                            	<tr>
                                    <td><s:property value="#item.materialid"/></td>
                                    <td><s:property value="#item.materialname"/></td>
                                    <td><s:property value="#item.materialcontent"/></td>
                                    <td><s:date name="#item.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td><s:property value="#item.downloads"/></td>
                                    <td><s:property value="#item.realname"/></td>
                                    <td>
                                      <s:if test="#item.status == 0">启用中</s:if>
                                      <s:else>停用中</s:else>
                                    </td>
                                    <td>
                                     <s:if test="#item.status == 0"><a href="#deleteTip" onclick="deleteData('<s:property value='#item.materialid' />','<s:property value='#item.status' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>停用</a></s:if>
                                     <s:else><a href="#deleteTip" onclick="deleteData('<s:property value='#item.materialid' />','<s:property value='#item.status' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>启用</a></s:else>
                                     &nbsp;&nbsp;<a href="<%=request.getContextPath()%>/backer/system!getUserInfoByMater.action?pvMap.materid=<s:property value="#item.materialid"/>">下发详情</a>
                                     &nbsp;&nbsp;<a href="<%=request.getContextPath()%>/backer/system!getUserInfoByload.action?pvMap.materid=<s:property value="#item.materialid"/>">下载详情</a>
                                     &nbsp;&nbsp;<a onclick="OutputStream('<s:property value="#item.materialname"/>')" >下载</a>
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
}); 


function deleteData(id,status){
	if(status == 0){
	  $('#status',$('#deleteForm')).val(1);
      $('#msg').html("停用后无法再下载物料资源");
	}else{
	  $('#status',$('#deleteForm')).val(0);
	  $('#msg').html("启用后可以下载物料资源");
	}
	$('#materialid',$('#deleteForm')).val(id);
}

function initAdd(){
	$('#materialcontent',$('#saveEditForm')).val("");
}
function OutputStream(url,createtime){
	window.location.href='<%=request.getContextPath()%>/backer/download!execute.action?filename='+url;
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
<div id="deleteTip" class="modal fade tips-modal tips-modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
	<s:form action="system!updateMaterialStatus.action" id="deleteForm" name="deleteForm"
			 theme="simple" method="post"
			namespace="/backer" >
	 <s:hidden  id="materialid" name="paramCondition.materialid"></s:hidden>
	  <s:hidden  id="status" name="paramCondition.status"></s:hidden>
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title">提示</h3>
      </div>
      <div class="modal-body">
        <i class="ico-tips-warning"></i>
        <h4>确认修改状态？</h4>
        <p id="msg" class="tips-modal-sub"> </p>
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
            <h3 class="modal-title">下发物料</h3>
        </div>
        <s:form id="saveEditForm" class="form-horizontal parsley-form" enctype="multipart/form-data" method="post" namespace="/backer" role="form"  action="system!saveMaterial.action"  data-validate="parsley">
        <div class="modal-body">
            <div class="form-group">
                <label class="col-md-3 control-label">内容摘要</label>
                <div class="col-md-7">
                  <input id="materialcontent" name="paramCondition.materialcontent" type="text" class="form-control parsley-validated" >
                </div>
            </div>
            <br/><br/>
             <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>文件</label>
                <div id="url_file" class="col-md-7">
                  <input id="fileToUpload" onchange="upload()" name="file"  type="file" class="form-control parsley-validated" data-required="true">
                   <SPAN id="filesize"></SPAN>
                </div>
            </div>
            <br/>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
		                <label class="col-md-3 control-label">大区</label>
		                <div  class="col-md-7">
		                <select id="bigarea" onchange="changeArea()" name="paramCondition.areaname" class="form-control select2-input select2-offscreen" style="width:150px;" >
							  <option value="">请选择</option>
							  <s:iterator value="#request.dictTypeList" var="item">
								 <option value="<s:property value='#item.id' />"><s:property value='#item.name' /></option>
							  </s:iterator>
                            </select>
                      </div>
                </div>
                <br/>
                <div class="form-group" style="margin-top:20px;padding-top:20px;">
		                <label class="col-md-3 control-label">小区</label>
		                <div id="smarea" class="col-md-7">
					       
                   </div>
               </div>
               <br/>
               <div class="form-group" style="margin-top:20px;padding-top:20px;">
		                <label class="col-md-3 control-label">服务代码</label>
		                <div  class="col-md-7">
		                <input id="servecode" name="paramCondition.servecode" type="text" class="form-control parsley-validated" >
                   </div>
               </div>
               <br/>
               <div class="form-group" style="margin-top:20px;padding-top:20px;">
		                <label class="col-md-3 control-label">销售代码</label>
		                <div class="col-md-7">
		                <input id="sellcode" name="paramCondition.sellcode" type="text" class="form-control parsley-validated" >
                   </div>
               </div>
                 <div class="form-group" style="margin-top:20px;padding-top:20px;">
		                <label class="col-md-3 control-label">职位</label>
		                <div  class="col-md-7">
		                <select id="position" name="paramCondition.position" class="form-control select2-input select2-offscreen" style="width:150px;">
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
        <div class="modal-footer">
            <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
            <button type="button" onclick="tijiao();"  class="btn btn-save"><i class="ico-ok"></i>发送</button>
        </div>
        </s:form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>