package cc.mrbird.febs.api.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ArticleFavorite对象", description = "文章收藏表")
public class ArticleFavorite implements Serializable{
	/**
	 * 消息表
	 * 
	 * @author hw
	 */
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	@ApiModelProperty(value = "用户id")
    private Integer userId;
	
	@ApiModelProperty(value = "文章id")
    private Integer articleId;

	@ApiModelProperty(value = "创建时间")
    private Date createTime;
}