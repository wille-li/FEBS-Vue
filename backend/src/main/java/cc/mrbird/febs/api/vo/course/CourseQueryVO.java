package cc.mrbird.febs.api.vo.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Lin
 * @Description
 * @Date 2019/9/22 10:30 AM
 */
@Data
@ApiModel(value="课程查询对象", description="课程表查询条件")
public class CourseQueryVO implements Serializable {

    private static final long serialVersionUID = 2282694682879715893L;

    @ApiModelProperty(value = "主标题")
    private String title;

    @ApiModelProperty(value = "子标题")
    private String subTitle;

    @ApiModelProperty(value = "咨询师")
    private String teacherName;

    @ApiModelProperty(value = "咨询师id")
    private Integer teacherId;
}
