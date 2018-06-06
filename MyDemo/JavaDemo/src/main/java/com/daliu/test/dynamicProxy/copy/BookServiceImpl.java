package com.daliu.test.dynamicProxy.copy;

import com.daliu.test.dynamicProxy.BookService;

public class BookServiceImpl implements BookService {
    @Override
    public void buyBook() {
        System.out.println("买一本书...");
    }
}
