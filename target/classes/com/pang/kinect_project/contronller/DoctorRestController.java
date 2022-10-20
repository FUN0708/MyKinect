package com.pang.kinect_project.contronller;

import com.pang.kinect_project.contronller.RequestDto.LoginDto;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorRestController {

    @Autowired
    DoctorService doctorService;
    // @PostMapping("/api/doctorLogin")
    @CrossOrigin
    @RequestMapping(value = "/doctorLogin")
    @ResponseBody
    public Result login(@RequestBody LoginDto doctorDto){
        String d_id = doctorDto.getD_id();
        String password = doctorDto.getPassword();
        Result result = new Result();
        if(d_id.length() != 8) {
            result.setCode(400);
            result.setMessage("账户或密码输入错误");
        }
        result = doctorService.login(doctorDto.getD_id(), doctorDto.getPassword());
        // System.out.println(result.getMessage());
        return result;
    }


    @RequestMapping("/login")
    public Result login1(){
        //esult result = doctorService.login(doctor.getD_id(), doctor.getPassWord());
        //System.out.println(doctor.getD_id()+" "+doctor.getPassWord());
        System.out.println("收到请求");
        Result result = new Result(200, "登录成功");
        // 返回结果对象A
        return result;
    }

}
