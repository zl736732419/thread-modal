package com.zheng.thread.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 面包箱
 * @Author zhenglian
 * @Date 2018/6/24 23:21
 */
public class BreadBox {
    /**
     * 装馒头的箱子
     */
    private BlockingQueue box = new ArrayBlockingQueue(50);
    
    public void put(Bread bread) {
        try {
            box.put(bread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public Bread get() {
        try {
            return (Bread) box.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
