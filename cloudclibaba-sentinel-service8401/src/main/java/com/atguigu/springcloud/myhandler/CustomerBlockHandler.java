package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {
    public  static String handlerException(BlockException exception){
        return "error----------1,/(ćoć)/~~";
    }
    public  static String handlerException2(BlockException exception){
        return "error----------2,/(ćoć)/~~";
    }
}
