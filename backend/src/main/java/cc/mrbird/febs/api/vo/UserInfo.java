package cc.mrbird.febs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="微信授权返回用户对象", description="微信返回对象")
public class UserInfo {

    @ApiModelProperty(value = "用户信息")
    String nickName;

    @ApiModelProperty(value = "用户头像图片的 URL")
    String avatarUrl;

    @ApiModelProperty(value = "用户性别")
    Integer gender;

    @ApiModelProperty(value = "用户所在国家")
    String country;

    @ApiModelProperty(value = "用户所在省份")
    String province;

    @ApiModelProperty(value = "用户所在城市")
    String city;

    @ApiModelProperty(value = "显示 country，province，city 所用的语言")
    String language;

}
