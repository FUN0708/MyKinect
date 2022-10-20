package com.pang.kinect_project.contronller;

import com.pang.kinect_project.pojo.Patient;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientRestController {

    @Autowired
    PatientService patientService;

    /**
     * 插入患者基本信息
     * @param patient
     * @return
     */
    @RequestMapping("/insertPatient")
    public Result insertPatient(@RequestBody Patient patient){
        Result result = patientService.insertPatient(patient);
        return result;
    }
}
