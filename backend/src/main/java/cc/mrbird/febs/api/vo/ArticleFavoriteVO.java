package cc.mrbird.febs.api.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ArticleFavorite对象", description = "文章收藏表")
public class ArticleFavoriteVO implements Serializable{
	/**
	 * 消息表
	 * 
	 * @author hw
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "用户id")
    private Integer userId;
	
	@ApiModelProperty(value = "文章id")
    private Integer articleId;

}