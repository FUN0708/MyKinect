package com.pang.kinect_project.service.serviceImpl;

import com.pang.kinect_project.mapper.PatientDao;
import com.pang.kinect_project.pojo.Patient;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl extends BaseServiceImpl<Patient> implements PatientService {
    @Autowired
    PatientDao patientDao;
    @Override
    public Result insertPatient(Patient patient) {
        Result result = new Result();
        if(patient.getPatientID().length() != 18 || patient.getPatientPhone().length() != 11 ||
         patient.getPatientSex() > 2 || patient.getPatientSex() < 0 || patient.getPatientName().length() == 0){
            result.setCode(400);
            result.setMessage("保存失败，请检查输入");
        } else {
            if(selectPatientID(patient.getPatientID())){
                // 存在，则更新患者信息
                System.out.println("更新");
                boolean flag = patientDao.update(patient.getPatientID(), patient.getPatientName(),
                        patient.getPatientPhone(), patient.getPatientSex());
                if(flag){
                    result.setCode(200);
                    result.setMessage("保存成功");
                } else {
                    result.setCode(400);
                    result.setMessage("保存失败，请检查输入");
                }
            } else{
                // 不存在该患者，插入患者信息
                patientDao.insert(patient.getPatientID(), patient.getPatientName(),
                        patient.getPatientPhone(), patient.getPatientSex());
                result.setCode(200);
                result.setMessage("保存成功");
            }
        }
        return result;
    }

    @Override
    public boolean selectPatientID(String patientID) {
        // 身份证号长度不为18，直接返回
        if(patientID.length() != 18) return false;
        List<Patient> patient = patientDao.selectByPatientID(patientID);
        // 如果患者存在返回true, 不存在返回false
        return patient.size() > 0;
    }

    @Override
    public List<Patient> selectPatientById(String patientId) {
        return patientDao.selectByPatientID(patientId);
    }

    @Override
    public List<Patient> selectPatientByPhone(String patientPhone) {
        List<Patient> p =  patientDao.selectByPatientPhone(patientPhone);
//        System.out.println(p.size());
        return p;
    }
}
