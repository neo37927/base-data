package com.oop.api.jardatamybatissharding.demo;

import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.InlineShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ShardingTableDemo {


    private final DataSource dataSource;

    @Autowired
    public ShardingTableDemo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void getDataSourceMap() {
        try {
            // 配置真实数据源
            Map<String, DataSource> dataSourceMap = new HashMap<>();

            // 配置数据源
            dataSourceMap.put("ds_0", dataSource);
            dataSourceMap.put("ds_1", dataSource);

            // 配置Order表规则
            TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
            orderTableRuleConfig.setLogicTable("t_order");
            orderTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_${0..1}");

            // 配置分库策略
            orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds_${user_id % 2}"));

            // 配置分表策略
            orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_${order_id % 2}"));

            // 配置分片规则
            ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
            shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

            // 省略配置order_item表规则...

            // 获取数据源对象
            DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap(), new Properties());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
