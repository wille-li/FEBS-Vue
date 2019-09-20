package cc.mrbird.febs.api.dao;

import cc.mrbird.febs.api.domain.TeacherTime;

public interface TeacherTimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherTime record);

    int insertSelective(TeacherTime record);

    TeacherTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherTime record);

    int updateByPrimaryKey(TeacherTime record);
}