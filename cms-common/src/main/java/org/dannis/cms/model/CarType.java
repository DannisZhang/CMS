package org.dannis.cms.model;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-13 21:35
 */
public class CarType {
    /**
     * ID
     */
    private Integer id;
    /**
     * 车型名称
     */
    private String name;
    /**
     * 英语名称
     */
    private String englishName;
    /**
     * 车型简称
     */
    private String shortName;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
