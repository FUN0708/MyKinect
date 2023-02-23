package com.pang.kinect_project.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"}) // 使用hibernate
public class PeabodyScore {
    @Id
    private String projectID;
    private String scoreExplain;

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getScoreExplain() {
        return scoreExplain;
    }

    public void setScoreExplain(String scoreExplain) {
        this.scoreExplain = scoreExplain;
    }
}
