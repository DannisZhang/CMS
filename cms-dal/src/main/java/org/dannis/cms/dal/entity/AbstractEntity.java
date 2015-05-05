package org.dannis.cms.dal.entity;

import java.util.Date;

/**
 * ����ʵ����
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
     * ����ʱ��
     */
    private Date createdOn;
    /**
     * ������
     */
    private Integer createdBy;
    /**
     * ����޸�ʱ��
     */
    private Date lastModifiedOn;
    /**
     * ����޸���
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
