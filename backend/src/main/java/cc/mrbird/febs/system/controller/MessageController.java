package cc.mrbird.febs.system.controller;


import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.mrbird.febs.api.domain.Message;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.MessageService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.MessageVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.exception.FebsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/message")
@Api(value="获取消息控制器",tags={"用于管理员微信鉴权获取用户消息"})
public class MessageController extends BaseController{
	
	 @Autowired
	 MessageService messageService;
    
    @Log("所有人的消息")
    @GetMapping("/selectAllMessage")
    @ApiOperation("所有的人消息")
    public Result selectAllMessage() throws FebsException {
        return ResultUtil.success(messageService.selectMessageList(null));
    }
    
    @Log("新增消息")
    @PostMapping("addMessage")
    @ApiOperation("新增消息")
    public Result addMessage(@Valid MessageVO vo) throws FebsException {
    	Message mes = new Message();
    	BeanUtils.copyProperties(vo, mes, "id");
    	mes.setCreateTime(Calendar.getInstance().getTime());
        return ResultUtil.success(messageService.addMessage(mes));
    }
}
