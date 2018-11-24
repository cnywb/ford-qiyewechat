package com.ford.qiye.web.controller;

import com.ford.qiye.model.DtUser;
import com.ford.qiye.web.converter.StringEscapeEditor;
import com.fute.common.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by wanglijun on 16/8/5.
 */
public abstract class AbstractController {


    /**管理员*/
    protected static final String ROLE_ADMIN="管理员";

    /**JsonView*/
    protected final String JSON_VIEW="jsonView";
    /**excelView*/
    protected final String EXCEL_VIEW="reportExcelView";
    /**参数名称*/
    protected static final String FILENAME="fileName";
    /**添加成功**/
    protected static final String ADD_SUCCESS_MSG="";
    /**JSON字符串*/
    protected final String STR_JSON_VIEW="stringJsonView";
    /**JSON字符串参数属性名称*/
    protected final String STR_JSON__KEY="json";


    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor (
                new SimpleDateFormat (DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DateUtils.FORMAT_DATE_YYYY_MM_DD), true));
        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor (true,false));
    }


    /***
     * 返回Excel视图
     * @param modelAndView
     * @return ModelAndView
     */
    protected ModelAndView getExcelView(ModelAndView modelAndView){
        modelAndView.setViewName(EXCEL_VIEW);
        return modelAndView;
    }


    /**
     * 用户跳转view页面
     * @param folder   路径
     * @param jspName 名称(不加后缀)
     * @return 指定view页面
     */
    public String redirectJsp(@PathVariable String folder,
                              @PathVariable String jspName) {
        return "/" + folder + "/" + jspName;
    }

    /***
     * 转发到URL
     * @param url 转发到的URL
     * @return 转发路径
     */
    public String forward(String url){
        StringBuilder sb=new StringBuilder();
        sb.append("forward:");
        sb.append(url);
        return sb.toString();
    }


    /***
     * 重定向到相关页面
     * @param url
     * @return
     */
    protected String redirect(String url){
        StringBuilder sb=new StringBuilder();
        sb.append("redirect:");
        sb.append(url);
        return sb.toString();
    }


    protected String getUserId(){
         DtUser dtUser=getUser ();
        if(dtUser!=null){
            return dtUser.getId ();
        }
        return StringUtils.EMPTY;
    }




    protected Long departId(){
        DtUser dtUser=getUser ();
        if(dtUser!=null){
            return dtUser.getDepartId ();
        }
        return null;
    }

    /***
     * 是否是管理员
     * @param params
     * @return
     */
    protected  Boolean isAdmin(Map<String,String> params){
        DtUser dtUser=getUser ();
        if(dtUser!=null&&params.containsKey (ROLE_ADMIN)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }



    protected DtUser getUser(){
        Subject currentUser = SecurityUtils.getSubject();
        if( currentUser.getPrincipal() instanceof DtUser ) {
            return ( (DtUser)currentUser.getPrincipal ());
        }
        return null;
    }
    protected String getBasePath(HttpServletRequest request){

          return "http://"+request.getServerName();
    }
}
