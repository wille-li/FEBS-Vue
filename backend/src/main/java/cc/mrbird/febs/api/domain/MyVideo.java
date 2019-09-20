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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Date getBuyingTime() {
        return buyingTime;
    }

    public void setBuyingTime(Date buyingTime) {
        this.buyingTime = buyingTime;
    }
}