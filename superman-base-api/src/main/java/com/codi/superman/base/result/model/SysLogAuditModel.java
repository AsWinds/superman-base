package com.codi.superman.base.result.model;

import com.codi.superman.base.domain.SysLogAudit;

/**
 * 系统日志模型
 *
 * @author shi.pengyan
 * @date 2017-01-09 15:34
 */
public class SysLogAuditModel extends SysLogAudit {

    private String userName;
    private String userCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
