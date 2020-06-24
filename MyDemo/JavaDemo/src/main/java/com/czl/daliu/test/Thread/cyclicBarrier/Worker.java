package com.czl.daliu.test.Thread.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
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
    private CyclicBarrier barrier;

    public Worker(String name, CyclicBarrier barrier) {
        this.name = name;
        this.barrier = barrier;
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
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void doWork(){
        System.out.println(this.name + "正在干活!");
    }
}