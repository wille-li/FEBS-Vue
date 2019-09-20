package cc.mrbird.febs.api.dao;

import cc.mrbird.febs.api.domain.MyVideo;

public interface MyVideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyVideo record);

    int insertSelective(MyVideo record);

    MyVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyVideo record);

    int updateByPrimaryKey(MyVideo record);
}