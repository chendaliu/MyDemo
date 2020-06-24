package com.czl;

import java.util.concurrent.Callable;

/**
 * @Title:
 * @Description:
 * @Author: wb-ccl670938
 * @CreateTime: 2020-06-12 14:16
 * @Version:1.0
 **/
public class CallableTest implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "callable的返回值";
    }
}