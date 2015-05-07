package org.dannis.cms.dal.entity;

import java.util.Date;

/**
 * 基本实体类
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 13:49
 */
public abstract class AbstractEntity {
    /**
     * ID
     */
    private Integer id;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createdOn;
    /**
     * 创建人
     */
    private Integer createdBy;
    /**
     * 最近修改时间
     */
    private Date lastModifiedOn;
    /**
     * 最近修改人
     */
    private Integer lastModifiedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
