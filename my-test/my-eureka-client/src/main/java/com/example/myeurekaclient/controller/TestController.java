package com.example.myeurekaclient.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

    @Qualifier("eurekaRegistration")
    @Autowired
    private Registration registration;

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/info")
    public String info() {
        List<ServiceInstance> list = client.getInstances(registration.getServiceId());
        String info = "";
        if (list != null && list.size() > 0) {
            for (ServiceInstance itm : list) {
                info += "host：" + itm.getHost() + "，service_id：" + itm.getServiceId() +"\n";
                log.info(info);
            }
        }
        return info;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
