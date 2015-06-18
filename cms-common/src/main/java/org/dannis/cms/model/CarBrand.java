package org.dannis.cms.model;

import java.util.Date;

/**
 * 汽车品牌模型
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-13 21:39
 */
public class CarBrand {
    /**
     * ID
     */
    private Integer id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 英语名称
     */
    private String englishName;
    /**
     * 所属国家
     */
    private String country;
    /**
     * LOGO URL
     */
    private String logoUrl;
    /**
     * 备注
     */
    private String remark;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
