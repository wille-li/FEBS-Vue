package cc.mrbird.febs.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ApiModel(value="分页对象", description="分页与排序信息")
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    @ApiModelProperty(value = "每页数据条数", notes="默认10条")
    private int pageSize = 10;

    @ApiModelProperty(value = "页码", notes="从1开始")
    private int pageNum = 1;

    @ApiModelProperty(value = "排序字段")
    private String sortField;

    @ApiModelProperty(value = "页码", notes="descend: 降序， ascend：升序")
    private String sortOrder;
}
