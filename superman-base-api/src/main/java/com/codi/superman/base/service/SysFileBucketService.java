package com.codi.superman.base.service;

import com.codi.superman.base.domain.SysFileBucket;

import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-04-10 16:54
 */
public interface SysFileBucketService {

    SysFileBucket add(SysFileBucket record);

    int update(SysFileBucket record);

    int delete(Long bucketId);

    List<SysFileBucket> query();

    SysFileBucket query(Long bucketId);


}
