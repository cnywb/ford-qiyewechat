<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="xq" uri="http://www.xiqing.info/tags"%>
<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<script language="javascript" src="${pageContext.request.contextPath}/js/management/employee.js"></script>
<body class="p-activity p-activity-type">
<div class="wrapper">
    <jsp:include page="../include/top.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/left.jsp" flush="true"></jsp:include>



    <div class="content">
        <div class="crumb">
            <h5>基础功能 &#187; 员工信息录入及部门管理</h5>
        </div>

        <div class="content-container" >
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>员工信息录入及部门管理</span></h3>
                    <div class="row area-list-dec">
                        <div class="area-search">
                            <form class="form-inline" role="form"  method="post"
                                    id="searchForm"  name="searchForm"	action="index.do">
                            <div class="form-input">
                                <input class="form-control"    name="realName" placeholder="姓名" type="text"  value="${vo.realName}">
                            </div>
                            <div class="form-input" style="margin-left: 20px">
                                <input class="form-control"     name="userName" placeholder="帐号" type="text"  value="${vo.userName}">
                            </div>
                            <div class="form-input" style="margin-left: 20px">
                                <input class="form-control"   name="position" placeholder="职位" type="text"  value="${vo.position}">
                            </div>
                            <div class="form-input" style="margin-left: 20px">
                                <input class="form-control"    name="email" placeholder="邮箱" type="text"  value="${vo.email}">
                            </div>
                            <input type="hidden" name="departId" id="queryDepartId" value="${vo.departId}">
                                <!-- <div class="form-input" style="margin-left: 20px">
                                    <select id="departid" name="pvMap.departid"  class="form-control select2-input select2-offscreen" style="width:150px;" >
                                    	<option value="" selected="selected">全部部门</option>

                                    </select>
                         		</div>-->

                        </div>
                        <div class="form-search-sub" style="margin-left:20px;">
                            <button type="button" id="btn_query" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                            <a href="#save_edit"   data-toggle="modal" onclick="initAdd();" class="btn btn-default btnAdd" data-backdrop="static" style="margin-left:30px;"><i class="ico-add"></i>添加</a>
                            <a href="#deletedepart"  style="display:none" data-toggle="modal" class="btn btn-default" data-backdrop="static" ><span  id="deletede">AA</span></a>
                            <button type="button" id="btn_export" class="btn btn-default"><i class="ico-export"></i>导出</button>

                        </div>
                        </form>
                    </div>
                </div>

                <div class="table-responsive" style="border: 0px solid blue;width: 20%;height: auto;float: left">
                    <table style="border-left: 1px solid #DDDDDD;border-top:0px;border-bottom:0px;border-right width: 100%;"
                           class="table table-striped table-bordered table-hover table-highlight">
                        <thead>
                        <tr style="height: 32px;">
                            <th><center>部门类型</center></th>
                        </tr>
                        </thead>
                        <tbody style="border-right: 0px;">
                        <tr style="height: 555px;border-right: 0px;">
                            <th style="border: 0px">
                                <iframe scrolling="no" src="${pageContext.request.contextPath}/department/orgtree.do" width="100%" height="100%" frameborder=0></iframe>
                            </th>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="table-responsive"  style="width: 78%;float: left">
                    <table id="mymenutable"  class="table table-striped table-bordered table-hover table-highlight table-checkable order-column" data-provide="datatable" data-display-rows="10" data-info="true" data-length-change="true" data-paginate="true">
                        <thead>
                        <tr>
                            <th>缩略图</th>
                            <th>姓名</th>
                            <th>帐号</th>
                            <th>职位</th>
                            <th>手机</th>
                            <th>邮箱</th>
                            <th>部门</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <input id="log_roleid" value="${request.pvMap.roleid}" type="hidden"/>
                        <c:forEach items="${page.data}" var="item">
                            <input type="hidden" id="realname_${item.id}" value="${item.realName}"/>
                            <input type="hidden" id="username_${item.id}" value="${item.userName}"/>
                            <input type="hidden" id="position_${item.id}" value="${item.position}"/>
                            <input type="hidden" id="phone_${item.id}" value="${item.phone}"/>
                            <input type="hidden" id="email_${item.id}" value="${item.email}"/>
                            <input type="hidden" id="departid_${item.id}" value="${item.departId}"/>
                            <input type="hidden" id="headimage_${item.id}" value="${item.headImage}"/>
                            <input type="hidden" id="sex_${item.id}" value="${item.sex}"/>
                            <input type="hidden" id="wxnum_${item.id}" value="${item.wxNum}"/>
                            <tr>
                                <td><img src="${pageContext.request.contextPath}${item.headImage}" width="40" height="40"/></td>
                                <td>${item.realName}</td>
                                <td>${item.userName}</td>
                                <td>${item.position}</td>
                                <td>${item.phone}</td>
                                <td>${item.email}</td>
                                <td>${item.departName}</td>
                                <td>
                                    <a href="#save_edit" onclick="EditInfo('${item.id}')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>编辑</a>
                                    <a href="#deleteTip" onclick="deleteData('${item.id}')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>删除</a>
                                    <a href="#mark_edit" onclick="markData('${item.id}')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>标签设置</a>
                                    <a href="#role_edit" onclick="roleData('${item.id}')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>角色设置</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <xq:page pagination="${page}" formId="searchForm"/>
            </div>
        </div>
    </div>
