package cc.mrbird.febs.api.controller;

import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.domain.Video;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.Map;

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

    @Log("分页查询课程")
    @GetMapping("listPage")
    @ApiOperation("分页查询课程")
    public Map<String, Object> listPage(QueryRequest queryRequest, VideoQueryVO vo) {
        IPage<Video> data = videoService.listPage(queryRequest, vo);
        return getDataTable(data);
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

        boolean result = videoService.updateById(video);
        if (!result) {
            return ResultUtil.fail("更新失败");
        }

        return ResultUtil.success();

    }

    @Log("删除视频")
    @GetMapping("/delete/{id}")
    @ApiOperation("删除视频")
    public Result delete(@NotBlank(message = "{required}") @PathVariable Integer id) {
        boolean result = videoService.removeById(id);
        if (!result) {
            return ResultUtil.fail("删除视频失败");
        }
        return ResultUtil.success();
    }

}
