package com.oop.api.reverseengineering.core.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Column {
    /**变量名*/
    private String name;
    /**对应的静态变量名*/
    private String staticFinalName;
    /**首字母大写*/
    private String firstUpperName;
    /**数据库字段原始名*/
    private String dbName;
    /**类型*/
    private String type;
    /**数据库字段原始类型*/
    private String dbType;
    /**sqlmap中对应的类型*/
    private String sqlMapColumnType;
    /**描述*/
    private String remarks;
    /**默认值*/
    private String defaultValue;
}
