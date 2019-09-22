package cc.mrbird.febs.api.service;

import cc.mrbird.febs.api.domain.Video;
import cc.mrbird.febs.api.vo.video.VideoQueryVO;
import cc.mrbird.febs.common.domain.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author Lin
 * @Description
 * @Date 2019/9/21 2:22 PM
 */
public interface VideoService extends IService<Video> {

    IPage<Video> listPage(QueryRequest request, VideoQueryVO query);
}
