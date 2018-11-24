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
                    <h3 class="current-tit"><span>经销商导入成功</span></h3>
						<div class="tab-content tab-pills-content">
							<div class="tab-pane active">
								<div class="table-responsive">
								
								导入成功！！！！！！！！！！！！！
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function filechoose(){
		var filename=$('#fileFileName').val();
		var RegExp=/\.xls$/;
		if(RegExp.test(filename)==false){
			alert("文件格式必须是.xls结尾才能上传");
			$('#fileFileName').val("");
		}
	}
	</script>
</body>
</html>