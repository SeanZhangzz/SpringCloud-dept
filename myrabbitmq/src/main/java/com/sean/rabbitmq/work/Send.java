package com.sean.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sean.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for(int i = 0; i<50 ; i++){
            String msg = "work msg " + i;

            channel.basicPublish("",QUEUE_NAME, null, msg.getBytes());

            System.out.println("send msg:" + msg);
        }

        channel.close();

        connection.close();

    }
}
