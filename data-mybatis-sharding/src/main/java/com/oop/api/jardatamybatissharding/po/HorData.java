package com.oop.api.jardatamybatissharding.po;

import lombok.Data;
/**
 * HorData实体
 * 
 * @author 系统生成
 *
 */
@Data
public class HorData extends BasePo {
	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "hor_data";
	/***/
	private String  channelName = "";
	/** 对应的静态变量值*/
	public static final String FIELD_CHANNEL_NAME = "channelName";
	/***/
	private String  honorData = "";
	/** 对应的静态变量值*/
	public static final String FIELD_HONOR_DATA = "honorData";
	/**用户调用唯一标识*/
	private Integer  userId = 0;
	/**用户调用唯一标识 对应的静态变量值*/
	public static final String FIELD_USER_ID = "userId";
	/***/
	private String  honorUrl = "";
	/** 对应的静态变量值*/
	public static final String FIELD_HONOR_URL = "honorUrl";
	/**文件路径*/
	private String  filePath = "";
	/**文件路径 对应的静态变量值*/
	public static final String FIELD_FILE_PATH = "filePath";
	
}
