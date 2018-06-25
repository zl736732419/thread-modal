package com.zheng.thread.masterworker;

/**
 * 一个计算1到100立方和例子
 * @Author zhenglian
 * @Date 2018/6/25 21:12
 */
public class App {
    public static void main(String[] args) {
        Worker worker = new PowerWorker();
        int workerCount = 5;
        Master master = new PowerMaster(worker, workerCount);
        for(int i = 1; i <= 3; i++) {
            master.pushTask(i);
        }
        master.execute();
        int result = master.getResult();
        System.out.println("计算结果为：" + result);
    }
}
