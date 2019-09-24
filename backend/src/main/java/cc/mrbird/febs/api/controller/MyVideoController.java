package cc.mrbird.febs.api.controller;


import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import cc.mrbird.febs.api.domain.MyVideo;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.MyVideoService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.MyVideoVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author hw
 */
@RestController
@RequestMapping("/api/myVideo")
@Api(value="我的视频控制器",tags={"用于个人视频"})
public class MyVideoController {

	
	@Autowired
	MyVideoService MyVideoService;

	@Log("新增我的视频")
	@PostMapping("/addMyVideo")
	@ApiOperation("新增我的视频")
	public Result addMyVideo(@Valid MyVideoVO MyVideoVO) throws FebsException {

		MyVideo MyVideo = new MyVideo();
		BeanUtils.copyProperties(MyVideoVO, MyVideo, "id");
		MyVideo.setBuyingTime(Calendar.getInstance().getTime());
		MyVideo.setUserId(10086);
		this.MyVideoService.save(MyVideo);
		return ResultUtil.success();
	}

	@Log("删除我的视频")
	@GetMapping("/deleteMyVideo/{id}")
	@ApiOperation("删除我的视频")
	public Result selectMyMessage(@PathVariable Integer id) throws FebsException {
		this.MyVideoService.removeById(id);
		return ResultUtil.success();
	}

	@Log("查询我的所有视频")
	@GetMapping("/selectMyVideo")
	@ApiOperation("查询我的所有视频")
	public Result selectAllMessage() throws FebsException {
		//Integer userId = FebsUtil.getCurrentUser().getUserId().intValue();
		return ResultUtil.success(this.MyVideoService.selectListByUserId(10086));
	}
}
