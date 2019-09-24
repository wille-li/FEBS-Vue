package cc.mrbird.febs.api.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cc.mrbird.febs.api.domain.TeacherOrder;
import cc.mrbird.febs.api.domain.UserOrder;

/**
 * @author hw
 */
public interface TeacherOrderService extends IService<TeacherOrder> {

	List<UserOrder> selectListByUserId(Integer userId);

}
