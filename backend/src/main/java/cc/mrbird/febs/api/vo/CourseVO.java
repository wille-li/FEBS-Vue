package cc.mrbird.febs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="课程对象", description="课程表表")
public class CourseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "列表图片")
    private String imageUrl;

    @ApiModelProperty(value = "主标题")
    private String title;

    @ApiModelProperty(value = "子标题")
    private String subTitle;

    @ApiModelProperty(value = "价格")
    private Integer price;

    @ApiModelProperty(value = "咨询师 ID")
    private Integer teacherId;
}
