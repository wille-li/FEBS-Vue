package cc.mrbird.febs.api.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import cc.mrbird.febs.api.domain.Article;
import cc.mrbird.febs.common.domain.QueryRequest;

/**
 * @author lzz
 */
public interface ArticleService extends IService<Article> {

    IPage<Article> find(QueryRequest request, Article sysLog);


    List<Article> banner();
}
