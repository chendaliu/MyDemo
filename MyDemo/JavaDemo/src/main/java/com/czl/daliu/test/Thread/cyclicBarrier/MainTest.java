package com.czl.daliu.test.Thread.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @Title:
 * @Description:
 * @Author: wb-ccl670938
 * @CreateTime: 2020-06-22 19:08
 * @Version:1.0
 **/
public class MainTest {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {System.out.println("所有工人干完活，老板开始检查");});
        new Thread(new Worker("张三", barrier)).start();
        new Thread(new Worker("李四", barrier)).start();
        new Thread(new Worker("王五", barrier)).start();
        System.out.println();
    }

}