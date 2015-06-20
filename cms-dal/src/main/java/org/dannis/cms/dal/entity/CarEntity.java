package org.dannis.cms.dal.entity;

import java.util.Date;

/**
 * 汽车实体
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-13 01:11
 */
public class CarEntity extends AbstractEntity {
    /**
     * 品牌ID
     */
    private Integer brandId;
    /**
     * 级别ID
     */
    private Integer levelId;
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
     * 表显里程，单位万公里
     */
    private Double mileage;
    /**
     * 上牌时间
     */
    private Date registrationTime;
    /**
     * 价格，单位万元
     */
    private Double price;
    /**
     * 最低价格，单位万元
     */
    private Double lowestPrice;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 备注
     */
    private String remark;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
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

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
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
}
