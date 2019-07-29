package cc.mrbird.febs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="Answer对象", description="回答表")
public class AnswerVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "回答问题 ID" ,required=true)
    private Integer questionId;

    @ApiModelProperty(value = "回答内容" ,required=true)
    private String content;

    @ApiModelProperty(value = "回答老师 ID" ,required=true)
    private Integer teacherId;

    @ApiModelProperty(value = "回答音频 ID")
    private Integer voiceId;

    @ApiModelProperty(value = "语音时间长度（s）")
    private Integer voiceTime;

    @ApiModelProperty(value = "关键字")
    private String keyWord;
}
