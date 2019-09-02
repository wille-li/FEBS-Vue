package cc.mrbird.febs.api.controller;


import cc.mrbird.febs.api.domain.Answer;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.domain.Teacher;
import cc.mrbird.febs.api.service.TeacherService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.AnswerVO;
import cc.mrbird.febs.api.vo.BookingInfoVO;
import cc.mrbird.febs.api.vo.TeacherDetailVO;
import cc.mrbird.febs.api.vo.TeacherVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @Log("获取提问老师")
    @GetMapping("/list")
    public Result listTeacher() throws FebsException {
        return ResultUtil.success(teacherService.list());
    }

    @Log("获取提问老师详情")
    @GetMapping("/{id}")
    public Result getTeacherDetail(@NotBlank(message = "{required}") @PathVariable Integer id) throws FebsException {
        Teacher teacher = teacherService.getById(id);
        TeacherDetailVO teacherDetailVO = new TeacherDetailVO();
        BeanUtils.copyProperties(teacherDetailVO, teacher, "id");
        BookingInfoVO vo = new BookingInfoVO();
        vo.setId(1);
        vo.setStartTime(Calendar.getInstance().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 3);
        vo.setEndTime(calendar.getTime());
        vo.setTeacherId(id);
        vo.setStatus(1);
        vo.setPrice(300);
        BookingInfoVO vo1 = new BookingInfoVO();
        vo1.setId(2);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,3);
        vo1.setStartTime(calendar.getTime());
        calendar.add(Calendar.HOUR, 3);
        vo1.setEndTime(calendar.getTime());
        vo1.setTeacherId(id);
        vo1.setStatus(1);
        vo1.setPrice(900);
        List<BookingInfoVO> list = new ArrayList<>();
        list.add(vo);
        list.add(vo1);
        return ResultUtil.success(teacher);
    }
}
