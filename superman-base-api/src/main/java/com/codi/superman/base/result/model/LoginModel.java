package com.codi.superman.base.result.model;

import com.codi.base.domain.BaseDomain;

/**
 * 登陆返回结果
 *
 * @author shi.pengyan
 * @date 2017-01-09 10:15
 */
public class LoginModel extends BaseDomain {

    private boolean isLogin;
    private String userCode;
    private String userName;
    private String token;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
