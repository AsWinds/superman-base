package com.codi.superman.base;

import com.codi.base.exception.BaseAppException;
import com.codi.superman.BaseTest;
import com.codi.superman.base.common.Const;
import com.codi.superman.base.domain.SysParam;
import com.codi.superman.base.service.SysParamService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-25 18:59
 */
public class SysParamTest extends BaseTest {

    @Autowired
    private SysParamService sysParamService;

    private Long paramId;
    private String paramCode;

    @Override
    public void before() {
        super.before();
        paramId = 1001L;
        paramCode = "CONFIG_DEVELOPMENT";
    }

    @Test
    public void addTest() throws BaseAppException {
        SysParam sysParam = new SysParam();

        sysParam.setParamCode("CONFIG_DEVELOPMENT");
        sysParam.setParamValue("true");
        sysParam.setDescription("是否开发模式");
        sysParam.setParamText("nidongde");
        sysParam.setParamMoreValue("a=1;b=3");

        sysParamService.addParam(sysParam);
    }

    @Test
    public void modifyTest() throws BaseAppException {
        SysParam sysParam = sysParamService.queryParam(paramCode);
        sysParam.setParamValue("false");
        sysParam.setParamMoreValue("a=b;c=1;");
        sysParam.setDescription("更改了");

        sysParamService.updateParam(sysParam);
    }

    @Test
    public void disableTest() throws BaseAppException {
        sysParamService.disableParam(paramId);
    }

    @Test
    public void enableTest() throws BaseAppException {
        sysParamService.enableParam(paramId);
    }

    @Test
    public void queryTest() throws BaseAppException {
        logger.debug("===================");
        logger.debug("param={}", sysParamService.queryParam(paramCode));
    }

    @Test
    public void queryParamsTest() throws BaseAppException {
        logger.debug("param={}", sysParamService.queryParams(paramCode));
    }

    @Test
    public void queryParams2Test() throws BaseAppException {
        logger.debug("param={}", sysParamService.queryParams(paramCode, Const.STATE_X));
    }
}
