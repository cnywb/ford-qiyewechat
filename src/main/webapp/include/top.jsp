<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="header">
	<h1 class="site-logo">
		<span>微信后台</span>
	</h1>
	<ul class="nav navbar-nav navbar-right">
		<li>
			<a href="javascript:void(0);"><i class="ico-user"></i><s:property value="#session.username"/></a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/backer/system!initpassword.action"><i
				class="ico-psd"></i>修改密码</a>
		</li>
		<li>
			<a href="#about" data-toggle="modal" data-backdrop="static"><i
				class="ico-about"></i>关于</a>
		</li>
		<li>
			<a href='<%=request.getContextPath()%>/login/check!logOut.action'
				target=_top><i class="ico-exit"></i>退出</a>
		</li>
	</ul>
</div>