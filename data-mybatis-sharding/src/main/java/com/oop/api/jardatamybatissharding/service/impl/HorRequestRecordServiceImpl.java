package com.oop.api.jardatamybatissharding.service.impl;

import com.oop.api.jardatamybatissharding.dataSource.session.DataSession;
import com.oop.api.jardatamybatissharding.po.HorRequestRecord;
import com.oop.api.jardatamybatissharding.service.HorRequestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorRequestRecordServiceImpl implements HorRequestRecordService {

    @Autowired
    private DataSession dataSession;

    @Override
    public HorRequestRecord findByUUID(String uuid) {
        HorRequestRecord horRequestRecord = dataSession.querySingleResultByUUID(HorRequestRecord.class,uuid);
        return horRequestRecord;
    }
}
