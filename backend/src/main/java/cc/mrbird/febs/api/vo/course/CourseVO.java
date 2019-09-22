package cc.mrbird.febs.api.vo.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value="课程对象", description="课程表表")
public class CourseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程id")
    private Integer id;

    @ApiModelProperty(value = "列表图片")
    private String imageUrl;

    @ApiModelProperty(value = "主标题")
    private String title;

    @ApiModelProperty(value = "子标题")
    private String subTitle;

    @ApiModelProperty(value = "价格")
    private Integer price;

    @ApiModelProperty(value = "学习人数")
    private Integer learningCount;

    @ApiModelProperty(value = "咨询师 ID")
    private Integer teacherId;

    @ApiModelProperty(value = "咨询师名称")
    private String teacherName;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
