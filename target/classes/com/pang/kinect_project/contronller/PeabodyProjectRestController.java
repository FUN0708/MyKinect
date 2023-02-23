package com.pang.kinect_project.contronller;

import com.pang.kinect_project.pojo.PeabodyProject;
import com.pang.kinect_project.service.PeabodyProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeabodyProjectRestController {
    @Autowired
    PeabodyProjectService peabodyProjectService;

    @RequestMapping("/searchProjectByTypeAndAge")
    public List<PeabodyProject> selectProjectByTypeAndAge(@RequestParam int age, @RequestParam int type){
//        System.out.println("查询peabody项目");
        if(age >= 0 && type > 0 && type < 4){
            return peabodyProjectService.selectProjectByAgeAndType(age, type);
        } else if(age >= 0){
            return peabodyProjectService.selectProjectItemByAge(age);
        } else if(type > 0 && type < 4){
            return peabodyProjectService.selectProjectByType(type);
        } else{
            return peabodyProjectService.selectAll();
        }
    }

    @RequestMapping("/searchAllProject")
    public List<PeabodyProject> selectAllProject(){
//        System.out.println("查询全部peabody项目");
        return peabodyProjectService.selectAll();
    }
}
