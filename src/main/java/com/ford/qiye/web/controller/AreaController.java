package com.ford.qiye.web.controller;

import com.ford.qiye.model.Area;
import com.ford.qiye.model.AreaTree;
import com.ford.qiye.service.AreaService;
import com.ford.qiye.web.controller.model.AreaVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
@Controller
@RequestMapping("/area")
public class AreaController extends AbstractController{

    @Autowired
    private AreaService areaService;

    /**首页*/
    private static final String AREA_RETURN="area/index";

    @RequestMapping("/all")
    @ResponseBody
    public List<AreaTree>  all(){
        return areaService.queryAllArea ();
    }


    @RequestMapping("/index")
    public String index(){
        return AREA_RETURN;
    }


    @RequestMapping("/handler")
    @ResponseBody
    public String handler(AreaVo vo){

        if(vo.getId ()!=null){
            //编辑
            Area area=areaService.findById (vo.getId ());
            area.setCode (vo.getCode ());
            area.setName (vo.getName ());
            area.setRemark (vo.getRemark ());
            area.setIfActive (vo.getIfActive ());
            area.setParentId (vo.getParentId ());
            this.areaService.update (area);
        }else{
            //添加
            Area area=new Area ();
            BeanUtils.copyProperties (vo,area);
            area.setIfActive (1);
            this.areaService.insert (area);
        }
        return "1";
    }



    //获得小区信息
    @RequestMapping("/small")
    @ResponseBody
    public   List<AreaTree> smallArea(Long parentId){
        List<AreaTree> areaTrees= areaService.queryByParentId (parentId);
        return areaTrees;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Long id){
        if(id!=null){
            //执行删除操作
            this.areaService.delete (id);
            return "2";
        }
        return "1";
    }

}
