package cc.mrbird.febs.api.controller;


import cc.mrbird.febs.api.domain.Answer;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.AnswerService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.AnswerVO;
import cc.mrbird.febs.api.vo.WxResVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.QueryRequest;
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
import java.util.List;

/**
 * @author lzz
 */
@RestController
@RequestMapping("/api/wx")
@Api(value="微信鉴权API",tags={"用于微信鉴权获取用户信息"})
public class WxAuthController extends BaseController {


    @Log("微信鉴权")
    @PostMapping
    @ApiOperation("微信鉴权")
    public Result add(WxResVO vo) throws FebsException {

        return ResultUtil.success();
    }


}
