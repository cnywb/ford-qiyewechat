<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>福特汽车企业号后台</title>
	<link href="<s:url value='/css/bootstrap.min.css' />" rel="stylesheet" />
	<link href="<s:url value='/css/bootstrap-responsive.min.css' />" rel="stylesheet" />
	<link href="<s:url value='/css/main.css' />" rel="stylesheet" />
	<link rel="stylesheet" href="<s:url value='/plugin/ztree/css/zTreeStyle/zTreeStyle.css' />" type="text/css">
<script src="<s:url value='/js/jquery-1.11.2.js' />"></script>
<script src="<s:url value='/js/bootstrap.min.js' />"></script>
<script src="<s:url value='/js/app.js' />"></script>
<script src="<s:url value='/plugin/ztree/js/jquery.ztree.core-3.5.js' />"></script>
<script src="<s:url value='/plugin/ztree/js/jquery.ztree.excheck-3.5.js' />"></script>
<script src="<s:url value='/plugin/ztree/js/jquery.ztree.exedit-3.5.js' />"></script>
<script charset="utf-8" src="<s:url value='/js/management/area_manage.js' />"></script>
  <style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</head>

<body class="p-dashboard">
	<div class="wrapper">
		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>

		<div class="content">
			<div class="crumb">
				<h5>
					区域管理
				</h5>
			</div>
			<div class="content-container">
				<div class="row">
					<div class="col-md-12" role="main" id="main">
						<h3 class="current-tit">
						
						</h3>
						<ul id="treeDemo" class="ztree" ></ul>
						<button type="button" class="btn btn-default" id="btn_add" >添加大区</button>
                    </div>
				</div>
			</div>
		</div>
	</div>
	
<div class="modal fade" role="dialog" id="addModal" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" >新建</h4>
      </div>
      <div class="modal-body">
        <div class="container-fluid">
        <input type="hidden" name="parentId" id="parentId" >
          <div class="row">
             <div class="col-md-12 input-group">
             <span class="input-group-addon" id="sizing-addon1">名称</span>
             <input type="text" name="name" class="form-control" placeholder="名称" aria-describedby="sizing-addon2" >
             </div>
          </div>
   
           <br>
         <div class="row">
             <div class="col-md-12 input-group">
             <span class="input-group-addon" id="sizing-addon1">代码</span>
             <input type="text" name="code" class="form-control" placeholder="代码" aria-describedby="sizing-addon2" >
             </div>
          </div>
           <br>
           <div class="row">
             <div class="col-md-12 input-group">
             <span class="input-group-addon" id="sizing-addon1">备注</span>
             <input type="text" name="remark" class="form-control" placeholder="备注" aria-describedby="sizing-addon2" >
             </div>
          </div> 
           <br>
           <div class="row">
             <div class="col-md-12 input-group">
             <span class="input-group-addon" id="sizing-addon1">排序</span>
             <input type="text" name="priority" class="form-control" placeholder="排序" aria-describedby="sizing-addon2" >
             </div>
          </div>              
         </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="save();">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
	
	
	<div class="modal fade" role="dialog" id="editModal" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" >编辑</h4>
      </div>
      <div class="modal-body">
        <div class="container-fluid">
        <input type="hidden" name="id" >
        <input type="hidden" name="parentId"  >
          <div class="row">
             <div class="col-md-12 input-group">
             <span class="input-group-addon" id="sizing-addon1">名称</span>
             <input type="text" name="name" class="form-control" placeholder="名称" aria-describedby="sizing-addon2" >
             </div>
          </div>
   
           <br>
         <div class="row">
             <div class="col-md-12 input-group">
             <span class="input-group-addon" id="sizing-addon1">代码</span>
             <input type="text" name="code" class="form-control" placeholder="代码" aria-describedby="sizing-addon2" >
             </div>
          </div>
           <br>
           <div class="row">
             <div class="col-md-12 input-group">
             <span class="input-group-addon" id="sizing-addon1">备注</span>
             <input type="text" name="remark" class="form-control" placeholder="备注" aria-describedby="sizing-addon2" >
             </div>
          </div> 
           <br>
           <div class="row">
             <div class="col-md-12 input-group">
             <span class="input-group-addon" id="sizing-addon1">排序</span>
             <input type="text" name="priority" class="form-control" placeholder="排序" aria-describedby="sizing-addon2" >
             </div>
          </div>              
         </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="update();">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
	<div class="modal fade " role="dialog" id="alertModal" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" >警告</h4>
      </div>
      <div class="modal-body">
      <p>
	  </p>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    <jsp:include page="../include/foot.jsp" flush="true"></jsp:include>
</body>
</html>