package com.pang.kinect_project.service.serviceImpl;

import com.pang.kinect_project.mapper.PeabodyProjectDao;
import com.pang.kinect_project.pojo.PeabodyProject;
import com.pang.kinect_project.service.PeabodyProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeabodyProjectServiceImpl extends BaseServiceImpl<PeabodyProject>
    implements PeabodyProjectService {
    @Autowired
    PeabodyProjectDao peabodyProjectDao;

    @Override
    public List<PeabodyProject> selectAll() {
        return peabodyProjectDao.selectAll();
    }

    // 根据月龄选择评估项目
    @Override
    public List<PeabodyProject> selectProjectItemByAge(int age){
        return peabodyProjectDao.selectProjectItemByAge(age);
    }

    @Override
    public List<PeabodyProject> selectProjectByType(int index) {
        return peabodyProjectDao.selectProjectItemByType(index);
    }

    @Override
    public List<PeabodyProject> selectProjectByAgeAndType(int age, int type) {
        return peabodyProjectDao.selectProjectItemByAgeAndType(age, type);
    }

}
