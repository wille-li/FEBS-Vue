package cc.mrbird.febs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="问题对象", description="问题")
public class QuestionVO {

    @ApiModelProperty(value = "问题内容" ,required=true)
    private String content;

    @ApiModelProperty(value = "提问模式" ,required=true)
    private Integer mode;

    @ApiModelProperty(value = "指定老师id")
    private String teacherId;

    @ApiModelProperty(value = "提问费用" ,required=true)
    private Integer price;

    @ApiModelProperty(value = "微信唯一id" ,required=true)
    private String openId;
}
