package cc.mrbird.febs.api.dao;

import cc.mrbird.febs.api.domain.WxThird;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * WxThirdMapper继承基类
 */
@Repository
public interface WxThirdMapper extends BaseMapper<WxThird> {


    Integer selectCountByWxOpenId(String openId);

}