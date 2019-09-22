package cc.mrbird.febs.api.service;

import cc.mrbird.febs.api.domain.Course;
import cc.mrbird.febs.api.vo.course.CourseQueryVO;
import cc.mrbird.febs.common.domain.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author lzz
 */
public interface CourseService extends IService<Course> {

    List<Course> hot();

    IPage<Course> listPage(QueryRequest request, CourseQueryVO query);

    void deleteCourses(String[] courseIds);
}
