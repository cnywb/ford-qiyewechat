<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="../include/header2.jsp" flush="true"></jsp:include>
<link rel="stylesheet" href="<s:url value='/plugin/kindeditor/themes/default/default.css' />" />
<link rel="stylesheet" href="<s:url value='/plugin/kindeditor/plugins/code/prettify.css' />" />
<script charset="utf-8" src="<s:url value='/plugin/kindeditor/kindeditor.js' />"></script>
<script charset="utf-8" src="<s:url value='/plugin/kindeditor/lang/zh_CN.js' />"></script>
<link rel="stylesheet" href="<s:url value='/plugin/ztree/css/zTreeStyle/zTreeStyle.css' />" type="text/css">
<script src="<s:url value='/plugin/ztree/js/jquery.ztree.core-3.5.js' />"></script>
<script src="<s:url value='/plugin/ztree/js/jquery.ztree.excheck-3.5.js' />"></script>
<script src="<s:url value='/plugin/ztree/js/jquery.ztree.exedit-3.5.js' />"></script>
<script charset="utf-8" src="<s:url value='/js/management/question_list.js' />"></script>
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>

</head>

<body class="p-inte p-inte-change"  >
	<div class="wrapper">
		<jsp:include page="../include/top.jsp" flush="true"></jsp:include>
		<jsp:include page="../include/left.jsp" flush="true"></jsp:include>

	<div class="content" >
        <div class="crumb">
            <h5>业务管理 &#187; 咨询解答</h5>
        </div>
        <div class="content-container" style="overflow:auto">
            <div class="row">
                <div class="col-md-12" role="main">
                    <h3 class="current-tit"><span>咨询解答管理</span></h3>
                    <div class="row area-list-dec">     
                    	 <div class="area-search">
                            <s:form class="form-inline" role="form" namespace="/backer"
								id="searchForm" action="system!getQuestionList.action">
                                <div class="form-input">
                                 	<input class="form-control"  id="qucontent" value="<s:property value="#request.pvMap.qucontent"/>"  name="pvMap.qucontent" placeholder="内容关键字" type="text"  value="<s:property value='#request.pvMap.markname' />">
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
                                <div class="form-select" style="margin-left:30px;">
                                    <label class="control-label">状态</label>
                                    <select name="pvMap.status" class="form-control select2-input select2-offscreen" style="width:90px;" >
                                            <option value=""  <s:if test="#request.pvMap.status==''">selected="selected"</s:if> >全部</option>
					                		<option value="0" <s:if test="#request.pvMap.status==0">selected="selected"</s:if> >未答复</option>
					                		<option value="1" <s:if test="#request.pvMap.status==1">selected="selected"</s:if>>已答复</option>
					                		<option value="2" <s:if test="#request.pvMap.status==2">selected="selected"</s:if>>已关闭</option>
					                		
                                    </select>
                                </div>
                                <s:if test="#request.pvMap.userDeparmentId==3">
                                <div class="form-input" style="margin-left:30px;">
                                     <input type="hidden"  id="areaname" name="pvMap.areaname" value="#request.pvMap.areaname"  />
                                     <input type="hidden" id="samllname" name="pvMap.samllname" value="#request.pvMap.samllname" />
                                 	<input class="form-control"  id="treeArea" value="<s:property value="#request.pvMap.treeAreaName"/>"  name="pvMap.treeAreaName" placeholder="所属区域" type="text"  >
                                </div>
                                </s:if>
                                <div class="form-search-sub" style="margin-left:20px;">
                                    <button type="button" id="btn_query" class="btn btn-default"><i class="ico-search"></i>查 询</button>
                                     
                                </div>
                                 <div class="form-search-sub" style="margin-left:20px;">
                                 
                                     <button type="button" id="btn_export" class="btn btn-default"><i class="ico-export"></i>导出</button>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="table-responsive"  >
                        <table id="mymenutable" class="table table-striped table-bordered table-hover table-highlight table-checkable order-column" data-provide="datatable" data-display-rows="10" data-info="true" data-length-change="true" data-paginate="true">
                            <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>经销商姓名</th>
                                    <th>服务代码</th>
                                    <th>所属大区</th>
                                    <th>所属小区</th>
                                    <th>发送部门</th>
                                    <th>内容摘要</th>
                                    <th>提问时间</th>
                                   <!--  <th>解答时间</th>
                                    <th>解答人</th> -->
                                    <th>状态</th>    
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#request.dataList" status="st" id="item">
                            <input type="hidden" id="qucontent_<s:property value="#item.QUID"/>" value="<s:property value="#item.QUCONTENT"/>"/>
                            <input type="hidden" id="quname_<s:property value="#item.QUID"/>" value="<s:property value="#item.QUIZNAME"/>"/>
                            <input type="hidden" id="phone_<s:property value="#item.QUID"/>" value="<s:property value="#item.TEL"/>"/>
                            <input type="hidden" id="answername_<s:property value="#item.QUID"/>" value="<s:property value="#item.ANSWERNAME"/>"/>
                            <input type="hidden" id="answercontent_<s:property value="#item.QUID"/>" value="<s:property value="#item.ANSWERCONTENT"/>"/>
                            <input type="hidden" id="quizid_<s:property value="#item.QUID"/>" value="<s:property value="#item.QUIZID"/>"/>
                            <input type="hidden" id="departname_<s:property value="#item.QUID"/>" value="<s:property value="#item.DEPARTNAME"/>"/>
                            	<tr>
                                    <td><s:property value="#item.QUID"/></td>
                                    <td><s:property value="#item.QUIZNAME"/></td>
                                    <td><s:property value="#item.SERVECODE"/></td>
                                    <td><s:property value="#item.AREANAME"/></td>
                                    <td><s:property value="#item.SMALLNAME"/></td>
                                    <td><s:property value="#item.DEPARTNAME"/></td>
                                    <td><s:property value="#item.QUCONTENT"/></td>
                                    <td><s:date name="#item.QUIZTIME" format="yyyy-MM-dd HH:mm:ss"/></td>
                                   <%--  <td><s:date name="#item.answertime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td><s:property value="#item.answername"/></td> --%>
                                    <td>
                                      <s:if test="#item.STATUS == 0">未回复</s:if>
                                      <s:if test="#item.STATUS == 1">已回复</s:if>
                                      <s:if test="#item.STATUS == 2">已关闭</s:if>
                                      
                                    </td>
                                    <td>
                                     <s:if test="#item.STATUS!=2">
                                      <br>
                                     <a href="#save_edit" onclick="answerData('<s:property value='#item.QUID' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>回复</a>
                                     <br>
                                     <a href="#save_edit" onclick="setReplyAndTransferData('<s:property value='#item.QUID' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>回复并转移</a>
                                    <br>
                                    <a href="#transform_to" onclick="transferData('<s:property value='#item.QUID' />')" data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>转移</a>
									 </s:if>
									  <br>
  									   <a href="consult/answerDetail.action?questionId=<s:property value="#item.QUID"/>" target="_blank" ><i class="ico-about"></i>查看回复内容</a>
  									   <s:if test="#item.STATUS==1">
  									     
  									     <br><a href="#share_modal" onclick="shareData('<s:property value='#item.QUID' />')"  data-toggle="modal" data-backdrop="static"><i class="ico-about"></i>分享</a>
									   
									   </s:if>
									    <s:if test="#request.ISADMIN==true">
									    <br><a href="javascript:;" onclick="deleteQuestionAndAnswer('<s:property value='#item.QUID' />')"  ><i class="ico-danger"></i>删除</a>
									    <s:if test="#item.STATUS!=2">
									   <br><a href="javascript:;" onclick="closeQuestion('<s:property value='#item.QUID' />')"  ><i class="ico-danger"></i>关闭</a>
							           </s:if>
							            </s:if>
									</td> 
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



