package com.ford.qiye.web.controller;

import com.alibaba.fastjson.JSON;
import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.*;
import com.ford.qiye.service.AreaService;
import com.ford.qiye.service.MaterialService;
import com.ford.qiye.service.TaskService;
import com.ford.qiye.web.controller.model.MaterialDetailVo;
import com.ford.qiye.web.controller.model.MaterialDownloadVo;
import com.ford.qiye.web.controller.model.MaterialHandlerVo;
import com.ford.qiye.web.controller.model.MaterialVo;
import com.fute.common.util.DateUtils;
import com.fute.wechat.service.message.MessageService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by wanglijun on 16/8/9.
 */
@Controller
@RequestMapping("/material")
public class MaterialController extends AbstractController{

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (MainController.class);

    /**首页*/
    private static final String INDEX_RETURN="material/index";
    /**下载明细*/
    private static final String DOWNLOAD_RETURN="material/download";
    /**下载明细*/
    private static final String DETAIL_RETURN="material/detail";


    @Resource
    private MessageService messageService;

    @Autowired
    private MaterialService materialService;
    @Autowired
    private AreaService areaService;

    /***
     * 任务下载
     */
    @Autowired
    private TaskService taskService;

    @RequestMapping("/index")
    public String index(@ModelAttribute("vo") MaterialVo vo, Model model){

        PageGrid pageGrid=vo;

        Date startDate= DateUtils.parser (vo.getStartDate ()+" 00:00:00",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Date endDate= DateUtils.parser (vo.getEndDate ()+" 23:59:59",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Pagination<DtMaterial>  list= materialService.queryPage (pageGrid,vo.getName (),vo.getStatus (),vo.getDepartId (),startDate,endDate);

        model.addAttribute ("page",list);


        //查询大区区域信息
        List<AreaTree> areas=areaService.queryByParentId (0L);

        model.addAttribute ("areas",areas);

        return INDEX_RETURN;
    }

    /**新增*/
    @RequestMapping("/handler")
    @ResponseBody
    public String handler(MaterialHandlerVo vo,Error errors,HttpServletRequest request){

        logger.info ("vo:{}",vo.toString ());
        List<String>  serveCodes=this.parse(vo.getServeCode ());
        List<String>  sellCodes=this.parse(vo.getSellCode ());
        //保存物料信息
        DtMaterial dtMaterial=new DtMaterial ();
        dtMaterial.setName (vo.getFileName ());
        dtMaterial.setSaveUrl (vo.getFilePath ());
        dtMaterial.setUserId (this.getUserId ());
        dtMaterial.setContent (vo.getContent ());
        dtMaterial.setAreaName(vo.getAreaName ());
        dtMaterial.setSmallName (this.getUser ().getSmallName ());
        dtMaterial.setSellCode (this.getUser ().getSellCode ());
        dtMaterial.setServeCode (this.getUser ().getServeCode ());
        dtMaterial.setSmallName (this.getUser ().getSmallName ());
        dtMaterial.setDepartId (this.departId ());
        dtMaterial.setDownload (0);
        dtMaterial.setStatus (DtMaterial.STATUS_INUSE);
        dtMaterial.setTimestamp (DateUtils.formatDate (new Date (),DateUtils.FORMAT_DATETIME_YYYYMMDDHHMMSSSSS));
        //保存物料信息
        Long materialId=this.materialService.insert(dtMaterial);



        vo.setRealName (this.getUser ().getRealName ());
        vo.setBasePath (this.getBasePath (request));
        //下发任务列表
        DtTask dtTask=new DtTask ();
        dtTask.setStatus (DtTask.STATUS_PENDING);
        dtTask.setDataType (TaskDataType.MATERIAL);
        dtTask.setBusinessId (materialId);
        dtTask.setParams (JSON.toJSONString (vo));
        dtTask.setRemark ("物料下发任务");

        taskService.insert (dtTask);

        return "1";
    }

    private void sendMessage(String userId,String content){
        try {
            messageService.sendQyMessage ("text", userId, "0", content);
        }catch (Exception e){
            logger.error (e.getMessage (),e);
        }
    }


    private List<Map<String,Object>> buildParams(Map<String,Object> queryParams,MaterialHandlerVo vo,List<String> serveCodes,List<String> sellCodes){
        List< Map<String,Object>> queryList=new ArrayList<> ();

        if(!CollectionUtils.isEmpty (vo.getSmallAreaIds ())&&!CollectionUtils.isEmpty (serveCodes)&&!CollectionUtils.isEmpty (sellCodes)){
            //销售-小区,经销商服务代码,经销商销售码
            for(String smallArea:vo.getSmallAreaIds ()){
                queryParams.put ("smallArea",smallArea);
                queryList=this.dealerCode (queryList,queryParams,serveCodes,sellCodes);
            }
        }else if(!CollectionUtils.isEmpty (vo.getSmallAreaIds ())&&!CollectionUtils.isEmpty (serveCodes)){
            //销售-小区,经销商服务代码,
            for(String smallArea:vo.getSmallAreaIds ()){
                queryParams.put ("smallArea",smallArea);
                for(String serveCode:serveCodes){
                    queryParams.put ("serveCode",serveCode);
                    queryList.add (queryParams);
                }
            }

        }else if(!CollectionUtils.isEmpty (vo.getSmallAreaIds ())&&!CollectionUtils.isEmpty (sellCodes)){
            //销售-小区,经销商销售代码
            for(String smallArea:vo.getSmallAreaIds ()){
                queryParams.put ("smallArea",smallArea);
                for(String sellCode:sellCodes) {
                    queryParams.put ("sellCode", sellCode);
                    queryList.add (queryParams);
                }
            }
        }else if(!CollectionUtils.isEmpty (vo.getSmallAreaIds ())){
            //只有小区
            for(String smallArea:vo.getSmallAreaIds ()){
                queryParams.put ("smallArea",smallArea);
                queryList.add (queryParams);
            }
        }else if(!CollectionUtils.isEmpty (serveCodes)&&!CollectionUtils.isEmpty (sellCodes)){
            //经销商服务代码,经销商销售码
            queryList=this.dealerCode (queryList,queryParams,serveCodes,sellCodes);
        }else if(!CollectionUtils.isEmpty (serveCodes)){
            //经销商服务代码,
            for(String serveCode:serveCodes){
                queryParams.put ("serveCode",serveCode);
                queryList.add (queryParams);
            }
        }else if(!CollectionUtils.isEmpty (sellCodes)) {
            //经销商销售码
            for (String sellCode : sellCodes) {
                queryParams.put ("sellCode", sellCode);
                queryList.add (queryParams);
            }
        }
        return queryList;
    }
    /***
     *
     * @param list
     * @param params
     * @param serveCodes
     * @param sellCodes
     * @return
     */
    private List<Map<String,Object>>  dealerCode(List< Map<String,Object>> list,Map<String,Object> params,List<String> serveCodes,List<String> sellCodes){
        for(String serveCode:serveCodes){
            params.put ("serveCode",serveCode);
            for(String sellCode:sellCodes){
                params.put ("sellCode",sellCode);
                list.add (params);
            }
        }
        return list;
    }

    private List<String> parse(String code) {
        List<String> list=new ArrayList<> ();
        if(StringUtils.isEmpty (code)){
           return list;
        }
        StringTokenizer st = new StringTokenizer(code, ";");
        while (st.hasMoreElements()) {
            list.add(st.nextToken());
        }
        return list;
    }


    /**
     * @param id
     * @param status
     * @param model
     * @return
     */
    @RequestMapping("/status")
    @ResponseBody
    public String  updateStaus(Long id, Integer status,Model model){
        if(id!=null&&status!=null){
            this.materialService.updateStatus (id,status);
            return "1";
        }
        return "0";
    }



    @RequestMapping("/downloadFile")
    public void downloadFile(Long id, HttpServletRequest request, HttpServletResponse response){
        String filePath=null;
        String fileName=null;
        if(id!=null){
            DtMaterial material=materialService.findById (id);
            if(material==null){
                return;
            }
            fileName=material.getName ();
            filePath=material.getSaveUrl ();
            DtMaterialUser materialUser=new DtMaterialUser ();
            materialUser.setMaterialId (id);
            materialUser.setUserId (getUserId ());
            materialUser.setIsDownload (1);
            //保存下载记录
            this.materialService.saveMaterialUser(materialUser);
            //下载次数
            this.materialService.updateDownload(id);
        }
        //执行文件下载
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+ fileName);
        OutputStream os=null;
        InputStream inputStream=null;
        try {


            inputStream = new FileInputStream (new File(filePath));
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }


        } catch (FileNotFoundException e) {
           logger.error (e.getMessage (),e);
        } catch (IOException e) {
            logger.error (e.getMessage (),e);
        }finally {
            IOUtils.closeQuietly (os);
            IOUtils.closeQuietly (inputStream);
        }
    }

