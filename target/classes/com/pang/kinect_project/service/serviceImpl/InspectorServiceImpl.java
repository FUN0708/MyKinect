package com.pang.kinect_project.service.serviceImpl;

import com.pang.kinect_project.mapper.InspectorDao;
import com.pang.kinect_project.pojo.Inspector;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.InspectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspectorServiceImpl extends BaseServiceImpl<Inspector> implements InspectorService{
    @Autowired
    InspectorDao inspectorDao;

    /**
     * 检测人员登录验证
     * @param d_id
     * @param password
     * @return
     */
    @Override
    public Result login(String d_id, String password) {
        Inspector inspector = inspectorDao.login(d_id);
        Result result = new Result();
        if(inspector != null && inspector.getPassword().equals(password)){
            result.setCode(200);
            result.setMessage("登录成功");
        }else{
            result.setCode(400);
            result.setMessage("登录失败，请重新输入用户名和密码");
        }
        return result;
    }
}
