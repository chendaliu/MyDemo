package com.daliu.test.dynamicProxy.dynamicProxy.copy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxy implements InvocationHandler {

    private Object delegate;

    public JdkDynamicProxy(Object bookService){
        this.delegate = bookService;
    }

    @SuppressWarnings("unchecked")
	public <T> T getProxy(){
    	return (T) Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        prepareMoneyForBuyBook();
        Object obj = method.invoke(delegate, args);
        readBookAfterBuy();
        return obj;
    }

    private void prepareMoneyForBuyBook(){
        System.out.println("为买本准备好钱...");
    }

    private void readBookAfterBuy(){
        System.out.println("终于可以看自己喜欢的书了...");
    }
}