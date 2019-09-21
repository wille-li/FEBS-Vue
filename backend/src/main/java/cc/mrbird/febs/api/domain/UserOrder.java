package cc.mrbird.febs.api.domain;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 我的预约
 *
 * @author hw
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserOrder对象", description="我的预约")
public class UserOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "预约开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "预约结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "预约人微信号")
    private String userId;

	@ApiModelProperty(value = "状态   空档  0 ;已约 1 ;完成 2 '")
    private Integer status;
	
    @ApiModelProperty(value = "教师名字")
    private String teacherName;
    
    @ApiModelProperty(value = "用户头像")
    private String avatar;


}
