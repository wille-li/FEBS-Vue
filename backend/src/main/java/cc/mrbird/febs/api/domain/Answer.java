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
 * 回答表
 *
 * @author lzz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Answer对象", description="回答表")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String content;

    @ApiModelProperty(value = "回答问题")
    private Integer questionId;

    @ApiModelProperty(value = "收听（查看）次数")
    private Integer listenTime;

    @ApiModelProperty(value = "回答老师id")
    private Integer teacherId;

    private String teacherName;

    private Integer voiceId;

    @ApiModelProperty(value = "语音时间")
    private Integer voiceTime;

    @ApiModelProperty(value = "关键字")
    private String keyWord;

    private String createBy;

    private Date createTime;

}
