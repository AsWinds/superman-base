package com.codi.superman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 项目基类
 *
 * @author shi.pengyan
 * @date 2016年11月8日 上午10:54:39
 */
public abstract class BaseController extends com.codi.base.domain.BaseController {

    @Autowired
    protected RedisTemplate<String, String> redisTemplate;

}
