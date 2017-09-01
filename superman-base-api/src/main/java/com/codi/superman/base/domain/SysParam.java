package com.codi.superman.base.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.codi.base.domain.BaseDomain;

import java.util.Date;

/**
 * 参数模型
 *
 * @author spy
 * @date 2016-12-25 17:16
 */
public class SysParam extends BaseDomain {
    private Long id;

    private String paramCode;

    private String paramValue;

    private String paramText;

    private String paramMoreValue;

    private String state;

    private String description;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    public String getParamText() {
        return paramText;
    }

    public void setParamText(String paramText) {
        this.paramText = paramText == null ? null : paramText.trim();
    }

    public String getParamMoreValue() {
        return paramMoreValue;
    }

    public void setParamMoreValue(String paramMoreValue) {
        this.paramMoreValue = paramMoreValue == null ? null : paramMoreValue.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
}
