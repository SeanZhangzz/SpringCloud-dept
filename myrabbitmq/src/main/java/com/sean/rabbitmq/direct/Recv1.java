package com.sean.rabbitmq.direct;

import com.rabbitmq.client.*;
import com.sean.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// 工作队列模式，未指定每次发送消息的个数，会一口气把消息全发送给消费者
public class Recv1 {

    private static final String QUEUE_NAME = "test_exchange_queue1";
    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");

        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // 有新消息时，进入此方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[1] new msg recv:" + msg);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[1] done");
            }
        };

        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);


    }

    /**
     * old method to consumer
     * @param channel
     */
    private static void oldConsumer(Channel channel) {
        //QueueingConsumer consumer = new QueueingConsumer(channel);
    }
}
