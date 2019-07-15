package com.sean.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sean.rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * topic 匹配模式，routing key可以由包含句号的字符串组成（foods.add.end）,bind key可以由包含* #的字符串组成
 * #匹配0个或多个单词  *匹配一个单词
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String msg = "商品处理...... ";

        channel.basicPublish(EXCHANGE_NAME, "goods.delete", null, msg.getBytes());

        System.out.println("send msg:" + msg);

        channel.close();

        connection.close();

    }
}
