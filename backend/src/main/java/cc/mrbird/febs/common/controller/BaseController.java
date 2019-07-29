package cc.mrbird.febs.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
        Map<String, Object> rspData = new HashMap<>();
        if (pageInfo != null) {
            rspData.put("rows", pageInfo.getRecords());
            rspData.put("total", pageInfo.getTotal());
        } else {
            rspData.put("rows", null);
            rspData.put("total", 0);
        }

        return rspData;
    }


}
