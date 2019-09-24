package cc.mrbird.febs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.mrbird.febs.api.dao.ArticleFavoriteMapper;
import cc.mrbird.febs.api.domain.ArticleFavorite;
import cc.mrbird.febs.api.service.ArticleFavoriteService;

/**
 * @author hw
 */
@Service
public class ArticleFavoriteServiceImpl extends ServiceImpl<ArticleFavoriteMapper, ArticleFavorite> implements ArticleFavoriteService {

	@Autowired
	ArticleFavoriteMapper articleFavoriteMapper;
	
	@Override
	public List<ArticleFavorite> selectListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return articleFavoriteMapper.selectListByUserId(userId);
	}

    
}
