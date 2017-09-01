package com.codi.superman.util;

import com.codi.base.i18n.ResUtil;
import com.codi.superman.BaseTest;
import com.codi.superman.base.common.ErrorConst;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-22 14:32
 */
public class ResUtilTest extends BaseTest {

    @Test
    public void test() {
        System.out.println(ResUtil.get(ErrorConst.USER_SESSION_TIMEOUT));
        System.out.println(ResUtil.get("1022"));
    }
}
