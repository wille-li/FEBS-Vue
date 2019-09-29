package cc.mrbird.febs.api.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.mrbird.febs.api.dao.TeacherTimeMapper;
import cc.mrbird.febs.api.domain.TeacherTime;
import cc.mrbird.febs.api.service.TeacherTimeService;
import cc.mrbird.febs.api.vo.TeacherTimeVO;

/**
 * @author hw
 */
@Service
public class TeacherTimeServiceImpl extends ServiceImpl<TeacherTimeMapper, TeacherTime> implements TeacherTimeService {

	@Autowired
	TeacherTimeMapper teacherTimeMapper;
	@Override
	public List<TeacherTime> selectTeacherTimeList(@Valid TeacherTimeVO vo) {
		
		return teacherTimeMapper.selectTeacherTimeList(vo);
	}

    
}
