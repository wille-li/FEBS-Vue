package cc.mrbird.febs.api.service.impl;

import cc.mrbird.febs.api.domain.Question;
import cc.mrbird.febs.api.dao.QuestionMapper;
import cc.mrbird.febs.api.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lzz
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
