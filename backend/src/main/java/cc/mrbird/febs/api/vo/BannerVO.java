package cc.mrbird.febs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Banner 对象", description="banner 对象")
public class BannerVO {

    private Integer id;

    @ApiModelProperty(value = "跳转路径")
    private String redirectUrl;

    @ApiModelProperty(value = "文章内容")
    private String title;

    @ApiModelProperty(value = "banner 连接类型 , 默认：0 为URL")
    private Integer type = 0;

    @ApiModelProperty(value = "图片路径")
    private String url;
}
