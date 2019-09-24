package cc.mrbird.febs.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cc.mrbird.febs.api.domain.ArticleFavorite;
/**
 * 
 * @author hw
 *
 */
public interface ArticleFavoriteMapper extends BaseMapper<ArticleFavorite>{

	List<ArticleFavorite> selectListByUserId(@Param("userId") Integer userId);
}