package com.zheng.thread.future;

import java.util.concurrent.Callable;

/**
 * @Author zhenglian
 * @Date 2018/6/24 21:48
 */
public class MyCallable implements Callable<String> {

    public String call() throws Exception {
        System.out.println("im callable method");
        Thread.sleep(3000);
        return "hello";
    }
}
