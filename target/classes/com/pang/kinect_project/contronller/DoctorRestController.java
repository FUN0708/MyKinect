package com.pang.kinect_project.contronller;

import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorRestController {

    @Autowired
    DoctorService doctorService;
    @RequestMapping("/doctorLogin")
    public Result login(@RequestParam("d_id") String d_id, @RequestParam("password") String password){
        Result result = doctorService.login(d_id, password);
        // 返回结果对象A
        return result;
    }
}
