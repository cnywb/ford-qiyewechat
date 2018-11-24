<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="zh-cn"> <!--<![endif]-->
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>添加问题</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/js/mobile/css/line-spin-fade-loader.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/plugin/mobiscroll/css/mobiscroll.android-ics-2.5.2.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/js/zepto.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/js/zepto.alert.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/js/one.js"></script>

         
<SCRIPT type="text/javascript">
  function sub(){
	 var departId=$('#departId').val();
	  if(departId==""){
		    alert('请选择部门');
			return false;
	  }
	  var len=$('#qucontent').val().length;
		if(len<1){
			alert("问题内容不能为空!");
			return false;
		}else if(len>100){
		   alert("问题内容在100字以内!");
		   return false;
		}
	  var params=$('#questionForm').serialize();
	  console.log(params);
	  one.util.trans('questionsave.do',params,function(data,status){
		     data=JSON.parse(data);
			if(data.status==1){
			  	alert(data.message);
				one.util.href('question.do');
				return;
			}
		   alert(data.message);
	  });
}
 

</SCRIPT>
</head>

<body >
<div class="top_title">
	<div class="txt f-20">我要提问</div>
    <a class="ico_back">返回</a>
    <a class="ico_find">查找</a>
</div>
 <form  id="questionForm" method="post" action="${pageContext.request.contextPath}/we/questionsave.do">
<div class="send_txbox">
	<div class="textarea"><textarea id="qucontent" name="content" class="text"></textarea></div>
    <div class="txt">不超过&nbsp;<span></span>100&nbsp;字</div>
</div>
<span style="color:white">选择部门：</span>
<select id="departId" name="departId"  >
         <option value="">选择部门</option>
         <c:forEach items="${departments}" var="item">
     		<option value="${item.departId}">${item.departName}</option>
         </c:forEach>
</select>
<div class="btn_send"><input onclick="sub()" type="button" value="发布"></div>
</form>
</body>
</html>