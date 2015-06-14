package org.dannis.cms.manager;

import org.dannis.cms.dal.db.CarImageMapper;
import org.dannis.cms.dal.entity.CarImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-14 00:30
 */
@Component
public class CarImageManager {
    @Autowired
    private CarImageMapper carImageMapper;

    /**
     * 批量保存汽车图片
     *
     * @param carId 汽车ID
     * @param imageUrls 汽车图片列表
     */
    public void saveImages(Integer carId,List<String> imageUrls) {
        List<CarImageEntity> images = new ArrayList<>();
        if (null != carId && imageUrls != null && imageUrls.size() > 0) {
            for (String imageUrl : imageUrls) {
                CarImageEntity entity = new CarImageEntity();
                entity.setCarId(carId);
                entity.setImageUrl(imageUrl);
                images.add(entity);
            }
        }
        carImageMapper.saveImages(images);
    }

    /**
     * 根据汽车ID删除图片
     *
     * @param carId 汽车ID
     */
    public void deleteImages(Integer carId) {
        carImageMapper.deleteImages(carId);
    }

    /**
     * 根据汽车ID查询汽车图片
     *
     * @param carId 汽车ID
     * @return 汽车图片列表
     */
    public List<String> queryImages(Integer carId) {
        return carImageMapper.queryImages(carId);
    }
}
