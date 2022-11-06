package com.pang.kinect_project.pojo.primaryEntity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 患者检查项目中需要两个主键来进行标识
 */
@Data
public class PatientTest implements Serializable {
    private String patientID;
    private String testDate;

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //自动生成的即可
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
