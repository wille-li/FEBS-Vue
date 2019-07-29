package cc.mrbird.febs.api.dao;

import cc.mrbird.febs.api.domain.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author lzz
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> banner();

}
