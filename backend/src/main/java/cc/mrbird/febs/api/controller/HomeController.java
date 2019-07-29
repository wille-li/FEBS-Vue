package cc.mrbird.febs.api.controller;

import cc.mrbird.febs.api.domain.Article;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.AnswerService;
import cc.mrbird.febs.api.service.ArticleService;
import cc.mrbird.febs.api.service.CourseService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.BannerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/home")
@Api(value="首页 controller",tags={"首页接口"})
public class HomeController {

    @Autowired
    AnswerService answerService;

    @Autowired
    CourseService courseService;

    @Autowired
    ArticleService articleService;

    @GetMapping("banner")
    @ApiOperation("Banner 文章接口")
    public Result list() {
        List<Article> articleList = articleService.banner();
        List<BannerVO> bannerVOList = new ArrayList<>();
        for (Article article : articleList) {
            BannerVO vo = new BannerVO();
            BeanUtils.copyProperties(article, vo);
            bannerVOList.add(vo);
        }
        return ResultUtil.success(bannerVOList);
    }

    @GetMapping("hot/video")
    @ApiOperation("热门视频")
    public Result hotVideo() {
        return ResultUtil.success(courseService.hot());
    }

    @GetMapping("hot/ask")
    @ApiOperation("热门问答")
    public Result hotAsk() {
        return ResultUtil.success(answerService.hot());
    }
}
