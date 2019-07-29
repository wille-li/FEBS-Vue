package cc.mrbird.febs.api.service;

import cc.mrbird.febs.api.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author lzz
 */
public interface CourseService extends IService<Course> {

    List<Course> hot();
}
