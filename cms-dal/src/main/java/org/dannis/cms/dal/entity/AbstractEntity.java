package org.dannis.cms.dal.entity;

import java.util.Date;

/**
 * 抽象实体类
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
     * 创建时间
     */
    private Date createdOn;
    /**
     * 创建人
     */
    private Integer createdBy;
    /**
     * 最后修改时间
     */
    private Date lastModifiedOn;
    /**
     * 最后修改人
     */
    private Integer lastModifiedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
