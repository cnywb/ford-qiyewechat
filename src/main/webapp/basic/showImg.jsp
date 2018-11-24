<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String imgPath = null!=request.getAttribute("imgPath") ? request.getAttribute("imgPath").toString():"" ;
%>
<script type="text/javascript">
parent.uploadSuccess('<%=imgPath %>');
</script>