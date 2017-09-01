package com.codi.superman.base.domain;

import com.codi.base.domain.BaseDomain;

import java.util.Date;

/**
 * 用户会话
 *
 * @author shi.pengyan
 * @date 2016年11月8日 上午10:25:56
 */
public class SysUserSession extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String token;

    private Long userId;

    private Date createDate;

    private Date lastUpdateDate;

    private String ua;

    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua == null ? null : ua.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
