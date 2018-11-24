<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../include/header2.jsp" flush="true"></jsp:include>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#mymenutable tr").each(function(){
                $(this).children().eq(0).html($(this).children().eq(0).text());
            });
            $("#mymenutable img").each(function(){
                $(this).css({"width":"100px"});
                $(this).wrap('<a href="'+$(this).attr("src")+'" target="_blank" ></a>');
            });
        });
    </script>
</head>
<body class="p-inte p-inte-change"  >
<div class="wrapper">
    <jsp:include page="../include/top.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/left.jsp" flush="true"></jsp:include>
    <div class="content" >
        <div class="crumb">
            <h5>业务管理 &#187;咨询解答回复详情 </h5>
        </div>
        <div class="content-container" style="overflow:auto">
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>咨询解答回复详情</span></h3>
                    <div class="table-responsive"  >
                        <table id="mymenutable" class="table table-striped table-bordered table-hover table-highlight table-checkable order-column" data-provide="datatable" data-display-rows="10" data-info="true" data-length-change="true" data-paginate="true">
                            <thead>
                            <tr>
                                <th>回复内容</th>
                                <th>回复部门</th>
                                <th>回复人</th>
                                <th>回复时间</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${answers}"  var="item">
                                    <tr>
                                        <td><c:out value="${item.answerContent}"/></td>
                                        <td>${item.departName}</td>
                                        <td>${item.realName}</td>
                                        <td><fmt:formatDate value="${item.answerTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>