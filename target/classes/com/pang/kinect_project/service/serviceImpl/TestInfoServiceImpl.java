package com.pang.kinect_project.service.serviceImpl;

import com.pang.kinect_project.mapper.TestInfoDao;
import com.pang.kinect_project.pojo.TestInfo;
import com.pang.kinect_project.result.Result;
import com.pang.kinect_project.service.TestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
@Service
public class TestInfoServiceImpl extends BaseServiceImpl<TestInfo> implements TestInfoService {
    @Autowired
    TestInfoDao testInfoDao;

    /**
     * 患者信息保存
     * @param testInfo
     * @return
     */
    @Override
    public Result insertTestInfo(TestInfo testInfo) {
        Result result = new Result();
        System.out.println("进入insertTestInfo啦");
        System.out.println(testInfo.getTestDate()+" "+testInfo.getPatientID()+" "+testInfo.getPatientWeight()+" "+
                testInfo.getInspector()+" "+testInfo.getPatientHeight()+" "+testInfo.getPatientAge()+
                " "+testInfo.getPatientHeight());
        if(testInfo.getPatientID().length() != 18 || testInfo.getPatientAge() > 200 ||
                testInfo.getHospitalName().length() == 0 || testInfo.getInspector().length() == 0 ||
                testInfo.getPatientWeight() < 0 || testInfo.getPatientHeight() < 30){
            System.out.println("信息输入有误");
            result.setCode(400);
            result.setMessage("患者信息保存失败，请重新录入");
        } else{
            // date为yyyy-MM-hh
            if (selectByPatientIDAndTestDate(testInfo.getPatientID(), testInfo.getTestDate())){
                 System.out.println("更新患者信息");
                boolean flag = testInfoDao.update(testInfo.getPatientID(), testInfo.getTestDate(), testInfo.getHospitalName(),
                        testInfo.getInspector(), testInfo.getPatientWeight(), testInfo.getPatientHeight(),
                        testInfo.getPatientAge());
                if(flag){
                    result.setCode(200);
                    result.setMessage("患者信息保存成功");
                } else {
                    result.setCode(400);
                    result.setMessage("患者信息保存失败，请重新录入");
                }
            } else{
                System.out.println("插入患者信息");
                testInfoDao.insert(testInfo.getPatientID(), testInfo.getTestDate(), testInfo.getHospitalName(),
                        testInfo.getInspector(), testInfo.getPatientWeight(), testInfo.getPatientHeight(),
                        testInfo.getPatientAge());
                result.setCode(200);
                result.setMessage("患者信息保存成功");
            }
        }
        return result;
    }

    /**
     * 根据患者id和检查日期查询
     * @param patientID
     * @param testDate
     * @return
     */
    @Override
    public boolean selectByPatientIDAndTestDate(String patientID, String testDate) {
        System.out.println("进入查找患者信息啦");
        if(patientID.length() != 18) return false;
        TestInfo testInfo = testInfoDao.selectTestInfoByPatientID(patientID,testDate);
        if(testInfo != null) System.out.println("查询到患者");
        return testInfo != null;
    }

}