</div>
</div>




<script type="text/javascript">

</script>
<a href="#error" id="statea" data-toggle="modal"  style="display: none"><span>提示</span></a>
<input type="hidden" id="state" value="${request.state}"/>
<div id="error" class="modal fade tips-modal tips-modal-warning">
    <div class="modal-dialog">
        <div class="modal-content">
            <form theme="simple" method="post" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title">提示</h3>
                </div>
                <div class="modal-body">
                    <i class="ico-tips-warning"></i>
                    <h4>您输入的手机号已存在<span id="st"></span>，添加不成功！</h4>
                    <p class="tips-modal-sub"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-close" data-dismiss="modal">确定</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="deleteTip" class="modal fade tips-modal tips-modal-warning">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="delete.do" id="deleteForm" name="deleteForm"
                    theme="simple" method="post"
                    namespace="/backer" >
                <input type="hidden"    id="deleteId" name="id" />
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title">提示</h3>
                </div>
                <div class="modal-body">
                    <i class="ico-tips-warning"></i>
                    <h4>确定删除？</h4>
                    <p class="tips-modal-sub">员工删除之后无法恢复</p>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
                    <button type="submit" class="btn btn-save"><i class="ico-ok"></i>确 定</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- 添加 -->
<div id="save_edit" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title">员工信息编辑</h3>
            </div>
            <form id="saveEditForm" class="form-horizontal parsley-form" method="post"   role="form"  action="handler.do"  data-validate="parsley">
            <input type="hidden"  id="id" name="id" />
            <div class="modal-body">
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label"><i class="required">*</i>姓名</label>
                        <div class="col-md-8">
                            <input id="realname"name="realName" type="text" class="form-control parsley-validated" data-required="true">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label"><i class="required">*</i>帐号名称</label>
                        <div class="col-md-8">
                            <input id="username" name="userName" type="text" class="form-control parsley-validated" data-required="true">
                        </div>
                    </div>
               </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label"><i class="required">*</i>帐号密码</label>
                        <div class="col-md-8">
                            <input id="password" name="password" type="password" class="form-control parsley-validated">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">性别</label>
                        <div class="col-md-8">
                            <select id="sex" name="sex" class="form-control select2-input select2-offscreen" style="width:150px;" >
                                <option value="1" >男</option>
                                <option value="2" >女</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">职位</label>
                        <div class="col-md-8">
                            <select id="position" name="position" class="form-control select2-input select2-offscreen" style="width:150px;" >
                                <option value="">请选择</option>
                                <option value="总经理">总经理</option>
                                <option value="销售经理">销售经理</option>
                                <option value="服务经理">服务经理</option>
                                <option value="DCRC经理">DCRC经理</option>
                                <option value="零件经理">零件经理</option>
                                <option value="索赔员">索赔员</option>
                                <option value="市场经理">市场经理</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label"><i class="required">*</i>手机</label>
                        <div class="col-md-8">
                            <input id="phone" name="phone" type="text" class="form-control parsley-validated" data-required="true">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">微信号</label>
                        <div class="col-md-8">
                            <input id="wxnum" name="wxNum" type="text" class="form-control parsley-validated">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">邮箱</label>
                        <div class="col-md-8">
                            <input id="email" name="email" type="text" class="form-control parsley-validated" data-type="email">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">部门</label>
                        <div class="col-md-8">
                            <select id="departid" name="departId" class="form-control select2-input select2-offscreen" style="width:150px;">
                                <option value="" selected="selected">全部</option>
                                <c:forEach items="${departments}" var="item">
                                    <option value="${item.id}">${item.departName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">头像</label>
                        <div class="col-md-8">
                            <input type="hidden" id="headImage" name="headImage">
                            <img id="showMyImg" alt="" src="${pageContext.request.contextPath}" class="form-control" class="form-control" style="width:120px;height:160px;cursor: pointer">
                            <span style="color:red">分辨率：300 * 400</span>
                        </div>
                    </div>
                    </div>
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-save"><i class="ico-ok"></i>保 存</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--标签设置 -->
<div id="mark_edit" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title">标签设置</h3>
            </div>
            <form id="markEditForm" class="form-horizontal parsley-form" method="post"   role="form"  action="#"  data-validate="parsley" >
            <input type="hidden" id="userId" name="userId"/>
            <div class="modal-body">
                <div class="form-group">
                    <label class="col-md-3 control-label"><i class="required">*</i>用户当前标签</label>
                    <div class="col-md-7" id="marknames">
                    </div>
                </div>
                <div class="form-group" style="margin-top:20px;padding-top:20px;">
                    <label class="col-md-3 control-label"><i class="required">*</i>所有标签</label>
                    <div class="col-md-7">
                        <c:forEach items="${marks}" var="item">
                            <label title="${item.name}" class="icheck-inline">
                                <input type="checkbox" name="markIds" class="icheck-input" value="${item.id}"/>${item.name}
                            </label>
                        </c:forEach>
                    </div>
                </div>
            </div>
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
                <button type="button" id="saveUserMark" class="btn btn-save"><i class="ico-ok"></i>保 存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 角色 -->
<div id="role_edit" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title">角色设置</h3>
            </div>
            <form id="roleEditForm" class="form-horizontal parsley-form" method="post"   role="form"  action="#"  data-validate="parsley" >
            <input type="hidden" id="id" name="userId"/>
            <div class="modal-body">
                <div class="form-group">
                    <label class="col-md-3 control-label"><i class="required">*</i>用户当前的角色</label>
                    <div class="col-md-7" id="rolenames">
                    </div>
                </div>
                <div class="form-group" style="margin-top:20px;padding-top:20px;">
                    <label class="col-md-3 control-label"><i class="required">*</i>所有角色</label>
                    <div class="col-md-7">
                        <c:forEach items="${roles}" var="item">
                            <label title="${item.name}" class="icheck-inline">
                                <input type="checkbox" name="roleIds" class="icheck-input" value="${item.id}"/>${item.name}
                            </label>
                        </c:forEach>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-cancel" data-dismiss="modal">取消</button>
                <button type="button" id="saveUserRole" class="btn btn-save"><i class="ico-ok"></i>保 存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<a href="#deletedepart"><span></span></a>
<div id="deletedepart" class="modal fade tips-modal tips-modal-warning">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="Basic!editDepart.action" id="deletedepartForm" name="deleteForm"
                    theme="simple" method="post"
                    namespace="/backer" >
                <input type="hidden"    id="deletedepartId" name="paramMap.departId" />
                <input type="hidden" name="paramMap.op" value="2"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title">提示</h3>
                </div>
                <div class="modal-body">
                    <i class="ico-tips-warning"></i>
                    <h4>确定删除？</h4>
                    <p class="tips-modal-sub">部门删除之后无法恢复</p>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
                    <button type="submit"   class="btn btn-save"><i class="ico-ok"></i>确 定</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>