package cc.mrbird.febs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="Answer对象", description="回答表")
public class TeacherVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "称号")
    private String title;

    @ApiModelProperty(value = "擅长")
    private String skillIn;

    @ApiModelProperty(value = "咨询费")
    private Integer price;

    @ApiModelProperty(value = "简介")
    private String resume;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "工作经验")
    private Integer experience;
}
