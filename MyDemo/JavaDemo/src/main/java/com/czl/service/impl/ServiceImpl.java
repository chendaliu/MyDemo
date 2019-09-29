package com.czl.service.impl;

import com.czl.service.IService;
import org.springframework.stereotype.Service;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@Service
public class ServiceImpl implements IService {

    private  static final Logger logger = LogManager.getLogManager().getLogger(ServiceImpl.class.getName());

    /**
     * 常规业务方法（不超时）
     */
    public void executeBusinessA() {
        //模拟一个执行时间
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * 超时业务方法
     */
    public void executeBusinessB() {
        //模拟一个超时执行时间
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


    }
}
