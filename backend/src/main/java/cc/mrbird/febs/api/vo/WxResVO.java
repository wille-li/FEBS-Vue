package cc.mrbird.febs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 微信授权返回对象
 * @Description https://developers.weixin.qq.com/miniprogram/dev/api/open-api/user-info/wx.getUserInfo.html
 * @author lzz
 */
@Data
@ApiModel(value="微信授权返回对象", description="微信返回对象")
public class WxResVO {

    @ApiModelProperty(value = "用户信息")
    UserInfo userInfo;

    @ApiModelProperty(value = "不包括敏感信息的原始数据字符串，用于计算签名")
    String rawData;

    @ApiModelProperty(value = "使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息")
    String signature;

    @ApiModelProperty(value = "包括敏感数据在内的完整用户信息的加密数据")
    String encryptedData;

    @ApiModelProperty(value = "加密算法的初始向量")
    String iv;

    @ApiModelProperty(value = "敏感数据对应的云 ID，开通云开发的小程序才会返回，可通过云调用直接获取开放数据")
    String cloudID;
}
