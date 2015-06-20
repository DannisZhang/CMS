package org.dannis.cms.vo;

import java.util.Date;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-16 21:40
 */
public class CarVO {
    /**
     * ID
     */
    private Integer id;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 车型
     */
    private String type;
    /**
     * 车系
     */
    private String series;
    /**
     * 车身结构，如两厢、三厢等
     */
    private String structure;
    /**
     * 排量
     */
    private String displacement;
    /**
     * 排放标准
     */
    private String emissionStandard;
    /**
     * 变速箱
     */
    private String gearbox;
    /**
     * 上牌时间
     */
    private String registrationTime;
    /**
     * 里程数，单位公里
     */
    private Double mileage;
    /**
     * 价格
     */
    private Double price;
    /**
     * 最低价格
     */
    private Double lowestPrice;
    /**
     * 图片地址
     */
    private String imageUrls;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 备注信息
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getEmissionStandard() {
        return emissionStandard;
    }

    public void setEmissionStandard(String emissionStandard) {
        this.emissionStandard = emissionStandard;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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
