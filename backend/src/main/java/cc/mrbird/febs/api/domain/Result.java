package cc.mrbird.febs.api.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="返回类", description="视频表")
public class Result {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "信息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private Object data;

}
