package com.ford.qiye.web.controller.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 密码修改
 * Created by wanglijun on 16/8/6.
 */
public class PasswordVo {


    @NotEmpty(message = "新密码不能为空")
    private String password;

    @NotEmpty(message = "旧密码不能为空")
    private String confirmPassword;


    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
