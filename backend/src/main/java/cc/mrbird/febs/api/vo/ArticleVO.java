package cc.mrbird.febs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value="文章对象", description="文章入参")
public class ArticleVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章内容")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "图片路径")
    private String url;
}
