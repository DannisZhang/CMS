package org.dannis.cms.dal.entity;

/**
 * 汽车图片实体
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-14 00:16
 */
public class CarImageEntity {
    /**
     * 汽车ID
     */
    private Integer carId;
    /**
     * 图片URL
     */
    private String imageUrl;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
