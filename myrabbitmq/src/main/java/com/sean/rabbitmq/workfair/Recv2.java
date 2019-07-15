package com.sean.rabbitmq.workfair;

import com.rabbitmq.client.*;
import com.sean.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv2 {

    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 保证一次只发一个,如果每次发五个呢，是保持消费者始终有五个消息在消费吗？
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // 有新消息时，进入此方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[2] new msg recv:" + msg);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[2] done");

                    // 手动应答
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        // 监听队列
        boolean autoAck = false; // 关闭自动应答
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);


    }

    /**
     * old method to consumer
     * @param channel
     */
    private static void oldConsumer(Channel channel) {
        //QueueingConsumer consumer = new QueueingConsumer(channel);
    }
}
