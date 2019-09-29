package cc.mrbird.febs.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cc.mrbird.febs.api.domain.MyVideo;

public interface MyVideoMapper extends BaseMapper<MyVideo>{

	List<MyVideo> selectListByUserId(@Param("userId")Integer userId);
}