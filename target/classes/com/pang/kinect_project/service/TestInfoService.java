package com.pang.kinect_project.service;

import com.pang.kinect_project.pojo.TestInfo;
import com.pang.kinect_project.result.Result;

import java.util.Date;

public interface TestInfoService extends BaseService<TestInfo>{
    /**
     * 插入患者检查信息
     * @param testInfo
     * @return
     */
    Result insertTestInfo(TestInfo testInfo);

    boolean selectByPatientIDAndTestDate(String patientID, String testDate);
}
