package com.pang.kinect_project.pojo.primaryEntity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestItem implements Serializable {
    private String patientID;
    private String testDate;
    private int projectID;

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

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
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
