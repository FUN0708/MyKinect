package com.pang.kinect_project.service.serviceImpl;

import com.pang.kinect_project.mapper.PatientTestItemDao;
import com.pang.kinect_project.pojo.PatientTestItem;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.PatientTestItemService;
import com.pang.kinect_project.utils.IdentityCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PatientTestItemServiceImpl extends BaseServiceImpl<PatientTestItem> implements PatientTestItemService {
    @Autowired
    PatientTestItemDao patientTestItemDao;

    @Override
    public Result insertPatientTestItem(PatientTestItem ptItem) {
        Result result = new Result(400,"患者检测项目信息保存失败");
        if(IdentityCheckUtils.idCardValidate(ptItem.getPatientID())){
            PatientTestItem p = patientTestItemDao.selectByPatientIdAndDateAndPid(ptItem.getPatientID(),
                    ptItem.getTestDate(), ptItem.getProjectID());
            if(p == null){
                patientTestItemDao.insert(ptItem.getPatientID(), ptItem.getTestDate(),
                        ptItem.getProjectID(), ptItem.getProjectFileUrl());
                result.setCode(200);
                result.setMessage("患者检测项目信息保存成功");
            } else {
               boolean flag = patientTestItemDao.update(ptItem.getPatientID(), ptItem.getTestDate(),
                        ptItem.getProjectID(), ptItem.getProjectFileUrl());
               if(flag) {
                   result.setCode(200);
                   result.setMessage("患者检测项目信息保存成功");
               }
            }
        }
        return result;
    }

    @Override
    public PatientTestItem select(String patientID, String testDate, int projectID) {
        return null;
    }


    @Override
    public List<PatientTestItem> selectAllByPatientID(String patientID) {
        List<PatientTestItem> list = new ArrayList<>();
        list = patientTestItemDao.selectAllByPatientID(patientID);
        return list;
    }
}
