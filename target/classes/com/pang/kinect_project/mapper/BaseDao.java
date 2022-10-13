package com.pang.kinect_project.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 定义基类数据操作
 */
@Mapper
public interface BaseDao<T> {
    void insert(T entity) throws Exception;

}
