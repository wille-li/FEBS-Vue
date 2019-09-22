package cc.mrbird.febs.api.service.impl;

import cc.mrbird.febs.api.dao.VideoMapper;
import cc.mrbird.febs.api.domain.Course;
import cc.mrbird.febs.api.domain.Video;
import cc.mrbird.febs.api.service.VideoService;
import cc.mrbird.febs.api.vo.video.VideoQueryVO;
import cc.mrbird.febs.common.domain.FebsConstant;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Author Lin
 * @Description
 * @Date 2019/9/21 2:23 PM
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public IPage<Video> listPage(QueryRequest request, VideoQueryVO query) {
        try {
            QueryWrapper<Video> queryWrapper = new QueryWrapper<>();

            if (StringUtils.isNotBlank(query.getName())) {
                queryWrapper.lambda().like(Video::getName, query.getName());
            }
            if (query.getCourseId() != null) {
                queryWrapper.lambda().like(Video::getCourseId, query.getCourseId());
            }

            Page<Video> page = new Page<>(request.getPageNum(), request.getPageSize());
            SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, true);

            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取课程视频列表异常", e);
            return null;
        }
    }
}
