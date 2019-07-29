package cc.mrbird.febs.api.service.impl;

import cc.mrbird.febs.api.domain.Course;
import cc.mrbird.febs.api.dao.CourseMapper;
import cc.mrbird.febs.api.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
