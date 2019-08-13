package cc.mrbird.febs.api.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxSessionVO implements Serializable {

    String openid;

    String session_key;

    String unionid;

    String errcode;

    String errmsg;
}
