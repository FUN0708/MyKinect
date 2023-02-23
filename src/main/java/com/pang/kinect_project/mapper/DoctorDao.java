package com.pang.kinect_project.mapper;

import com.pang.kinect_project.pojo.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
@Mapper
public interface DoctorDao extends BaseDao<Doctor> {
    // 集成Jpa可以用这个
    //Doctor findDoctorByd_idAndPassWord(String d_id, String password);

    // 使用mybatis方法
    // 登录验证
    @Select("select * from doctor where docterId = #{d_id}")
    public Doctor login(String d_id);

}
