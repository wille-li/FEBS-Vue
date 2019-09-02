package cc.mrbird.febs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value="预约详情", description="预约详情")
public class BookingInfoVO {

    @ApiModelProperty(value = "预约id")
    private Integer id;

    @ApiModelProperty(value = "老师ID")
    private Integer teacherId;

    @ApiModelProperty(value = "预约开始时间")
    private Date startTime;

    @ApiModelProperty(value = "预约结束时间")
    private Date endTime;

    @ApiModelProperty(value = "预约信息状态")
    private Integer status;

    @ApiModelProperty(value = "预约价格")
    private Integer price;
}
