package com.sean.rabbitmq.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sean.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String QUEUE_NAME = "test_tx_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        try {
            channel.txSelect();

            String msg = "work msg ";

            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());



            System.out.println("send msg:" + msg);
            int i = 1 / 0;
            channel.txCommit();

        }catch (Exception e){
            System.out.println("rollback....");
            channel.txRollback();
        }

        channel.close();

        connection.close();

    }
}
