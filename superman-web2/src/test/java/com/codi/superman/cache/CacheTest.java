package com.codi.superman.cache;

import com.codi.base.cache.CacheUtil;
import com.codi.superman.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;

/**
 * Created by shi.pengyan on 2016-12-20 11:06.
 */
public class CacheTest extends BaseTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void writeKeyTest() {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        String key = CacheUtil.getKey("test", "key1");

        op.set(key, "" + Math.random());
    }

    @Test
    public void multiKeyTest() {
        Set<String> keys = redisTemplate.keys("spring:*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

    @Test
    public void listTest() {
        String key = CacheUtil.getKey("test", "list");
        BoundListOperations<String, String> listOps = redisTemplate.boundListOps(key);

        listOps.rightPushAll("1", "2", "3");

    }
}
