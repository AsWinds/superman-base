package com.codi.superman.base.service;

import com.codi.base.exception.BaseAppException;

/**
 * sys cache service
 *
 * @author shi.pengyan
 * @date 2017-01-05 20:07
 */
public interface SysCacheService {

    int TYPE_STRING = 0;
    int TYPE_LIST = 1;
    int TYPE_SET = 2;
    int TYPE_ZSET = 3;
    int TYPE_HASH = 4;

    /**
     * get value
     *
     * @param key
     * @param type 类型，见上
     * @param start 起始
     * @param end 结束
     * @return
     * @throws BaseAppException
     */
    Object getValueByKey(String key, Integer type, Long start, Long end) throws BaseAppException;


    /**
     * 删除Key，支持通配符
     *
     * @param key
     * @return
     * @throws BaseAppException
     */
    Integer deleteByKey(String key) throws BaseAppException;

}
