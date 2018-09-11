package com.oop.api.jardatamybatissharding.po;

import com.oop.api.jardatamybatissharding.po.BasePo;
import lombok.Data;
import  java.util.Date;
/**
 * HorInfoValidation实体
 * 
 * @author 系统生成
 *
 */
@Data
public class HorInfoValidation extends BasePo {
	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "HOR_INFO_VALIDATION";
	/**删除标志*/
	private Integer  isDelete = 0;
	/**删除标志 对应的静态变量值*/
	public static final String FIELD_IS_DELETE = "isDelete";
	/**删除标记*/
	private Integer  deleteFlag = 0;
	/**删除标记 对应的静态变量值*/
	public static final String FIELD_DELETE_FLAG = "deleteFlag";
	/**删除备注*/
	private String  deleteRemark = "";
	/**删除备注 对应的静态变量值*/
	public static final String FIELD_DELETE_REMARK = "deleteRemark";
	/**合同号*/
	private String  boId = "";
	/**合同号 对应的静态变量值*/
	public static final String FIELD_BO_ID = "boId";
	/**预发标号*/
	private String  bpId = "";
	/**预发标号 对应的静态变量值*/
	public static final String FIELD_BP_ID = "bpId";
	/**用户ID*/
	private Integer  userId = 0;
	/**用户ID 对应的静态变量值*/
	public static final String FIELD_USER_ID = "userId";
	/**用户名*/
	private String  userName = "";
	/**用户名 对应的静态变量值*/
	public static final String FIELD_USER_NAME = "userName";
	/**真实姓名*/
	private String  realName = "";
	/**真实姓名 对应的静态变量值*/
	public static final String FIELD_REAL_NAME = "realName";
	/**来源：1.内部，2.外部*/
	private Integer  source = 0;
	/**来源：1.内部，2.外部 对应的静态变量值*/
	public static final String FIELD_SOURCE = "source";
	/**产品id*/
	private Integer  pId = 0;
	/**产品id 对应的静态变量值*/
	public static final String FIELD_P_ID = "pId";
	/**产品名*/
	private String  pName = "";
	/**产品名 对应的静态变量值*/
	public static final String FIELD_P_NAME = "pName";
	/**学校/单位省份编号*/
	private String  provinceCode = "";
	/**学校/单位省份编号 对应的静态变量值*/
	public static final String FIELD_PROVINCE_CODE = "provinceCode";
	/**学校/单位省份名称*/
	private String  provinceName = "";
	/**学校/单位省份名称 对应的静态变量值*/
	public static final String FIELD_PROVINCE_NAME = "provinceName";
	/**黑名单类型（枚举）*/
	private String  type = "";
	/**黑名单类型（枚举） 对应的静态变量值*/
	public static final String FIELD_TYPE = "type";
	/**数据类型（枚举）*/
	private Integer  usertype = 0;
	/**数据类型（枚举） 对应的静态变量值*/
	public static final String FIELD_USERTYPE = "usertype";
	/**创建人id*/
	private Integer  creatorId = 0;
	/**创建人id 对应的静态变量值*/
	public static final String FIELD_CREATOR_ID = "creatorId";
	/**创建人用户名*/
	private String  creatorUserName = "";
	/**创建人用户名 对应的静态变量值*/
	public static final String FIELD_CREATOR_USER_NAME = "creatorUserName";
	/**创建人姓名*/
	private String  creatorRealName = "";
	/**创建人姓名 对应的静态变量值*/
	public static final String FIELD_CREATOR_REAL_NAME = "creatorRealName";
	/**更新人id*/
	private Integer  updatorId = 0;
	/**更新人id 对应的静态变量值*/
	public static final String FIELD_UPDATOR_ID = "updatorId";
	/**更新人用户名*/
	private String  updatorUserName = "";
	/**更新人用户名 对应的静态变量值*/
	public static final String FIELD_UPDATOR_USER_NAME = "updatorUserName";
	/**更新人姓名*/
	private String  updatorRealName = "";
	/**更新人姓名 对应的静态变量值*/
	public static final String FIELD_UPDATOR_REAL_NAME = "updatorRealName";
	/**身份证号码*/
	private String  idNum = "";
	/**身份证号码 对应的静态变量值*/
	public static final String FIELD_ID_NUM = "idNum";
	/**手机号码*/
	private String  mobileNum = "";
	/**手机号码 对应的静态变量值*/
	public static final String FIELD_MOBILE_NUM = "mobileNum";
	/**家庭地址*/
	private String  homeAddress = "";
	/**家庭地址 对应的静态变量值*/
	public static final String FIELD_HOME_ADDRESS = "homeAddress";
	/**居住地址*/
	private String  currentAddress = "";
	/**居住地址 对应的静态变量值*/
	public static final String FIELD_CURRENT_ADDRESS = "currentAddress";
	/**学校/单位名称*/
	private String  schoolCompanyName = "";
	/**学校/单位名称 对应的静态变量值*/
	public static final String FIELD_SCHOOL_COMPANY_NAME = "schoolCompanyName";
	/**学校/单位地址*/
	private String  schoolCompanyAddress = "";
	/**学校/单位地址 对应的静态变量值*/
	public static final String FIELD_SCHOOL_COMPANY_ADDRESS = "schoolCompanyAddress";
	/**学校/单位电话*/
	private String  schoolCompanyPhone = "";
	/**学校/单位电话 对应的静态变量值*/
	public static final String FIELD_SCHOOL_COMPANY_PHONE = "schoolCompanyPhone";
	/**qq号*/
	private String  qqNum = "";
	/**qq号 对应的静态变量值*/
	public static final String FIELD_QQ_NUM = "qqNum";
	/**邮箱*/
	private String  email = "";
	/**邮箱 对应的静态变量值*/
	public static final String FIELD_EMAIL = "email";
	/**亲属手机*/
	private String  parentPhone = "";
	/**亲属手机 对应的静态变量值*/
	public static final String FIELD_PARENT_PHONE = "parentPhone";
	/**辅导员手机*/
	private String  counsellorPhone = "";
	/**辅导员手机 对应的静态变量值*/
	public static final String FIELD_COUNSELLOR_PHONE = "counsellorPhone";
	/**联系人手机*/
	private String  contact1Phone = "";
	/**联系人手机 对应的静态变量值*/
	public static final String FIELD_CONTACT1_PHONE = "contact1Phone";
	/**联系人手机*/
	private String  contact2Phone = "";
	/**联系人手机 对应的静态变量值*/
	public static final String FIELD_CONTACT2_PHONE = "contact2Phone";
	/**联系人手机*/
	private String  contact3Phone = "";
	/**联系人手机 对应的静态变量值*/
	public static final String FIELD_CONTACT3_PHONE = "contact3Phone";
	/**电话*/
	private String  tel = "";
	/**电话 对应的静态变量值*/
	public static final String FIELD_TEL = "tel";
	/**满标时间*/
	private Date  boFinishTime = new Date();
	/**满标时间 对应的静态变量值*/
	public static final String FIELD_BO_FINISH_TIME = "boFinishTime";
	/**逾期天数*/
	private Integer  boOverdueDayCount = 0;
	/**逾期天数 对应的静态变量值*/
	public static final String FIELD_BO_OVERDUE_DAY_COUNT = "boOverdueDayCount";
	/**来源表*/
	private String  libName = "";
	/**来源表 对应的静态变量值*/
	public static final String FIELD_LIB_NAME = "libName";
	/**提交时间*/
	private Date  submitTime = new Date();
	/**提交时间 对应的静态变量值*/
	public static final String FIELD_SUBMIT_TIME = "submitTime";
	/**渠道类型*/
	private String  approachType = "";
	/**渠道类型 对应的静态变量值*/
	public static final String FIELD_APPROACH_TYPE = "approachType";
	/**渠道名称*/
	private String  approachName = "";
	/**渠道名称 对应的静态变量值*/
	public static final String FIELD_APPROACH_NAME = "approachName";
	/**版本*/
	private Integer  version = 0;
	/**版本 对应的静态变量值*/
	public static final String FIELD_VERSION = "version";
	
}
