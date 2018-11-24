<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="zh-cn"> <!--<![endif]-->

<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>咨询解答信息</title>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" />
<style type="text/css">
   .a_btn a{
    background: #F0442F none repeat scroll 0 0;
    border-radius: 3px;
    color: #fff;
    display: block;
    font-size: 22px;
    height: 56px;
    line-height: 56px;
    text-align: center;
    width: 100%;
}

</style>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".p_box").each(function(){
		$(this).find("p").html($(this).find("p").text());
	});
	$(".p_box img").each(function(){
		$(this).css({"width":"100px"});
		$(this).wrap('<a href="'+$(this).attr("src")+'" target="_blank" ></a>');
	});
	
});
</script>
</head>
<body>
<s:iterator value="#request.dataList" status="st" id="item">
<div class="question_box" style="">
	<div class="p_box"><s:property value="#item.qucontent"/></div>
    <div class="span_box"><span>发布时间：<i><s:date name="#item.quiztime" format="yyyy-MM-dd HH:mm:ss"/></i></span>
    <span>发送部门：<i><s:property value="#item.deptname"/></i></span>
    </div>
</div>
<s:iterator value="#item.answerList" status="st2" id="answer">
<ul class="interaction_list" style="">
	<li>
    	<div class="xx_01">
        	<span>回复者：</span>
           <span><s:property value="#answer.realname"/></span>
            <i><s:property value="#answer.answertime"/></i>
            <div class="clear"></div>
        </div>
        <div class="xx_01">
        	<span>回复部门：</span>
           <span>
          <s:property value="#answer.departname"/>
          </span>
            <div class="clear"></div>
         </div>
        <div class="p_box">
        	<p><s:property value="#answer.answercontent"/>
           </p>
        </div>
    </li>
</ul>
</s:iterator>
</s:iterator>

<br/>
 <div class="a_btn"><a href="<%=request.getContextPath()%>/backer/system!findDepartListAll.action">我要提问</a></div>


</body>
</html>