package cc.mrbird.febs.api.vo.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Lin
 * @Description
 * @Date 2019/9/22 12:27 PM
 */
@Data
@ApiModel(value="视频查询对象", description="视频查询参数")
public class VideoQueryVO implements Serializable {

    private static final long serialVersionUID = 4735062609757227549L;

    @ApiModelProperty(value = "视频名称")
    private String name;

    @ApiModelProperty(value = "课程id")
    private Integer courseId;
}
