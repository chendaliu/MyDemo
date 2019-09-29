package com.czl.daliu.test.dynamicProxy.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义注解处理器 https://juejin.im/post/5a6a8ab0f265da3e4b76fc4f
 * @author 263619
 *
 */
public class ActionHandle {

	public static void action(Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Method[] mtds = obj.getClass().getDeclaredMethods();
		for(Method mt : mtds){
			Action ac = mt.getAnnotation(Action.class);
			if(ac == null){
				continue;
			}
			String rst = ac.action();
			if("0".equals(rst)){
				// 0 表示要执行
				mt.invoke(obj);
			}
		}
	}
	
	public static void main(String[] args) {
		//测试自定义注解处理器
		Dog dg = new Dog();
		try {
			ActionHandle.action(dg);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
}
