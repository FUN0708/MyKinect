package com.pang.kinect_project.service;


import com.pang.kinect_project.pojo.PatientTestItem;
import com.pang.kinect_project.result.Result;

import java.util.List;

public interface PatientTestItemService extends BaseService<PatientTestItem>{
    /**
     * 插入用户检查项目信息
     * @param patientTestItem
     * @return
     */
    Result insertPatientTestItem(PatientTestItem patientTestItem);


    /**
     * 查询用户单项项目检查信息
     * @param patientID
     * @param testDate
     * @param projectID
     * @return
     */
    PatientTestItem select(String patientID, String testDate, int projectID);

    List<PatientTestItem> selectAllByPatientID(String patient);

}
