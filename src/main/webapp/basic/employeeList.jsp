<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-activity p-activity-type">
	<div class="wrapper">
		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>
	

	
	<div class="content">
        <div class="crumb">
            <h5>基础功能 &#187; 员工信息录入及部门管理</h5>
        </div>
       
        <div class="content-container" >
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>员工信息录入及部门管理</span></h3>
                    <div class="row area-list-dec">     
                    	 <div class="area-search">
                            <s:form class="form-inline" role="form" namespace="/backer"
								id="searchForm" 	action="Basic!getEmployeeList.action">
                                <div class="form-input">
                                 	<input class="form-control"  id="realname"   name="pvMap.realname" placeholder="姓名" type="text"  value="<s:property value='#request.pvMap.realname' />">
                                </div>
                                 <div class="form-input" style="margin-left: 20px">
                                   <input class="form-control"  id="username"  name="pvMap.username" placeholder="帐号" type="text"  value="<s:property value='#request.pvMap.username' />">
                                </div> 
                                <div class="form-input" style="margin-left: 20px">
                                   <input class="form-control"  id="username"  name="pvMap.position" placeholder="职位" type="text"  value="<s:property value='#request.pvMap.position' />">
                                </div> 
                                <div class="form-input" style="margin-left: 20px">
                                   <input class="form-control"  id="username"  name="pvMap.email" placeholder="邮箱" type="text"  value="<s:property value='#request.pvMap.email' />">
                                </div>  
                               <input type="hidden" name="pvMap.departid" id="departid" value="<s:property value="#request.pvMap.departid"/>">
                              <!--   <div class="form-input" style="margin-left: 20px">
                                    <select id="departid" name="pvMap.departid"  class="form-control select2-input select2-offscreen" style="width:150px;" >
                                    	<option value="" selected="selected">全部部门</option>
										<s:iterator value="#request.departList" var="item">
											<s:if test="#request.pvMap.departid==#item.departid">
												<option value="<s:property value='#item.departid' />" selected="selected"><s:property value='#item.departname' /></option>
											</s:if>
											<s:else>
								       		<option value="<s:property value='#item.departid' />"><s:property value='#item.departname' /></option>
								       		</s:else>
								       </s:iterator>
                                    </select>
                         		</div>   -->
                              
                                </div>
                                <div class="form-search-sub" style="margin-left:20px;">
                                    <button type="submit" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                    <a href="#save_edit" data-toggle="modal" onclick="initAdd();" class="btn btn-default" data-backdrop="static" style="margin-left:30px;"><i class="ico-add"></i>添加</a>
                                <a href="#deletedepart"  style="display:none" data-toggle="modal" class="btn btn-default" data-backdrop="static" ><span  id="deletede">AA</span></a>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    
                     <div class="table-responsive" style="border: 0px solid blue;width: 20%;height: auto;float: left">
						<table style="border-left: 1px solid #DDDDDD;border-top:0px;border-bottom:0px;border-right width: 100%;"
							class="table table-striped table-bordered table-hover table-highlight">
								<thead>
									<tr style="height: 32px;">
										<th><center>部门类型</center></th>
									</tr>
								</thead>
								<tbody style="border-right: 0px;">
									<tr style="height: 555px;border-right: 0px;">
										<th style="border: 0px">
											<iframe scrolling="no" src="<%=request.getContextPath() %>/basic/orgtree.jsp" width="100%" height="100%" frameborder=0></iframe>
										</th>
									</tr>
								</tbody>
						</table>
					</div>
                    
                    <div class="table-responsive"  style="width: 78%;float: left">
                        <table id="mymenutable"  class="table table-striped table-bordered table-hover table-highlight table-checkable order-column" data-provide="datatable" data-display-rows="10" data-info="true" data-length-change="true" data-paginate="true">
                            <thead>
                                <tr>
                                	<th>缩略图</th>
                                    <th>姓名</th>
                                    <th>帐号</th>
                                    <th>职位</th>
                                    <th>手机</th>
                                    <th>邮箱</th>
                                    <th>部门</th>    
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <input id="log_roleid" value="<s:property value="#request.pvMap.roleid"/>" type="hidden"/>
                            <s:iterator value="#request.dataList" status="st" id="item">
                            <input type="hidden" id="realname_<s:property value='#item.id' />" value="<s:property value='#item.realname' />"/>
                            <input type="hidden" id="username_<s:property value='#item.id' />" value="<s:property value='#item.username' />"/>
                            <input type="hidden" id="position_<s:property value='#item.id' />" value="<s:property value='#item.position' />"/>
                            <input type="hidden" id="phone_<s:property value='#item.id' />" value="<s:property value='#item.phone' />"/>
                            <input type="hidden" id="email_<s:property value='#item.id' />" value="<s:property value='#item.email' />"/>
                            <input type="hidden" id="departid_<s:property value='#item.id' />" value="<s:property value='#item.departid' />"/>                           
                            <input type="hidden" id="headimage_<s:property value='#item.id' />" value="<s:property value='#item.headimage' />"/>
                            <input type="hidden" id="sex_<s:property value='#item.id' />" value="<s:property value='#item.sex' />"/>
                            <input type="hidden" id="wxnum_<s:property value='#item.id' />" value="<s:property value='#item.wxnum' />"/>
                            <input type="hidden" id="marknames_<s:property value='#item.id' />" value="<s:property value='#item.marknames' />"/>
                            <input type="hidden" id="markids_<s:property value='#item.id' />" value="<s:property value='#item.markids' />"/>
                            <input type="hidden" id="rolenames_<s:property value='#item.id' />" value="<s:property value='#item.rolenames' />"/>
                            <input type="hidden" id="roleids_<s:property value='#item.id' />" value="<s:property value='#item.roleids' />"/>
                            	<s:if test="#item.id!='' and  #item.id!=null">
                            	<tr>
                            		<td><img src="../<s:property value="#item.headimage"/>" width="40" height="40"/></td>
                                    <td><s:property value="#item.realname"/></td>
                                    <td><s:property value="#item.username"/></td>
                                    <td><s:property value="#item.position"/></td>
                                    <td><s:property value="#item.phone"/></td>
                                    <td><s:property value="#item.email"/></td>   
                                    <td><s:property value="#item.departname"/></td>   
                                    <td>
									<a href="#save_edit" onclick="EditInfo('<s:property value='#item.id'/>')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>编辑</a>
									<a href="#deleteTip" onclick="deleteData('<s:property value='#item.id' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>删除</a>
									<a href="#mark_edit" onclick="markData('<s:property value='#item.id' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>标签设置</a>
								
									<s:if test="#request.pvMap.roleid == 1">
									 <a href="#role_edit" onclick="roleData('<s:property value='#item.id' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>角色设置</a>
									</s:if>
									<s:elseif test="#request.pvMap.roleid == 2">
									<a href="#role_edit" onclick="roleData('<s:property value='#item.id' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>角色设置</a>
									</s:elseif>
									<s:else></s:else>
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
	$('#position',$('#saveEditForm')).val($('#position_'+id).val());
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
	$('#headimage',$('#saveEditForm')).val($('#headimage_'+id).val());
	var defaultimg ="<%=request.getContextPath()%>/basic/upload/nopic.jpg";
	if(!hhutil.isEmpty($('#headimage_'+id).val())){
		defaultimg = "<%=request.getContextPath()%>"+$('#headimage_'+id).val() ;
	}
	$('#showMyImg',$('#saveEditForm')).attr("src",defaultimg);
	
	$('#id',$('#saveEditForm')).val(id);
}
function initAdd(){
	$('#userpass').show();
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
	/*
	var selectedCheckbox = $("input[name='roleidlist']:checkbox:checked");
	if(selectedCheckbox.length > 1){
       alert("一个用户只能设置一个角色！！");
       return false;
     }else{
    		var roleid= $("#log_roleid").val();
    		if(parseInt(roleid) == 2){
    	       if($(selectedCheckbox).val() == 1){
    	    	   alert("您的权限不够，不能设置管理员权限！！");
    	    	   return false;
           	    }
    	       
    		}
     }*/
  
   $("#roleEditForm").submit();
}
</script>
<a href="#error" id="statea" data-toggle="modal"  style="display: none"><span>提示</span></a>
<input type="hidden" id="state" value="<s:property value="#request.state"/>"/>
<div id="error" class="modal fade tips-modal tips-modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
	<s:form theme="simple" method="post" >
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
	<s:form action="Basic!deleteEmployee.action" id="deleteForm" name="deleteForm"
			 theme="simple" method="post"
			namespace="/backer" >
	 <s:hidden  id="id" name="paramMap.id" ></s:hidden>
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
            <h3 class="modal-title">员工信息编辑</h3>
        </div>
        <s:form id="saveEditForm" class="form-horizontal parsley-form" method="post" namespace="/backer" role="form"  action="Basic!editEmployee.action"  data-validate="parsley">
        <s:hidden  id="id" name="paramMap.id" ></s:hidden>
        
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
            <div class="form-group" style="margin-top:20px;padding-top:20px;" id="userpass">
                <label class="col-md-3 control-label"><i class="required">*</i>帐号密码</label>
                <div class="col-md-7">
                  <input id="password" name="paramMap.password" type="text" class="form-control parsley-validated">
                </div>
            </div>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
                <label class="col-md-3 control-label">性别</label>
                <div class="col-md-7">
                  <select id="sex" name="paramMap.sex" class="form-control select2-input select2-offscreen" style="width:150px;" >
							<option value="1" >男</option>
							<option value="2" >女</option>
                     </select> 
                     </div>
            </div>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
                <label class="col-md-3 control-label">职位</label>
                <div class="col-md-7">
                  <input id="position" name="paramMap.position" type="text" class="form-control parsley-validated">
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
                  <input id="wxnum" name="paramMap.wxnum" type="text" class="form-control parsley-validated">
                </div>
            </div>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
                <label class="col-md-3 control-label">邮箱</label>
                <div class="col-md-7">
                  <input id="email" name="paramMap.email" type="text" class="form-control parsley-validated" data-type="email">
                </div>
            </div>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
                <label class="col-md-3 control-label">部门</label>
                <div class="col-md-7">
                		 <select id="departid" name="paramMap.departid" class="form-control select2-input select2-offscreen" style="width:150px;">
                                    	<option value="" selected="selected">全部</option>
										<s:iterator value="#request.departList" var="item">
								       		<option value="<s:property value='#item.departid' />"><s:property value='#item.departname' /></option>
								       </s:iterator>
                                    </select>
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
<!--标签设置 -->
<div id="mark_edit" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">标签设置</h3>
        </div>
        <s:form id="markEditForm" class="form-horizontal parsley-form" method="post" namespace="/backer" role="form"  action="Basic!editMarkEmployee.action"  data-validate="parsley" >
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
<div id="role_edit" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">角色设置</h3>
        </div>
        <s:form id="roleEditForm" class="form-horizontal parsley-form" method="post" namespace="/backer" role="form"  action="Basic!editRoleEmployee.action"  data-validate="parsley" >
        <input type="hidden" id="id" name="paramMap.id"/>
        <input type="hidden" id="roleids" name="paramMap.roleids"/>
          <div class="modal-body">
                <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>用户当前的角色</label>
                <div class="col-md-7" id="rolenames">
                </div>
            </div>
            <div class="form-group" style="margin-top:20px;padding-top:20px;">
                <label class="col-md-3 control-label"><i class="required">*</i>所有角色</label>
                <div class="col-md-7">
                	<input type="hidden" name="paramMap.roleid" id="roleids1"/>
                	 <s:iterator value="#request.rolelist" var="role">
	                	<label title="${role.rolename}" class="icheck-inline">
	                        <input id="role_${role.roleid}" type="checkbox" name="roleidlist" class="icheck-input" value="${role.roleid}">${role.rolename}
	                    </label>
                		</s:iterator>
                  </div>
             </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
            <button type="button" onclick="submitRole()" class="btn btn-save"><i class="ico-ok"></i>保 存</button>
        </div>
        </s:form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<a href="#deletedepart"><span></span></a>
