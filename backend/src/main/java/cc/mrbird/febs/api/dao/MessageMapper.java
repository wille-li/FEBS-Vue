package cc.mrbird.febs.api.dao;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cc.mrbird.febs.api.domain.Message;

public interface MessageMapper extends BaseMapper<Message>{
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    /**
     * 查询消息列表 userID为空 查询所有消息
     * @param userId
     * @return
     */
	List<Message> selectMessageList(@Param("userId") Integer userId);
	/**
	 * 消息列表增加（包含多个）
	 * @param message
	 * @return
	 */
	Integer addMessage(@Valid Message message);
}