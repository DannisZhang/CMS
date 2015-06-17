package org.dannis.cms.dal.entity;

/**
 * 汽车品牌实体
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-13 21:59
 */
public class CarBrandEntity extends AbstractEntity {
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
}
