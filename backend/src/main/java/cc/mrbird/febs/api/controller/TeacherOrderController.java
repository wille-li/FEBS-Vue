package cc.mrbird.febs.api.controller;


import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import cc.mrbird.febs.api.domain.MyVideo;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.domain.TeacherOrder;
import cc.mrbird.febs.api.service.TeacherOrderService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.MyVideoVO;
import cc.mrbird.febs.api.vo.TeacherOrderVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author hw
 */
@RestController
@RequestMapping("/api/teacherOrder")
@Api(value="教师预约表",tags={"用于教师预约"})
public class TeacherOrderController {
	
	@Autowired
	TeacherOrderService teacherOrderSerivce;

	
	@Log("查询我的所有预约")
	@GetMapping("/selectAllTeacherOrder")
	@ApiOperation("查询我的所有预约")
	public Result selectAllTeacherOrder() throws FebsException {
		//Integer userId = FebsUtil.getCurrentUser().getUserId().intValue();
		return ResultUtil.success(this.teacherOrderSerivce.selectListByUserId(10086));
	}
	
	@Log("新增预约")
	@PostMapping("/addAllTeacherOrder")
	@ApiOperation("新增我的预约")
	public Result addAllTeacherOrder(@Valid TeacherOrderVO vo) throws FebsException {
		TeacherOrder teacherOrder = new TeacherOrder();
		BeanUtils.copyProperties(vo, teacherOrder, "id");
		teacherOrder.setCreateTime(Calendar.getInstance().getTime());
		teacherOrder.setCreateBy("黄某人");
		this.teacherOrderSerivce.addAllTeacherOrder(teacherOrder);
		return ResultUtil.success();
	}
	
}
