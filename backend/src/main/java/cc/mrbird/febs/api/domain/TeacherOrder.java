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
 * 老师预约表
 *
 * @author lzz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TeacherOrder对象", description="老师预约表")
public class TeacherOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "老师id")
    private Integer teacherId;

    @ApiModelProperty(value = "预约开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "预约结束时间")
    private LocalDateTime endTime;

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
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
