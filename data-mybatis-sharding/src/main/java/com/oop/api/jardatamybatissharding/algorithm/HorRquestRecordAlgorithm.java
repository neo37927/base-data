package com.oop.api.jardatamybatissharding.algorithm;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class HorRquestRecordAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<String> preciseShardingValue) {
        char lastChar = preciseShardingValue.getValue().charAt(preciseShardingValue.getValue().length()-1);

        for (String each : tableNames) {
            if (each.endsWith(Integer.valueOf(String.valueOf(lastChar),16)/2+"")) {
                return each;
            }
        }
        //TODO 分表分库异常
        throw new IllegalArgumentException();
    }
}
