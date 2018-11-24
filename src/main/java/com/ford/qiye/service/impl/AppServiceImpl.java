package com.ford.qiye.service.impl;

import com.ford.qiye.dao.DtAppMapper;
import com.ford.qiye.model.DtApp;
import com.ford.qiye.service.AppService;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Created by wanglijun on 16/8/17.
 */
@Service
public class AppServiceImpl implements AppService {


    @Autowired
    private DtAppMapper appMapper;

   public  DtApp  findByDepartId(Long departId){
       List<DtApp> apps=appMapper.findByDepartId (departId);
       if(CollectionUtils.isEmpty (apps)){
           return  null;
       }
       return apps.get (0);
    }
}
