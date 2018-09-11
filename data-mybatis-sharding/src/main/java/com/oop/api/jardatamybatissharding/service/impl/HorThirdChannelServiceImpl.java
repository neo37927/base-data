package com.oop.api.jardatamybatissharding.service.impl;

import com.oop.api.jardatamybatissharding.dataSource.session.DataSession;
import com.oop.api.jardatamybatissharding.po.HorThirdChannel;
import com.oop.api.jardatamybatissharding.service.HorThirdChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorThirdChannelServiceImpl implements HorThirdChannelService {

    @Autowired
    private DataSession dataSession;

    @Override
    public HorThirdChannel findByUUID(String uuid) {
        return dataSession.querySingleResultByUUID(HorThirdChannel.class,uuid);
    }
}
