<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<jsp:include page="../include/header.jsp" flush="true"></jsp:include>
<body class="p-activity>
	<div class="wrapper">
		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>
	<div class="content">
        <div class="crumb">
            <h5>批量导入</h5>
        </div>
        <div class="content-container">
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>批量导入经销商</span></h3>
						<div class="tab-content tab-pills-content">
							<div class="tab-pane active">
								<div class="table-responsive">
								<s:form action="Basic!SaveAgencyInto.action"
									enctype="multipart/form-data" theme="simple" method="post"
									namespace="/backer">
								   <s:file name="fileFileName" id="fileFileName"  onchange="filechoose()"></s:file>
									<button style="margin-top:50px" type="submit" class="btn btn-default" >导入</button>&nbsp;&nbsp;<font color="red"><%=request.getAttribute("error")==null?"":request.getAttribute("error")%></font>
								</s:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>

</html>