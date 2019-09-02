package cc.mrbird.febs.api.controller;


import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.MsgVO;
import cc.mrbird.febs.api.vo.WxResVO;
import cc.mrbird.febs.api.vo.WxSessionVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/msg")
@Api(value="获取信息控制器",tags={"用于微信鉴权获取用户信息"})
@Log4j2
public class MsgController {

    @Log("我的消息")
    @GetMapping
    @ApiOperation("我的消息")
    public Result auth(String openid) throws FebsException {

        List<MsgVO> list = new ArrayList<>();
        MsgVO vo = new MsgVO();
        vo.setContent("恭喜您，您的提问获得收听+1，我们已经将0.5元的分成打入您的余额中，还请查收。");
        vo.setType("问答消息");
        vo.setId(1);
        vo.setCreateTime("2019-01-16");
        list.add(vo);
        vo = new MsgVO();
        vo.setContent("您已经成功购买课程《股权设计基础》视频课程，感谢您的支持，快快收听吧！");
        vo.setType("视频消息");
        vo.setId(1);
        vo.setCreateTime("2019-01-16");
        list.add(vo);
        vo = new MsgVO();
        vo.setContent("恭喜您，您的转发已经到达要求，我们将赠送您一次免费提问的权利。");
        vo.setType("问答消息");
        vo.setId(1);
        vo.setCreateTime("2019-01-16");
        list.add(vo);
        return ResultUtil.success(list);
    }
}
