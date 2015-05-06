package org.dannis.cms.model;

/**
 * �ֻ�����ҵ��ģ��
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-05-04 21:56
 */
public class MobilePhoneNumber {
    /**
     * ����
     */
    private String number;
    /**
     * ��Ӫ��
     */
    private String operator;
    /**
     * ������
     */
    private String attribution;
    /**
     * ������
     */
    private Double wholesalePrice;
    /**
     * �׼�
     */
    private Double floorPrice;
    /**
     * �������
     */
    private Double balance;
    /**
     * ���ȼ�
     */
    private Integer priority;
    /**
     * ��ע
     */
    private String remark;

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
}
