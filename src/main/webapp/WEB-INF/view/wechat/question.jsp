<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="zh-cn"> <!--<![endif]-->

<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>咨询解答信息</title>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
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
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
$(function(){
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
<c:forEach  items="${questions}" var="item">
    <div class="question_box" style="">
        <div class="p_box">${item.content}</div>
        <div class="span_box"><span>发布时间：<i><fmt:formatDate value="${item.questionTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></i></span>
        <span>发送部门：<i>${item.departName}</i></span>
        </div>
    </div>
    <c:forEach  items="${item.answers}" var="answer">
    <ul class="interaction_list" style="">
        <li>
            <div class="xx_01">
                <span>回复者：</span>
               <span>${answer.realName}</span>
                <i><fmt:formatDate value="${answer.answerTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></i>
                <div class="clear"></div>
            </div>
            <div class="xx_01">
                <span>回复部门：</span>
               <span>
              ${answer.departName}
              </span>
                <div class="clear"></div>
             </div>
            <div class="p_box">
                <p>${answer.answerContent}
               </p>
            </div>
        </li>
    </ul>
    </c:forEach>
</c:forEach>

<br/>
 <div class="a_btn"><a href="${pageContext.request.contextPath}/we/questionissue.do">我要提问</a></div>
</body>
</html>