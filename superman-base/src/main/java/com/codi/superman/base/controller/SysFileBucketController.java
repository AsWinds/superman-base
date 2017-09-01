package com.codi.superman.base.controller;

import com.codi.base.domain.BaseResult;
import com.codi.superman.base.domain.SysFileBucket;
import com.codi.superman.base.service.SysFileBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件桶管理
 *
 * @author shi.pengyan
 * @date 2017-04-10 16:51
 */
@RestController
@RequestMapping("/sys/fileBucket")
public class SysFileBucketController {

    @Autowired
    private SysFileBucketService sysFileBucketService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(SysFileBucket sysFileBucket) {
        return BaseResult.success(sysFileBucketService.add(sysFileBucket));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(SysFileBucket sysFileBucket) {
        sysFileBucketService.update(sysFileBucket);

        return BaseResult.success();
    }

    @RequestMapping(value = "/{bucketId}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(name = "bucketId") Long bucketId) {
        sysFileBucketService.delete(bucketId);

        return BaseResult.success();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BaseResult queryFileBuckets() {

        return BaseResult.success(sysFileBucketService.query());
    }

}
