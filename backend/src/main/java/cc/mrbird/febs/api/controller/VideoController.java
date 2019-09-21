package cc.mrbird.febs.api.controller;

import cc.mrbird.febs.api.domain.Course;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.domain.Video;
import cc.mrbird.febs.api.service.CourseService;
import cc.mrbird.febs.api.service.VideoService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.VideoVO;
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
 * @Author Lin
 * @Description
 * @Date 2019/9/21 2:21 PM
 */
@RestController
@RequestMapping("/api/video")
@Api(value = "视频 API", tags = {"视频相关接口"})
public class VideoController {


    @Autowired
    private VideoService videoService;

    @Autowired
    private CourseService courseService;

    @Log("添加视频")
    @PostMapping("/add")
    @ApiOperation("添加视频")
    public Result add(@Valid VideoVO vo) {
        Video video = new Video();
        BeanUtils.copyProperties(vo, video, "id");
        video.setCreateTime(Calendar.getInstance().getTime());
        videoService.save(video);
        return ResultUtil.success();
    }

    @Log("所有视频")
    @GetMapping("list")
    @ApiOperation("所有视频")
    public Result listAll() throws FebsException {
        return ResultUtil.success(this.videoService.list());
    }

    @Log("更新视频")
    @PostMapping("/update")
    @ApiOperation("更新视频")
    public Result update(@Valid VideoVO vo) throws FebsException {
        Video video = videoService.getById(vo.getId());
        if (video == null) {
            return ResultUtil.fail("修改的视频不存在");
        }

        BeanUtils.copyProperties(vo, video);
        if(vo.getCourseId() != null && !Objects.equals(vo.getCourseId(),video.getCourseId())){
            Course course = courseService.getById(vo.getCourseId());
            if(course == null){
                return ResultUtil.fail("视频相关的课程不存在");
            }
        }

        boolean result = videoService.updateById(video);
        if(!result){
            return ResultUtil.fail("更新失败");
        }

        return ResultUtil.success();

    }

    @Log("删除视频")
    @GetMapping("/delete/{id}")
    @ApiOperation("删除视频")
    public Result delete(@NotBlank(message = "{required}") @PathVariable Integer id){
        boolean result = videoService.removeById(id);
        if(!result){
            return ResultUtil.fail("删除视频失败");
        }
        return ResultUtil.success();
    }

}
