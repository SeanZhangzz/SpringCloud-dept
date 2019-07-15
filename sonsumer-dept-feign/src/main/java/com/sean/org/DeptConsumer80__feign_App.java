package com.sean.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.sean.org"}) //feign集成了ribbon，restTemplate,相当于以接口的形式提供服务
//@ComponentScan("com.sean.org")
public class DeptConsumer80__feign_App {

    public static void main(String[] args){
        SpringApplication.run(DeptConsumer80__feign_App.class, args);
    }
}
