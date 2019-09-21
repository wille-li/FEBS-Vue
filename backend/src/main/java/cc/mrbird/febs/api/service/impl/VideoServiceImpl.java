package cc.mrbird.febs.api.service.impl;

import cc.mrbird.febs.api.dao.VideoMapper;
import cc.mrbird.febs.api.domain.Video;
import cc.mrbird.febs.api.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author Lin
 * @Description
 * @Date 2019/9/21 2:23 PM
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
}
