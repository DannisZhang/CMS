package org.dannis.cms.model;

/**
 * 城市模型
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-29 01:04
 */
public class City {
    /**
     * 城市编码
     */
    private String code;
    /**
     * 城市名称
     */
    private String name;

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
}
