package cc.mrbird.febs.system.domain;

import cc.mrbird.febs.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("answer")
@Excel("问答信息")
public class QA implements Serializable {

    private static final long serialVersionUID = 7187628714679791771L;

    public static final Integer ACCESS = 1;

    public static final Integer REJECT = 2;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long questionId;

    @ExcelField(value = "回答内容")
    private String content;

    @ExcelField(value = "问题内容")
    private String questionContent;

    @ExcelField(value = "老师名称")
    private String teacherName;

    private Integer voiceId;

    private Integer voiceTime;

    @ExcelField(value = "状态")
    private Integer status;

    @ExcelField(value = "创建人")
    private String createBy;

    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

}