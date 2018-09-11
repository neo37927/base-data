package com.oop.api.jardatamybatissharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import com.oop.api.jardatamybatissharding.algorithm.HorRquestRecordAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 基于Druid
 */
@Configuration
public class DataBaseConfig {
    private Logger logger = LoggerFactory.getLogger(DataBaseConfig.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;

//    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setMinIdle(minIdle);
        datasource.setInitialSize(initialSize);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "root");
        reg.addInitParameter("loginPassword", "123456");
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }


    @Bean()
    @Primary
    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getHorRequestRecordTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("hor_request_record");
//        shardingRuleConfig.getBindingTableGroups().add("t_order, hor_request_record");
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
//                new InlineShardingStrategyConfiguration("uuid", "demo_ds_0"));//demo_ds_${user_id % 2}"
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(
//                new StandardShardingStrategyConfiguration("uuid", HorRquestRecordAlgorithm.class.getName()));
        Properties props = new Properties();
        props.setProperty("sql.show","true");
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig,new HashMap<>(),props);
    }

    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("demo_ds_${0..1}.t_order_${0..1}");
        orderTableRuleConfig.setKeyGeneratorColumnName("order_id");
        return orderTableRuleConfig;
    }

    TableRuleConfiguration getHorRequestRecordTableRuleConfiguration() {
        TableRuleConfiguration horRequestRecordTableRuleConfig = new TableRuleConfiguration();
        horRequestRecordTableRuleConfig.setLogicTable("hor_request_record");
        horRequestRecordTableRuleConfig.setActualDataNodes("demo_ds_0.hor_request_record_${0..1}");
        //TODO 分片策略
        ShardingStrategyConfiguration shardingStrategyConfiguration =
                new StandardShardingStrategyConfiguration("uuid", HorRquestRecordAlgorithm.class.getName());
        horRequestRecordTableRuleConfig.setTableShardingStrategyConfig(shardingStrategyConfiguration);
        return horRequestRecordTableRuleConfig;
    }

    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(2, 1);
        result.put("demo_ds_0", dataSource());
//        result.put("demo_ds_1", dataSource());
        return result;
    }

    /*  读写分离
     DataSource getMasterSlaveDataSource() throws SQLException {
     MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration();
     masterSlaveRuleConfig.setName("demo_ds_master_slave");
     masterSlaveRuleConfig.setMasterDataSourceName("demo_ds_master");
     masterSlaveRuleConfig.setSlaveDataSourceNames(Arrays.asList("demo_ds_slave_0", "demo_ds_slave_1"));
     return MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(), masterSlaveRuleConfig);
     }

     Map<String, DataSource> createDataSourceMap() {
     final Map<String, DataSource> result = new HashMap<>(3, 1);
     result.put("demo_ds_master", DataSourceUtil.createDataSource("demo_ds_master"));
     result.put("demo_ds_slave_0", DataSourceUtil.createDataSource("demo_ds_slave_0"));
     result.put("demo_ds_slave_1", DataSourceUtil.createDataSource("demo_ds_slave_1"));
     return result;
     }

     * **/
}
