package cc.mrbird.febs.api.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.qcloudsms.httpclient.HTTPException;

import cc.mrbird.febs.api.dao.WxThirdMapper;
import cc.mrbird.febs.api.domain.WxThird;
import cc.mrbird.febs.api.domain.wx.WxUserInfo;
import cc.mrbird.febs.api.service.WxAuthService;
import cc.mrbird.febs.api.util.WechatUtil;
import cc.mrbird.febs.api.vo.UserVO;
import cc.mrbird.febs.api.vo.WxResVO;
import cc.mrbird.febs.api.vo.WxSessionVO;
import cc.mrbird.febs.common.exception.RedisConnectException;
import cc.mrbird.febs.common.service.RedisService;
import cc.mrbird.febs.system.domain.User;
import cc.mrbird.febs.system.service.UserService;
import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class WxAuthServiceImpl extends ServiceImpl<WxThirdMapper, WxThird> implements WxAuthService {

    @Value("${wx.auth.url}")
    String authUrl;

    @Value("${wx.auth.appId}")
    String appId;

    @Value("${wx.auth.secret}")
    String secret;

    @Value("${sms.app.id}")
    Integer smsAppId;

    @Value("${sms.app.key}")
    String smsAppKey;

    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;


    final static String GRANG_TYPE = "authorization_code";

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
    public UserVO register(WxResVO vo) throws Exception {
//        if (validateCode(vo.getPhoneNum(), vo.getCode())){
//            throw new IllegalArgumentException("验证码不正确。");
//        }
//        if (!vo.getPhoneNum().substring(5).equals(vo.getValidCode())){
//            throw new IllegalArgumentException("验证码不正确。");
//        }

        // 获取Session ID 做校验
        String openId = vo.getOpenid();
        String sessionKey = redisService.get(openId);
        String desc = WechatUtil.decryptData(vo.getEncryptedData(), sessionKey, vo.getIv());
        WxUserInfo wxUserInfo = JSON.parseObject(desc, WxUserInfo.class);
        User user = userService.findByName(vo.getOpenid());
        if (null == user) {
            // 创建用户
            user = new User();
            user.setUsername(wxUserInfo.getOpenId());
            user.setPassword(wxUserInfo.getOpenId());
            user.setNickName(wxUserInfo.getNickName());
            user.setAvatar(wxUserInfo.getAvatarUrl());
            user.setSsex(Integer.toString(wxUserInfo.getGender()));
            user.setStatus(User.STATUS_VALID);
            Date now = Calendar.getInstance().getTime();
            user.setCreateTime(now);
            user.setBalance(.0);
            user.setLastLoginTime(now);
            user.setModifyTime(now);
            user.setOpenId(wxUserInfo.getOpenId());
            user.setMobile(vo.getPhoneNum());
            userService.save(user);
        } else {
            throw new IllegalArgumentException("手机已被其他用户注册。");
        }

        UserVO userVO = new UserVO();
        userVO.setName(user.getNickName());
        userVO.setAvatarUrl(user.getAvatar());
        userVO.setBalance(user.getBalance());
        userVO.setMobile(user.getMobile());
        // TODO: 会员和消息条数还没做。。。。
        userVO.setVipLevel(0);
        userVO.setVipExpireDate(sdf.format(Calendar.getInstance().getTime()));
        userVO.setMsgCount(1);

        log.info(desc);
        return userVO;
    }

    @Override
    public boolean sendMsm(String phoneNum) {
        String code = randomCode();
        try {
            log.info(phoneNum.substring(5));
            redisService.set(phoneNum, phoneNum.substring(5), 60000L);
            return sendSms(phoneNum, code);
        } catch (RedisConnectException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HTTPException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean bindingPhone(String openId, String phoneNum) {
        userService.bindingPhone(openId, phoneNum);
        return false;
    }

    @Override
    public boolean validateCode(String phoneNum, String code) {
        try {
            String validCode = redisService.get(phoneNum);
            return validCode == null || !validCode.equals(code);
        } catch (RedisConnectException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String randomCode(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6 ; i++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }

    private boolean sendSms(String phoneNum, String code) throws HTTPException, IOException {
        log.info(phoneNum + ":" + code);
        /**
        SmsSingleSender smsSingleSender = new SmsSingleSender(smsAppId, smsAppKey);
        SmsSingleSenderResult result = smsSingleSender.send(0, "86", phoneNum,
                "【简门注册】你的验证码是："+code, "", "");
        log.info(result.errMsg);
        **/
        return true;
    }
}
