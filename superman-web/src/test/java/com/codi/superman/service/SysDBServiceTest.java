package com.codi.superman.service;

import com.codi.superman.BaseTest;
import com.codi.superman.base.domain.SysBanner;
import com.codi.superman.base.service.SysBannerService;
import com.codi.superman.base.service.SysDBService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-02-04 15:32
 */
public class SysDBServiceTest extends BaseTest {

    @Autowired
    private SysDBService sysDBService;

    @Autowired
    private SysBannerService sysBannerService;
    private static final String TABLE_NAME = "SYS_USER";

/*    @Test
    public void testCount() {
        //SYS_USER 表主键是USER_ID
        println(sysDBService.getCount(TABLE_NAME, "USER_ID"));
        println(sysDBService.getCount(TABLE_NAME, "USER_ID", "USER_ID", "1002"));
    }

    @Test
    public void testIsUnique() {
        println(sysDBService.isUnique(TABLE_NAME, "USER_ID", "-1"));
        println(sysDBService.isUnique(TABLE_NAME, "USER_ID", "1002"));
        println(sysDBService.isUnique(TABLE_NAME, "USER_CODE", "admin", "USER_ID", "1"));
    }*/


}
