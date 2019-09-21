package cc.mrbird.febs.api.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.mrbird.febs.api.dao.ArticleMapper;
import cc.mrbird.febs.api.domain.Article;
import cc.mrbird.febs.api.service.ArticleService;
import cc.mrbird.febs.common.domain.FebsConstant;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;

/**
 * @author lzz
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public IPage<Article> find(QueryRequest request, Article article) {
        try {
            QueryWrapper<Article> queryWrapper = new QueryWrapper<>();

            if (StringUtils.isNotBlank(article.getTitle())) {
                queryWrapper.lambda().eq(Article::getTitle, article.getTitle().toLowerCase());
            }
            if (StringUtils.isNotBlank(article.getContent())) {
                queryWrapper.lambda().like(Article::getContent, article.getContent());
            }
            Page<Article> page = new Page<>(request.getPageNum(), request.getPageSize());
            SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取系统日志失败", e);
            return null;
        }
    }

    @Override
    public List<Article> banner() {
        return this.baseMapper.banner();
    }
}
