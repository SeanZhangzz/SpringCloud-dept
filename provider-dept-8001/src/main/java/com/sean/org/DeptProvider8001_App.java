package com.sean.org;

import com.sean.org.service.impl.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class DeptProvider8001_App {

    @Autowired
    private DeptServiceImpl service;

    public static void main(String[] args){
        SpringApplication.run(DeptProvider8001_App.class, args);
        //System.out.println("************************" +  new DeptProvider8001_App().service.getClass());
    }
}
