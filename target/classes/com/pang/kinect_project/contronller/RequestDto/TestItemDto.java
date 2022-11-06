package com.pang.kinect_project.contronller.RequestDto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestItemDto {
    private String patientID;
    private String testDate;
    private int projectID;
    private MultipartFile projectFile;

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

    public MultipartFile getProjectFile() {
        return projectFile;
    }

    public void setProjectFile(MultipartFile projectFile) {
        this.projectFile = projectFile;
    }
}
