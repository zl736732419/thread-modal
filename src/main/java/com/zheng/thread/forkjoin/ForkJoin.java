package com.zheng.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 递归与回溯的思想
 * 这里模拟一个摘苹果的场景：有100棵苹果树，每棵苹果树有10个苹果，现在要把他们摘下来。
 * 为了节约时间，规定每个线程最多只能摘10棵苹树以便于节约时间。各个线程摘完之后汇总计算总苹果树。
 * @Author zhenglian
 * @Date 2018/6/24 22:00
 */
public class ForkJoin {
    
    public static void main(String[] args) throws Exception {
        countApple();
    }

    private static void countApple() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        ResultTask task = new ResultTask(100);
        ForkJoinTask<Integer> future = pool.submit(task);
        Integer result = future.get();
        System.out.println(result);

    }
    

}
