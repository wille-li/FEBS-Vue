package cc.mrbird.febs.api.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class MsgVO implements Serializable {

    Integer id;

    String type;

    String content;

    String createTime;
}
