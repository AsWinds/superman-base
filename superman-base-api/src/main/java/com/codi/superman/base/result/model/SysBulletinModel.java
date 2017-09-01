package com.codi.superman.base.result.model;

import com.codi.superman.base.domain.SysBulletin;

/**
 * Created by 周翔 on 2017/4/18.
 */
public class SysBulletinModel extends SysBulletin {
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
