package cc.mrbird.febs.api.dao;

import cc.mrbird.febs.api.domain.Answer;
import cc.mrbird.febs.api.domain.QuestionAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author lzz
 */
public interface AnswerMapper extends BaseMapper<Answer> {

    List<QuestionAnswer> hot();

}
