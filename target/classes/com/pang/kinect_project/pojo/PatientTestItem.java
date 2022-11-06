package com.pang.kinect_project.pojo;

import com.pang.kinect_project.pojo.primaryEntity.PatientTest;
import com.pang.kinect_project.pojo.primaryEntity.TestItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * 用户检查项目pojo
 */
@Data
@IdClass(TestItem.class)
@AllArgsConstructor
@NoArgsConstructor
public class PatientTestItem {
    @Id
    private String patientID;
    @Id
    private String testDate;
    @Id
    private int projectID;
    // 用户检查项目文件的保存地址
    private String projectFileUrl;

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

    public String getProjectFileUrl() {
        return projectFileUrl;
    }

    public void setProjectFileUrl(String projectFileUrl) {
        this.projectFileUrl = projectFileUrl;
    }
}
