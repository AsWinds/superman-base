package com.codi.superman.base.util;

import com.codi.superman.base.domain.SysUser;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-01-12 14:34
 */
public class BuilderTest {
    @Test
    public void stringBuilderTest() {
        SysUser user = new SysUser();
        user.setUserId(1000L);
        user.setUserName("username");
        user.setPwd("123123123");

        System.out.println(ToStringBuilder.reflectionToString(user));
        System.out.println(user);
    }
}
