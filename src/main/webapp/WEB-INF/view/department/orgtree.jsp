<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/basic/ztree/css/demo.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/basic/ztree/css/zTreeStyle/zTreeStyle.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hhutil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/basic/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/basic/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/basic/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/basic/ztree/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/management/orgtree.js"></script>

	
	<script type="text/javascript">



		</script>
		<style type="text/css">
		.ztree li span.button.add {margin-left:0px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
		</style>
<body>

<input type="hidden" value="" id="departid">

	<form id="departform" action="#" method="post">
	<input style="position:relative;display:inline-block;padding:6px 8px;height:30px;border-radius:3px;-moz-border-radius:3px;-webkit-border-radius:3px;" class="form-control"  id="departname"   name="departName" placeholder="一级部门" type="text" />
	<button type="button" id="addDepart"   class="btn btn-default" style="margin-top:5px;float:right;height:30px;background:#1badf2;color:#fff;border:1px solid #129ada">添加</button>
	</form>

	<div style="width: 110%;border: 0px solid #d3d7d4;border-right:0px; height: 595px;float: left;SCROLLING:no;overflow:hidden">
		<ul id="treeDemo" class="ztree" style="overflow:hidden;SCROLLING:no;border:0px;height: 570px;width:90% ;background: #ffffff;font-family:verdana,'宋体','Microsoft Yahei',Tahoma,Arial;font-size: 15px"></ul>
		<style type="text/css">
			.ztree li{
				border：1px red solid;
				margin-top: 5px;
				font-size: 16px;
			}
		</style>
	</div>
</body>
</html>