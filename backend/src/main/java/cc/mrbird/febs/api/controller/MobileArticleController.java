package cc.mrbird.febs.api.controller;


import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.mrbird.febs.api.domain.Article;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.ArticleService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.ArticleVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lzz
 */
@RestController
@RequestMapping("/api/article")
@Api(value="文章 API",tags={"文章相关接口"})
public class MobileArticleController extends BaseController {

    @Autowired
    ArticleService articleService;

    @Log("新增文章")
    @ApiOperation("新增文章")
    @PostMapping
    public Result add(@Valid ArticleVO articleVO) throws FebsException {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article, "id");
        article.setCreateTime(Calendar.getInstance().getTime());
        article.setLikeCount(0);
        article.setCollect(0);
        this.articleService.save(article);
        return ResultUtil.success();
    }

    @Log("获取文章")
    @ApiOperation("获取文章")
    @GetMapping
    public Result list(ArticleVO articleVO, QueryRequest request ) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article, "id");
        return ResultUtil.success(getDataTable(articleService.find(request, article)));
    }

    @Log("点赞文章")
    @ApiOperation("点赞文章")
    @PostMapping("like")
    public Result like(Integer id) throws FebsException {
        // TODO:  添加内容
        return ResultUtil.success();
    }

    @Log("收藏文章")
    @ApiOperation("收藏文章")
    @PostMapping("collect")
    public Result collect(Integer id) throws FebsException {
        // TODO: 添加内容
        return ResultUtil.success();
    }

}
