package cc.mrbird.febs.api.dao;

import cc.mrbird.febs.api.domain.ArticleFavorite;

public interface ArticleFavoriteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleFavorite record);

    int insertSelective(ArticleFavorite record);

    ArticleFavorite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleFavorite record);

    int updateByPrimaryKey(ArticleFavorite record);
}