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
     * 品牌
     */
    private CarBrand brand;
    /**
     * 车型
     */
    private CarType type;
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
     * 车龄
     */
    private Integer age;
    /**
     * 里程数，单位公里
     */
    private Integer mileage;
    /**
     * 价格
     */
    private Double price;
    /**
     * 图片地址
     */
    private List<String> imageUrls;

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
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

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
