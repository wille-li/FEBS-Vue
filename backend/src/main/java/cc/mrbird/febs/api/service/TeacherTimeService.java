package cc.mrbird.febs.api.service;

import java.util.List;

import javax.validation.Valid;

import com.baomidou.mybatisplus.extension.service.IService;

import cc.mrbird.febs.api.domain.TeacherTime;
import cc.mrbird.febs.api.vo.TeacherTimeVO;

/**
 * 
 * @author hw
 *
 */
public interface TeacherTimeService extends IService<TeacherTime> {

	List<TeacherTime> selectTeacherTimeList(@Valid TeacherTimeVO vo);

}
