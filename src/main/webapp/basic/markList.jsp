<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="xq" uri="http://www.xiqing.info/tags"%>
<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-activity p-activity-ggk">
	<div class="wrapper">
		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>

	<div class="content">
        <div class="crumb">
            <h5>基础功能 &#187; 标签管理</h5>
        </div>
        <div class="content-container">
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>标签管理</span></h3>
                    <div class="row area-list-dec">     
                    	 <div class="area-search">
                            <s:form class="form-inline" role="form" namespace="/backer"
								id="searchForm" 	action="Basic!getMarkList.action">
                                <div class="form-input">
                                 	<input class="form-control"  id="markname"   name="pvMap.markname" placeholder="标签名称" type="text"  value="<s:property value='#request.pvMap.markname' />">
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
                                    <th>标签</th>   
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#request.dataList" status="st" id="item">
                            <input type="hidden" id="markname_<s:property value='#item.markid' />" value="<s:property value='#item.markname' />"/>                        
                            	<tr>
                                    <td><s:property value="#item.markid"/></td>
                                    <td><s:property value="#item.markname"/></td>
                                    <td>
									<a href="#save_edit" onclick="EditInfo('<s:property value='#item.markid'/>')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>编辑</a>
									<a href="#deleteTip" onclick="deleteData('<s:property value='#item.markid' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>删除</a>
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
	$('#markid',$('#deleteForm')).val(id);
}
//点击编辑，初始化form表单
function EditInfo(id){
	$('#markname',$('#saveEditForm')).val($('#markname_'+id).val());
	$('#markid',$('#saveEditForm')).val(id);
}
function initAdd(){
	$('#markname',$('#saveEditForm')).val("");
	$('#markid',$('#saveEditForm')).val("");
}
</script>
<div id="deleteTip" class="modal fade tips-modal tips-modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
	<s:form action="Basic!deleteMark.action" id="deleteForm" name="deleteForm"
			 theme="simple" method="post"
			namespace="/backer" >
	 <s:hidden  id="markid" name="paramMap.markid" ></s:hidden>
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title">提示</h3>
      </div>
      <div class="modal-body">
        <i class="ico-tips-warning"></i>
        <h4>确定删除？</h4>
        <p class="tips-modal-sub">标签删除之后无法恢复</p>
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
            <h3 class="modal-title">标签编辑</h3>
        </div>
        <s:form id="saveEditForm" class="form-horizontal parsley-form" method="post" namespace="/backer" role="form"  action="Basic!editMark.action"  data-validate="parsley">
        <s:hidden  id="markid" name="paramMap.markid" ></s:hidden>
        <div class="modal-body">
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>标签</label>
                <div class="col-md-7">
                  <input id="markname"name="paramMap.markname" type="text" class="form-control parsley-validated" data-required="true">
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


</body>
</html>