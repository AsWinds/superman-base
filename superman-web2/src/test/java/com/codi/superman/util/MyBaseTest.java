package com.codi.superman.util;

import com.codi.base.domain.BaseResult;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-22 13:48
 */
public class MyBaseTest {

    @Test
    public void baseResultTest() {
        BaseResult result = new BaseResult();
        System.out.println(result);
        System.out.println(System.getProperty("user.name"));
    }

    @Test
    public void test() throws IOException {
        System.out.println(this.getClass().getClassLoader().getResource("i18n/"));

        List<String> strings = IOUtils.readLines(this.getClass().getClassLoader().getResourceAsStream("i18n/"), "UTF-8");
        System.out.println(strings);
//        System.out.println(IOUtils.readLines(this.getClass().getResourceAsStream("i18n"), "UTF-8"));

    }

}
