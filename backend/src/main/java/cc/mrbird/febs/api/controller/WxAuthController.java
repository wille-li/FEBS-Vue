package cc.mrbird.febs.api.controller;


import cc.mrbird.febs.api.domain.Answer;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.AnswerService;
import cc.mrbird.febs.api.service.WxAuthService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.util.WXCoreUtil;
import cc.mrbird.febs.api.util.WechatUtil;
import cc.mrbird.febs.api.vo.AnswerVO;
import cc.mrbird.febs.api.vo.UserVO;
import cc.mrbird.febs.api.vo.WxResVO;
import cc.mrbird.febs.api.vo.WxSessionVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.exception.RedisConnectException;
import cc.mrbird.febs.system.domain.User;
import cc.mrbird.febs.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.List;

/**
 * @author lzz
 */
@RestController
@RequestMapping("/api/wx")
@Api(value="微信鉴权API",tags={"用于微信鉴权获取用户信息"})
@Log4j2
public class WxAuthController extends BaseController {

    @Autowired
    WxAuthService wxAuthService;

    @Autowired
    UserService userService;


    @Log("微信鉴权")
    @PostMapping
    @ApiOperation("微信鉴权")
    public Result auth(@RequestBody WxResVO vo) throws FebsException {

        WxSessionVO resVO = wxAuthService.login(vo);
        String openId = resVO.getOpenid();
        User user = userService.findByOpenId(openId);
        if (null != user) {
            UserVO userVO = new UserVO();
            userVO.setName(user.getNickName());
            userVO.setVipLevel(0);
            userVO.setAvatarUrl(user.getAvatar());
            userVO.setBalance(user.getBalance());
            userVO.setMobile(user.getMobile());
            userVO.setMsgCount(0);
            resVO.setUserVO(userVO);
        }

        return ResultUtil.success(resVO);
    }

    @Log("微信注册")
    @PostMapping("/register")
    @ApiOperation("微信注册")
    public Result register(@RequestBody WxResVO vo) throws Exception {
        try {
            UserVO user = wxAuthService.register(vo);
            return ResultUtil.success(user);
        } catch (IllegalArgumentException e) {
            return ResultUtil.fail(e.getMessage());
        }
    }

    @Log("微信注册手机验证")
    @PostMapping("/code/{phoneNum}")
    @ApiOperation("微信注册手机验证")
    public Result registerMsm(@NotBlank(message = "{required}") @PathVariable String phoneNum){

        if (wxAuthService.sendMsm(phoneNum)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(1004, "发送短信失败");
        }

    }

}
