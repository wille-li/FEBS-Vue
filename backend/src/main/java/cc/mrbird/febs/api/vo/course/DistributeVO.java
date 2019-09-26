package cc.mrbird.febs.api.vo.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Lin
 * @Description
 * @Date 2019/9/26 11:15 PM
 */
@Data
@ApiModel(value="课程分配视频对象", description="课程分配视频")
public class DistributeVO {

    @NotNull(message = "{required}")
    @ApiModelProperty(value = "课程id")
    private Integer courseId;

    @NotEmpty(message = "{required}")
    @ApiModelProperty(value = "视频id")
    private List<Integer> videoIds;
}
