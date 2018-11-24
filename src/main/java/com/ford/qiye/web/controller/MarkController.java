package com.ford.qiye.web.controller;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.DtMark;
import com.ford.qiye.service.MarkService;
import com.ford.qiye.web.controller.model.MarkVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wanglijun on 16/8/10.
 */
@Controller
@RequestMapping("/mark")
public class MarkController extends AbstractController{

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (MainController.class);

    /**首页*/
    private static final String INDEX_RETURN="mark/index";
    /**首页*/
    private static final String INDEX_RETURN_DO="/mark/index.do";


    @Autowired
    private MarkService markService;


    @RequestMapping("/index")
    public String index(MarkVo vo,Model model){

        PageGrid pageGrid=vo;

        Pagination<DtMark> list=markService.queryPage (pageGrid,vo.getId (),vo.getName ());

        model.addAttribute ("page",list);

        return INDEX_RETURN;
    }

    @RequestMapping("user")
    @ResponseBody
    public List<DtMark> mark(String userId){

        if(StringUtils.isNotEmpty (userId)){
           return this.markService.findByUserId (userId);
        }
        return null;
    }





    @RequestMapping("/handler")
    public String add(MarkVo vo,Model model) {

        if(vo.getId ()==null){
            //新增
            DtMark mark=new DtMark (vo.getName (),DtMark.VALID);
            markService.insert (mark);
        }else{
            //编辑
            DtMark dtMark=markService.findById (vo.getId ());
            if(dtMark!=null){
                dtMark.setName (vo.getName ());
                markService.update (dtMark);
            }
        }
        return this.redirect (INDEX_RETURN_DO);
    }

    @RequestMapping("/delete")
    public String delete(MarkVo vo,Model model) {

        if(vo.getId ()!=null){
            markService.deleteById (vo.getId ());
        }
        return this.redirect (INDEX_RETURN_DO);
    }
}
