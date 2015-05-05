package org.dannis.cms.dal.entity;

/**
 * 电话号码实体类
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 13:48
 */
public class PhoneNumberEntity extends AbstractEntity {
    /**
     * 号码类型
     */
    private String type;
    /**
     * 号码
     */
    private String number;
    /**
     * 运营商
     */
    private String operator;
    /**
     * 归属地
     */
    private String attribution;
    /**
     * 批发价
     */
    private Double wholesalePrice;
    /**
     * 底价
     */
    private Double floorPrice;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public Double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(Double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public Double getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(Double floorPrice) {
        this.floorPrice = floorPrice;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
