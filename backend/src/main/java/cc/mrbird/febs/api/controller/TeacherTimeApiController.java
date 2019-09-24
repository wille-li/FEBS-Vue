package cc.mrbird.febs.api.controller;


import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.domain.TeacherTime;
import cc.mrbird.febs.api.service.TeacherTimeService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.TeacherTimeVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author hw
 */
@RestController
@RequestMapping("/api/teacherTime")
@Api(value="教师档期表",tags={"用于教师发布档期时间"})
public class TeacherTimeApiController {
	@Autowired
	TeacherTimeService teacherTimeService;

    @Log("新增教师档期")
    @PostMapping("addTeacherTime")
    @ApiOperation("新增教师档期")
    public Result addTeacherTime(@Valid TeacherTimeVO vo) throws FebsException {
    	
    	TeacherTime teacherTime = new TeacherTime();
    	BeanUtils.copyProperties(vo, teacherTime, "id");
    	teacherTime.setCreateBy(FebsUtil.getCurrentUser().getUsername());
    	teacherTime.setCreateTime(Calendar.getInstance().getTime());
        return ResultUtil.success(teacherTimeService.save(teacherTime));
    }

}
