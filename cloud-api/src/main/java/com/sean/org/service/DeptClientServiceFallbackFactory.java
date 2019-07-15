package com.sean.org.service;

import com.sean.org.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //客户端的面向切面的服务降级处理
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept get(Long id) {
                Dept dept = new Dept();
                dept.setDeptno(id);
                dept.setName("此Id的数据不存在：" + id + " 服务器已做降级处理");
                dept.setDb_source("无数据");
                return dept;
            }

            @Override
            public List<Dept> list() {
                return new ArrayList<>(3);
            }
        };
    }
}
