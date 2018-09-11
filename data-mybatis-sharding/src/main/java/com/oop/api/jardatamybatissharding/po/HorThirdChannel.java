package com.oop.api.jardatamybatissharding.po;

import lombok.Data;
/**
 * HorThirdChannel实体
 * 
 * @author 系统生成
 *
 */
@Data
public class HorThirdChannel extends BasePo {
	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "hor_third_channel";
	/**第三方征信渠道说明,描述*/
	private String  description = "";
	/**第三方征信渠道说明,描述 对应的静态变量值*/
	public static final String FIELD_DESCRIPTION = "description";
	/**启用状态: 0:未启用, 1: 启用*/
	private Integer  status = 0;
	/**启用状态: 0:未启用, 1: 启用 对应的静态变量值*/
	public static final String FIELD_STATUS = "status";
	/**渠道编号HonorType.name()*/
	private String  code = "";
	/**渠道编号HonorType.name() 对应的静态变量值*/
	public static final String FIELD_CODE = "code";
	/**第三方征信渠道名称*/
	private String  channelName = "";
	/**第三方征信渠道名称 对应的静态变量值*/
	public static final String FIELD_CHANNEL_NAME = "channelName";
	/**权重*/
	private Integer  weight = 0;
	/**权重 对应的静态变量值*/
	public static final String FIELD_WEIGHT = "weight";
	
}
