package cc.mrbird.febs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.mrbird.febs.api.dao.MyVideoMapper;
import cc.mrbird.febs.api.domain.MyVideo;
import cc.mrbird.febs.api.service.MyVideoService;

/**
 * @author hw
 */
@Service
public class MyVideoServiceImpl extends ServiceImpl<MyVideoMapper, MyVideo> implements MyVideoService {

	@Autowired
	MyVideoMapper myVideoMapeer;
	@Override
	public List<MyVideo> selectListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return myVideoMapeer.selectListByUserId(userId);
	}

    
}
