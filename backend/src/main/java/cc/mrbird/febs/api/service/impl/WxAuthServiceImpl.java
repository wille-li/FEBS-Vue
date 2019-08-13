package cc.mrbird.febs.api.service.impl;

import cc.mrbird.febs.api.dao.WxThirdMapper;

import cc.mrbird.febs.api.domain.WxThird;
import cc.mrbird.febs.api.service.WxAuthService;
import cc.mrbird.febs.api.util.WechatUtil;
import cc.mrbird.febs.api.vo.WxResVO;
import cc.mrbird.febs.api.vo.WxSessionVO;
import cc.mrbird.febs.common.exception.RedisConnectException;
import cc.mrbird.febs.common.service.RedisService;
import cc.mrbird.febs.system.domain.User;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@Log4j2
public class WxAuthServiceImpl extends ServiceImpl<WxThirdMapper, WxThird> implements WxAuthService {

    @Value("${wx.auth.url}")
    String authUrl;

    @Value("${wx.auth.appId}")
    String appId;

    @Value("${wx.auth.secret}")
    String secret;

    @Autowired
    RedisService redisService;

    final static String GRANG_TYPE = "authorization_code";

    @Override
    public WxSessionVO login(WxResVO vo) {
        RestTemplate restTemplate = new RestTemplate();
        String url = authUrl
                + "?appid=" + appId
                + "&secret=" + secret
                + "&js_code=" + vo.getCode()
                + "&grant_type=" + GRANG_TYPE;

        ResponseEntity<String> session = restTemplate.getForEntity(url, String.class);
        String result = session.getBody();
        WxSessionVO respVO = JSON.parseObject(result, WxSessionVO.class);

        try {
            redisService.set(respVO.getOpenid(), respVO.getSession_key());
        } catch (RedisConnectException e) {
            e.printStackTrace();
        }finally {
            respVO.setSession_key(null);
        }
        return respVO;
    }

    @Override
    public User register(WxResVO vo) throws Exception {
        // 获取Session ID 做校验
        String sessionKey = redisService.get(vo.getOpenid());
        String sign = WechatUtil.shaEncode(vo.getRawData() + sessionKey);
        if (!sign.equals(vo.getRawData())) {
            throw new IllegalAccessException("签名校验失败");
        }
        return null;
    }
}
