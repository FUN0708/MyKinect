package com.pang.kinect_project.service.serviceImpl;

import com.pang.kinect_project.mapper.DoctorDao;
import com.pang.kinect_project.pojo.Doctor;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.BaseService;
import com.pang.kinect_project.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
@Service
public class DoctorServiceImpl extends BaseServiceImpl<Doctor> implements DoctorService {
    @Autowired
    DoctorDao doctorDao;

    @Override
    public Result login(String d_id, String password) {
        Doctor doctor = doctorDao.login(d_id);
        Result result = new Result();
        // 验证查询返回结果
        if(doctor != null && password.equals(doctor.getPassWord())){
            result.setCode(200);
            result.setMessage("登录成功");
        } else{
            result.setCode(500);
            result.setMessage("用户名或密码错误");
        }
        return result;
    }
}
