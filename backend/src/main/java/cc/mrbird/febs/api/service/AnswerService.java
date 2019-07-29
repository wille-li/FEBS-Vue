package cc.mrbird.febs.api.service;

import cc.mrbird.febs.api.domain.Answer;
import cc.mrbird.febs.api.domain.QuestionAnswer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author lzz
 */
public interface AnswerService extends IService<Answer> {

    List<QuestionAnswer> hot();
}
