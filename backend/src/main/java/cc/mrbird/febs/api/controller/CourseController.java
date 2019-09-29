package cc.mrbird.febs.api.controller;


import cc.mrbird.febs.api.domain.Course;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.domain.Teacher;
import cc.mrbird.febs.api.domain.Video;
import cc.mrbird.febs.api.service.CourseService;
import cc.mrbird.febs.api.service.TeacherService;
import cc.mrbird.febs.api.service.VideoService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.course.CourseQueryVO;
import cc.mrbird.febs.api.vo.course.CourseVO;
import cc.mrbird.febs.api.vo.course.DistributeVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lzz
 */
@Slf4j
@RestController
@RequestMapping("/api/course")
@Api(value = "课程 API", tags = {"课程相关接口"})
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private VideoService videoService;

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

    @Log("分页查询课程")
    @GetMapping("listPage")
    @ApiOperation("分页查询课程")
    public Map<String, Object> listPage(QueryRequest queryRequest, CourseQueryVO vo) {
        IPage<Course> data = courseService.listPage(queryRequest, vo);
        List<Course> courses = data.getRecords();
        if (CollectionUtils.isEmpty(courses)) {
            return getDataTable(data);
        }
        List<Integer> teacherIds = courses.stream().map(Course::getTeacherId).distinct().collect(Collectors.toList());
        List<Teacher> teachers = (List<Teacher>) teacherService.listByIds(teacherIds);
        Map<Integer, Teacher> teacherMap = teachers.stream().collect(Collectors.toMap(Teacher::getId, Function.identity()));
        List<CourseVO> rows = new ArrayList<>();
        courses.forEach(course -> {
            CourseVO courseVO = new CourseVO();
            BeanUtils.copyProperties(course, courseVO);
            Teacher teacher = teacherMap.get(course.getTeacherId());
            courseVO.setTeacherName(teacher == null ? "" : teacher.getName());
            rows.add(courseVO);
        });
        Map<String, Object> result = getDataTable(data);
        result.put("rows", rows);
        return result;
    }

    @Log("更新课程")
    @PostMapping("/update")
    @ApiOperation("更新课程")
    public Result update(@Valid CourseVO vo) throws FebsException {
        Course course = courseService.getById(vo.getId());
        if (course == null) {
            return ResultUtil.fail("修改的课程不存在");
        }

        BeanUtils.copyProperties(vo, course);

        boolean result = courseService.updateById(course);
        if (!result) {
            return ResultUtil.fail("更新失败");
        }

        return ResultUtil.success();

    }

    @Log("删除课程")
    @DeleteMapping("/delete/{courseIds}")
    @ApiOperation("删除课程")
    public Result delete(@NotBlank(message = "{required}") @PathVariable String courseIds) {
        try {
            String[] ids = courseIds.split(StringPool.COMMA);
            courseService.deleteCourses(ids);
            return ResultUtil.success();
        } catch (Exception e) {
            String message = "删除课程失败";
            log.error(message, e);
            return ResultUtil.fail(message);
        }
    }

    @Log("获取课程")
    @GetMapping("/{courseId}")
    @ApiOperation("获取课程")
    public Result get(@NotBlank(message = "{required}") @PathVariable String courseId){

        try {
            Course course = courseService.getById(courseId);
            return ResultUtil.success(course);
        } catch (Exception e) {
            String message = "删除课程失败";
            log.error(message, e);
            return ResultUtil.fail(message);
        }
    }

    @Log("分配视频")
    @PostMapping("/distribute")
    @ApiOperation("分配视频")
    public Result distributeVideo(@Valid DistributeVO vo){
        Course course = courseService.getById(vo.getCourseId());
        if(course == null){
            return ResultUtil.fail("课程不存在，请重新选择");
        }

        List<Video> videos = (List<Video>) videoService.listByIds(vo.getVideoIds());
        if(CollectionUtils.isEmpty(videos)){
            return ResultUtil.fail("选择的视频不存在，请重新选择");
        }
        videos.forEach(video -> video.setCourseId(vo.getCourseId()));
        videoService.updateBatchById(videos);
        return ResultUtil.success();
    }

}
