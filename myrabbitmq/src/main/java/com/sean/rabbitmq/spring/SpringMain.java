package com.sean.rabbitmq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

    @Autowired
    //private RabbitTemplate template;

    public static void main(String[] args) throws InterruptedException {
        // 发送消息
       // template.convertAndSend("hello world");
        Thread.sleep(1000);
    }
}
