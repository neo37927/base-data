package com.oop.api.jardatamybatissharding.web;

import com.alibaba.fastjson.JSON;
import com.oop.api.jardatamybatissharding.service.HorRequestRecordService;
import com.oop.api.jardatamybatissharding.po.HorRequestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/horRequestRecord/")
public class HorRequestRecordController {

    @Autowired
    private HorRequestRecordService horRequestRecordService;

    @GetMapping(path = "findByUUID")
    @ResponseBody
    public String findByUUID(String uuid) {
        HorRequestRecord horRequestRecord = horRequestRecordService.findByUUID(uuid);

        return JSON.toJSONString(horRequestRecord);
    }
}
