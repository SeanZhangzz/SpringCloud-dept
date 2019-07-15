package com.sean.rabbitmq.spring;

public class MyConsumer {


    // 消费消息
    public void listen(String foo){
        System.out.println("消费者：" + foo);
    }
}
