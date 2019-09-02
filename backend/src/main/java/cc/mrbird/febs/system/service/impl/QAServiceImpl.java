package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.domain.FebsConstant;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.domain.Tree;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.common.utils.TreeUtil;
import cc.mrbird.febs.system.dao.QAMapper;
import cc.mrbird.febs.system.domain.Dept;
import cc.mrbird.febs.system.domain.QA;
import cc.mrbird.febs.system.domain.SysLog;
import cc.mrbird.febs.system.service.QAService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("qaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QAServiceImpl extends ServiceImpl<QAMapper, QA> implements QAService {

    @Override
    public int updateStatus(Integer id, Integer status) {
        return this.baseMapper.updateStatus(id, status);
    }

    @Override
    public IPage<QA> findQA(QueryRequest request, QA qa) {
        try {
            QueryWrapper<QA> queryWrapper = new QueryWrapper<>();

            if (StringUtils.isNotBlank(qa.getContent())) {
                queryWrapper.lambda().eq(QA::getContent, qa.getContent());
            }
            if (StringUtils.isNotBlank(qa.getQuestionContent())) {
                queryWrapper.lambda().like(QA::getQuestionContent, qa.getQuestionContent());
            }
            if (StringUtils.isNotBlank(qa.getTeacherName())) {
                queryWrapper.lambda().eq(QA::getTeacherName, qa.getTeacherName());
            }

            Page<QA> page = new Page<>(request.getPageNum(), request.getPageSize());
            SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, true);

            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取问答异常", e);
            return null;
        }
    }
}
