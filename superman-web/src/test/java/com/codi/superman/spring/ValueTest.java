package com.codi.superman.spring;

import com.codi.superman.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-02-08 9:15
 */
public class ValueTest extends BaseTest {

    @Value("${dubbo.protocol.name}")
    private String dubboProtocolName;

    @Test
    public void valueTest() {
        System.out.println("dubbo protocol name is " + dubboProtocolName);
    }
}
