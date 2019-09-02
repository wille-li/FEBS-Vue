package cc.mrbird.febs.system.service;

import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.system.domain.Dict;
import cc.mrbird.febs.system.domain.QA;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface QAService extends IService<QA> {

    int updateStatus(Integer id, Integer status);

    IPage<QA> findQA(QueryRequest request, QA dict);
}
