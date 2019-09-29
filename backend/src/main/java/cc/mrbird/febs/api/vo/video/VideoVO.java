package cc.mrbird.febs.api.vo.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Lin
 * @Description
 * @Date 2019/9/21 1:44 PM
 */
@Data
@ApiModel(value="视频对象", description="视频表")
public class VideoVO implements Serializable {
    private static final long serialVersionUID = -1640742994915847194L;

    @ApiModelProperty(value = "视频id")
    private Integer id;

    @ApiModelProperty(value = "视频名称")
    private String name;

    @ApiModelProperty(value = "语音路径")
    private String videoUrl;

    @ApiModelProperty("课程Id")
    private Integer courseId;

    @ApiModelProperty("课程标题")
    private String courseTitle;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
