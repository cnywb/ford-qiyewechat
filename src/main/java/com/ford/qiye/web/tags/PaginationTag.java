package com.ford.qiye.web.tags;

import com.ford.qiye.common.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by wanglijun on 16/8/7.
 */
public class PaginationTag extends TagSupport {

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (PaginationTag.class);

    /**分页信息*/
    private Pagination pagination;

    /**查询Form的Id*/
    private String formId;

    /**Action*/
    private String action="index.do";


    @Override
    public int doStartTag() throws JspException {


        JspWriter out = this.pageContext.getOut();

        HttpServletRequest  request=(HttpServletRequest) this.pageContext.getRequest ();


        logger.info ("logger:{}", request.getPathInfo ());


        StringBuffer rt = new StringBuffer();

        if(action.indexOf ("?")>0){
            action=action+"&pageIndex=";
        }else{
            action="?pageIndex=";
        }


        rt.append("<div class='row area-table-bar'><div class='area-pager'>");
        rt.append("&nbsp;&nbsp;<SPAN class='current'>共有&nbsp;"+ pagination.getTotal () + "&nbsp;条记录");
        rt.append(",&nbsp;当前第&nbsp;" + pagination.getPageIndex () + "/"
                + pagination.getTotalPage () + "&nbsp;页&nbsp;</span>");

        if (this.pagination.getTotalPage () > 0) {
            rt.append(" <ul class='pagination pagination-sm'>");

            // 上一页
            if ((this.pagination.getPageIndex() > 1) && (this.pagination.getTotalPage () > 1)) {
                rt.append("<li><a href='#'");
                rt.append(" onclick='javascript:onPage("+(this.pagination.getPageIndex() - 1)+");'>");
            } else {
                rt.append("<li><a  class='disabled'>");
            }

            rt.append("上一页");
            if ((this. pagination.getPageIndex() > 1) && (this.pagination.getTotalPage () > 1))
                rt.append("</a></li>");
            else {
                rt.append("</a></li>");
            }
            // 当前页
            rt.append(" <li class='active'><a href=''>" +this.pagination.getPageIndex()
                    + "</a></li>");

            // 下一页
            if (this. pagination.getPageIndex() <this.pagination.getTotalPage ()) {
                rt.append("<li><a href='");
                rt.append("#");
                rt.append("'onclick='javascript:onPage("+(this.pagination.getPageIndex()+1) + ");'>");
            } else {
                rt.append("<li><a  class='disabled'>");
            }
            rt.append("下一页");
            if (this. pagination.getPageIndex() < this.pagination.getTotalPage ())
                rt.append("</a></li>");
            else {
                rt.append("</a></li>");
            }
            rt.append(" </ul>");
            rt.append("<div class='pagination-jump'>");
            // 跳转页面
            rt .append("<input id='pagerSelect' name='pagerSelect'  class='form-control'/>");
            rt.append("<input type='button' value='跳转'  onclick='javascript:gotoPage();'  class='btn' />");
            rt.append("<script type='text/javascript'>");
            rt.append("function gotoPage(){ var pageIndex=$('#pagerSelect').val();$('#searchForm').attr('action','"+action+"'+pageIndex); $('#searchForm').submit(); }");
            rt.append("function onPage(pageIndex){$('#searchForm').attr('action','"+action+"'+pageIndex); $('#searchForm').submit(); }");
            rt.append("</script>");
            rt.append("</div>");
            rt.append("</div>");


        }
        try {
            out.print (rt.toString ());
        } catch (IOException e) {
            e.printStackTrace ();
        }

        return SKIP_BODY;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }



    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
