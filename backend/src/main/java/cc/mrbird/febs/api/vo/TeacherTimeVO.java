package cc.mrbird.febs.api.vo;

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
@ApiModel(value = "TeacherTime对象", description = "教师档期时间表")

public class TeacherTimeVO implements Serializable{
	
	/**
	 * 教师档期时间表
	 * 
	 * @author hw
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户id")
    private Integer teacherId;

	@ApiModelProperty(value = "档期开始时间")
    private Date scheduleStartTime;

	@ApiModelProperty(value = "档期结束时间")
    private Date scheduleEndTime;
	
	@ApiModelProperty(value = "状态   空档  0 ;已约 1 ;完成 2 '")
    private Integer status;
}