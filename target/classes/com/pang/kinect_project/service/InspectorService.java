package com.pang.kinect_project.service;

import com.pang.kinect_project.mapper.InspectorDao;
import com.pang.kinect_project.pojo.Inspector;
import com.pang.kinect_project.result.Result;
import org.springframework.stereotype.Service;

public interface InspectorService extends BaseService<Inspector>{
    Result login(String d_id, String password);
}
