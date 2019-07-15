package com.sean.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableZuulProxy //开启zuul路由
public class Zuul9527_StartSpringCloudApp {
    public static void main(String[] args) {
        SpringApplication.run(Zuul9527_StartSpringCloudApp.class, args);
    }
}
