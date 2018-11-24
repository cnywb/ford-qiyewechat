<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String imgPath = null!=request.getAttribute("imgPath") ? request.getAttribute("imgPath").toString():"" ;
	System.out.println("showImg2========================="+imgPath+"======================");
%>
<script type="text/javascript">
parent.uploadSuccess2('<%=imgPath %>');
</script>