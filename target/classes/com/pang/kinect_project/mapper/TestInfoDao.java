package com.pang.kinect_project.mapper;

import com.pang.kinect_project.pojo.TestInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface TestInfoDao extends BaseDao<TestInfo>{
    /**
     * 插入患者检查信息
     */
    @Insert("insert into testInfo(patientID, testDate, hospitalName, inspector, patientWeight, patientHeight," +
            "patientAge) values(#{patientID}, #{testDate}, #{hospitalName}, #{inspector}, #{patientWeight}, " +
            "#{patientHeight}, #{patientAge})")
    void insert(@Param("patientID") String patientID, @Param("testDate") String testDate,
                @Param("hospitalName")String hospitalName, @Param("inspector") String inspector,
                @Param("patientWeight")double patientWeight, @Param("patientHeight")double patientHeight,
                @Param("patientAge")int patientAge);

    /**
     * 更新患者检查信息
     */
    @Update("update testInfo set hospitalName = #{hospitalName}, inspector =  #{inspector}," +
            "patientWeight =  #{patientWeight}, patientHeight = #{patientHeight}," +
            "patientAge = #{patientAge} " +
            "where patientID = #{patientID} and testDate = #{testDate}")
    boolean update(@Param("patientID") String patientID, @Param("testDate") String testDate,
                @Param("hospitalName")String hospitalName, @Param("inspector") String inspector,
                @Param("patientWeight")double patientWeight, @Param("patientHeight")double patientHeight,
                @Param("patientAge")int patientAge);

    /**
     * 根据患者id和检查时间查找信息
     */
    @Select("select * from testInfo where patientID = #{patientID} and testDate = #{testDate}")
    TestInfo selectTestInfoByPatientID(String patientID, String testDate);
}
