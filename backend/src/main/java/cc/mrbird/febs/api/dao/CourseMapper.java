package cc.mrbird.febs.api.dao;

import cc.mrbird.febs.api.domain.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author lzz
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> hot();
}
