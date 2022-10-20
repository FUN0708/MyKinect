package com.pang.kinect_project.mapper;

import com.pang.kinect_project.pojo.Patient;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PatientDao extends BaseDao<Patient>{
    /**
     * 出入患者信息
     * @param patientID
     * @param patientName
     * @param patientPhone
     * @param patientSex
     */
    @Insert("insert into patient(patientID,patientName, patientPhone, patientSex) values " +
            "(#{patientID}, #{patientName}, #{patientPhone}, #{patientSex})")
    public void insert(@Param("patientID") String patientID, @Param("patientName") String patientName,
                       @Param("patientPhone")String patientPhone, @Param("patientSex")int patientSex);

    /**
     * 更新患者信息
     * @param
     * @return
     */
    @Update("update patient set patientName = #{patientName}," +
            "patientPhone = #{patientPhone}," +
            "patientSex = #{patientSex} where patientID = #{patientID} ")
    public boolean update(@Param("patientID") String patientID, @Param("patientName") String patientName,
                          @Param("patientPhone")String patientPhone, @Param("patientSex")int patientSex);
    /**
     * 根据patientID查询患者信息
     * @param patientID
     * @return
     */
    @Select("select * from patient where patientID = #{patientID}")
    public Patient selectByPatientID(String patientID);
}
