package com.zheng.thread.masterworker;

import java.util.Map;
import java.util.Optional;
import java.util.Queue;

/**
 * 工作线程
 * 从任务池中分配任务，然后将计算结果分别保存
 * @Author zhenglian
 * @Date 2018/6/25 20:49
 */
public abstract class Worker implements Runnable {
    /**
     * 任务池
     * 工作线程从其中获取任务并执行
     */
    private Queue<Object> tasks;
    /**
     * 子任务执行的结果
     */
    private Map<String, Object> results;
    
    /**
     * 任务处理逻辑
     * @param obj
     * @return
     */
    protected abstract Object handle(Object obj);
    
    
    @Override
    public void run() {
        while (true) {
            Object task = tasks.poll();
            // 任务已经处理完成
            if (!Optional.ofNullable(task).isPresent()) {
                break;
            }
            Object result = handle(task);
            if (!Optional.ofNullable(result).isPresent()) {
                continue;
            }
            results.put(Integer.toString(result.hashCode()), result);
        }
    }

    public Queue<Object> getTasks() {
        return tasks;
    }

    public void setTasks(Queue<Object> tasks) {
        this.tasks = tasks;
    }

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }
}
