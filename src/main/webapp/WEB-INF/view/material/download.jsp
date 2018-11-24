<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="xq" uri="http://www.xiqing.info/tags"%>
<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-inte p-inte-gain">
	<div class="wrapper">
		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>

	<div class="content">
        <div class="crumb">
            <h5>业务管理 &#187; 物料下发</h5>
        </div>
        <div class="content-container">
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>下载详情</span></h3>
                    <div class="row area-list-dec">     
                    	 <div class="area-search">
                            <form class="form-inline" role="form"  method="post"  name="searchForm"
								id="searchForm" action="download.do?materialId=${vo.materialId}">
								<input class="form-control"  name="materialId" type="hidden"  value="${vo.materialId}">
                                <div class="form-input">
                                 	<input class="form-control"  id="realname" name="realName" placeholder="姓名" type="text"  value="${vo.realName}">
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
                                    <button type="submit" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table id="mymenutable"  class="table table-striped table-bordered table-hover table-highlight table-checkable order-column" data-provide="datatable" data-display-rows="10" data-info="true" data-length-change="true" data-paginate="true">
                            <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>帐号</th>
                                    <th>职位</th>
                                    <th>手机</th>
                                    <th>邮箱</th>
                                    <th>所属部门</th>
                                    <th>下载时间</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.data}"  var="item">
                            	<tr>
                                    <td>${item.realName}</td>
                                    <td>${item.userName}</td>
                                    <td>${item.position}</td>
                                    <td>${item.phone}</td>
                                    <td>${item.email}</td>
                                    <td>${item.departName}</td>
                                    <td><fmt:formatDate value="${item.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                </tr>
                               </c:forEach>
                            </tbody>
                        </table>         
                    </div>
                    <xq:page pagination="${page}" formId="searchForm" action="download.do?materialId=${vo.materialId}"/>
                </div>
            </div>
        </div>
    </div>
</div>




<script type="text/javascript">
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
});
</script>
</body>
</html>