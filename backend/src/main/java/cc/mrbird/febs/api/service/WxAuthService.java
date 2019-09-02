package cc.mrbird.febs.api.service;


import cc.mrbird.febs.api.domain.WxThird;
import cc.mrbird.febs.api.vo.UserVO;
import cc.mrbird.febs.api.vo.WxResVO;
import cc.mrbird.febs.api.vo.WxSessionVO;
import cc.mrbird.febs.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WxAuthService extends IService<WxThird> {

    WxSessionVO login(WxResVO vo);

    UserVO register(WxResVO vo) throws Exception;

    boolean sendMsm(String phoneNum);

    boolean bindingPhone(String openId, String phoneNum);

    boolean validateCode(String phoneNum, String code);
}
