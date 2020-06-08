package com.czl.daliu.test.Thread.countDowLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title:
 * @Description:
 * @Author: wb-ccl670938
 * @CreateTime: 2020-06-08 11:02
 * @Version:1.0
 **/
public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        executorService.execute(new Worker("张三", countDownLatch));
        executorService.execute(new Worker("李四", countDownLatch));
        executorService.execute(new Worker("王五", countDownLatch));
        executorService.execute(new Boss(countDownLatch));

        executorService.shutdown();
    }
}