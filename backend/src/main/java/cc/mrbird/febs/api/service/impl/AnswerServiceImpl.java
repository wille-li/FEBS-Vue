package cc.mrbird.febs.api.service.impl;

import cc.mrbird.febs.api.domain.Answer;
import cc.mrbird.febs.api.dao.AnswerMapper;
import cc.mrbird.febs.api.domain.QuestionAnswer;
import cc.mrbird.febs.api.service.AnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzz
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {

    @Override
    public List<QuestionAnswer> hot() {
        return baseMapper.hot();
    }
}
