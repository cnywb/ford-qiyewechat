<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

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
                    <h3 class="current-tit"><span>下发详情</span></h3>
                    <div class="row area-list-dec">     
                    	 <div class="area-search">
                            <s:form class="form-inline" role="form" namespace="/backer"
								id="searchForm" action="system!getUserInfoByMater.action">
								<input class="form-control"  name="pvMap.materid" type="hidden"  value="<s:property value='#request.pvMap.materid' />">
                                <div class="form-input">
                                 	<input class="form-control"  id="realname" name="pvMap.realname" placeholder="姓名" type="text"  value="<s:property value='#request.pvMap.realname' />">
                                </div>
                                <div class="form-date-single-time" style="margin-left:20px;">
                                    <input class="form-control date-picker-single-time" id="startDate" type="text" 
										name="pvMap.gainstarttime" placeholder="开始时间"
										value="<s:property value='#request.pvMap.gainstarttime' />" readonly>
                                    <span class="date-picker-addon"><i class="ico-calendar"></i></span>
                                </div>
                                <div class="form-date-single-time" style="margin-left:20px;">
                                    <input class="form-control date-picker-single-time" id="endDate" type="text" 
										name="pvMap.gainendtime" placeholder="结束时间"
										value="<s:property value='#request.pvMap.gainendtime' />" readonly>
                                    <span class="date-picker-addon"><i class="ico-calendar"></i></span>
                                </div>
                                <div class="form-search-sub" style="margin-left:20px;">
                                    <button type="submit" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                    <a href="#save_edit" data-toggle="modal" onclick="initAdd();" class="btn btn-default" data-backdrop="static" style="margin-left:30px;"><i class="ico-add"></i>添加</a>
                                </div>
                            </s:form>
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
                            <s:iterator value="#request.dataList" status="st" id="item">
                            	<tr>
                                    <td><s:property value="#item.realname"/></td>
                                    <td><s:property value="#item.username"/></td>
                                    <td><s:property value="#item.position"/></td>
                                    <td><s:property value="#item.phone"/></td>
                                    <td><s:property value="#item.email"/></td>
                                    <td><s:property value="#item.departname"/></td>
                                    <td><s:property value="#item.createtime"/></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>         
                    </div>
                    ${pager}
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