package com.sean.rabbitmq.workfair;

import com.rabbitmq.client.*;
import com.sean.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// 工作队列模式，未指定每次发送消息的个数，会一口气把消息全发送给消费者
public class Recv1 {

    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        final Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 保证一次只发一个
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

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
                }finally {
                    System.out.println("[1] done");

                    // 手动应答
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }

            }
        };

        // 监听队列
        boolean autoAck = false; // 关闭自动应答，队列收到消息应答后，会在内存中删除此消息
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
