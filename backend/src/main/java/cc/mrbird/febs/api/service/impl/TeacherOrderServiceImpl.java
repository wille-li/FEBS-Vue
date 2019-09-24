package cc.mrbird.febs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.mrbird.febs.api.dao.TeacherOrderMapper;
import cc.mrbird.febs.api.dao.TeacherTimeMapper;
import cc.mrbird.febs.api.domain.TeacherOrder;
import cc.mrbird.febs.api.domain.TeacherTime;
import cc.mrbird.febs.api.domain.UserOrder;
import cc.mrbird.febs.api.service.TeacherOrderService;

/**
 * @author hw
 */
@Service
public class TeacherOrderServiceImpl extends ServiceImpl<TeacherOrderMapper, TeacherOrder> implements TeacherOrderService {

	@Autowired
	TeacherOrderMapper teacherOrderMapper;
	
	@Autowired
	TeacherTimeMapper teacherTimeMapper;
	
	@Override 
	public List<UserOrder> selectListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return teacherOrderMapper.selectListByUserId(userId);
	}
	@Override
	public int addAllTeacherOrder(TeacherOrder teacherOrder) {
		//预约完 修改预约表状态
		TeacherTime time = new TeacherTime();
		time.setId(teacherOrder.getTeacherTimeId());
		time.setStatus(1);//已预约
		teacherTimeMapper.updateById(time);
		return teacherOrderMapper.insert(teacherOrder);
	}

}
