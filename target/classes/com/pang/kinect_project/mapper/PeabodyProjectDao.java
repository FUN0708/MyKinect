package com.pang.kinect_project.mapper;

import com.pang.kinect_project.pojo.PeabodyProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PeabodyProjectDao extends BaseDao<PeabodyProject> {
    /***
     * 根据月龄选择检测项目
     * @param age
     * @return
     */
    @Select("select * from peabodyproject where age = #{age}")
    public List<PeabodyProject> selectProjectItemByAge(int age);

    /**
     * 查询全部检查项目
     * @return
     */
    @Select("select * from peabodyproject")
    public List<PeabodyProject> selectAll();

    /**
     * 根据类型查询检查项目
     * @param type
     * @return
     */
    @Select("select * from peabodyproject where substring(projectId,3,1) = #{type}")
    public List<PeabodyProject> selectProjectItemByType(int type);

    /**
     * 根据月龄和类型查询检查项目
     * @param age
     * @param type
     * @return
     */
    @Select("select * from peabodyproject where age = #{age} and substring(projectId,3,1) = #{type}")
    public List<PeabodyProject> selectProjectItemByAgeAndType(int age, int type);


}
