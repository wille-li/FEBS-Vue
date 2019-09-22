package cc.mrbird.febs.api.service.impl;

import cc.mrbird.febs.api.dao.CourseMapper;
import cc.mrbird.febs.api.domain.Course;
import cc.mrbird.febs.api.service.CourseService;
import cc.mrbird.febs.api.vo.course.CourseQueryVO;
import cc.mrbird.febs.common.domain.FebsConstant;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzz
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public List<Course> hot() {
        return baseMapper.hot();
    }

    @Override
    public IPage<Course> listPage(QueryRequest request, CourseQueryVO query) {
        try {
            QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

            if (StringUtils.isNotBlank(query.getSubTitle())) {
                queryWrapper.lambda().like(Course::getSubTitle, query.getSubTitle());
            }
            if (StringUtils.isNotBlank(query.getTitle())) {
                queryWrapper.lambda().like(Course::getTitle, query.getTitle());
            }
            if (StringUtils.isNotBlank(query.getTeacherName()) && query.getTeacherId() != null) {
                queryWrapper.lambda().eq(Course::getTeacherId, query.getTeacherId());
            }

            Page<Course> page = new Page<>(request.getPageNum(), request.getPageSize());
            SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, true);

            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取课程列表异常", e);
            return null;
        }
    }

    @Override
    public void deleteCourses(String[] courseIds) {
        List<String> list = Arrays.asList(courseIds);
        this.baseMapper.deleteBatchIds(list);
    }
}
