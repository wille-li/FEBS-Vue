package cc.mrbird.febs.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 回答表
 *
 * @author lzz
 */
@Data
@ApiModel(value="问答对象", description="问答对象")
public class QuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "回答ID")
    private Integer answerId;

    @ApiModelProperty(value = "问题内容")
    private String content;

    @ApiModelProperty(value = "问题 ID")
    private Integer questionId;

    @ApiModelProperty(value = "收听（查看）次数")
    private Integer listenTime;

    @ApiModelProperty(value = "回答老师id")
    private Integer teacherId;

    @ApiModelProperty(value = "老师名称")
    private String teacherName;

    @ApiModelProperty(value = "老师头像")
    private String avatar;

    @ApiModelProperty(value = "老师头像")
    private Integer voiceId;

    @ApiModelProperty(value = "语音时间")
    private Integer voiceTime;

    @ApiModelProperty(value = "关键字")
    private String keyWord;

}
