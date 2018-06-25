package com.zheng.thread.masterworker;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;
import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/6/25 21:48
 */
public class PowerMaster extends Master {
    public PowerMaster(Worker worker, Integer countNum) {
        super(worker, countNum);
    }

    @Override
    public int getResult() {
        int sum = 0;
        while (MapUtils.isNotEmpty(getResults()) || !isComplete()) {
            Object result;
            for (Map.Entry<String, Object> entry : getResults().entrySet()) {
                String key = entry.getKey();
                result = entry.getValue();
                sum += Integer.parseInt(result+"");
                if (Optional.ofNullable(result).isPresent()) {
                    getResults().remove(key);
                    break;
                }
            }
        }
        return sum;
    }
}
