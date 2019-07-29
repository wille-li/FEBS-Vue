package cc.mrbird.febs.api.controller;


import cc.mrbird.febs.api.domain.Answer;
import cc.mrbird.febs.api.domain.Course;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.CourseService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.AnswerVO;
import cc.mrbird.febs.api.vo.CourseVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Calendar;

/**
 * @author lzz
 */
@RestController
@RequestMapping("/api/course")
@Api(value="课程 API",tags={"课程相关接口"})
public class CourseController {

    @Autowired
    CourseService courseService;

    @Log("添加课程")
    @PostMapping
    @ApiOperation("添加课程")
    public Result add(@Valid CourseVO vo) throws FebsException {
        Course course = new Course();
        BeanUtils.copyProperties(vo, course, "id");
        course.setCreateTime(Calendar.getInstance().getTime());
        course.setLearningCount(0);
        this.courseService.save(course);
        return ResultUtil.success();
    }

    @Log("所有课程")
    @GetMapping
    @ApiOperation("所有课程")
    public Result listAll() throws FebsException {
        return ResultUtil.success(this.courseService.list());
    }

}
