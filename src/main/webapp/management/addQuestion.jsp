<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="zh-cn"> <!--<![endif]-->
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>添加问题</title>
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" />
    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
   <link href="<s:url value='/plugin/mobiscroll/css/mobiscroll.core-2.5.2.css' />" rel="stylesheet" />
 
   <link href="<s:url value='/plugin/mobiscroll/css/mobiscroll.android-ics-2.5.2.css' />" rel="stylesheet" />
   <script src="<s:url value='/plugin/mobiscroll/js/mobiscroll.core-2.5.2.js' />"></script>
   <script src="<s:url value='/plugin/mobiscroll/js/mobiscroll.core-2.5.2-zh.js' />"></script>
   <script src="<s:url value='/plugin/mobiscroll/js/mobiscroll.list-2.5.1.js' />"></script>
   <script src="<s:url value='/plugin/mobiscroll/js/mobiscroll.select-2.5.1.js' />"></script>
         
<SCRIPT type="text/javascript">
  function sub(){
	 var deptid=$("#deptid").val(); 
	  if(deptid==""){
		  alert("请选择部门!");
			return false;
	  }
	var len=  document.getElementById("qucontent").value.length;
	if(len<1){
		alert("问题内容不能为空!");
		return false;
	}else if(len>100){
       alert("问题内容在100字以内!");
       return false;
	}
	$("#questionForm").submit();
}
 
  $(function () {

	    $('#deptid').mobiscroll().select({
	        theme: 'android-ics light',
	        lang: 'zh',
	        display: 'modal',
	        minWidth: 200,
	        label: '选择部门'
	    });
  });
</SCRIPT>
<s:if test="#request.existQuestion==true">
<SCRIPT type="text/javascript">
alert("请勿重复提交问题!");
</SCRIPT>
</s:if>
<s:if test="#request.canAsk==false">
<SCRIPT type="text/javascript">
alert("内部员工不可以提问!");
</SCRIPT>
</s:if>
</head>

<body >
<div class="top_title">
	<div class="txt f-20">我要提问</div>
    <a class="ico_back">返回</a>
    <a class="ico_find">查找</a>
</div>
 <form  id="questionForm" method="post" action="<%=request.getContextPath()%>/backer/system!saveQuestion.action" >
<div class="send_txbox">
	<div class="textarea"><textarea id="qucontent" name="paramMap.qucontent" class="text"></textarea></div>
    <div class="txt">不超过&nbsp;<span></span>100&nbsp;字</div>
</div>
<span style="color:white">选择部门：</span>
<select id="deptid" name="paramMap.deptid"  >
         <option value=""></option>
         <s:iterator value="#request.dictDataList" var="item">
     		<option value="<s:property value='#item.departid' />"><s:property value='#item.departname' /></option>
         </s:iterator>
</select>
<div class="btn_send"><input onclick="sub()" type="button" value="发布"></div>
</form>
</body>
</html>