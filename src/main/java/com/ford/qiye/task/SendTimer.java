package com.ford.qiye.task;

import com.alibaba.fastjson.JSON;
import com.ford.qiye.common.MessageTemplate;
import com.ford.qiye.model.*;
import com.ford.qiye.service.MaterialService;
import com.ford.qiye.service.TaskService;
import com.ford.qiye.web.controller.model.MaterialHandlerVo;
import com.fute.common.util.DateUtils;
import com.fute.wechat.service.message.MessageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wanglijun on 16/9/23.
 */
public class SendTimer  {
    /***
     * 日志
     */
    private  static final Logger logger= LoggerFactory.getLogger (SendTimer.class);

    @Autowired
    private MaterialService materialService;

    @Resource
    private MessageService messageService;

    /***
     * 任务下载
     */
    @Autowired
    private TaskService taskService;

    public void sendMaterial(){

        logger.info ("物料下发......");

        List<DtTask>  tasks=taskService.queryByStatus (DtTask.STATUS_PENDING, TaskDataType.MATERIAL);
        for(DtTask task:tasks){

            //更新状态:处理中

            String batchNo= DateUtils.formatDate (new Date(),DateUtils.FORMAT_DATETIME_YYYYMMDDHHMMSSSSS);

            taskService.updateById (task.getId (),DtTask.STATUS_HANDLING,batchNo,new Date ());

            //调用业务处理
            this.handing (task);
            //将状态更新为:处理完成;
            taskService.updateById (task.getId (),DtTask.STATUS_FINISHED,null,new Date ());
        }
    }

    /***
     *
     * @param task
     */
    private void handing(DtTask task){

        if(StringUtils.isEmpty (task.getParams ())){
            logger.info ("参数为为空");
            return;
        }

        MaterialHandlerVo vo=JSON.parseObject (task.getParams (),MaterialHandlerVo.class);
        if(vo==null){
            logger.info ("参数转为对象为空");
            return;
        }

        logger.info ("vo:{}",vo.toString ());
        List<String>  serveCodes=this.parse(vo.getServeCode ());
        List<String>  sellCodes=this.parse(vo.getSellCode ());
        //保存物料信息
        DtMaterial dtMaterial =this.materialService.findById (task.getBusinessId ());

        Map<String,Object> queryParams=new HashMap<> ();
        //查询条件
        if(vo.getAreaName ()==null) {
            queryParams.put ("areaName", vo.getAreaName ());
        }
        //职位
        if(StringUtils.isNotEmpty (vo.getPosition ())){
            queryParams.put ("position", vo.getPosition ());
        }
        queryParams.put ("realName",null);
        queryParams.put ("startDate",null);
        queryParams.put ("endDate",null);
        queryParams.put ("endDate",null);
        queryParams.put ("materialId",null);
        //查询条件列表
        List< Map<String,Object>> queryList=this.buildParams (queryParams,vo,serveCodes,sellCodes);
        //遍历条件
        for(Map<String,Object> params:queryList){
            //查询用户数据
            List<DtUser>  users=this.materialService.findMaterialByUser(params);
            for(DtUser user:users){
                String context= MessageTemplate.format (MessageTemplate.MSG_MATERIAL,vo.getRealName (),vo.getFileName (),vo.getBasePath ());
                DtMaterialUser materialUser=this.materialService.findByUserId (task.getBusinessId (),user.getId ());
                if(materialUser!=null){
                    continue;
                }
                //物料下载
                materialUser=new DtMaterialUser ();
                materialUser.setMaterialId(task.getBusinessId ());
                materialUser.setUserId (user.getId ());
                materialUser.setIsDownload (DtMaterialUser.DOWNLOAD_NO);
                //保存下载记录
                this.materialService.saveMaterialUser(materialUser);
                //发送消息
                this.sendMessage (user.getId (),context);
            }
        }
    }

    private void sendMessage(String userId,String content){
        try {
            messageService.sendQyMessage ("text", userId, "0", content);
        }catch (Exception e){
            logger.error (e.getMessage (),e);
        }
    }




    private List<Map<String,Object>> buildParams(Map<String,Object> queryParams,MaterialHandlerVo vo,List<String> serveCodes,List<String> sellCodes){
        List<Map<String,Object>> queryList=new ArrayList<> ();

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
}
