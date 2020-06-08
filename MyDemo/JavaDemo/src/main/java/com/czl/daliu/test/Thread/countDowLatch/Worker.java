package com.czl.daliu.test.Thread.countDowLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Title:
 * @Description:
 * @Author: wb-ccl670938
 * @CreateTime: 2020-06-08 11:55
 * @Version:1.0
 **/
public class Worker implements Runnable {

    private String name;
    private CountDownLatch countDownLatch;

    public Worker(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        this.doWork();
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "活干完了，用时：" + (System.currentTimeMillis() - currentTime));
        this.countDownLatch.countDown();
    }

    private void doWork(){
        System.out.println(this.name + "正在干活!");
    }
}