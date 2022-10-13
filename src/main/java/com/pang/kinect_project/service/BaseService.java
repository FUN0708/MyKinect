package com.pang.kinect_project.service;

public interface BaseService<T> {
    /**
     * 插入数据
     * @param entity
     * @return
     */
    boolean insert(T entity) ;
}