<!-- 添加 -->
<div id="save_edit" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">回复</h3>
        </div>
        <form id="saveEditForm" class="form-horizontal parsley-form" method="post"  action="<%=request.getContextPath() %>/backer/system!edtAnswers.action"  data-validate="parsley">
        <input type="hidden" name="paramCondition.quid"  id="quid"/>
        <input type="hidden" name="paramCondition.userid" id="userid">
        <div class="modal-body">
        
            <div class="form-group">
                <label class="col-md-3 control-label">姓名:</label>
                <div class="col-md-7">
                  <p id="quname" class="form-control parsley-validated" ></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>手机:</label>
                <div class="col-md-7">
                   <p id="telnum" class="form-control parsley-validated" ></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>问题:</label>
                <div class="col-md-7">
                    <p id="questionname" ></p>
                </div>
            </div>
                       <div class="panel-heading p-0">
                           <!-- begin nav-tabs -->
                            <div class="tab-overflow">
                                <ul class="nav nav-tabs nav-tabs-inverse">
                                    <li class="active"><a href="#nav-tab-1" data-toggle="tab">文本</a></li>
                                    <li ><a href="#nav-tab-2" data-toggle="tab">图片</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="tab-content" style="padding: 1px">
                           <div class="tab-pane fade active in" id="nav-tab-1">
                                <div class="form-group">
                                     <label class="col-md-3 control-label"><i class="required">*</i>回复:</label>
                                     <div class="col-md-7">
                                     <textarea  name="paramCondition.answercontent" type="text" class="form-control parsley-validated" data-required="true"></textarea>
                                     </div>
                                </div>
                           </div>
                            <div class="tab-pane fade in" id="nav-tab-2">
                        
                             <div class="row">
                              <div class="col-md-3">
                                       <input class="btn btn-default"  type="button" id="delegate_btn_upload_image" value="上传图片" />
                                       <input type="hidden" name="paramCondition.imageSrc" id="imageSrc" />
                              </div>
                             <div class="col-md-4">
                                <a href="javascript:;" class="thumbnail">
                                  <img  src="" id="upload_image" />
                               </a>
                              </div>
                              </div>
                           </div>
                        </div>
                        
                              <div class="form-group" id="transform_to_department" style="display: none;">
                                <label class="col-md-3 control-label"><i class="required"></i>转移:</label>
                                <div class="col-md-7">
                               <select id="reply_depart_id" name="paramCondition.deptid" class="form-control select2-input select2-offscreen" style="width:150px;"   >
                                  <option value="" selected="selected">请选择</option>
                                  <s:iterator value="#request.dictDataList" var="item">
		       		              <option value="<s:property value='#item.departid' />"><s:property value='#item.departname' /></option>
		                         </s:iterator>
                              </select>
                       
                                   </div>
                              </div> 
        </div>
        <div class="modal-footer">
             <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
            <button type="button" class="btn btn-save" onclick="$('#saveEditForm').submit();"><i class="ico-ok"></i>发送</button>
        </div>
        </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--转移--%>
