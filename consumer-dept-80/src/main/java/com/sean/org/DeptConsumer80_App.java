package com.sean.org;

import com.sean.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="SERVERCLOUD-DEPT", configuration = MySelfRule.class) // MySelfRule不能放在此包中，不然所有的ribbon client都会启用此rule
public class DeptConsumer80_App {

    public static void main(String[] args){
        SpringApplication.run(DeptConsumer80_App.class, args);
    }
}
