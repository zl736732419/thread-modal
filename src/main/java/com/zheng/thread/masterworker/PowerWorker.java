package com.zheng.thread.masterworker;

import java.util.Optional;

/**
 * 进行立方运算
 *
 * @Author zhenglian
 * @Date 2018/6/25 21:01
 */
public class PowerWorker extends Worker {
    @Override
    protected Object handle(Object obj) {
        if (!Optional.ofNullable(obj).isPresent()) {
            return null;
        }
        Integer num = Integer.parseInt(obj + "");
        return num * num * num;
    }
}
