package org.dannis.cms.model;

import java.util.Date;
import java.util.List;

/**
 * 汽车模型
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-12 21:06
 */
public class Car {
    /**
     * ID
     */
    private Integer id;
    /**
     * 品牌
     */
    private CarBrand brand;
    /**
     * 级别
     */
    private CarLevel level;
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
    private Date registrationTime;
    /**
     * 里程数，单位公里
     */
    private Integer mileage;
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
    private List<String> imageUrls;
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

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public CarLevel getLevel() {
        return level;
    }

    public void setLevel(CarLevel level) {
        this.level = level;
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

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
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
