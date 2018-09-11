package com.oop.api.jardatamybatissharding.service;


import com.oop.api.jardatamybatissharding.po.HorRequestRecord;

public interface HorRequestRecordService {
    HorRequestRecord findByUUID(String uuid);
}
