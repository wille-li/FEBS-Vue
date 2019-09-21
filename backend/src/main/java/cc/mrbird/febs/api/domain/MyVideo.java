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
@ApiModel(value = "MyVideo对象", description = "我的视频表")

public class MyVideo implements Serializable{
	/**
	 * 我的视频表
	 * 
	 * @author hw
	 */
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	@ApiModelProperty(value = "用户id")
    private Integer userId;

	@ApiModelProperty(value = "视频id")
    private Integer videoId;
	
	@ApiModelProperty(value = "购买价格")
    private Double buyingPrice;

	@ApiModelProperty(value = "购买时间")
    private Date buyingTime;
}