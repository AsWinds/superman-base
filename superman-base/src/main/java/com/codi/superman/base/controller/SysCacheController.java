package com.codi.superman.base.controller;

import com.codi.base.domain.BaseController;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.BaseAppException;
import com.codi.superman.base.annotation.RequiresPermissions;
import com.codi.superman.base.service.SysCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Cache(Redis) 相关
 *
 * @author shi.pengyan
 * @date 2017-01-05 20:00
 */
@RestController
@RequestMapping("/sys/cache")
public class SysCacheController extends BaseController {

    @Autowired
    private SysCacheService sysCacheService;

    /**
     * 获取redis中值
     *
     * @param key
     * @param type
     * @param start
     * @param end
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    @RequiresPermissions("sys:cache:query")
    public BaseResult getCacheValueByKey(@RequestParam(value = "key") String key,
                                         @RequestParam(value = "type", defaultValue = "0") Integer type,
                                         @RequestParam(value = "start", defaultValue = "1") Long start,
                                         @RequestParam(value = "end", defaultValue = "10") Long end) throws BaseAppException {

        BaseResult result = new BaseResult();
        result.setResult(sysCacheService.getValueByKey(key, type, start, end));

        return result;
    }

    /**
     * 删除key，支持通配符
     *
     * @param key
     * @return
     * @throws BaseAppException
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:cache:delete")
    public BaseResult deleteCacheByKey(@RequestParam(value = "key") String key) throws BaseAppException {
        BaseResult result = new BaseResult();

        result.setResult(sysCacheService.deleteByKey(key));
        return result;
    }

}
