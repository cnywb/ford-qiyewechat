<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="sidebar affix">
	<ul class="main-nav open-active">
		<li class="nav-dashboard">
			<a href="<%=request.getContextPath()%>/backer/system!mainjsp.action"><i class="ico-dashboard "></i>首页</a>
		</li>
		<s:iterator value="#session.menuList" var="item">
			<s:if test="#item.PARENTID==0">
				<li class="<s:property value="#item.REMARK"/> dropdown">
	 				<a href="javascript:void(0);"> <i class="ico-gear"></i><s:property value="#item.NAME"/><span class="caret"></span> </a>
	 				<ul class="sub-nav">
	 				<s:iterator value="#session.menuList" var="child">
							<s:if test="#item.ID==#child.PARENTID">
									<li class="<s:property value="#child.REMARK"/>">
										<a href="<%=request.getContextPath()%><s:property value="#child.LINKURL"/>"><s:property value="#child.NAME"/></a>
									</li>
							</s:if>
		 				</s:iterator>
		 			</ul>
	 			 </li>
			</s:if>
		</s:iterator>
	</ul>
</div>
