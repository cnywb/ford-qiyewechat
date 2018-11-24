<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="xq" uri="http://www.xiqing.info/tags"%>
<div class="sidebar affix">
	<ul class="main-nav open-active">
		<li class="nav-dashboard">
			<a href="${pageContext.request.contextPath}/main/index.do"><i class="ico-dashboard "></i>首页</a>
		</li>
		 <xq:menu/>
	</ul>
</div>
