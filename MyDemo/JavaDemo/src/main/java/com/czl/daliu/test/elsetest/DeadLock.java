package com.czl.daliu.test.elsetest;

public class DeadLock {
    public static Object o1 = new Object();
    public static Object o2 = new Object();

    public static void main(String[] args) {
        Thread th1 = new Thread(new Thread1());
        Thread th2 = new Thread(new Thread2());

        th1.start();
        th2.start();
    }
}

class Thread1 implements Runnable{

    @Override
    public void run() {
        synchronized (DeadLock.o1){
            System.out.println("获得了o1的锁");
            try {
                Thread.sleep(3*1000);
                synchronized (DeadLock.o2){
                    System.out.println("获得了o2的锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Thread2 implements Runnable{

    @Override
    public void run() {
        synchronized (DeadLock.o2){
            System.out.println("获得了o2的锁");
            try {
                Thread.sleep(3*1000);
                synchronized (DeadLock.o1){
                    System.out.println("获得了o1的锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}