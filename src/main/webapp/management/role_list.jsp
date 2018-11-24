<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<body class="p-inte p-inte-product">
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
	<div class="wrapper">
        <jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>

		<div class="content">
			<div class="crumb">
				<h5>
					业务功能 » 权限分配
				</h5>
			</div>

			<div class="content-container">
				<div class="row">
					<div class="col-md-12" role="main" id="main">
						<h3 class="current-tit">
							<span>角色列表</span>
						</h3>
						<form id="saveForm" class="form-horizontal parsley-form"
								method="post" name="saveForm" action="<%=request.getContextPath() %>/backer/system!editRoleFunction.action">
								<input type="hidden" name="pvMap.funids" value="">
						<div class="row area-list-dec">
                                <div class="area-search">
                                       
                                       <div class="form-select">
	                                       <select id="roleid1" name="pvMap.roleid" class="form-control"  placeholder="角色" >
	                                          <c:forEach value="#request.dataGridList" status="st" id="item">
	                                             <option statu="<s:property value="#item.STATUS"/>" <s:if test="#item.ROLEID == #request.pvMap.roleid">selected='selected'</s:if> value="<s:property value="#item.ROLEID"/>"><s:property value="#item.ROLENAME"/></option>
	                                          </c:forEach>
						                	</select>
										</div>
										<button data-toggle="modal" data-backdrop="static"
											href="#save_update" onclick="initAdd()"
											style="margin-left: 30px;" type="button"
											class="btn btn-default">
											<i class="ico-edit"></i>修改角色名称
										</button>
                                    <%--<a href="#save_edit" data-toggle="modal" onclick="initAdd();" class="btn btn-default" data-backdrop="static" style="margin-left:30px;"><i class="ico-add"></i>添加</a>
                                --%></div>
                            </div>
                                <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table-highlight table-checkable">
                                    <tbody>
                                       <s:iterator value="#request.dataList" status="st" id="item">
	                                        <tr>
	                                        <s:if test="#item.PARENTID == 0">
	                                        	<td style="width: 100px;"><label title="<s:property value="#item.name"/>" class="icheck-inline">
							                        <input name="ids" type="checkbox" 
							                        	class="icheck-input fundidmain" status="0"
							                        	value="<s:property value="#item.FUNID"/>" />
							                       <s:property value="#item.NAME"/>
							                    </label></td>
	                                    	    <td>
	                                        		<s:iterator value="#request.dataList" status="sts" id="child">
	                                        		 <s:if test="#child.PARENTID == #item.FUNID">
			                                        		<label title="<s:property value="#child.NAME"/>" class="icheck-inline">
									                        <input name="ids" type="checkbox" 
									                        	class="icheck-input fundidchild" 
									                        	value="<s:property value="#child.FUNID"/>" />
									                        	<s:property value="#child.NAME"/>
									                   		 </label>
			                                        	</s:if>
			                                        	
	                                        		</s:iterator>
	                                            </td>
	                                          
	                                          </s:if>
	                                        </tr>
                                        </s:iterator>
                                    </tbody>
                                </table>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table-highlight table-checkable">
                                    <tbody>
                                      
                                    </tbody>
                                </table>
                            </div>
					</div>
				</div>
				<div class="modal-footer">
			            <button type="submit" id="btn-save" onclick="document.getElementById('saveForm').submit();" class="btn btn-default"><i class="ico-ok"></i>保 存</button>
			        </div>
			      	</form>
			</div>           
                    
		</div>
	</div>
	<script type="text/javascript">
	$(function() {
		$('.user-tooltip').hover(function() {
			$(this).find('.user-detail').stop().show();
		}, function() {
			$(this).find('.user-detail').stop().hide();
		});
	});
	
	//点击编辑，初始化form表单
	function initEdit(id){
		$('#name',$('#saveEditForm')).val($('#name_'+id).val());
		$('#roleid',$('#saveEditForm')).val(id);
	}
	function initAdd(){
        var roleid = $('#roleid1').val();
        var rolename = $('#roleid1').find("option[value="+roleid+"]").text();
        var status = $('#roleid1').find("option[value="+roleid+"]").attr("statu");
        $("#save_update").find("input[type=radio]").each(function(){
             if($(this).val() == status){
                 $(this).iCheck('check');
              }
          });
        $('#roleid',$('#save_update')).val(roleid);
        $('#name',$('#save_update')).val(rolename);
        
        
		$('#name',$('#saveEditForm')).val("");
		//默认选择是
		$('#roleid',$('#saveEditForm')).val("");
	}
	//授权
	function showAccredit(id){
		$('#authUser').text($('#name_'+id).val());
		$('#roleid',$('#authRolesForm')).val(id);
		var funids =  $('#funids_'+id).val();
		
		if(funids!=null || funids!=""){
			$("#roleids",$('#authRolesForm')).val(funids);
			var funidArray = funids.split(",");
			$("input[name='funid']:checkbox").each(function(){
				var flag = false ;
				for(var i=0,len=funidArray.length;i<len;i++){
					if($(this).val()==funidArray[i]){
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
	}
	function getRootPath(){
	    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	    var curWwwPath=window.document.location.href;
	    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	    var pathName=window.document.location.pathname;
	    var pos=curWwwPath.indexOf(pathName);
	    //获取主机地址，如： http://localhost:8080
	    var localhostPath=curWwwPath.substring(0,pos);
	    //获取带"/"的项目名，如：/nnjssd
//	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	    return localhostPath;
//	    return(localhostPath+projectName);
	}
    $(function(){
        //选中父节点中，全选子节点
		$('.fundidmain').on('ifChecked', function(event){
			var status = $(this).attr("status");
			console.log("status="+status);
			//status=1,从子菜单点击进来的不需要全选
			if(!hhutil.isEmpty(status) && status!='1'){
				$(this).parent().parent().parent().find(".icheck-input").each(function(){
			  		$(this).iCheck('check');
			  		$(this).attr("checked");
			  		$(this).attr("status","0");
				 });	
			}
		});
		$('.fundidmain').on('ifUnchecked', function(event){
			$(this).parent().parent().parent().find(".icheck-input").each(function(){
		  		$(this).iCheck('uncheck');
		  		$(this).removeAttr("checked");
		  		$(this).attr("status","0");
			 });
		});

		 //选中子节点,父节点也选中
		$('.fundidchild').on('ifChecked', function(event){
			$(this).parent().parent().parent().find(".fundidmain").attr("status","1");
		  	$(this).parent().parent().parent().find(".fundidmain").each(function(){
		  		$(this).iCheck('check');
		  		$(this).attr("checked");
			 });
		});
		$('.fundidchild').on('ifUnchecked', function(event){
			//查询子节点选中的个数，如果大于0，主节点则选中；否则主节点不选中
			var selectnum=0;
			$(this).parent().parent().parent().find(".fundidchild").each(function(){
				if($(this).is(":checked")){
					//当前是选中的状态
					selectnum++;
					console.log($(this).val());
				}
			});
			
			 console.log("当前数量="+selectnum);
			 if(selectnum>=1){
				 //主节点选中
			 	$(this).parent().parent().parent().find(".fundidmain").attr("status","1");
			  	$(this).parent().parent().parent().find(".fundidmain").each(function(){
			  		$(this).iCheck('check');
			  		$(this).attr("checked");
				 });
			 }else{
				 //主节点取消选中
				 $(this).parent().parent().parent().find(".fundidmain").attr("status","0");
			  	 $(this).parent().parent().parent().find(".fundidmain").each(function(){
			  		$(this).iCheck('uncheck');
			  		$(this).removeAttr("checked");
				 });
			 }
	    });
		
        $('#roleid1').change(function(){
            var roleid = $(this).val();

            $(".fundidmain").each(function(){
            	$(this).iCheck('uncheck');
        	});
          
			//查询当前角色对应的功能
			$.ajax({url:"<%=request.getContextPath()%>/backer/system!findFactFunByRole.action",data:{roleid:+$("#roleid1").val()},type:"post",success:function(data){
			    var dataList=JSON.parse(data);
			    dataList=dataList.data;
        		console.log(dataList);
				//遍历，选中相应的功能
        		$(".icheck-input").each(function(){
					var func1 = $(this).val();
					for(var i=0,len=dataList.length;i<len;i++){
						var func2 = dataList[i];
						if(func1==func2){
							//避免触动主节点的全选事件。
							//设置是从子节点点击进来的。
							$(this).attr("status","1");
							$(this).iCheck('check');
							break;
						}
					}
            	});
			}});
        });
        var roleid = $('#roleid1').val();
        
        if(roleid>0){
        	$('#roleid1').trigger("change");
        }
    });
</script>



<!-- 添加 -->
<div id="save_edit" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">添加角色</h3>
        </div>
        <form id="saveEditForm" class="form-horizontal parsley-form" method="post"  action="<%=request.getContextPath() %>/backer/system!saveRole.action"  data-validate="parsley">
        <input type="hidden" name="roleid"  id="roleid"/>
        <div class="modal-body">
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>角色名称</label>
                <div class="col-md-7">
                  <input id="name" name="paramCondition.name" type="text" class="form-control parsley-validated" data-required="true">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>角色是否启用</label>
            		<div class="col-md-7">
							<label title="" class="icheck-inline">
							<input type="radio" name="paramCondition.status" value="1" class="icheck-input fundidchild" checked="checked">启用 
							<input type="radio" name="paramCondition.status" value="0" class="icheck-input fundidchild" >禁用
							</label>
					</div>
			  </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
            <button class="btn btn-save"><i class="ico-ok"></i>保 存</button>
        </div>
        </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- 添加 -->
<div id="save_update" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">编辑角色</h3>
        </div>
        <form id="saveupdateForm" class="form-horizontal parsley-form" method="post"  action="<%=request.getContextPath() %>/backer/system!saveRole.action"  data-validate="parsley">
        <input type="hidden" name="paramCondition.roleid"  id="roleid"/>
        <div class="modal-body">
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>角色名称</label>
                <div class="col-md-7">
                  <input id="name" name="paramCondition.name" type="text" class="form-control parsley-validated" data-required="true">
                </div> 
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>角色是否启用</label>
            		<div class="col-md-7">
							<label title="" class="icheck-inline">
							<input type="radio" id="sta" name="paramCondition.status" value="1" class="icheck-input fundidchild" >启用 
							<input type="radio" id="sta" name="paramCondition.status" value="0" class="icheck-input fundidchild" >禁用
							</label>
					</div>
			  </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
            <button class="btn btn-save"><i class="ico-ok"></i>保 存</button>
        </div>
        </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="accredit" class="modal fade accredit">
  <div class="modal-dialog w550">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">授权角色菜单</h3>
        </div>
        <form id="authRolesForm" class="form-horizontal" role="form" 
        action="<%=request.getContextPath()%>/backer/system!editRoleFunction" method="post">
        <input type="hidden" id="roleid" name="pvMap.roleid"/>
        <input type="hidden" id="funids" name="pvMap.funids"/>
        <div class="modal-body">
            <div class="accredit-tip">
                <p>您正在给用户<span id="authUser"></span>授予菜单权限</p>
            </div>
            <div class="form-group">
                <div class="col-md-12 ui-icheck-label"> 
                  <s:iterator value="#request.dataList" status="st" id="item">
                    <label title=" <s:property value="#item.name"/>" class="icheck-inline">
                        <input type="checkbox" name="ids" class="icheck-input" value=" <s:property value="#item.funid"/>"> <s:property value="#item.name"/>
                    </label>
                </s:iterator>
                </div>
            </div>
            <div class="ui-msg-alert">
                <p><i class="ico-warning"></i>至少选择一种菜单</p>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
            <button type="submit" class="btn btn-save"><i class="ico-ok"></i>保 存</button>
        </div>
        </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>