<div id="deletedepart" class="modal fade tips-modal tips-modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
	<s:form action="Basic!editDepart.action" id="deletedepartForm" name="deleteForm"
			 theme="simple" method="post"
			namespace="/backer" >
	 <s:hidden  id="deletedepartId" name="paramMap.departId" ></s:hidden>
	 <input type="hidden" name="paramMap.op" value="2"/>
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title">提示</h3>
      </div>
      <div class="modal-body">
        <i class="ico-tips-warning"></i>
        <h4>确定删除？</h4>
        <p class="tips-modal-sub">部门删除之后无法恢复</p>
      </div>
		
      <div class="modal-footer">
        <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
        <button type="submit" class="btn btn-save"><i class="ico-ok"></i>确 定</button>
      </div>
      </s:form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<s:form action="Basic!uploadEmployeeImg.action" id="imgUploadForm" name="imgUploadForm"
	enctype="multipart/form-data" theme="simple" method="post"
	namespace="/backer"  target="imgFrame">
<s:file style="width:0px;height:0px;"  name="imgurl" id="imgurl" onchange="document.getElementById('imgUploadForm').submit();"  ></s:file>
</s:form>
<iframe id="imgFrame"  name="imgFrame" style="display:none;width:0px;height:0px;"></iframe>
</body>
</html>