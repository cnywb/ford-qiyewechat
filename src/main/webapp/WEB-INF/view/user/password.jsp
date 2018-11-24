<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <html class="ie ie7"> <![endif]-->
<!--[if IE 8 ]> <html class="ie ie8"> <![endif]-->
<!--[if IE 9 ]> <html class="ie ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="zh-cn">
<!--<![endif]-->
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body>
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
                        <form class="form-horizontal parsley-form" role="form" method="post" action="${pageContext.request.contextPath}/main/password.do"
                              data-validate="parsley">
                            <input type="hidden" name="id" value="${user.id}">
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                        &nbsp;
                                </label>
                                <label class="col-md-10 control-label">
                                    <span class="required" style="color:red"> <s:errors path="password"></s:errors></span>
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    用户名称
                                </label>
                                <div class="col-md-5">
                                    <input type="text" name="userName" id="userName"  value="${user.userName}" size="50" readonly="true"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    <i class="required">*</i>新密码
                                </label>
                                <div class="col-md-5">
                                    <input type="text" name="password" id="password" size="50"
                                           onfocus="this.type='password';this.value='';"  required="true"
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
                                    <input type="password" id="repassword" name="confirmPassword"
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
<script type="text/javascript">


    $(function() {
        $('#fm').submit(function() {
            if (!$(this).form('validate')) {
                return false;
            }
            if (!confirm('密码更新,请重新登录!')) {
                return false;
            }
        });

        var login='${login}';
        if(login=='1'){
           var flag= window.confirm("请重新登录");
           if(flag){
               window.location='../logout.do';
           }
        }else if(one.util.isNotEmpty(login)){
            alert("修改密码失败");
        }
    });
</script>
</body>
</html>
