package com.zheng.thread.masterworker;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 任务接收者，进行接收、分配任务等操作
 * @Author zhenglian
 * @Date 2018/6/25 20:45
 */
public class Master {
    /**
     * 任务队列
     */
    private Queue<Object> tasks = new ConcurrentLinkedQueue<>();
    /**
     * 工作线程
     */
    private Map<String, Thread> workers = new ConcurrentHashMap<>();

    /**
     * 子任务执行的结果队列
     */
    private Map<String, Object> results = new ConcurrentHashMap<>();

    /**
     * 是否所有的子任务都已经完成了
     * @return
     */
    public boolean isComplete() {
        boolean complete = true;
        Collection<Thread> workerThreads = workers.values();
        for (Thread worker : workerThreads) {
            if (!Objects.equals(worker.getState(), Thread.State.TERMINATED)) {
                complete = false;
                break;
            }
        }
        return complete;
    }
    
    public Master(Worker worker, Integer countNum) { 
        if (!Optional.ofNullable(worker).isPresent()) {
            worker = new PowerWorker();
        }
        worker.setTasks(tasks);
        worker.setResults(results);
        if (!Optional.ofNullable(countNum).isPresent() || countNum <= 0) {
            countNum = 1;
        }
        
        Thread workerThread;
        for (int i = 0; i < countNum; i++) {
             workerThread = new Thread(worker, "worker【" + "】");
             workers.put((i+1)+"", workerThread);
        }
    }

    /**
     * 添加任务
     * @param task
     */
    public void pushTask(Object task) {
        tasks.add(task);
    }

    /**
     * 开启工作线程，执行任务
     */
    public void execute() {
        workers.entrySet().stream()
                .forEach(entry -> {
                    Thread workerThread = entry.getValue();
                    workerThread.start();
                });
    }

    public Queue<Object> getTasks() {
        return tasks;
    }

    public void setTasks(Queue<Object> tasks) {
        this.tasks = tasks;
    }

    public Map<String, Thread> getWorkers() {
        return workers;
    }

    public void setWorkers(Map<String, Thread> workers) {
        this.workers = workers;
    }

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }
}
