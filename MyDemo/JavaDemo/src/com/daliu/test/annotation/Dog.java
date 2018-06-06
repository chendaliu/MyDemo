package com.daliu.test.annotation;

public class Dog {
    @Action
    public void say(){
        System.out.println("小狗会说话...");
    }

    @Action(action = "1")
    public void sing(){
        System.out.println("小狗会唱歌...");
    }
    
    public void jump(){
        System.out.println("小狗会跳...");
    }

}
