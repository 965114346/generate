package com.zyy.generate.core;

/**
 * @author yangyang
 */
public interface Adapter {

    /**
     * 代理方法
     * @param tableNames
     */
    void adapter(String... tableNames);
}
