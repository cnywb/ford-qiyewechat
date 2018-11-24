<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="zh-cn"> <!--<![endif]-->

<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>物料下载详情</title>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
	 function locking(){   
		   document.all.ly.style.display="block";   
		   document.all.ly.style.width=document.body.clientWidth;   
		   document.all.ly.style.height=document.body.clientHeight;   
		   document.all.Layer2.style.display='block';  
		   }   

	 function Lock_CheckForm(theForm){   
		   document.all.ly.style.display='none';document.all.Layer2.style.display='none';
		  return   false;   
		   } 
</script>
</head>
<body>
 <div id="ly" style="color: #154BA0;
    background:#70F4D6;
    filter: Alpha(Opacity=10, Style=0);
    opacity: 0.10;
    position: absolute;
    height: 100%;
    width:100%;
    z-index:20;display:none;border:1px red solid"></div>
    <!--          浮层框架开始         -->
     	<center>
        <table  id="Layer2" style="position: absolute;background:white;
	    height:70%;
	    width:80%;
	    text-align:center;
	    margin-left:10%;
	    margin-top:20%;
		vertical-align:middle;
	    z-index:30;display:none;border:1px blue solid" border="0px" cellpadding="0" cellspacing="0" style="border: 0    solid    #e7e3e7; border-collapse: collapse ;" >
            <tr>
                <td style="background-color: #73A2d6; color: #fff; padding-left: 4px; padding-top: 2px; font-weight: bold; font-size: 12px;" height="30" valign="middle">
                  <div align="right"><a href=JavaScript:; onclick="Lock_CheckForm(this);">[关闭]</a> &nbsp;&nbsp;&nbsp;&nbsp;</div>
                </td>
                
            </tr>
            <tr >
                <td style="padding-left: 10%;padding-right:10%" align="center">
                     <p id="remark" >
                      
                     </p>
                </td>
            </tr>
             <tr>
                <td style="padding-left: 10%;padding-right:10%" >
                     <p id="durl" >
                       
                     </p>
                </td>
            </tr>
             <tr>
                <td style="padding-left: 10%;padding-right:10%" align="center">
                     <p id="warm">
                        
                     </p>
                </td>
            </tr>
        </table>
     	<center>
<%----%>
<div class="main_bg">  
  <div class="jptg_list">
  	<ul>
    	<li class="head_row">
            <span style="width:20%">资源名称</span>
            <span style="width:20%">资源备注</span>
            <span style="width:20%">下发用户</span>
            <span style="width:20%">下发时间</span>
            <span style="width:20%">操作</span>
        <div class="clear"></div>
        </li>
        <c:forEach items="${dataList}"   var="item">
        <li>
        	<a>
            	<div style="width:20%;" class="txt">${item.name}</i></div>
                <div style="width:20%" class="txt">${item.content} </i></div>
                <div style="width:20%" class="txt">${item.realName}</div>
                <div style="width:20%" class="txt"><fmt:formatDate value="${item.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                <div style="width:20%" class="txt"><a style='color: blue' onclick="OutputStream('${item.id}','${item.content}')" >下载</a></div>
                <div class="clear"></div>
            </a>
        </li>
       </c:forEach>
    </ul>
  </div>
</div>
 
<script type="text/javascript">
function OutputStream(id,content){
    var urls="${pageContext.request.contextPath}/material/downloadFile.do?id="+id;
     $('#remark').html("<b>资源备注 ：</b>"+content);
     $('#durl').html("<b>下载地址：</b><a style='color:#2A73EB' href='"+urls+"'>"+urls+"</a>");
     $('#warm').html("*注,Android 手机可以点击直接下载<br/>IOS手机可复制下载链接进行物料下载");
     locking();
     window.location.href(urls);
    }
</script>
</body>
</html>