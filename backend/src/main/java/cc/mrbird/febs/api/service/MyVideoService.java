package cc.mrbird.febs.api.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cc.mrbird.febs.api.domain.MyVideo;

/**
 * 
 * @author hw
 *
 */
public interface MyVideoService extends IService<MyVideo> {

	List<MyVideo> selectListByUserId(Integer userId);

}
