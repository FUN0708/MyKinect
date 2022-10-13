package com.pang.kinect_project.service.serviceImpl;

import com.pang.kinect_project.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Override
    public boolean insert(T entity) {
        return false;
    }
}
