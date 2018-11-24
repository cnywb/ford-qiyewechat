package com.ford.qiye.model;

import java.util.Date;

/**
 * Created by wanglijun on 16/8/7.
 */
public class DtMessage {
    /***
     * 内容类型为:1 文本
     */
    public static final Integer CONTENT_TYPE_TEXT=1;
    /***
     * 内容类型为:2 图片
     */
    public static final Integer CONTENT_TYPE_IMAGE=2;

    /**ID*/
    private Long id;
    /**消息内容*/
    private String content;
    /**消息创建时间*/
    private Date createTime;
    /**用户ID*/
    private String userId;
    /**状态*/
    private Integer  status;
    /**内容类型*/
    private Integer contentType;
    /**职位*/
    private String position;
    /**经销商销售代码*/
    private String  sellCode;
    /**经销商服务代码*/
    private String serveCode;
    /**大区代码*/
    private String areaName;
    /**小区代码*/
    private String smallName;
    /**真实姓名*/
    private String realName;
    /**头像*/
    private String headImage;
    /**消息图片ID*/
    private Long imageId;
    /**消息标题*/
    private String  imageTitle;
    /**消息标题*/
    private String imgContent;
    /**图片*/
    private String  image;
    /**作者*/
    private String author;
    /**URL*/
    private String url;
    /**appendIx*/
    private String appendix;
    /***消息创建时间*/
    private Date imageCreateTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getAppendix() {
        return appendix;
    }

    public void setAppendix(String appendix) {
        this.appendix = appendix;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImgContent() {
        return imgContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }

    public Date getImageCreateTime() {
        return imageCreateTime;
    }

    public void setImageCreateTime(Date imageCreateTime) {
        this.imageCreateTime = imageCreateTime;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSellCode() {
        return sellCode;
    }

    public void setSellCode(String sellCode) {
        this.sellCode = sellCode;
    }

    public String getServeCode() {
        return serveCode;
    }

    public void setServeCode(String serveCode) {
        this.serveCode = serveCode;
    }

    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
