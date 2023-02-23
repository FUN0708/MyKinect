package com.pang.kinect_project.contronller;

import com.pang.kinect_project.pojo.Patient;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.PatientService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientRestController {

    @Autowired
    PatientService patientService;

    /**
     * 插入患者基本信息
     * @param patient
     * @return
     */
    @RequestMapping("/savePatient")
    @ResponseBody
    public Result insertPatient(@RequestBody Patient patient){
        System.out.println(patient.getPatientSex()+","+patient.getPatientID()+","+patient.getPatientName());
        Result result = patientService.insertPatient(patient);
        return result;
    }

    // 根据患者手机号或身份证号查询信息
    @RequestMapping("/getPatient")
    public List<Patient> getPatient(@RequestParam String searchInfo){
//        System.out.println(searchInfo);
        if(searchInfo.length() == 11){
            return patientService.selectPatientByPhone(searchInfo);
        } else if(searchInfo.length() == 18){
            return patientService.selectPatientById(searchInfo);
        } else return null;
    }
}
