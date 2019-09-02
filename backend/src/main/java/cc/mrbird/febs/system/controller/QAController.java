package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.system.domain.QA;
import cc.mrbird.febs.system.domain.Role;
import cc.mrbird.febs.system.service.QAService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@Validated
@RestController
@RequestMapping("qa")
public class QAController  extends BaseController {

    @Autowired
    QAService qaService;

    @GetMapping("/audit")
    @RequiresPermissions("qa:view")
    public Map<String, Object> roleList(QueryRequest queryRequest, QA qa) {
        return getDataTable(qaService.findQA(queryRequest, qa));
    }

    @PostMapping("/audit/access/{id}")
    @RequiresPermissions("qa:update")
    public Integer access(@PathVariable Integer id) {
        return qaService.updateStatus(id, QA.ACCESS);
    }

    @PostMapping("/audit/reject/{id}")
    @RequiresPermissions("qa:update")
    public Integer reject(@PathVariable Integer id) {
        return qaService.updateStatus(id, QA.REJECT);
    }
}
