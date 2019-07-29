package cc.mrbird.febs.api.controller;


import cc.mrbird.febs.api.domain.Answer;
import cc.mrbird.febs.api.domain.Question;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.QuestionService;
import cc.mrbird.febs.api.util.Constant;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.AnswerVO;
import cc.mrbird.febs.api.vo.QuestionVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import com.wuwenze.poi.util.Const;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzz
 */
@RestController
@RequestMapping("/api/question")
@Api(value="问题 controller",tags={"问题相关操作"})
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Log("新增问题")
    @PostMapping
    public Result add(@Valid QuestionVO questionVO) throws FebsException {
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question, "id");
        question.setCreateTime(Calendar.getInstance().getTime());
        question.setStatus(Constant.INIT);
        this.questionService.save(question);
        return ResultUtil.success();
    }

    @Log("获取所有模式")
    @GetMapping("modes")
    public Result listMode() throws FebsException{
        return ResultUtil.success(Constant.Mode.toMap());
    }
}
