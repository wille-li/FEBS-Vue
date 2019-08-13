package cc.mrbird.febs.api.service;


import cc.mrbird.febs.api.domain.WxThird;
import cc.mrbird.febs.api.vo.WxResVO;
import cc.mrbird.febs.api.vo.WxSessionVO;
import cc.mrbird.febs.common.exception.RedisConnectException;
import cc.mrbird.febs.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WxAuthService extends IService<WxThird> {

    WxSessionVO login(WxResVO vo);

    User register(WxResVO vo) throws Exception;
}
