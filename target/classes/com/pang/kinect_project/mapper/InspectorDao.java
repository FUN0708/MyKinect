package com.pang.kinect_project.mapper;

import com.pang.kinect_project.pojo.Inspector;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface  InspectorDao extends BaseDao<Inspector>{
    /**
     * 根据用户账号查找用户信息
     * @param d_id
     * @return
     */
    @Select("select * from inspector where d_id=#{d_id}")
    public Inspector login(String d_id);
}
