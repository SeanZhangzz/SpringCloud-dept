package com.sean.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer7001_Config_App {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7001_Config_App.class, args);
    }
}
