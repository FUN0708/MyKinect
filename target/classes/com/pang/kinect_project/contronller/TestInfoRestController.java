package com.pang.kinect_project.contronller;

import com.pang.kinect_project.pojo.TestInfo;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.TestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestInfoRestController {
    @Autowired
    TestInfoService testInfoService;

    /**
     * 患者检查信息保存
     * @param testInfo
     * @return
     */
    @RequestMapping("/saveTestInfo")
    public Result saveTestInfo(@RequestBody TestInfo testInfo){
        Result result = testInfoService.insertTestInfo(testInfo);
        return result;
    }
}
