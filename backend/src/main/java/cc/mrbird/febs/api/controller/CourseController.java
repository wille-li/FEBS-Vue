package cc.mrbird.febs.api.controller;


import cc.mrbird.febs.api.domain.Course;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.domain.Teacher;
import cc.mrbird.febs.api.service.CourseService;
import cc.mrbird.febs.api.service.TeacherService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.CourseVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.Objects;

/**
 * @author lzz
 */
@RestController
@RequestMapping("/api/course")
@Api(value = "课程 API", tags = {"课程相关接口"})
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Log("添加课程")
    @PostMapping("/add")
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
    @GetMapping("list")
    @ApiOperation("所有课程")
    public Result listAll() throws FebsException {
        return ResultUtil.success(this.courseService.list());
    }

    @Log("更新课程")
    @PostMapping("/update")
    @ApiOperation("更新课程")
    public Result update(@Valid CourseVO vo) throws FebsException {
        Course course = courseService.getById(vo.getId());
        if (course == null) {
            return ResultUtil.fail("修改的课程不存在");
        }
        // 检查提交的教师id
        if(vo.getTeacherId() != null && !Objects.equals(course.getTeacherId(),vo.getTeacherId())){
            Teacher teacher = teacherService.getById(vo.getTeacherId());
            if(teacher == null){
                return ResultUtil.fail("没查询到任课教师");
            }
        }

        BeanUtils.copyProperties(vo, course);

        boolean result = courseService.updateById(course);
        if(!result){
            return ResultUtil.fail("更新失败");
        }

        return ResultUtil.success();

    }

    @Log("删除课程")
    @GetMapping("/delete/{id}")
    @ApiOperation("删除课程")
    public Result delete(@NotBlank(message = "{required}") @PathVariable Integer id){
        boolean result = courseService.removeById(id);
        if(!result){
            return ResultUtil.fail("删除课程失败");
        }
        return ResultUtil.success();
    }

}
