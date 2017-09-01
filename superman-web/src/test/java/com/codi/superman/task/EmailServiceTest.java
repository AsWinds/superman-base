package com.codi.superman.task;

import com.codi.superman.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-01-13 10:23
 */
public class EmailServiceTest extends BaseTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmail() throws Exception {
        System.out.println("Current Thread=" + Thread.currentThread().getName());
        System.out.println("=======invoke send email begin");
        emailService.sendAsync();
        emailService.send();
        System.out.println("=======invoke send email over.");
        System.in.read();// prevent stop
    }
}
