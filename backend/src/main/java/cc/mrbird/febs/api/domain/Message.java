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
@ApiModel(value = "Message对象", description = "问题表")
public class Message implements Serializable {
	/**
	 * 消息表
	 * 
	 * @author hw
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	@ApiModelProperty(value = "消息类型")
	private String messageType;
	
	@ApiModelProperty(value = "消息内容")
	private String messageContent;
	
	@ApiModelProperty(value = "消息状态")
	private Integer messageStatus;
	
	@ApiModelProperty(value = "消息时间")
	private Date messageTime;
	
	@ApiModelProperty(value = "消息接收者id")
	private Integer userId;
	
	@ApiModelProperty(value = "创建人")
	private String createBy;
	
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

}