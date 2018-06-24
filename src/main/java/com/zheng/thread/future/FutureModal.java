package com.zheng.thread.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhenglian
 * @Date 2018/6/24 21:49
 */
public class FutureModal {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(3);
        MyCallable callable = new MyCallable();
        Future<String> future = service.submit(callable);
        // 阻塞获取直到获取到结果对象
//        String result = future.get();
        // 会阻塞到指定的时间，如果还没有返回数据就抛出TimeoutException
        String result = future.get(2000, TimeUnit.MILLISECONDS);
        System.out.println("response: " + result);
    }
}
