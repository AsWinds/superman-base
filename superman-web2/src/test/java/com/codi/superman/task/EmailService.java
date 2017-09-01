package com.codi.superman.task;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-01-13 10:43
 */
public interface EmailService {

    void sendAsync() throws Exception;

    void send() throws Exception;
}
