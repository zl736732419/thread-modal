package com.zheng.thread.producerconsumer;

import java.io.Serializable;

/**
 * 面包
 * @Author zhenglian
 * @Date 2018/6/24 23:23
 */
public class Bread implements Serializable {
    private String name;

    public Bread() {
        
    }
    
    public Bread(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
