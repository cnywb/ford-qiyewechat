<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	window.location.href="<%=request.getContextPath()%>/backer/system!getMaterByUserid.action?paramMap.userid="+'${userid}';
}); 
</script>
</html>