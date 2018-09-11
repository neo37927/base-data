package com.oop.api.jardatamybatissharding.web;

import com.alibaba.fastjson.JSON;
import com.oop.api.jardatamybatissharding.service.HorThirdChannelService;
import com.oop.api.jardatamybatissharding.po.HorThirdChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/horThirdChannel/")
public class HorThirdChannelController {

    @Autowired
    private HorThirdChannelService horThirdChannelService;

    @GetMapping(path = "findByUUID")
    @ResponseBody
    public String findByUUID(String uuid) {
        HorThirdChannel horThirdChannel = horThirdChannelService.findByUUID(uuid);

        return JSON.toJSONString(horThirdChannel);
    }
}
