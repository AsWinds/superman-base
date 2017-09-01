package com.codi.superman;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-application.xml"})
public abstract class BaseTest {

    protected ApplicationContext ctx;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeClass
    public static void setUp() {
        System.setProperty("CODI_HOME", "../../config/CODI_HOME");
    }

    @Before
    public void before() {
//        ctx = new ClassPathXmlApplicationContext("classpath:spring-application.xml");
    }

    protected void println(Object obj) {
        System.out.println(obj);
    }

}
