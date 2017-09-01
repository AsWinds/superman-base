package com.codi.superman.base.domain;


import com.alibaba.fastjson.annotation.JSONField;
import com.codi.base.domain.BaseDomain;

import java.util.Date;

/**
 * 用户模型
 *
 * @author shi.pengyan
 * @date 2016年11月8日 上午10:25:35
 */
public class SysUser extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private String userCode;

    private String pwd;

    private String mobile;

    private String memo;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date userEffDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date userExpDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    private String state;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date stateDate;

    private String isLocked;

    private Date pwdExpDate;

    private String forceLogin;

    private Integer loginFail;

    private Date unlockDate;

    private Long userSrc;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getUserEffDate() {
        return userEffDate;
    }

    public void setUserEffDate(Date userEffDate) {
        this.userEffDate = userEffDate;
    }

    public Date getUserExpDate() {
        return userExpDate;
    }

    public void setUserExpDate(Date userExpDate) {
        this.userExpDate = userExpDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked == null ? null : isLocked.trim();
    }

    public Date getPwdExpDate() {
        return pwdExpDate;
    }

    public void setPwdExpDate(Date pwdExpDate) {
        this.pwdExpDate = pwdExpDate;
    }

    public String getForceLogin() {
        return forceLogin;
    }

    public void setForceLogin(String forceLogin) {
        this.forceLogin = forceLogin == null ? null : forceLogin.trim();
    }

    public Integer getLoginFail() {
        return loginFail;
    }

    public void setLoginFail(Integer loginFail) {
        this.loginFail = loginFail;
    }

    public Date getUnlockDate() {
        return unlockDate;
    }

    public void setUnlockDate(Date unlockDate) {
        this.unlockDate = unlockDate;
    }

    public Long getUserSrc() {
        return userSrc;
    }

    public void setUserSrc(Long userSrc) {
        this.userSrc = userSrc;
    }
}
