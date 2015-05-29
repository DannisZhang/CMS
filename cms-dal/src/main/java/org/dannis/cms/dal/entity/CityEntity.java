package org.dannis.cms.dal.entity;

/**
 * 城市实体类
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-29 23:24
 */
public class CityEntity extends AbstractEntity {
    /**
     * 城市名称
     */
    private String name;
    /**
     * 城市编码
     */
    private String code;
    /**
     * 省份编码
     */
    private String provinceCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
