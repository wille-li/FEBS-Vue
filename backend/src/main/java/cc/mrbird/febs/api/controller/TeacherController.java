package cc.mrbird.febs.api.controller;


import cc.mrbird.febs.api.domain.Answer;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.domain.Teacher;
import cc.mrbird.febs.api.service.TeacherService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.AnswerVO;
import cc.mrbird.febs.api.vo.TeacherVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Calendar;

/**
 * @author lzz
 */
@RestController
@RequestMapping("/api/teacher")
@Api(value="咨询师 API",tags={"咨询师相关操作"})
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Log("新增老师")
    @PostMapping
    public Result add(@Valid TeacherVO teacherVO) throws FebsException {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherVO, teacher, "id");
        teacher.setCreateTime(Calendar.getInstance().getTime());
        this.teacherService.save(teacher);
        return ResultUtil.success();
    }
}
