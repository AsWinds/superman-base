package com.codi.superman.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-01-13 10:22
 */
@Component("emailService")
public class EmailServiceImpl implements EmailService {

    @Async("customExecutor")
    @Override
    public void sendAsync() throws Exception {
        System.out.println("Send email async.....");
        System.out.println("Current Thread=" + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("Send email async flow over");
    }

    @Override
    public void send() throws InterruptedException {
        System.out.println("Send email sync.....");
        System.out.println("Current Thread=" + Thread.currentThread().getName());
        System.out.println("Send email sync flow over");
    }

}
