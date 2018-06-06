package com.daliu.test.dynamicProxy.dynamicProxy.copy;

public class BookServiceImpl implements BookService {
    @Override
    public void buyBook() {
        System.out.println("买一本书...");
    }
}
