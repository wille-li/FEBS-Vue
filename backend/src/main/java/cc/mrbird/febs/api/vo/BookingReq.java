package cc.mrbird.febs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="预约请求", description="预约请求")
public class BookingReq {

    @ApiModelProperty(value = "预约id")
    private Integer bookingId;

    @ApiModelProperty(value = "店铺名称")
    private String storeName;

    @ApiModelProperty(value = "店铺类型")
    private String storeType;

    @ApiModelProperty(value = "咨询简述")
    private String askContent;
}
