package com.zheng.thread.producerconsumer;

import java.io.Serializable;

/**
 * 面包生产者
 *
 * @Author zhenglian
 * @Date 2018/6/24 23:26
 */
public class Producer implements Serializable {
    private String name;
    private BreadBox breadBox;

    public Producer(String name, BreadBox box) {
        this.name = name;
        this.breadBox = box;
    }

    /**
     * 生产面包
     *
     * @throws InterruptedException
     */
    public void create() {
        new Thread(()->{
            System.out.println("生产者【" + name + "】开始生产面包...............");
            int count = 0;
            Bread bread;
            while (true) {
                bread = new Bread("面包" + (++count));
                breadBox.put(bread);
                System.out.println("生产者【" + name + "】成功生产面包：" + bread.getName());
            }
        }).start();
    }

    public BreadBox getBreadBox() {
        return breadBox;
    }

    public void setBreadBox(BreadBox breadBox) {
        this.breadBox = breadBox;
    }
}
