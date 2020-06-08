package com.czl.daliu.test.Thread.countDowLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Title:
 * @Description:
 * @Author: wb-ccl670938
 * @CreateTime: 2020-06-08 12:04
 * @Version:1.0
 **/
public class Boss implements Runnable {

    private CountDownLatch countDownLatch;

    public Boss(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        System.out.println("老板正在等所有工人干完活");
        try {
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有工人干完活，老板开始检查");
    }
}