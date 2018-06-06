package com.daliu.test.dynamicProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib动态代理
 * @author 263619
 * @since 2018年1月26日10:51:19
 */
public class CGLibDynamicProxy implements MethodInterceptor {

	private static CGLibDynamicProxy instance = new CGLibDynamicProxy();
	
	public static CGLibDynamicProxy getInstance(){
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls) {
		return (T) Enhancer.create(cls, this);
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		prepareMoneyForBuyBook();
		Object result = proxy.invokeSuper(obj, args);
		readBookAfterBuy();
		return result;
	}

	private void prepareMoneyForBuyBook(){
        System.out.println("为买本准备好钱...");
    }

    private void readBookAfterBuy(){
        System.out.println("终于可以看自己喜欢的书了...");
    }
	
}
