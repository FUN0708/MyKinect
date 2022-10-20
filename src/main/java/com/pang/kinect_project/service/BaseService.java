package com.pang.kinect_project.service;

public interface BaseService<T> {
    /**
     * 插入数据
     * @param entity
     * @return
     */
    boolean insert(T entity) ;

    /**
     * 刪除數據
     */
    boolean delete(T entity);
}
