package com.sean.org.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientRest {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defaultZone}")
    //@Value("${spring.application.name}")
    private String eurekaServers;

    @Value("${server.port}}")
    private String port;

    @RequestMapping("/config")
    public String getConfig(){
        String info = "applicationName:" + applicationName + " eurekaServers:" + eurekaServers + " port:" + port;
        System.out.println("********此服务器信息："+ info);
        return info;
    }

}
