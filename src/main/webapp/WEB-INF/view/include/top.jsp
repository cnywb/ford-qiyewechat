<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="com.ford.qiye.model.DtUser" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="header">
	<h1 class="site-logo">
		<span>微信后台</span>
	</h1>
	<ul class="nav navbar-nav navbar-right">
		<li>
			<a href="javascript:void(0);"><shiro:principal property="userName"/><i class="ico-user"></i>

			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/main/password.do"><i class="ico-psd"></i>修改密码</a>
		</li>
		<li>
			<a href="#about" data-toggle="modal" data-backdrop="static"><i
				class="ico-about"></i>关于</a>
		</li>
		<li>
			<a href='${pageContext.request.contextPath}/logout.do' 	target=_top><i class="ico-exit"></i>退出</a>
		</li>
	</ul>
	<%
		//Object obj=SecurityUtils.getSubject ().getPrincipal ();

     	//DtUser  user=(DtUser)obj;
		//if(user!=null&&user.getExpiredDay ()<15){
	%>
	<script type="text/javascript">
		$(function(){
			//密码到期提示
			lion.util.error('提示','您的密码将<shiro:principal property="expiredDay"/>天后过期,请及时修改');
		});
	</script>
 	<%//}%>
</div>