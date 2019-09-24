package cc.mrbird.febs.api.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 问题表
 *
 * @author lzz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Question对象", description="问题表")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "问题题目")
    private String title;

    @ApiModelProperty(value = "问题内容")
    private String content;

    @ApiModelProperty(value = "提问模式")
    private Integer mode;

    @ApiModelProperty(value = "指定老师id")
    private String teacherId;

    @ApiModelProperty(value = "提问价格")
    private Integer price;

    @ApiModelProperty(value = "问题状态")
    private Integer status;

    @ApiModelProperty(value = "创建人wx")
    private String createByWx;

    private Date createTime;


}
