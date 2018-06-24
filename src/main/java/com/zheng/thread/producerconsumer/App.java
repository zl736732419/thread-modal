package com.zheng.thread.producerconsumer;

/**
 * @Author zhenglian
 * @Date 2018/6/24 23:35
 */
public class App {
    public static void main(String[] args) throws Exception {
        BreadBox box = new BreadBox();
        Producer producer = new Producer("生产者1号", box);
        Consumer consumer;
        for (int i = 0; i < 2; i++) {
            consumer = new Consumer("消费者" + (i + 1) + "号", box);
            consumer.consume();
        }
        producer.create();
    }
}
