package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    public static final String INVOKE_URL="http://cloud-provider-payment";

    @RequestMapping("/order/get")
    public String get(){
        String res = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return res;
    }
}
