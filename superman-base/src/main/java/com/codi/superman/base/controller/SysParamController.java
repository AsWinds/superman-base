package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.base.util.Assert;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.domain.SysParam;
import com.codi.superman.base.result.SysParamResult;
import com.codi.superman.base.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 参数 controller
 *
 * @author shi.pengyan
 * @date 2016-12-26 9:40
 */
@RestController
@RequestMapping("/sys/param")
public class SysParamController extends BaseController {

    @Autowired
    private SysParamService sysParamService;


    /**
     * 添加param
     *
     * @param sysParam
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(path = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:param:add")
    public BaseResult addParam(SysParam sysParam) throws BaseAppException {
        Assert.notNull(sysParam);
        sysParamService.addParam(sysParam);
        BaseResult result = new BaseResult();
        result.setResult(sysParam);
        return result;
    }

    /**
     * 更新 param
     *
     * @param sysParam
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(path = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:param:update")
    public BaseResult updateParam(SysParam sysParam) throws BaseAppException {
        Assert.notNull(sysParam);
        Assert.notNull(sysParam.getId());

        sysParamService.updateParam(sysParam);

        BaseResult result = new BaseResult();
        return result;
    }

    /**
     * 删除 param
     *
     * @param paramId
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(path = "delete/{paramId}", method = RequestMethod.DELETE)
    @RequiresPermissions("sys:param:delete")
    public BaseResult delParam(@PathVariable(value = "paramId") Long paramId) throws BaseAppException {

        sysParamService.delParam(paramId);

        BaseResult result = new BaseResult();
        return result;
    }


    /**
     * 查询总数
     *
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("/paramsCount")
    @RequiresPermissions("sys:param:query")
    public BaseResult getParamCount() throws BaseAppException {
        BaseResult result = new BaseResult();
        result.setResult(sysParamService.queryParamsCount());
        return result;
    }

    /**
     * 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BaseAppException
     */
    @RequestMapping("/params")
    @RequiresPermissions("sys:param:query")
    public SysParamResult getParams(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) throws BaseAppException {
        List<SysParam> sysParams = sysParamService.queryParams(pageIndex, pageSize);

        SysParamResult result = new SysParamResult();
        result.setResult(sysParams);

        return result;
    }


}
