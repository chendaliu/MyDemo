package com.daliu.test.dynamicProxy.copy;

import java.lang.reflect.Method;

import com.daliu.test.dynamicProxy.CGLibDynamicProxy;



public class DynamicProxyTest {

	public static void main(String[] args) {
		/*BookService bookService = new JdkDynamicProxy(new BookServiceImpl()).getProxy();
		bookService.buyBook();*/
		
		BookServiceImpl cgBookService = CGLibDynamicProxy.getInstance().getProxy(BookServiceImpl.class);
		System.out.println("我们得到的bean是：" + cgBookService.getClass());
		System.out.println("实际调用者的父类：" + cgBookService.getClass().getSuperclass());
		
		try {
			Class<?> c = Class.forName(cgBookService.getClass().getName());
			Class<?> beanc = Class.forName("com.daliu.test.dynamicProxy.BookServiceImpl");
			
			Method[] beanc_method = beanc.getMethods();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//cgBookService.buyBook();
    }

    /*public static void createProxyClassFile()
    {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { Subject.class } );
        try
        {
            FileOutputStream out = new FileOutputStream( name + ".class" );
            out.write( data );
            out.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }*/
    
}
