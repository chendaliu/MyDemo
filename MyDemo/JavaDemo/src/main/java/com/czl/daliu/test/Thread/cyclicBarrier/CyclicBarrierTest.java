package com.czl.daliu.test.Thread.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Title:
 * @Description:
 * @Author: wb-ccl670938
 * @CreateTime: 2020-06-22 17:47
 * @Version:1.0
 **/
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {System.out.println("线程组执行结束");});
        for(int i = 0; i < 5; i++){
            new Thread(new MyRunnable(barrier), "队友"+i).start();
        }
        System.out.println("main function is finished.");
    }


    private static class MyRunnable implements Runnable{
        private CyclicBarrier barrier;

        public MyRunnable(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run() {
            for(int i = 0; i < 3; i++) {
                try {
                    Random rand = new Random();
                    int randomNum = rand.nextInt((3000 - 1000) + 1) + 1000;//产生1000到3000之间的随机整数
                    Thread.sleep(randomNum);
                    System.out.println(Thread.currentThread().getName() + ", 通过了第"+i+"个障碍物, 使用了 "+((double)randomNum/1000)+"s");
                    this.barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}