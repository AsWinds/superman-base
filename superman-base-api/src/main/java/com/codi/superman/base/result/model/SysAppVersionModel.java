package com.codi.superman.base.result.model;

import com.codi.superman.base.domain.SysAppVersion;

/**
 * sys App Version
 *
 * @author shi.pengyan
 * @date 2017-01-09 15:57
 */
public class SysAppVersionModel extends SysAppVersion {
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
