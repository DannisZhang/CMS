package org.dannis.cms.dal.entity;

/**
 * 省份实体类
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-29 23:26
 */
public class ProvinceEntity extends AbstractEntity {
    /**
     * 省份名称
     */
    private String name;
    /**
     * 省份编码
     */
    private String code;

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
}
