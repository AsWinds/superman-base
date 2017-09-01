package com.codi.superman.base.service.impl;

import com.codi.base.exception.BaseAppException;
import com.codi.base.spring.SpringContextHolder;
import com.codi.superman.base.service.SysCacheService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Sys cache Service( for redis)
 *
 * @author shi.pengyan
 * @date 2017-01-05 20:13
 */
@Service("sysCacheService")
public class SysCacheServiceImpl extends AbstractServiceImpl implements SysCacheService {


    @Override
    public Object getValueByKey(String key, Integer type, Long start, Long end) throws BaseAppException {
        RedisTemplate<String, String> redisTemplate = getRedisTemplate();
        switch (type) {
            case TYPE_STRING:
                return redisTemplate.opsForValue().get(key);
            case TYPE_LIST:
                return redisTemplate.opsForList().range(key, start, end);
            case TYPE_SET:
                return redisTemplate.opsForSet().members(key);
            case TYPE_ZSET:
                return redisTemplate.opsForZSet().range(key, start, end);
            case TYPE_HASH:
                return redisTemplate.opsForHash().entries(key);
            default:
                return null;
        }
    }

    @Override
    public Integer deleteByKey(String key) throws BaseAppException {
        RedisTemplate<String, String> redisTemplate = getRedisTemplate();
        Set<String> keys = redisTemplate.keys(key);

        if (keys != null) {
            redisTemplate.delete(keys);
            return keys.size();
        }

        return 0;
    }


    private RedisTemplate<String, String> getRedisTemplate() {
        return SpringContextHolder.getBean("redisTemplate");
    }

}
