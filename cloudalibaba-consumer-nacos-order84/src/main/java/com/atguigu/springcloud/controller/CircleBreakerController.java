package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/{id}")
    @SentinelResource(value = "payment",fallback = "handlerFallback",blockHandler = "blockHandler")
    public String fallback(@PathVariable("id") Integer id){
        String res = restTemplate.getForObject(SERVICE_URL+"/payment/"+id, String.class);
        if(id==4){
            throw new IllegalArgumentException(("非法参数异常"));
        }
        return res;
    }
    public String handlerFallback(@PathVariable("id") Integer id,Throwable e){
        return "id="+id+"/(ㄒoㄒ)/~~fallback"+e.getMessage();
    }
    public String blockHandler(@PathVariable("id") Integer id, BlockException exception){
        return "id="+id+"/(ㄒoㄒ)/~~block"+exception.getMessage();
    }
    @Resource
    private PaymentService paymentService;
    @GetMapping("/payment/{id}")
    public String payment(@PathVariable("id") Integer id){
        return paymentService.payment(id);
    }
}
