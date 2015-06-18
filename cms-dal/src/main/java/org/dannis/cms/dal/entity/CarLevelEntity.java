package org.dannis.cms.dal.entity;

/**
 * 汽车级别实体
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-13 01:09
 */
public class CarLevelEntity extends AbstractEntity {
    /**
     * 级别名称
     */
    private String name;
    /**
     * 英语名称
     */
    private String englishName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
