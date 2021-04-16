package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.myhandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "...................testA";
    }
    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"\t"+"...testB");
        return "...................testB";
    }
    @GetMapping("/testD")
    public String testD(){
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD测试RT");*/
        log.info("testD测试异常比例");
        int age = 10/0;
        return "...................testD";
    }
    @GetMapping("/testHostKey")
    @SentinelResource(value = "testHostKey",blockHandler = "deal_testHostKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "------testHostKey";
    }
    public String deal_testHostKey(String p1, String p2, BlockException exception){
        return "-deal_testHostKey,/(ㄒoㄒ)/~~";
    }


    @GetMapping("/rateLimit/customerBlockHandle")
    @SentinelResource(value ="customerBlockHandle",
                      blockHandlerClass = CustomerBlockHandler.class,
                      blockHandler = "handlerException2")
    public String customerBlockHandler(){
        return "(●'◡'●)";
    }
}
