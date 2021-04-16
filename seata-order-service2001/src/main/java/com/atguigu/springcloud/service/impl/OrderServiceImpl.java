package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("新建订单！！！！！！");
        orderDao.create(order);
        log.info("库存减少！！！！！！");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("额度减少！！！！！！");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("修改订单状态！！！");
        orderDao.update(order.getUserId(),0);
        log.info("------>下订单结束");
    }
}
