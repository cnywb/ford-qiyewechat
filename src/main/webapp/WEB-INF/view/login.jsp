<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <html class="ie ie7"> <![endif]-->
<!--[if IE 8 ]> <html class="ie ie8"> <![endif]-->
<!--[if IE 9 ]> <html class="ie ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="zh-cn">
	<!--<![endif]-->
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>用户登录入口|长安福特汽车微信企业号管理平台</title>
		<link rel="icon" href="${pageContext.request.contextPath}/images/logo.png" type="image/x-icon"/>
		<link href="${pageContext.request.contextPath}/css/login.css"
			rel="stylesheet" />
	</head>
	<body class="p_login">

		<div class="login_layout">
			<div class="login_decbg"></div>
			<div class="login_content">
				<div class="login_cont">
					<form:form theme="simple" action="${pageContext.request.contextPath}/login.do" method="post"  commandName="loginVo" modelAttribute="loginVo">
						<h3>
							<span>微信后台</span>
						</h3>
						<p>
							<label for="userName">
								用户名
							</label>
							<form:input path="userName" id="userName" size="21" value=""/>

						</p>
						<p>
							<label for="password">
								密&nbsp;&nbsp;&nbsp;码
							</label>
							<form:password path="password" size="21" value="" id="password"/>
						</p>
						<div class="login_tips login_alert">
							<p>
								<form:errors path="*" cssStyle="color:red">
							</form:errors>
							</p>
						</div>
						<p class="login_sub">
							<input name="Submit" type="submit" class="login_ipt_sub"
								id="Submit" value="登 陆">
							<input name="cs" type="button" class="login_ipt_sub" id="cs"
								value="取 消">

						</p>
					</form:form>
				</div>
			</div>
			<div class="login_foot">
				<div>
					<p class="floatl">
						Copyright &copy; 2008-2013 All Rights Reserved. 长安福特 版权所有
					</p>
					<p class="floatr">
						长安福特汽车有限公司| 客服：021-61316987
					</p>
				</div>
			</div>
		</div>
	</body>
</html>


