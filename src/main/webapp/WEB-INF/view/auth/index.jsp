<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="xq" uri="http://www.xiqing.info/tags"%>
<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-activity p-activity-auth">
<div class="wrapper">
    <jsp:include page="../include/top.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/left.jsp" flush="true"></jsp:include>
    <div class="content">
        <div class="crumb">
            <h5>基础功能 &#187;用户权限</h5>
        </div>
        <div class="content-container">
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>用户权限查询</span></h3>
                    <div class="row area-list-dec">
                        <div class="area-search">
                            <form class="form-inline form-horizontal" role="form"  id="searchForm" method="post" name="searchForm" action="index.do">
                                <div class="form-input">
                                    <input class="form-control"    name="userName" placeholder="用户名" type="text"  value="${vo.userName}">
                                </div>
                                <div class="form-input">
                                    <input class="form-control"    name="roleName" placeholder="角色名称" type="text"  value="${vo.roleName}">
                                </div>
                                <div class="form-input">
                                    <input class="form-control"    name="functionName" placeholder="资源名称" type="text"  value="${vo.functionName}">
                                </div>
                                <div class="form-search-sub" style="margin-left:20px;">
                                    <button type="submit" class="btn btn-default" id="btn_query"><i class="ico-search"></i>查 询</button>
                                </div>
                                <div class="form-search-sub" style="margin-left:20px;">
                                    <button type="button" id="btn_export" class="btn btn-default"><i class="ico-export"></i>导出</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table id="mymenutable"  class="table table-striped table-bordered table-hover table-highlight table-checkable order-column" data-provide="datatable" data-display-rows="10" data-info="true" data-length-change="true" data-paginate="true">
                            <thead>
                            <tr>
                                <th>用户名</th>
                                <th>用户姓名</th>
                                <th>角色名称</th>
                                <th>资源名称</th>
                                <th>路径</th>
                                <th>顺序</th>
                                <th>备注</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.data}" var="item">
                                <tr>
                                    <td>${item.userName}</td>
                                    <td>${item.realName}</td>
                                    <td>${item.roleName}</td>
                                    <td>${item.functionName}</td>
                                    <td>${item.linkUrl}</td>
                                    <td>${item.priority}</td>
                                    <td>${item.remark}</td>
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
<script  type="text/javascript" language="JavaScript">
    $(function() {

        $("#btn_query").click(function () {
            $("#searchForm").attr("action", "index.do");
            $("#searchForm").submit();
        });

        $("#btn_export").click(function () {
            $("#searchForm").attr("action", "export.do");
            $("#searchForm").submit();
        });


    });
</script>
</body>
</html>