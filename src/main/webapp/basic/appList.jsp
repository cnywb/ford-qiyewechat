<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-activity p-activity-dzp">
	<div class="wrapper">
		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>

	<div class="content">
        <div class="crumb">
            <h5>基础功能 &#187; 应用管理</h5>
        </div>
        <div class="content-container">
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>应用管理</span></h3>
                    <div class="row area-list-dec">     
                    	 <div class="area-search">
                            <s:form class="form-inline" role="form" namespace="/backer"
								id="searchForm" 	action="Basic!getAppList.action">
                                <div class="form-input">
                                 	<input class="form-control"  id="appname"   name="pvMap.appname" placeholder="应用名称" type="text"  value="<s:property value='#request.pvMap.appname' />">
                                </div>
                                <div class="form-input" style="margin-left: 20px">
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
                                    <th>缩略图</th>
                                    <th>应用名称</th>   
                                    <th>应用地址</th>
                                    <th>所属部门</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#request.dataList" status="st" id="item">
                            <input type="hidden" id="appname_<s:property value='#item.appid' />" value="<s:property value='#item.appname' />"/>  
                            <input type="hidden" id="appimg_<s:property value='#item.appid' />" value="<s:property value='#item.appimg' />"/>       
                            <input type="hidden" id="linkurl_<s:property value='#item.appid' />" value="<s:property value='#item.linkurl' />"/>    
                            <input type="hidden" id="departid_<s:property value='#item.appid' />" value="<s:property value='#item.departid' />"/>                    
                            	<tr>
                                    <td><s:property value="#item.appid"/></td>
                                    <td><IMG alt="" src="<%=request.getContextPath() %><s:property value="#item.appimg"/>" style="width:50px;height:50px;"/></td>
                                    <td><s:property value="#item.appname"/></td>
                                    <td><s:property value="#item.linkurl"/></td>
                                    <td><s:property value="#item.departname"/></td>
                                    <td>
									<a href="#save_edit" onclick="EditInfo('<s:property value='#item.appid'/>')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>编辑</a>
									<a href="#deleteTip" onclick="deleteData('<s:property value='#item.appid' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>删除</a>
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
function deleteData(id){
	$('#appid',$('#deleteForm')).val(id);
}
//点击编辑，初始化form表单
function EditInfo(id){
	var defaultimg ="<%=request.getContextPath()%>/basic/upload/nopic.jpg";
	if(!hhutil.isEmpty($('#appimg_'+id).val())){
		defaultimg = "<%=request.getContextPath()%>"+$('#appimg_'+id).val() ;
	}
	$('#showMyImg',$('#saveEditForm')).attr("src",defaultimg);
	$('#appname',$('#saveEditForm')).val($('#appname_'+id).val());
	$('#linkurl',$('#saveEditForm')).val($('#linkurl_'+id).val());
	$('#departid',$('#saveEditForm')).select2("val", $('#departid_'+id).val());
	$('#appid',$('#saveEditForm')).val(id);
}
function initAdd(){
	var defaultimg ="<%=request.getContextPath()%>/basic/upload/nopic.jpg";
	$('#showMyImg',$('#saveEditForm')).attr("src",defaultimg);
	$('#appname',$('#saveEditForm')).val("");
	$('#linkurl',$('#saveEditForm')).val("");
	$('#departid',$('#saveEditForm')).select2("");
	$('#appid',$('#saveEditForm')).val("");
}
function uploadSuccess(path){
	var obj = document.getElementById('imgurl') ;
	obj.outerHTML=obj.outerHTML;
	$('#showMyImg',$('#saveEditForm')).attr("src","<%=request.getContextPath()%>"+path);
	$('#appimg',$('#saveEditForm')).val(path);
}
$(function(){
	$('#showMyImg',$('#saveEditForm')).click(function(){
		$('#imgurl').trigger("click");
	});
});
</script>
<div id="deleteTip" class="modal fade tips-modal tips-modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
	<s:form action="Basic!deleteApp.action" id="deleteForm" name="deleteForm"
			 theme="simple" method="post"
			namespace="/backer" >
       <input type="hidden" id="appid" name="paramMap.appid" />
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title">提示</h3>
      </div>
      <div class="modal-body">
        <i class="ico-tips-warning"></i>
        <h4>确定删除？</h4>
        <p class="tips-modal-sub">应用删除之后无法恢复</p>
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
            <h3 class="modal-title">应用编辑</h3>
        </div>
        <s:form id="saveEditForm" class="form-horizontal parsley-form" method="post" namespace="/backer" role="form"  action="Basic!editApp.action"  data-validate="parsley">
        <input type="hidden" id="appid" name="paramMap.appid" />
        <div class="modal-body">
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>应用名称</label>
                <div class="col-md-7">
                  <input id="appname"name="paramMap.appname" type="text" class="form-control parsley-validated" data-required="true">
                </div>
               </div>
               <br/><br/>
               <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>应用图标</label>
	                <div class="col-md-7">
	               	 	 <input type="hidden" id="appimg" name="paramMap.appimg">
	                	 <img id="showMyImg" alt="" src="<%=request.getContextPath() %>/" class="form-control" class="form-control" style="height:280px">
			             <font color="red">分辨率：300 * 400</font>
	                </div>
               </div>
               <br/><br/>
                 <div class="form-group" >
                <label class="col-md-3 control-label"><i class="required">*</i>应用地址</label>
                <div class="col-md-7">
                  <input id="linkurl"name="paramMap.linkurl" type="text" class="form-control parsley-validated" data-required="true">
                </div>
               </div>
               <div class="form-group" style="margin-top:290px">
                <label class="col-md-3 control-label"><i class="required">*</i>部门</label>
                <div class="col-md-7">
                  	<select id="departid" name="paramMap.departid" class="form-control select2-input select2-offscreen" style="width:150px;"  data-required="true" >
                                    	<option value="" selected="selected">全部</option>
										<s:iterator value="#request.departList" var="item">
								       		<option value="<s:property value='#item.departid' />"><s:property value='#item.departname' /></option>
								       </s:iterator>
                    </select>
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
<s:form action="Basic!uploadEmployeeImg.action" id="imgUploadForm" name="imgUploadForm"
	enctype="multipart/form-data" theme="simple" method="post"
	namespace="/backer"  target="imgFrame">
<s:file style="width:0px;height:0px;"  name="imgurl" id="imgurl" onchange="document.getElementById('imgUploadForm').submit();"  ></s:file>
</s:form>
<iframe id="imgFrame"  name="imgFrame" style="display:none;width:0px;height:0px;"></iframe>
</body>
</html>