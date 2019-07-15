package com.sean.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {

    /**
     * 获取MQ连接
     */
    public static Connection getConnection() throws IOException, TimeoutException {

        // 连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 设置服务地址
        factory.setHost("127.0.0.1");

        factory.setPort(5672);

        factory.setVirtualHost("/vhost_mmr");

        factory.setUsername("user_mmr");

        factory.setPassword("123");

        return factory.newConnection();
    }
}
