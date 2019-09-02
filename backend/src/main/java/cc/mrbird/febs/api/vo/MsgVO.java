package cc.mrbird.febs.api.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MsgVO implements Serializable {

    Integer id;

    String type;

    String content;

    String createTime;
}
