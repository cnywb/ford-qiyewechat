package com.ford.qiye.web.tags;

import com.ford.qiye.model.DtFunction;
import com.ford.qiye.model.DtUser;
import com.ford.qiye.service.FunctionService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by wanglijun on 16/8/6.
 */
public class MenuTag extends TagSupport {




    @Override
    public int doStartTag() throws JspException {

        FunctionService functionService=ContextLoader.getCurrentWebApplicationContext().getBean ("functionService",FunctionService.class);

        String userId=this.getUserId ();

        List<DtFunction> menus=functionService.queryMenuByUserId (userId);

        if(CollectionUtils.isEmpty (menus)){
            return SKIP_BODY;
        }

        JspWriter out = this.pageContext.getOut();

        String context= this.pageContext.getServletContext ().getContextPath ();

        StringBuilder sb=new StringBuilder ();

        for(DtFunction menu:menus){
            if(menu.getParentId ()==0){
                sb.append ("<li class=\"").append (menu.getRemark ()).append (" dropdown\">");
                sb.append ("<a href=\"javascript:void(0);\"> <i class=\"ico-gear\"></i>").append (menu.getName ()).append ("<span class=\"caret\"></span></a>");
                for(DtFunction subMenu:menus){
                    if(subMenu.getParentId ()==menu.getId ()) {
                        sb.append (" <ul class=\"sub-nav\">");
                        sb.append (" <li class=\" ").append (subMenu.getRemark ()).append (" \">");
                        sb.append (" <a href=\"").append (context).append (subMenu.getLinkUrl ()).append ("\">").append (subMenu.getName ()).append ("</a>");
                        sb.append ("</li>");
                        sb.append ("</ul>");
                    }
                }
                sb.append ("</li>");
            }
        }
        try {

            out.print (sb.toString ());
        } catch (IOException e) {
            e.printStackTrace ();
        }

        return SKIP_BODY;
    }

    protected String getUserId(){
        DtUser dtUser=getUser ();
        if(dtUser!=null){
            return dtUser.getId ();
        }
        return StringUtils.EMPTY;
    }


    protected DtUser getUser(){
        Subject currentUser = SecurityUtils.getSubject();
        if( currentUser.getPrincipal() instanceof DtUser ) {
            return ( (DtUser)currentUser.getPrincipal ());
        }
        return null;
    }
}