    /***
     * 下载明细
     * @param vo
     * @param model
     * @return
     */
    @RequestMapping("/download")
    public String download(@ModelAttribute("vo")MaterialDownloadVo vo, Model model){

        PageGrid pageGrid=vo;

        Date startDate= DateUtils.parser (vo.getStartDate ()+" 00:00:00",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Date endDate= DateUtils.parser (vo.getEndDate ()+" 23:59:59",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Pagination<DtUser>  list= materialService.findMaterialByUser (pageGrid,vo.getRealName(),startDate,endDate,vo.getMaterialId (),DtMaterialUser.DOWNLOAD_YES);

        model.addAttribute ("page",list);

        return DOWNLOAD_RETURN;
    }

    @RequestMapping("/detail")
    public String detail(@ModelAttribute("vo")MaterialDetailVo vo,Model model){
        PageGrid pageGrid=vo;

        Date startDate= DateUtils.parser (vo.getStartDate ()+" 00:00:00",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Date endDate= DateUtils.parser (vo.getEndDate ()+" 23:59:59",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Pagination<DtUser>  list= materialService.findUserByMaterialId (pageGrid,vo.getRealName(),startDate,endDate,vo.getMaterialId (),null,DtMaterialUser.DOWNLOAD_NO);

        model.addAttribute ("page",list);

        return  DETAIL_RETURN;
    }
}
