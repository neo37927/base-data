package com.oop.api.jardatamybatissharding.po;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class BasePo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    public static String FIELD_ID = "id";
    private String uuid;
    public static String FIELD_UUID = "uuid";
    private int createUser = 0;
    public static String FIELD_CREATE_USER = "createUser";
    private Date createTime;
    public static String FIELD_CREATE_TIME = "createTime";
    private int updateUser = 0;
    public static String FIELD_UPDATE_USER = "updateUser";
    private Date updateTime;
    public static String FIELD_UPDATE_TIME = "updateTime";
    private Integer disabled = Integer.valueOf(0);
    public static String FIELD_DISABLED = "disabled";
    private String remark = "";
    public static String FIELD_REMARK = "remark";
    private int orderBy;
    public static String FIELD_ORDER_BY = "orderBy";

    public BasePo() {
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public int getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getDisabled() {
        return this.disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public static <T> T getInstance(Class<T> clazz) {
        BasePo o = null;

        try {
            o = (BasePo)clazz.newInstance();
            o.setUuid(UUIDProvider.uuid());
            o.setDisabled(DeleteStatus.ENABLED);
            o.setCreateTime(new Date());
            o.setUpdateTime(new Date());
            return clazz.cast(o);
        } catch (InstantiationException var3) {
            var3.printStackTrace();
        } catch (IllegalAccessException var4) {
            var4.printStackTrace();
        }

        return null;
    }
    public interface DeleteStatus {
        Integer DISABLED = Integer.valueOf(1);
        Integer ENABLED = Integer.valueOf(0);
    }
    public static class UUIDProvider {
        public UUIDProvider() {
        }

        public static String uuid() {
            return UUID.randomUUID().toString().replace("-", "").toUpperCase();
        }
    }
}