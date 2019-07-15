package com.sean.rabbitmq.confirm;

import com.rabbitmq.client.*;
import com.sean.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// 工作队列模式，未指定每次发送消息的个数，会一口气把消息全发送给消费者
public class Recv1 {

    private static final String QUEUE_NAME = "test_confirm_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // 有新消息时，进入此方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[1] new msg recv:" + msg);
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[1] done");
            }
        };

        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }


}
