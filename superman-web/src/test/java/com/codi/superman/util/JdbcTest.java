package com.codi.superman.util;

import com.codi.superman.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2016-12-22 17:04
 */
public class JdbcTest extends BaseTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        int id = 1001;
        try {
            jdbcTemplate.queryForObject("select * from FM_USER where USER_ID=?", String.class, id);
        } finally {
        }
    }
}
