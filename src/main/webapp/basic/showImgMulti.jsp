<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String imgPath = null!=request.getAttribute("imgPath") ? request.getAttribute("imgPath").toString():"" ;
	String uploadid = null!=request.getAttribute("uploadid") ? request.getAttribute("uploadid").toString():"" ;
%>
<script type="text/javascript">
parent.uploadMultiSuccess('<%=imgPath %>','<%=uploadid %>');
</script>