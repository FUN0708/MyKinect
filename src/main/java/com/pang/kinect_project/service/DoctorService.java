package com.pang.kinect_project.service;

import com.pang.kinect_project.mapper.DoctorDao;
import com.pang.kinect_project.pojo.Doctor;
import com.pang.kinect_project.result.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.Doc;

public interface DoctorService extends BaseService<Doctor>{
    Result login(String d_id, String password);
}