<div id="transform_to" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">转移</h3>
        </div>
        <form id="tranForm" class="form-horizontal parsley-form" method="post"  action="<%=request.getContextPath() %>/backer/system!transformQuestion.action"  data-validate="parsley">
        <input type="hidden" name="paramCondition.quid"  id="quids"/>
        <div class="modal-body">
            <div class="form-group">
                <label class="col-md-3 control-label">姓名:</label>
                <div class="col-md-7">
                  <p id="name" class="form-control parsley-validated" ></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>手机:</label>
                <div class="col-md-7">
                   <p id="tel" class="form-control parsley-validated" ></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>问题:</label>
                <div class="col-md-7">
                    <p id="content"></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>转移:</label>
                <div class="col-md-7">
                <select id="departid" name="paramCondition.deptid" class="form-control select2-input select2-offscreen" style="width:150px;"  data-required="true" >
                    <option value="" selected="selected">请选择</option>
				 <s:iterator value="#request.dictDataList" var="item">
		       		<option value="<s:property value='#item.departid' />"><s:property value='#item.departname' /></option>
		         </s:iterator>
                              </select>
                       
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
            <button class="btn btn-save"><i class="ico-ok"></i>保 存</button>
        </div>
        </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="reply_content" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">回复内容</h3>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>回复内容</label>
                <div class="col-md-7">
                <textarea rows="10" cols=""  class="form-control parsley-validated" 
	                id="replycontent" readonly="readonly"></textarea>
                </div>
            </div>
            <br/><br/>
             <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>回答人</label>
                <div  class="col-md-7">
                  <input id="replyname" type="text" class="form-control parsley-validated" readonly="readonly" >
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required">*</i>回答者部门</label>
                <div  class="col-md-7">
                  <input id="departname" type="text" class="form-control parsley-validated" readonly="readonly" >
                </div>
            </div>
          </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div id="share_modal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title">分享</h3>
        </div>
        <input type="hidden"   id="share_quids"/>
        <div class="modal-body">
            <div class="form-group">
                <label class="col-md-3 control-label">姓名:</label>
                <div class="col-md-7">
                  <p id="share_name" class="form-control parsley-validated" ></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>手机:</label>
                <div class="col-md-7">
                   <p id="share_tel" class="form-control parsley-validated" ></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>问题:</label>
                <div class="col-md-7">
                    <p id="share_content" class="form-control parsley-validated" ></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label"><i class="required"></i>分享至:</label>
                <div class="col-md-7">
                <s:iterator value="#request.dictDataList" var="item">
		       		<input type="checkbox" name="checkbox_department" id="checkbox_<s:property value='#item.departid' />" value="<s:property value='#item.departid' />" /> <label for="checkbox_<s:property value='#item.departid' />"><s:property value='#item.departname' /></label><br/>   
		         </s:iterator>
                       
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-close" data-dismiss="modal">取 消</button>
            <button class="btn btn-save" onclick="shareQuestion();"><i class="ico-ok"></i>保 存</button>
        </div>
       
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

 <div class="modal fade " role="dialog" id="alertModal" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" >警告</h4>
      </div>
      <div class="modal-body">
      <p>
	  </p>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade " role="dialog" id="treeModal" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" >请选择区域</h4>
      </div>
      <div class="modal-body">
      <ul id="treeDemo" class="ztree" ></ul>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-primary" id="btn_clear_area">清空当前</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="btnc01">
<input type="button" value="上传图片" id="btn_upload_image" >
<input type="hidden"  id="upload_url" value="<s:url value='/wechat/media/uploadQyMedia.action?dir=' />" />
<input type="hidden" id="basePath" value="<%=basePath%>">
</div>
</body>
</html>