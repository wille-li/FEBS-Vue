package cc.mrbird.febs.api.controller;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.mrbird.febs.api.domain.ArticleFavorite;
import cc.mrbird.febs.api.domain.Result;
import cc.mrbird.febs.api.service.ArticleFavoriteService;
import cc.mrbird.febs.api.util.ResultUtil;
import cc.mrbird.febs.api.vo.ArticleFavoriteVO;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author hw
 */
@RestController
@RequestMapping("/api/articleFavorite")
@Api(value = "我的视频表控制器", tags = { "用于我的视频" })
public class ArticleFavoriteController {

	@Autowired
	ArticleFavoriteService articleFavoriteService;

	@Log("新增文章收藏")
	@PostMapping("/addArticleFavorite")
	@ApiOperation("新增文章收藏")
	public Result addArticleFavorite(@Valid ArticleFavoriteVO articleFavoriteVO) throws FebsException {

		ArticleFavorite articleFavorite = new ArticleFavorite();
		BeanUtils.copyProperties(articleFavoriteVO, articleFavorite, "id");
		articleFavorite.setCreateTime(Calendar.getInstance().getTime());
		this.articleFavoriteService.save(articleFavorite);
		return ResultUtil.success();
	}

	@Log("删除文章收藏")
	@GetMapping("/deleteArticleFavorite/{id}")
	@ApiOperation("删除文章收藏")
	public Result deleteArticleFavorite(@PathVariable Integer id) throws FebsException {
		this.articleFavoriteService.removeById(id);
		return ResultUtil.success();
	}

	@Log("查询我的所有收藏")
	@GetMapping("/selectAllArticleFavorite")
	@ApiOperation("查询我的所有收藏")
	public Result selectAllArticleFavorite() throws FebsException {
		Integer userId = FebsUtil.getCurrentUser().getUserId().intValue();
		return ResultUtil.success(this.articleFavoriteService.selectListByUserId(userId));
	}
}
