package com.codi.superman.base.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.codi.base.domain.BaseDomain;

import java.util.Date;

/**
 * 日志审计
 *
 * @author shi.pengyan
 * @date 2016年11月8日 上午10:24:51
 */
public class SysLogAudit extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String logType;

    private Long userId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
