package com.codi.superman.base.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.codi.base.domain.BaseDomain;

import java.util.Date;

/**
 * 亲，写个类注释呗
 *
 * @author 周翔
 * @date 2017-04-07 11:21
 */
public class SysCommonGroup extends BaseDomain {
    private Long groupId;

    private String groupName;

    private String bizGroupCode;

    private String bizCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private String description;

    private static final long serialVersionUID = 1L;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode == null ? null : bizCode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBizGroupCode() {
        return bizGroupCode;
    }

    public void setBizGroupCode(String bizGroupCode) {
        this.bizGroupCode = bizGroupCode == null ? null : bizGroupCode.trim();
    }
}
