package cc.mrbird.febs.api.controller;


import cc.mrbird.febs.system.domain.User;
import cc.mrbird.febs.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.MessageService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.exception.FebsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/message")
@Api(value="获取消息控制器",tags={"用于微信鉴权获取用户消息"})
public class MessageApiController extends BaseController{
	
	 @Autowired
	 MessageService messageService;
	 @Autowired
    UserService userService;

    @Log("我的消息")
    @GetMapping("/selectMyMessage/{openId}")
    @ApiOperation("我的消息")
    public Result selectMyMessage(@PathVariable String openId) throws FebsException {

        User user = userService.findByOpenId(openId);
        if (user ==null) {
            return ResultUtil.fail("用户不存在");
        }
        return ResultUtil.success(messageService.selectMessageList(user.getUserId().intValue()));
    }
}
