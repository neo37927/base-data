package com.oop.api.jardatamybatissharding.po;

import lombok.Data;
/**
 * HorRequestRecord实体
 * 
 * @author 系统生成
 *
 */
@Data
public class HorRequestRecord extends BasePo {
	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "hor_request_record";
	/**平台用户ID*/
	private Integer  platformId = 0;
	/**平台用户ID 对应的静态变量值*/
	public static final String FIELD_PLATFORM_ID = "platformId";
	/**第三方类型HonorType.name()*/
	private String  channelName = "";
	/**第三方类型HonorType.name() 对应的静态变量值*/
	public static final String FIELD_CHANNEL_NAME = "channelName";
	/**是否成功: 0失败，1成功，2第三方失败*/
	private Integer  isSuccess = 0;
	/**是否成功: 0失败，1成功，2第三方失败 对应的静态变量值*/
	public static final String FIELD_IS_SUCCESS = "isSuccess";
	/***/
	private Integer  timeSpend = 0;
	/** 对应的静态变量值*/
	public static final String FIELD_TIME_SPEND = "timeSpend";
	/**是否为新请求*/
	private Integer  isNewRequest = 0;
	/**是否为新请求 对应的静态变量值*/
	public static final String FIELD_IS_NEW_REQUEST = "isNewRequest";
	
}
