<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

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
                            <s:form class="form-inline" role="form" namespace="/backer" id="searchForm" 	action="system!getAgencyList.action">
                                <div class="form-input">
                                 	<input class="form-control"  id="realname"   name="pvMap.realname" placeholder="姓名" type="text"  value="<s:property value='#request.pvMap.realname' />">
                                </div>
                                <div class="form-input" style="margin-left: 20px">
                                   <input class="form-control"   name="pvMap.phone" placeholder="手机" type="text"  value="<s:property value='#request.pvMap.phone' />">
                                </div>  
                               <input type="hidden" name="pvMap.departid" id="departid" value="<s:property value="#request.pvMap.departid"/>">
                              <div class="form-input" style="margin-left: 20px">
                                   <input class="form-control"  name="pvMap.servecode" placeholder="服务代码" type="text"  value="<s:property value='#request.pvMap.servecode' />">
                         		</div>   
                               <div class="form-input" style="margin-left: 20px">
                                  <input class="form-control"  name="pvMap.sellcode" placeholder="销售代码" type="text"  value="<s:property value='#request.pvMap.sellcode' />">
                         		</div>
                         		 <div class="form-input" style="margin-left: 20px">
                                  <input class="form-control"  name="pvMap.username" placeholder="账号" type="text"  value="<s:property value='#request.pvMap.username' />">
                         		</div>
                         		 <div class="form-input" style="margin-left: 20px">
                                  <input class="form-control"  name="pvMap.email" placeholder="邮箱" type="text"  value="<s:property value='#request.pvMap.email' />">
                         		</div>
                         		 <div class="form-input" style="margin-left: 20px">
                         		 
                         		 <label for="queryPosition"> 职位:</label>
                         		 <select id="queryPosition" name="pvMap.position" class="form-control select2-input select2-offscreen" style="width:150px;" >
								 <option <s:if test="#request.pvMap.position==''">selected="selected"</s:if> value="">请选择</option>
								 <option <s:if test="#request.pvMap.position=='总经理'">selected="selected"</s:if> value="总经理" >总经理</option>
								 <option <s:if test="#request.pvMap.position=='销售经理'">selected="selected"</s:if> value="销售经理">销售经理</option>
								 <option <s:if test="#request.pvMap.position=='服务经理'">selected="selected"</s:if> value="服务经理">服务经理</option>
								 <option <s:if test="#request.pvMap.position=='DCRC经理'">selected="selected"</s:if> value="DCRC经理">DCRC经理</option>
								 <option <s:if test="#request.pvMap.position=='零件经理'">selected="selected"</s:if> value="零件经理">零件经理</option>
								 <option <s:if test="#request.pvMap.position=='索赔员'">selected="selected"</s:if> value="索赔员">索赔员</option>
								 <option <s:if test="#request.pvMap.position=='市场经理'">selected="selected"</s:if> value="市场经理">市场经理</option>
                               </select>
                         		</div>
                              
                                </div>
                                <br><br>
                                <div class="form-search-sub" style="margin-left:20px;">
                                    <button type="submit" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                    <a href="#save_edit" data-toggle="modal" onclick="initAdd();" class="btn btn-default" data-backdrop="static" style="margin-left:30px;"><i class="ico-add"></i>添加</a>
                                    <a href="javascript:;"  onclick="deleteConfirm();" class="btn btn-danger" data-backdrop="static" style="margin-left:30px;"><i class="ico-delete"></i>批量删除</a>
                                </div>
                            </s:form>
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
                            <s:iterator value="#request.dataList" status="st" id="item">
                            <input type="hidden" id="realname_<s:property value='#item.id' />" value="<s:property value='#item.realname' />"/>
                            <input type="hidden" id="username_<s:property value='#item.id' />" value="<s:property value='#item.username' />"/>
                            <input type="hidden" id="position_<s:property value='#item.id' />" value="<s:property value='#item.position' />"/>
                            <input type="hidden" id="phone_<s:property value='#item.id' />" value="<s:property value='#item.phone' />"/>
                            <input type="hidden" id="email_<s:property value='#item.id' />" value="<s:property value='#item.email' />"/>                           
                            <input type="hidden" id="headimage_<s:property value='#item.id' />" value="<s:property value='#item.headimage' />"/>
                            <input type="hidden" id="sex_<s:property value='#item.id' />" value="<s:property value='#item.sex' />"/>
                            <input type="hidden" id="wxnum_<s:property value='#item.id' />" value="<s:property value='#item.wxnum' />"/>
                            <input type="hidden" id="marknames_<s:property value='#item.id' />" value="<s:property value='#item.marknames' />"/>
                            <input type="hidden" id="markids_<s:property value='#item.id' />" value="<s:property value='#item.markids' />"/>
                            <input type="hidden" id="rolenames_<s:property value='#item.id' />" value="<s:property value='#item.rolenames' />"/>
                            <input type="hidden" id="roleids_<s:property value='#item.id' />" value="<s:property value='#item.roleids' />"/>
                            <input type="hidden" id="areaname_<s:property value='#item.id' />" value="<s:property value='#item.areaname' />"/>
                            <input type="hidden" id="smallname_<s:property value='#item.id' />" value="<s:property value='#item.smallname' />"/>
                            <input type="hidden" id="servecode_<s:property value='#item.id' />" value="<s:property value='#item.servecode' />"/>
                            <input type="hidden" id="sellcode_<s:property value='#item.id' />" value="<s:property value='#item.sellcode' />"/>
                           
                            	<s:if test="#item.id!='' and  #item.id!=null">
                            	<tr>
                            		<td><img src="../<s:property value="#item.headimage"/>" width="40" height="40"/></td>
                                    <td><s:property value="#item.realname"/></td>
                                    <td><s:property value="#item.username"/></td>
                                    <td><s:property value="#item.position"/></td>
                                    <td><s:property value="#item.phone"/></td>
                                    <td><s:property value="#item.email"/></td>   
                                    <td><s:property value="#item.bigarname"/></td> 
                                    <td><s:property value="#item.smareaname"/></td>  
                                    <td><s:property value="#item.servecode"/></td> 
                                    <td><s:property value="#item.sellcode"/></td>    
                                    <td>
									<a href="#save_edit" onclick="EditInfo('<s:property value='#item.id'/>')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>编辑</a>
									<a href="#deleteTip" onclick="deleteData('<s:property value='#item.id' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>删除</a>
									<a href="#mark_edit" onclick="markData('<s:property value='#item.id' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>标签设置</a>
									</td> 
                                </tr>
                                </s:if>
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
function deleteData(id){
	$('#id',$('#deleteForm')).val(id);
}
//点击编辑，初始化form表单
function EditInfo(id){
	$('#userpass').hide();
	$('#realname',$('#saveEditForm')).val($('#realname_'+id).val());
	$('#username',$('#saveEditForm')).val($('#username_'+id).val());
	
	$('#phone',$('#saveEditForm')).val($('#phone_'+id).val());
	$('#email',$('#saveEditForm')).val($('#email_'+id).val());
	$('#wxnum',$('#saveEditForm')).val($('#wxnum_'+id).val());
	
	$('#position',$('#saveEditForm')).select2("val",$('#position_'+id).val());
	$('#bigarea',$('#saveEditForm')).select2("val", $('#areaname_'+id).val());
	$("#smarea").html("");
    $.ajax({url:"<%=request.getContextPath()%>/backer/system!ajaxArea.action",data:{parentid:$('#bigarea').val()},type:"post",success:function(data){
	    var dataList=JSON.parse(data);
	    dataList=dataList.data;
	    $("#smarea").html("");
	    for(var i=0;i<dataList.length;i++){
	    	$("#smarea").append("<option value='"+dataList[i].id+"'>"+dataList[i].name+"</option>");
	    }
		$('#smarea',$('#saveEditForm')).select2("val",$('#smallname_'+id).val());
	}});
	$('#servecode',$('#saveEditForm')).val($('#servecode_'+id).val());
	$('#sellcode',$('#saveEditForm')).val($('#sellcode_'+id).val());
	var sex=$('#sex_'+id).val();
	if(sex=="1"){
		$('#sex',$('#saveEditForm')).select2("val", "1");
	}else{
		$('#sex',$('#saveEditForm')).select2("val", "2");
	}
	$('#headimage',$('#saveEditForm')).val($('#headimage_'+id).val());
	var defaultimg ="<%=request.getContextPath()%>/basic/upload/nopic.jpg";
	if(!hhutil.isEmpty($('#headimage_'+id).val())){
		defaultimg = "<%=request.getContextPath()%>"+$('#headimage_'+id).val() ;
	}
	$('#showMyImg',$('#saveEditForm')).attr("src",defaultimg);
	$('#id',$('#saveEditForm')).val(id);
   
}

