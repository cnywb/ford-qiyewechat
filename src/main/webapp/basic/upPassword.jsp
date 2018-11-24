<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<script type="text/javascript">
	$.extend($.fn.validatebox.defaults.rules, {
		/*必须和某个字段相等*/
		equalTo : {
			validator : function(value, param) {
				return $(param[0]).val() == value;
			},
			message : '字段不匹配'
		}

	});

	$(document).ready(function() {
		$('#fm').submit(function() {
			if (!$(this).form('validate')) {
				$.messager.alert('提示', '请检查输入框中的值！', 'error');
				return false;
			}
			if (!confirm('密码更新,请重新登录!')) {
				return false;
			}

		});
	});
</script>

<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-user p-user-password">
	<div class="wrapper">

		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>
		<div class="content">
			<div class="crumb">
				<h5>
					修改密码
				</h5>
			</div>
			<div class="content-container">
				<div class="row">
					<div class="col-md-12" role="main">
						<h3 class="current-tit">
							<span>修改密码</span>
						</h3>
						<div class="col-md-10">
							<form class="form-horizontal parsley-form" role="form" action="<%=request.getContextPath()%>/backer/system!updatepassword.action"
								data-validate="parsley">
								<input type="hidden" name="pvMap.id" value="${userid}">
								<div class="form-group">
									<label class="col-md-2 control-label">
										用户名称
									</label>
									<div class="col-md-5">
										${username}
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										<i class="required">*</i>新密码
									</label>
									<div class="col-md-5">
										<input type="text" name="pvMap.password" id="password" size="50"
											onfocus="this.type='password';this.value='';"
											missingMessage="请输入密码" invalidMessage="请检查输入的字符长度[3,8]"
											class="easyui-validatebox"
											data-options="required:true,validType:'length[3,20]'"
											value="请输入密码" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										<i class="required">*</i>重复密码
									</label>
									<div class="col-md-5">
										<input type="password" id="repassword"
											required="true" missingMessage="请确认密码" size="50"
											class="easyui-validatebox" validType="equalTo['#password']"
											invalidMessage="两次输入密码不匹配" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-5 col-md-offset-2">
										<button type="submit" class="btn btn-save">
											<i class="ico-ok"></i>保 存
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/foot.jsp" flush="true"></jsp:include>
</body>
</html>