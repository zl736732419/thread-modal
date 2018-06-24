package com.zheng.thread.producerconsumer;

import java.io.Serializable;

/**
 * @Author zhenglian
 * @Date 2018/6/24 23:30
 */
public class Consumer implements Serializable {
    private String name;
    private BreadBox breadBox;

    public Consumer(String name, BreadBox breadBox) {
        this.name = name;
        this.breadBox = breadBox;
    }

    /**
     * 消费面包
     */
    public void consume() {
        new Thread(()->{
            System.out.println("消费者【" + name + "】开始消费面包............");
            Bread bread;
            while (true) {
                bread = breadBox.get();
                System.out.println("消费者【" + name + "】成功取到面包【" + bread.getName() + "】");
            }
        }).start();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BreadBox getBreadBox() {
        return breadBox;
    }

    public void setBreadBox(BreadBox breadBox) {
        this.breadBox = breadBox;
    }
}
