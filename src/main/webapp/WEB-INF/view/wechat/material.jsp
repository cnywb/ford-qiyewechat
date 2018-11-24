<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	window.location.href="${pageContext.request.contextPath}/we/mymaterial.do?userId=${userid}";
});
</script>
</html>