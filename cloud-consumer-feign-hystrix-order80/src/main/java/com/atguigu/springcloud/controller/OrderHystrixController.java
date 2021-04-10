package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        return paymentHystrixService.payment_ok(id);
    }
    /*@GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id){
        return paymentHystrixService.payment_timeout(id);
    }*/


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    @HystrixCommand
    public String payment_timeout(@PathVariable("id") Integer id){
        return paymentHystrixService.payment_timeout(id);
    }
    public String paymentTimeoutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，有点繁忙，你妈的过一会再来";
    }

    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息";
    }
}
