package com.entity.po;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Slf4j
@Data
public class BasePo<T> implements Serializable{
    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;
    /** 唯一标识  */
    private int id;
    public static String FIELD_ID = "id";
    /** 唯一标识uuid  */
    private String uuid;
    public static String FIELD_UUID = "uuid";
    /** 创建人 */
    private int createUser;
    public static String FIELD_CREATE_USER = "createUser";
    /** 创建时间 */
    private Date createTime;
    public static String FIELD_CREATE_TIME = "createTime";
    /** 修改人 */
    private int updateUser;
    public static String FIELD_UPDATE_USER = "updateUser";
    /** 修改人时间  */
    private Date updateTime;
    public static String FIELD_UPDATE_TIME = "updateTime";
    /** 是否启用 */
    private Integer disabled;
    public static String FIELD_DISABLED = "disabled";
    /** 描述 */
    private String remark;
    public static String FIELD_REMARK = "remark";
    /** 排序 */
    private int orderBy;
    public static String FIELD_ORDER_BY = "orderBy";

    public static <T> T getInstance(Class<T> clazz) {
        BasePo o = null;
        try {
            o = (BasePo) clazz.newInstance();
            //TODO 集成ID生成器
            o.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
            //TODO 字典
            o.setDisabled(0);
            o.setCreateTime(new Date());
            o.setUpdateTime(new Date());
            return clazz.cast(o);
        } catch (InstantiationException e) {
            log.error(e.getMessage(),e.getCause());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(),e.getCause());
        }
        return null;
    }
}
