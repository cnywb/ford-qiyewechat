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
<title>长安福特服务企业管理平台</title>
<link href="<s:url value='/css/bootstrap.min.css' />" rel="stylesheet" />
<link href="<s:url value='/css/bootstrap-responsive.min.css' />" rel="stylesheet" />
<link href="<s:url value='/css/main.css' />" rel="stylesheet" />
<link rel="stylesheet" href="<s:url value='/plugin/ztree/css/zTreeStyle/zTreeStyle.css' />" type="text/css">
<script src="<s:url value='/js/jquery-1.11.2.js' />"></script>
<script src="<s:url value='/js/bootstrap.min.js' />"></script>
<script src="<s:url value='/js/app.js' />"></script>
<script charset="utf-8" src="<s:url value='/js/management/wechat_welcome_words_edit.js' />"></script>
 
</head>

<body class="p-dashboard">
	<div class="wrapper">
		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>

		<div class="content">
			<div class="crumb">
				<h5>
					关注欢迎词设置
				</h5>
			</div>
			<div class="content-container">
				<div class="row">
					<div class="col-md-12" role="main" id="main">
						<h3 class="current-tit">
						
						</h3>
						 <div class="col-md-12 input-group">
						 <textarea class="form-control" rows="5" id="paramValue" >
						<s:property value="t.paramValue.trim()" />
						</textarea>
                        </div>
						
						
						<button type="button" class="btn btn-default" id="btn_save" >保存</button>
                    </div>
				</div>
			</div>
		</div>
	</div>
	

	
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