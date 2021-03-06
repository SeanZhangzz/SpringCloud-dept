package com.sean.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //自动把此服务注册进eureka服务中
@EnableDiscoveryClient //启动服务发现
@EnableCircuitBreaker //对hystrixR熔断器的支持
public class DeptProvider8001_hystric_App {
    public static void main(String[] args){
        SpringApplication.run(DeptProvider8001_hystric_App.class, args);
    }
}
