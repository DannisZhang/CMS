package org.dannis.cms.model;

import java.util.List;

/**
 * 省份模型
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-29 01:04
 */
public class Province {
    /**
     * 省份编码
     */
    private String code;
    /**
     * 省份名称
     */
    private String name;
    /**
     * 城市列表
     */
    private List<City> cities;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
