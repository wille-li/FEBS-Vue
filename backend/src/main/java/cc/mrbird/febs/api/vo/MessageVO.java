package cc.mrbird.febs.api.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Message对象", description = "问题表")
public class MessageVO implements Serializable {
	/**
	 * 消息表
	 * 
	 * @author hw
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	@ApiModelProperty(value = "多个消息接收者")
	private String userIdS;  

}