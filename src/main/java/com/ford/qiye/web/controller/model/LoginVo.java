package com.ford.qiye.web.controller.model;

import javax.validation.constraints.NotNull;

/**
 *
 * Created by wanglijun on 16/8/5.
 */
public class LoginVo {
    /***
     * 用户名
     */
    @NotNull(message ="用户名不能为空")
    private String userName;

    /***密码*/
    @NotNull(message ="密码不能为空")
    private String password;


    public LoginVo() {

    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "LoginVo{" +
                "password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
