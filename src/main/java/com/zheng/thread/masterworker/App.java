package com.zheng.thread.masterworker;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;
import java.util.Optional;

/**
 * 一个计算1到100立方和例子
 * @Author zhenglian
 * @Date 2018/6/25 21:12
 */
public class App {
    public static void main(String[] args) {
        Worker worker = new PowerWorker();
        int workerCount = 5;
        Master master = new Master(worker, workerCount);
        for(int i = 1; i <= 3; i++) {
            master.pushTask(i);
        }
        master.execute();
        int sum = 0;
        while (MapUtils.isNotEmpty(master.getResults()) || !master.isComplete()) {
            Object result;
            for (Map.Entry<String, Object> entry : master.getResults().entrySet()) {
                String key = entry.getKey();
                result = entry.getValue();
                sum += Integer.parseInt(result+"");
                if (Optional.ofNullable(result).isPresent()) {
                    master.getResults().remove(key);
                    break;
                }
            }
        }
        System.out.println("计算结果为：" + sum);
    }
}
