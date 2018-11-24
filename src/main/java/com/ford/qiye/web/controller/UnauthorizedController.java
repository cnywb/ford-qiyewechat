package com.ford.qiye.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 未授权页面
 * Created by wanglijun on 16/8/5.
 */
@Controller
public class UnauthorizedController {

    private static final String UNAUTHORIZED="unauthorized";

    @RequestMapping("/unauthorized")
    public String index(){
        return UNAUTHORIZED;
    }

}
