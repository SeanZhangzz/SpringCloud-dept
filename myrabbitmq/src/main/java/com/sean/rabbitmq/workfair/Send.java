package com.sean.rabbitmq.workfair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sean.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 公平分发模式：
 * 1.prefetchCount = 1
 * 2.关闭自动应答
 * 3.处理完消息手动回执
 */
public class Send {

    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        // 获取连接
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //第二个参数为消息持久化，对已声明的队列不可更改其设置，否则会报错
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME,durable,false,false,null);

        /**
         * 每个消费者发送确认消息之前，消息队列不发送下一个消息
         * 限制一次只发一条给消费者，如果生产者不定义prefetchCount = 1呢？
         */
        int prefetchCount = 1;

        channel.basicQos(prefetchCount);

        for(int i = 0; i<50 ; i++){
            String msg = "work msg " + i;

            channel.basicPublish("",QUEUE_NAME, null, msg.getBytes());

            Thread.sleep(50);

            System.out.println("send msg:" + msg);
        }

        channel.close();

        connection.close();

    }
}
