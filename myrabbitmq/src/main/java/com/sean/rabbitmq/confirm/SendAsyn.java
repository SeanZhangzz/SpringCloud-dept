package com.sean.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.sean.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

// 消息confirm，队列收到消息并持久化后，给生产者发送一个确认消息
public class SendAsyn {

    private static final String QUEUE_NAME = "test_confirm_asyn_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        // 获取连接
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 生产者将channel设为confirm模式
        channel.confirmSelect();

        // 未确认的消息标识
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());

        channel.addConfirmListener(new ConfirmListener() {

            // 成功时调用此方法
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("---handleAck - multiple" + deliveryTag);
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("---handleAck : " + deliveryTag);
                    confirmSet.remove(deliveryTag);
                }
            }

            // 失败时调用此方法
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("---handleNack - multiple");
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("---handleNack");
                    confirmSet.remove(deliveryTag);
                }
            }
        });


        String msg = "asny confirm msg ";

        while (true) {
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            confirmSet.add(seqNo);

            Thread.sleep(5000);

            System.out.println("send msg:" + seqNo);
        }

//        channel.close();
//
//        connection.close();

    }
}
