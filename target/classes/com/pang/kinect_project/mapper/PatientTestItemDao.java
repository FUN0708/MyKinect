package com.pang.kinect_project.mapper;

import com.pang.kinect_project.pojo.PatientTestItem;
import org.apache.ibatis.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface PatientTestItemDao extends BaseDao<PatientTestItem>{

    // 插入用户检查项目信息
    @Insert("insert into patientTestItem(patientID, testDate, projectID, projectFileUrl) values(" +
            "#{patientID}, #{testDate}, #{projectID}, #{projectFileUrl})")
    void insert(@Param("patientID")String patientID, @Param("testDate")String testDate,
                @Param("projectID")int projectID, @Param("projectFileUrl")String projectFileUrl);

    // 更新用户检查项目文件
    @Update("update patientTestItem set projectFileUrl = #{projectFileUrl} where " +
            "patientID = #{patientID} and testDate = #{testDate} and projectID = #{projectID}")
    boolean update(@Param("patientID")String patientID, @Param("testDate")String testDate,
                @Param("projectID")int projectID, @Param("projectFileUrl")String projectFileUrl);

    // 根据用户身份证号，检测日期与检测项目查看单条用户检测信息

    @Select("select * from patientTestItem where patientID = #{patientID} and" +
            " testDate = #{testDate} and projectID = #{projectID}")
    PatientTestItem selectByPatientIdAndDateAndPid(String patientID, String testDate, int projectID);

    // 根据用户id， 查看所有检测项目信息
    @Select("select * from patientTestItem where patientID = #{patientID}")
    List<PatientTestItem> selectAllByPatientID(String patientID);



}
