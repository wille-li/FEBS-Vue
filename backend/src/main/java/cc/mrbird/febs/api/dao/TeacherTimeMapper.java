package cc.mrbird.febs.api.dao;

import java.util.List;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cc.mrbird.febs.api.domain.TeacherTime;
import cc.mrbird.febs.api.vo.TeacherTimeVO;
/**
 * 
 * @author hw
 *
 */
public interface TeacherTimeMapper extends BaseMapper<TeacherTime>{

	List<TeacherTime> selectTeacherTimeList(@Valid TeacherTimeVO vo);
	
}