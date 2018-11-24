package com.ford.qiye.model;

/**用户权限角色列表
 * Created by wanglijun on 16/12/4.
 */
public class UserAuthList{

    /**用户名*/
    private String userName;

    /**真实姓名*/
    private String realName;

    /**角色名称*/
    private String roleName;

    /**资源名称*/
    private String functionName;

    /**路径*/
    private String linkUrl;

    /**顺序*/
    private Long  priority;

    /**备注*/
    private String remark;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
