<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="xq" uri="http://www.xiqing.info/tags"%>
<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-inte p-inte-task">
<div class="wrapper">
    <jsp:include page="../include/top.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/left.jsp" flush="true"></jsp:include>
    <div class="content">
        <div class="crumb">
            <h5>基础功能 &#187; 任务管理</h5>
        </div>
        <div class="content-container">
            <div class="row">
                        <div class="col-md-12" role="main">
                            <h3 class="current-tit"><span>任务管理</span></h3>
                            <div class="row area-list-dec">
                                <div class="area-search">
                                    <form class="form-inline form-horizontal" role="form"  id="searchForm" method="post" name="searchForm" action="index.do">
                                        <div class="form-input">
                                            <input class="form-control"    name="batchNo" placeholder="批次号" type="text"  value="${vo.name}">
                                        </div>
                                        <div class="form-select" style="margin-left:30px;">
                                            <label class="control-label">状态</label>
                                            <select name="status" class="form-control select2-input select2-offscreen" style="width:90px;" >
                                                <option value=""  <c:if test="${questionVo.status==''}">selected="selected"</c:if> >全部</option>
                                                <option value="1" <c:if test="${questionVo.status==1}">selected="selected"</c:if> >待处理</option>
                                                <option value="2" <c:if test="${questionVo.status==2}">selected="selected"</c:if>>处理中</option>
                                                <option value="3" <c:if test="${questionVo.status==3}">selected="selected"</c:if>>完成</option>

                                            </select>
                                        </div>
                                        <div class="form-search-sub" style="margin-left:20px;">
                                            <button type="submit" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="table-responsive">
                        <table id="mymenutable"  class="table table-striped table-bordered table-hover table-highlight table-checkable order-column" data-provide="datatable" data-display-rows="10" data-info="true" data-length-change="true" data-paginate="true">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>批次号</th>
                                <th>状态</th>
                                <th>类型</th>
                                <th>备注</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.data}" var="item">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.batchNo}</td>
                                    <td>
                                       <c:if test="${item.status==1}">待处理</c:if>
                                       <c:if test="${item.status==2}">处理中</c:if>
                                       <c:if test="${item.status==3}">完成</c:if>
                                    </td>
                                    <td>${item.dataType}</td>
                                    <td>${item.remark}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div
                            <xq:page pagination="${page}" formId="searchForm"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>