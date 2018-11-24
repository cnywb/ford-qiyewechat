<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

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
		<title>用户登录 - 微信后台</title>
		<link href="<%=request.getContextPath()%>/images/favicon.ico"
			rel="shortcut icon" type="image/x-icon" />
		<link href="<%=request.getContextPath()%>/css/login.css"
			rel="stylesheet" />
	</head>
	<body class="p_login">

		<div class="login_layout">
			<div class="login_decbg"></div>
			<div class="login_content">
				<div class="login_cont">
					<s:form theme="simple" action="check!checkuser" namespace="/login">
						<h3>
							<span>微信后台</span>
						</h3>
						<p>
							<label for="userName">
								用户名
							</label>


							<s:textfield name="user.username" size="21" value=""></s:textfield>

						</p>
						<p>
							<label for="userPasswd">
								密&nbsp;&nbsp;&nbsp;码
							</label>
							<s:password name="user.password" size="21" value=""></s:password>


						</p>
						<div class="login_tips login_alert">
							<p>
								<s:if test="null!=#request.errorinfo and ''!=#request.errorinfo">
									<i class="ico-warning"></i><s:property value="#request.errorinfo"/>	
								</s:if>
							</p>
						</div>
						<p class="login_sub">
							<input name="Submit" type="submit" class="login_ipt_sub"
								id="Submit" value="登 陆">
							<input name="cs" type="button" class="login_ipt_sub" id="cs"
								value="取 消" onClick=
	showConfirmMsg1();
>

						</p>
					</s:form>
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


