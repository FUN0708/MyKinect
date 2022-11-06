package com.pang.kinect_project.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 定义基类数据操作
 */

public interface BaseDao<T> {
    /**
     * 插入某条数据
     * @param entity
     * @throws Exception
     */
    void insert(T entity) throws Exception;

    /**
     * 删除数据
     * @param entity
     * @throws Exception
     */
    void delete(T entity) throws Exception;

    /**
     * 更新数据
     */
    boolean update(T entity) throws Exception;

}
