package com.zheng.thread.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 计算任务
 * 也可继承自RecursiveAction抽象类，区别在于compute方法没有返回值，
 * 如果只需要执行动作则可以使用该接口
 * @Author zhenglian
 * @Date 2018/6/24 22:02
 */
public class ResultTask extends RecursiveTask<Integer> {
    /**
     * 苹果树数量
     */
    private Integer treeNum;
    
    public ResultTask(Integer count) {
        this.treeNum = count;
    }

    @Override
    protected Integer compute() {
        if (treeNum <= 10) {
            return getAppleNum();
        }
        
        // 拆分成两部分继续创建子任务
        int halfNum = treeNum / 2;
        int leaveNum = treeNum - halfNum;
        ResultTask left = new ResultTask(halfNum);
        ResultTask right = new ResultTask(leaveNum);
        left.fork();
        right.fork();
        
        // 合并两部分的结果
        int result = left.join() + right.join();
        
        return result;
    }

    /**
     * 每棵树有10个苹果，一个线程最多处理10棵树
     * 所以这里细化到具体每个线程执行的业务逻辑
     * @return
     */
    private Integer getAppleNum() {
        return treeNum * 10;
    }
}
