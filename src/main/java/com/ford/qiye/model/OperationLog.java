package com.ford.qiye.model;


import java.util.Date;

/**
 * 描述:LogSecurity
 * 操作权限 记录日志
 *
 * @author ziv.hung create on 16/8/25
 * @since 1.0
 */

public class OperationLog {



    /**ID*/
    protected Long id;
    /**用户名*/
    protected String userName;
    /**真实姓名*/
    private String realName;

    /**操作类型*/
    protected OperationType operationType;

    /**操作内容*/
    protected String operationContent;

    /**操作日期*/
    protected Date operationDate;


    public OperationLog() {
        super();
    }

    public static OperationLog instance(OperationType operationType) {
        OperationLog logSecurity = new OperationLog ();
        logSecurity.setOperationDate(new Date());
        logSecurity.setOperationType (operationType);
        return logSecurity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", operationType=" + operationType +
                ", operationContent='" + operationContent + '\'' +
                ", operationDate=" + operationDate +
                '}';
    }
}
