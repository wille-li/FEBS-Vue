package cc.mrbird.febs.system.dao;

import cc.mrbird.febs.system.domain.QA;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface QAMapper extends BaseMapper<QA> {

    int updateStatus(@Param("id")Integer id, @Param("status")Integer status);

}
