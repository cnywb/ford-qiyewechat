<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="xq" uri="http://www.xiqing.info/tags"%>
<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-activity p-activity-log">
<div class="wrapper">
    <jsp:include page="../include/top.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/left.jsp" flush="true"></jsp:include>
    <div class="content">
        <div class="crumb">
            <h5>基础功能 &#187; 操作日志</h5>
        </div>
        <div class="content-container">
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>操作日志</span></h3>
                    <div class="row area-list-dec">
                        <div class="area-search">
                            <form class="form-inline form-horizontal" role="form"  id="searchForm" method="post" name="searchForm" action="index.do">
                                <div class="form-input">
                                    <input class="form-control"    name="userName" placeholder="用户名" type="text"  value="${vo.userName}">
                                </div>
                                <div class="form-select" style="margin-left:30px;">
                                    <label class="control-label">操作类型</label>
                                    <select name="operationTypeName" class="form-control select2-input select2-offscreen" style="width:150px;" >
                                         <option value=""  <c:if test="${vo.operationTypeName==''}">selected="selected"</c:if> >全部</option>
                                        <c:forEach var="item" items="${types}">
                                            <option value="${item.value}" <c:if test="${vo.operationTypeName==item.value}">selected="selected"</c:if> >${item.value.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-date-single-time" style="margin-left:20px;">
                                    <input class="form-control date-picker-single-time" id="startDate" type="text"
                                           name="startDate" placeholder="开始时间"
                                           value="${vo.startDate}" readonly>
                                    <span class="date-picker-addon"><i class="ico-calendar"></i></span>
                                </div>
                                <div class="form-date-single-time" style="margin-left:20px;">
                                    <input class="form-control date-picker-single-time" id="endDate" type="text"
                                           name="endDate" placeholder="结束时间"
                                           value="${vo.endDate}" readonly>
                                    <span class="date-picker-addon"><i class="ico-calendar"></i></span>
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
                                <th>操作类型</th>
                                <th>操作内容</th>
                                <th>操作日期</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.data}" var="item">
                                <tr>
                                    <td>${item.userName}</td>
                                    <td>${item.operationType.name}</td>
                                    <td>${item.operationContent}</td>
                                    <td><fmt:formatDate value="${item.operationDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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

        $('#startDate',$('#searchForm')).dateRangePicker ({
            language: 'cn',
            singleDate : true,
            showShortcuts: false,
            format: 'YYYY-MM-DD ',
            time: {
                enabled: false
            }
        });
        $('#endDate',$('#searchForm')).dateRangePicker ({
            language: 'cn',
            singleDate : true,
            showShortcuts: false,
            format: 'YYYY-MM-DD ',
            time: {
                enabled: false
            }
        });


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