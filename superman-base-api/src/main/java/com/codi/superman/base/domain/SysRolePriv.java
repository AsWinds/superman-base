package com.codi.superman.base.domain;

import com.codi.base.domain.BaseDomain;

import java.util.Date;

/**
 * 角色权限
 *
 * @author shi.pengyan
 * @date 2016-12-22 15:11
 */
public class SysRolePriv extends BaseDomain {
    private Long roleId;
    private Long privId;
    private String state;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivId() {
        return privId;
    }

    public void setPrivId(Long privId) {
        this.privId = privId;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
