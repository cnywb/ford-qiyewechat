<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" autoFlush="true" %>
<!DOCTYPE html>
<jsp:include page="include/header.jsp" flush="true"></jsp:include>
<body class="p-dashboard">
	<div class="wrapper">
		<jsp:include page="include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="include/left.jsp" flush="true"></jsp:include>
		<div class="content">
			<div class="crumb">
				<h5>
					首页
				</h5>
			</div>
			<div class="content-container">
				<div class="row">
					<div class="col-md-12" role="main" id="main">
						<h3 class="current-tit">
							<span>首页</span>
						</h3>
						<div class="dash-cover">
							<div class="dash-cover-item">
							   <Strong style="margin-left:20px;">欢迎使用福特企业号管理系统！</Strong>
							</div>
                    	</div>
                    </div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="include/foot.jsp" flush="true"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/esl.js"></script>
	<script type="text/javascript">
	$(function(){
		function echartWidth () {
			var tabsWidth = $(".nav-pills").width();
			$(".ui-echarts").css ("width",function () {
				return (tabsWidth - 30);
			});
		}
		echartWidth();
		$(window).on('resize', echartWidth);
	});
</script>
</body>
</html>