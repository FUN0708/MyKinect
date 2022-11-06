package com.pang.kinect_project.contronller;

import com.pang.kinect_project.contronller.RequestDto.LoginDto;
import com.pang.kinect_project.pojo.Inspector;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.InspectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InspectorRestController {
    @Autowired
    InspectorService inspectorService;

    @RequestMapping("/inspectorLogin")
    public Result login(@RequestBody LoginDto loginDto){
        Result result = new Result();
        // 检查传入的用户账号和密码
        if(loginDto.getD_id().length() != 8 && loginDto.getPassword().length() == 0){
            result.setCode(200);
            result.setMessage("账号或密码错误，请重新输入");
        } else {
            result = inspectorService.login(loginDto.getD_id(), loginDto.getPassword());
        }
        return result;
    }
}
