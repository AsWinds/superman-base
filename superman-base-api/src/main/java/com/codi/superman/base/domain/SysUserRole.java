package com.codi.superman.base.domain;

import com.codi.base.domain.BaseDomain;

import java.util.Date;

/**
 * 用户权限
 *
 * @author shi.pengyan
 * @date 2016-12-22 15:10
 */
public class SysUserRole extends BaseDomain {
    private Long roleId;
    private Long userId;
    private String state;

    private Date createDate;

    private Date stateDate;

    private static final long serialVersionUID = 1L;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }
}
