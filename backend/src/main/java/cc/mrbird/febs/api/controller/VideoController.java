package cc.mrbird.febs.api.controller;

import cc.mrbird.febs.api.domain.Course;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.domain.Video;
import cc.mrbird.febs.api.service.CourseService;
import cc.mrbird.febs.api.service.VideoService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.video.VideoQueryVO;
import cc.mrbird.febs.api.vo.video.VideoVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @Author Lin
 * @Description
 * @Date 2019/9/21 2:21 PM
 */
@RestController
@RequestMapping("/api/video")
@Api(value = "视频 API", tags = {"视频相关接口"})
public class VideoController extends BaseController {

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
        if (vo.getCourseId() == null) {
            video.setCourseId(0);
        }
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

    @Log("分页查询课程")
    @GetMapping("listPage")
    @ApiOperation("分页查询课程")
    public Map<String, Object> listPage(QueryRequest queryRequest, VideoQueryVO vo) {
        IPage<Video> data = videoService.listPage(queryRequest, vo);
        List<Video> videos = data.getRecords();
        if (CollectionUtils.isEmpty(videos)) {
            return getDataTable(data);
        }

        List<Integer> courseIds = videos.stream().filter(video -> video.getCourseId() != 0).map(Video::getCourseId).distinct().collect(Collectors.toList());
        Map<Integer,Course> courseMap = new HashMap<>(courseIds.size());
        if (CollectionUtils.isNotEmpty(courseIds)) {
            List<Course> courses = (List<Course>) courseService.listByIds(courseIds);
            courseMap = courses.stream().collect(Collectors.toMap(Course::getId, Function.identity()));
        }

        List<VideoVO> rows = new ArrayList<>();
        for (Video video : videos) {
            VideoVO videoVO = new VideoVO();
            BeanUtils.copyProperties(video, videoVO);
            Course course = courseMap.get(video.getCourseId());
            videoVO.setCourseTitle(course == null ? "未分配" : course.getSubTitle());
            rows.add(videoVO);
        }
        Map<String, Object> result = getDataTable(data);
        result.put("rows", rows);
        return result;
    }

    @Log("更新视频")
    @PostMapping("/update")
    @ApiOperation("更新视频")
    public Result update(@Valid VideoVO vo) throws FebsException {
        Video video = videoService.getById(vo.getId());
        if (video == null) {
            return ResultUtil.fail("修改的视频不存在");
        }
        if (!Objects.equals(video.getName(), vo.getName())) {
            video.setName(vo.getName());
        }
        if (!Objects.equals(video.getVideoUrl(), vo.getVideoUrl())) {
            video.setVideoUrl(vo.getVideoUrl());
        }
        boolean result = videoService.updateById(video);
        if (!result) {
            return ResultUtil.fail("更新失败");
        }

        return ResultUtil.success();

    }

    @Log("删除视频")
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除视频")
    public Result delete(@NotBlank(message = "{required}") @PathVariable Integer id) {
        // 单方面删除视频后，是否需要将已选择该视频的课程进行调整呢？
        boolean result = videoService.removeById(id);
        if (!result) {
            return ResultUtil.fail("删除视频失败");
        }
        return ResultUtil.success();
    }

}
