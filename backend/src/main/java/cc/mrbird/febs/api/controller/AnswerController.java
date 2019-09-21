package cc.mrbird.febs.api.controller;


import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.mrbird.febs.api.domain.Answer;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.AnswerService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.AnswerVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import io.swagger.annotations.Api;

/**
 * @author lzz
 */
@RestController
@RequestMapping("/api/answer")
@Api(value="答案 API",tags={"答案相关操作"})
public class AnswerController extends BaseController {

    @Autowired
    AnswerService answerService;

    @Log("新增回答")
    @PostMapping
    public Result add(@Valid AnswerVO answerVO) throws FebsException {
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerVO, answer, "id");
        
        answer.setCreateTime(Calendar.getInstance().getTime());
        
        this.answerService.save(answer);
        return ResultUtil.success();
    }

    @GetMapping
    public List<Answer> list(QueryRequest request, @Valid AnswerVO answerVO) {
        return this.answerService.list();
    }



}
