package com.sean.org.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sean.org.entity.Dept;
import com.sean.org.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get") //一旦服务调用失败，则此自定义方法
    public Dept get(@PathVariable Long id) {
        Dept dept = deptService.get(id);
        if(null == dept){
            throw new RuntimeException("无此Id的信息："  + id);
        }
        return dept;
    }

    public Dept processHystrix_Get(@PathVariable Long id){
        Dept errorObj = new Dept();
        errorObj.setDeptno(id);
        errorObj.setName("无此Id的信息："  + id + ",null -- @HystrixCommand");
        errorObj.setDb_source("无信息");
        return errorObj;
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return deptService.list();
    }

    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> services = client.getServices();
        System.out.println("********" + services);

        List<ServiceInstance> srvList = client.getInstances("SERVERCLOUD-DEPT");

        for (ServiceInstance server : srvList) {
            System.out.println(server.getServiceId() + "\t" + server.getHost() + "\t" + server.getPort() + "\t" + server.getUri());
        }

        return this.client;

    }

}
