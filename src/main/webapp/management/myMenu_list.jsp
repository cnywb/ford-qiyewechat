<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>

<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-inte p-inte-source">
	<div class="wrapper">
		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>
		<div class="content">
			<div class="crumb">
				<h5>
					业务功能 » 菜单管理
				</h5>
			</div>

			<div class="content-container">
				<div class="row">
					<div class="col-md-12" role="main" id="main">
						<h3 class="current-tit">
							<span>菜单列表</span>
						</h3>
						<div class="row area-list-dec">
							<div class="area-search">
								<form cssClass="form-inline" action="<%=request.getContextPath() %>/backer/system!findAdminMenuList.action" method="post">
									<div class="form-help" >菜单名称：</div>
									<div class="form-date date " style="margin-left:0px;">
										<input class="form-control" type="text"
											name="pvMap.name" placeholder="菜单名称"
											value="<s:property value="#request.pvMap.name"/>">
									</div>
									<div class="form-help" >上级菜单名称：</div>
									<div class="form-date date " style="margin-left:0px;">
										<input class="form-control" type="text"
											name="pvMap.parentName" placeholder="上级名称"
											value="<s:property value="#request.pvMap.parentName"/>">
									</div>
									<div class="form-select" style="margin-left:30px;">
                                    <select name="pvMap.status" class="form-control select2-input select2-offscreen" style="width:90px;" >
                                    		<s:if test="#request.pvMap.status==1">
											<option value="" >全部</option>
					                		<option value="1" selected="selected">启用中</option>
					                		<option value="0">未启用</option>
					                		</s:if>
					                		<s:elseif test="#request.pvMap.status==0">
					                		<option value="" >全部</option>
					                		<option value="1">启用中</option>
					                		<option value="0"  selected="selected">未启用</option>
					                		</s:elseif>
					                		<s:elseif test="#request.pvMap.status==3">
					                		<option value=""  selected="selected">全部</option>
					                		<option value="1">启用中</option>
					                		<option value="0" >未启用</option>
					                		</s:elseif>
					                		<s:else>
					                		<option value="" selected="selected">全部</option>
					                		<option value="1" >启用中</option>
					                		<option value="0" >未启用</option>
					                		</s:else>
                                    </select>
                                </div>
									<div class="form-search-sub">
										<button type="submit" class="btn btn-default"
											style="margin-left: 10px;">
											<i class="ico-search"></i>查 询
										</button>
										<a href="#save_edit" data-toggle="modal" onclick="initAdd();" class="btn btn-default" data-backdrop="static" style="margin-left:30px;"><i class="ico-add"></i>添加</a>
									</div>
								</form>
							</div>
						</div>

						<div class="table-responsive">
							<table
								class="table table-striped table-bordered table-hover table-highlight">
								<thead>
									<tr>
										<th><center>菜单名称</center></th>
										<th><center>菜单路径</center></th>
										<th><center>上级菜单</center></th>
										<th><center>菜单样式</center></th>
										<th><center>菜单状态</center></th>
										<th><center>操作</center></th>
									</tr>
								</thead>
								<tbody>
                                <s:iterator value="#request.funcDGList" status="st" id="item">
										<input type="hidden" id="name_<s:property value="#item.id"/>" value="<s:property value="#item.name"/>"/>
										<input type="hidden" id="parentid_<s:property value="#item.id"/>" value="<s:property value="#item.parentid"/>"/>
										<input type="hidden" id="linkurl_<s:property value="#item.id"/>" value="<s:property value="#item.linkurl"/>"/>
										<input type="hidden" id="status_<s:property value="#item.id"/>" value="<s:property value="#item.status"/>"/>
										<input type="hidden" id="remark_<s:property value="#item.id"/>" value="<s:property value="#item.remark"/>"/>
										<tr>
											<td align="center"><s:property value="#item.name"/></td>
											<td align="center" ><s:property value="#item.linkurl"/></td>
											<td align="center" ><s:property value="#item.parentName"/></td>
											<td align="center" ><s:property value="#item.remark"/></td>
											<td align="center" >
											 <s:if test="#item.status == 0">
											   未启用
											 </s:if>
											 <s:else>
											  启用中
											 </s:else>
											</td>
											<td align="center">
												<a href="#save_edit" onclick="initEdit(<s:property value="#item.id"/>);" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>编辑</a>
												
												<%--<a href="#deleteTip" onclick="initData(<s:property value="#item.id"/>,-1);" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>删除</a>
												
												--%><s:if test="#item.status == 0 ">
														<a href="#deleteTip" onclick="initData(<s:property value="#item.id"/>,1);" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>启用</a>
												</s:if>
												<s:else>
														<a href="#deleteTip" onclick="initData(<s:property value="#item.id"/>,0);" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>停用</a>
											    </s:else>
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
		$('.user-tooltip').hover(function() {
			$(this).find('.user-detail').stop().show();
		}, function() {
			$(this).find('.user-detail').stop().hide();
		});
	});
	function initData(id,status){
		if(status==-1){
			$('#deleteForm').find('.modal-body').find('h4').html('确定删除该菜单吗?');
			$('#deleteForm').find('.modal-body').find('p').html('菜单删除之后无法恢复');
		}else if(status==0){
			$('#deleteForm').find('.modal-body').find('h4').html('确定停用该菜单吗?');
			$('#deleteForm').find('.modal-body').find('p').html('菜单停用后无法显示');
		}else{
			$('#deleteForm').find('.modal-body').find('h4').html('确定启用该菜单吗?');
			$('#deleteForm').find('.modal-body').find('p').html('菜单启用后即可显示');		
		}
		$('#status',$('#deleteForm')).val(status);
		$('#funid',$('#deleteForm')).val(id);
	}
	//点击编辑，初始化form表单
	function initEdit(id){
		$('#funid',$('#saveEditForm')).val(id);
		$('#name',$('#saveEditForm')).val($('#name_'+id).val());
		$('#parentid option',$('#saveEditForm')).each(function(){
			if($(this).val()==id){
				
				//alert($('#parentid option').attr('value'));
				//$(this).attr('value');
			}
		});
		$('#parentid',$('#saveEditForm')).select2("val", $('#parentid_'+id).val());
		$('#linkurl',$('#saveEditForm')).val($('#linkurl_'+id).val());
		$('#status',$('#saveEditForm')).val($('#status_'+id).val());
		$('#remark',$('#saveEditForm')).val($('#remark_'+id).val());
	}
	function initAdd(){
		$('#funid',$('#saveEditForm')).val("");
		$('#name',$('#saveEditForm')).val("");
		$('#parentid',$('#saveEditForm')).select2("val", "0");
		$('#linkurl',$('#saveEditForm')).val("");
		$('#status',$('#saveEditForm')).val("1");
		$('#remark',$('#saveEditForm')).val("");
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
</script>

<div id="deleteTip" class="modal fade tips-modal tips-modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
	<form action="<%=request.getContextPath() %>/backer/system!saveMenu.action" id="deleteForm" name="deleteForm"
			 method="post" >
	<input type="hidden" id="funid" name="paramCondition.id"  />
	<input type="hidden" id="status" name="paramCondition.status" />
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title">提示</h3>
      </div>
      <div class="modal-body">
        <i class="ico-tips-warning"></i>
        <h4>确定删除？</h4>
        <p class="tips-modal-sub">菜单删除之后无法恢复</p>
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
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">编辑菜单列表</h3>
        </div>
        <form id="saveEditForm" class="form-horizontal parsley-form" method="post"  action="<%=request.getContextPath() %>/backer/system!saveMenu.action"  data-validate="parsley">
        <input type="hidden" name="paramCondition.id"  id="funid"/>
        <input type="hidden" name="paramCondition.status"  id="status"/>
        <div class="modal-body">
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>菜单名称</label>
                <div class="col-md-7">
                  <input id="name" name="paramCondition.name" type="text" class="form-control parsley-validated" data-required="true">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>菜单路径</label>
                <div class="col-md-7">
                  <input id="linkurl" name="paramCondition.linkurl" type="text" class="form-control parsley-validated" >
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>上级菜单</label>
                <div class="col-md-7">
                    <select class="form-control select2-input select2-offscreen" name="paramCondition.parentid" id="parentid">
                    	<option value="0">不设置</option>
                    	<s:iterator value="#request.funTopList" step="st" id="item">
                    	  <option value="<s:property value="#item.id"/>"><s:property value="#item.name"/> </option>
                    	 </s:iterator>
                    </select>
                   
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>备注</label>
                <div class="col-md-7">
                  <input id="remark" name="paramCondition.remark" type="text" class="form-control parsley-validated">
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



</body>
</html>