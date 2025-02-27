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

/**
 * 老师预约表
 *
 * @author hw
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TeacherOrder对象", description="老师预约表")
public class TeacherOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "老师档期id")
    private Integer teacherTimeId;
    
    @ApiModelProperty(value = "预约开始时间")
    private Date startTime;

    @ApiModelProperty(value = "预约结束时间")
    private Date endTime;

    @ApiModelProperty(value = "预约人微信号")
    private String userId;

    @ApiModelProperty(value = "预约人电话")
    private String userPhone;

    @ApiModelProperty(value = "店名")
    private String storeName;

    @ApiModelProperty(value = "店铺类型")
    private String storeType;

    @ApiModelProperty(value = "咨询简述")
    private String askContent;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