function deleteConfirm(){
	if(confirm("注意:您正在删除当前查询条件下的所有经销商,删除经销商会删除该经销商相关的所有咨询与解答数据,您确认要进行此操作吗?")==false){
	  return ;
	}
	$("#searchForm").attr("action","system!deleteAgencyBatch.action");
	$("#searchForm").submit();
}
function initAdd(){
	$("#searchForm").attr("action","system!getAgencyList.action");
	$('#userpass').show();
	$('#realname',$('#saveEditForm')).val("");
	$('#username',$('#saveEditForm')).val("");
	
	$('#phone',$('#saveEditForm')).val("");
	$('#email',$('#saveEditForm')).val("");
	$('#wxnum',$('#saveEditForm')).val("");
	
	$('#position',$('#saveEditForm')).select2("val","");
	
	
	$('#servecode',$('#saveEditForm')).val("");
	$('#sellcode',$('#saveEditForm')).val("");
	$('#departid',$('#saveEditForm')).select2("val","");
	$('#headimage',$('#saveEditForm')).val("");
	$('#showMyImg',$('#saveEditForm')).attr("src","");
	$('#id',$('#saveEditForm')).val("");
}
function markData(id){
	var marknames=$('#marknames_'+id).val();
	var markList = marknames.split(",");
	var markids=$('#markids_'+id).val();
	var markidList = markids.split(",");
	console.log(markidList);
	console.log(markList);
	$('#marknames',$('#markEditForm')).html("");
	for(var i = 0; i < markList.length;i++){
		$('#marknames',$('#markEditForm')).append("<input type='button' class='but' value='"+markList[i]+"'/>&nbsp;&nbsp;<input  class='markid' type='hidden' value='"+markidList[i]+"'>");
	}
	$('#id',$('#markEditForm')).val(id);
	var markids =  $('#markids_'+id).val();
	if(markids!=null || markids!=""){
		$("#markids",$('#markEditForm')).val(markids);
		var markidslist = markids.split(",");
		$("input[name='markidlist']:checkbox").each(function(){
			var flag = false ;
			for(var i=0,len=markidslist.length;i<len;i++){
				if($(this).val()==markidslist[i]){
					flag = true ;
					break ;
				}
			}
			if(flag){
				$(this).iCheck('check');
			}else{
				$(this).iCheck("uncheck");
			}
		});
	} 
	$('#id',$('#markEditForm')).val(id);
}
function roleData(id){
	var rolenames=$('#rolenames_'+id).val();
	var roleList = rolenames.split(",");
	var roleids=$('#roleids_'+id).val();
	var roleidList = roleids.split(",");
	console.log(roleidList);
	console.log(roleList);
	$('#rolenames',$('#roleEditForm')).html("");
	for(var i = 0; i < roleList.length;i++){
		$('#rolenames',$('#roleEditForm')).append("<input type='button' class='but' value='"+roleList[i]+"'/>&nbsp;&nbsp;<input  class='roleid' type='hidden' value='"+roleidList[i]+"'>");
	}
	$('#id',$('#roleEditForm')).val(id);
	var roleids =  $('#roleids_'+id).val();
	if(roleids!=null || roleids!=""){
		$("#roleids",$('#roleEditForm')).val(roleids);
		var roleidslist = roleids.split(",");
		$("input[name='roleidlist']:checkbox").each(function(){
			var flag = false ;
			for(var i=0,len=roleidslist.length;i<len;i++){
				if($(this).val()==roleidslist[i]){
					flag = true ;
					break ;
				}
			}
			if(flag){
				$(this).iCheck('check');
			}else{
				$(this).iCheck("uncheck");
			}
		});
	} 
	$('#id',$('#roleEditForm')).val(id);
}
function uploadSuccess(path){
	var obj = document.getElementById('imgurl') ;
	obj.outerHTML=obj.outerHTML;
	$('#showMyImg',$('#saveEditForm')).attr("src","<%=request.getContextPath()%>"+path);
	$('#headimage',$('#saveEditForm')).val(path);
}
$(function(){
	var id= $("#bigarea").val();
	changeArea();
	
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
function changeArea(){
	$("#smarea").html("");
    $.ajax({url:"<%=request.getContextPath()%>/backer/system!ajaxArea.action",data:{parentid:$('#bigarea').val()},type:"post",success:function(data){
    var dataList=JSON.parse(data);
    dataList=dataList.data;
    for(var i=0;i<dataList.length;i++){
    	$("#smarea").append("<option value='"+dataList[i].id+"'>"+dataList[i].name+"</option>");
    }
}});
}
</script>
<a href="#error" id="statea" data-toggle="modal"  style="display: none"><span>提示</span></a>
<input type="hidden" id="state" value="<s:property value="#request.state"/>"/>
<div id="error" class="modal fade tips-modal tips-modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
	<s:form  theme="simple" method="post" >
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
      </s:form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div id="deleteTip" class="modal fade tips-modal tips-modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
	<s:form action="system!deleteAgency.action" id="deleteForm" name="deleteForm"
			 theme="simple" method="post"
			namespace="/backer" >
	 <input type="hidden" id="id" name="paramMap.id" />
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
            <h3 class="modal-title">经销商信息编辑</h3>
        </div>
        <s:form id="saveEditForm" class="form-horizontal parsley-form" method="post" namespace="/backer" role="form"  action="system!editAgency.action"  data-validate="parsley">
        <input type="hidden" id="id" name="paramMap.id" />
        <div class="modal-body">
             <div class="form-group">
                <label class="col-md-3 control-label">头像</label>
                <div class="col-md-7">
                	 <input type="hidden" id="headimage" name="paramMap.headimage">
		               <img id="showMyImg" alt="" src="<%=request.getContextPath() %>/" class="form-control" class="form-control" style="height:280px">
		               <font color="red">分辨率：300 * 400</font>
                         </div>
             </div>
            <div class="form-group" style="margin-top:280px;padding-top:20px;">
                <label class="col-md-3 control-label"><i class="required">*</i>姓名</label>
                <div class="col-md-7">
                  <input id="realname"name="paramMap.realname" type="text" class="form-control parsley-validated" data-required="true">
                </div>
            </div>
             <div class="form-group" style="margin-top:20px;padding-top:30px;">
                <label class="col-md-3 control-label"><i class="required">*</i>帐号名称</label>
                <div class="col-md-7">
                  <input id="username" name="paramMap.username" type="text" class="form-control parsley-validated" data-required="true">
                </div>
            </div>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
                <label class="col-md-3 control-label">性别</font></label>
                <div class="col-md-7">
                  <select id="sex" name="paramMap.sex" class="form-control select2-input select2-offscreen" style="width:150px;" >
							<option value="1" >男</option>
							<option value="2" >女</option>
                     </select> 
                     </div>
            </div>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
		                <label class="col-md-3 control-label">职位</label>
		                <div  class="col-md-7">
		                <select id="position" name="paramMap.position" class="form-control select2-input select2-offscreen" style="width:150px;" >
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
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
                <label class="col-md-3 control-label"><i class="required">*</i>手机</label>
                <div class="col-md-7">
                  <input id="phone" name="paramMap.phone" type="text" class="form-control parsley-validated" data-required="true">
                </div>
            </div>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
                <label class="col-md-3 control-label">微信号</label>
                <div class="col-md-7">
                  <input id="wxnum" name="paramMap.wxnum" type="text" class="form-control parsley-validated" >
                </div>
            </div>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
                <label class="col-md-3 control-label">邮箱</label>
                <div class="col-md-7">
                  <input id="email" name="paramMap.email" type="text" class="form-control parsley-validated" data-type="email">
                </div>
            </div>
                   <div class="form-group" style="margin-top:20px;padding-top:20px;">
		                <label class="col-md-3 control-label"><i class="required">*</i>大区</label>
		                <div  class="col-md-7">
		                 <select id="bigarea" onchange="changeArea()" name="paramMap.areaname" class="form-control select2-input select2-offscreen" style="width:150px;"  data-required="true" >
							  <s:iterator value="#request.dictTypeList" var="item">
								 <option value="<s:property value='#item.id' />"><s:property value='#item.name' /></option>
							  </s:iterator>
                            </select>
                      </div>
                </div>
                <input id="departid" name="paramMap.departid" type="hidden" value="1" class="form-control parsley-validated" >
                <div class="form-group" style="margin-top:20px;padding-top:20px;">
		                <label class="col-md-3 control-label"><i class="required">*</i>小区</label>
		                <div  class="col-md-7">
		                 <select id="smarea"  name="paramMap.smallname" class="form-control select2-input select2-offscreen" style="width:150px;"  data-required="true">
		                  <option value="">请选择</option>
		                 </select>
                   </div>
               </div>
               <div class="form-group" style="margin-top:20px;padding-top:20px;">
		                <label class="col-md-3 control-label"><i class="required">*</i>服务代码</label>
		                <div  class="col-md-7">
		                <input id="servecode" name="paramMap.servecode" type="text" class="form-control parsley-validated" data-required="true" >
                   </div>
               </div>
               <div class="form-group" style="margin-top:20px;padding-top:20px;">
		                <label class="col-md-3 control-label"><i class="required">*</i>销售代码</label>
		                <div class="col-md-7">
		                <input id="sellcode" name="paramMap.sellcode" type="text" class="form-control parsley-validated" data-required="true" >
                   </div>
               </div>
            </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-save"><i class="ico-ok"></i>保 存</button>
        </div>
        </s:form>
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
        <s:form id="markEditForm" class="form-horizontal parsley-form" method="post" namespace="/backer" role="form"  action="system!editMarkAgency.action"  data-validate="parsley" >
        <input type="hidden" id="id" name="paramMap.id"/>
        <input type="hidden" id="markids" name="paramMap.markids"/>
          <div class="modal-body">
                <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>用户当前标签</label>
                <div class="col-md-7" id="marknames">
                </div>
            </div>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
                <label class="col-md-3 control-label"><i class="required">*</i>所有标签</label>
                <div class="col-md-7">
                	<input type="hidden" name="paramMap.markid" id="markids1"/>
                	 <s:iterator value="#request.marklist" var="mark">
	                	<label title="${mark.markname}" class="icheck-inline">
	                        <input type="checkbox" name="markidlist" class="icheck-input" value="${mark.markid}">${mark.markname }
	                    </label>
                	</s:iterator>
                         </div>
                   </div>
            </div>

            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-save"><i class="ico-ok"></i>保 存</button>
        </div>
        </s:form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 角色 -->

<s:form action="system!uploadAgencyImg.action" id="imgUploadForm" name="imgUploadForm"
	enctype="multipart/form-data" theme="simple" method="post"
	namespace="/backer"  target="imgFrame">
<s:file style="width:0px;height:0px;"  name="imgurl" id="imgurl" onchange="document.getElementById('imgUploadForm').submit();"  ></s:file>
</s:form>
<iframe id="imgFrame"  name="imgFrame" style="display:none;width:0px;height:0px;"></iframe>
</body>
</html>