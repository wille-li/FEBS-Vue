package cc.mrbird.febs.api.domain;

import java.time.LocalDateTime;
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
 * 老师
 *
 * @author lzz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Teacher 对象", description="咨询师")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "头衔")
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

    private String createBy;

    private Date createTime;


}
