package com.pang.kinect_project.service;

import com.pang.kinect_project.pojo.PeabodyProject;

import java.util.List;

public interface PeabodyProjectService extends BaseService<PeabodyProject>{
    // 查找全部
    List<PeabodyProject> selectAll();
    // 根据月龄查找
    List<PeabodyProject> selectProjectItemByAge(int age);
    // 根据类别查找
    List<PeabodyProject> selectProjectByType(int index);
    // 根据月龄和类别查找
    List<PeabodyProject> selectProjectByAgeAndType(int age, int type);
}
