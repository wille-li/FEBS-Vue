package cc.mrbird.febs.api.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cc.mrbird.febs.api.domain.ArticleFavorite;

/**
 * 
 * @author hw
 *
 */
public interface ArticleFavoriteService extends IService<ArticleFavorite> {

	List<ArticleFavorite> selectListByUserId(Integer userId);

}
