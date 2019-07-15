package com.sean.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sean.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// 消息confirm，队列收到消息并持久化后，给生产者发送一个确认消息
public class Send {

    private static final String QUEUE_NAME = "test_confirm_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        // 获取连接
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 生产者将channel设为confirm模式
        channel.confirmSelect();

        String msg = "confirm msg ";

        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());


        System.out.println("send msg:" + msg);

        // 等待确认消息
        if(channel.waitForConfirms()){
            System.out.println("send successful");
        }else{
            System.out.println("send fail");
        }

        channel.close();

        connection.close();

    }
}
