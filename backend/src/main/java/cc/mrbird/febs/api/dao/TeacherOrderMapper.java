package cc.mrbird.febs.api.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cc.mrbird.febs.api.domain.TeacherOrder;
import cc.mrbird.febs.api.domain.UserOrder;

/**
 * @author hw
 */
public interface TeacherOrderMapper extends BaseMapper<TeacherOrder> {

	List<UserOrder> selectListByUserId(Integer userId);

}
