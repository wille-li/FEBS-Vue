package cc.mrbird.febs.api.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    private String name;

    private String mobile;

    private Double balance;

    private String avatarUrl;

    private Integer vipLevel;

    private String vipExpireDate;

    private Integer msgCount;

}
