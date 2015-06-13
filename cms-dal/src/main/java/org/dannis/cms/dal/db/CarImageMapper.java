package org.dannis.cms.dal.db;

import org.dannis.cms.dal.entity.CarImageEntity;

import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-14 00:15
 */
public interface CarImageMapper {
    /**
     * 批量保存汽车图片
     *
     * @param images 汽车图片列表
     */
    void saveImages(List<CarImageEntity> images);

    /**
     * 根据汽车ID删除图片
     *
     * @param carId 汽车ID
     */
    void deleteImages(Integer carId);

    /**
     * 根据汽车ID查询汽车图片
     *
     * @param carId 汽车ID
     * @return 汽车图片列表
     */
    List<String> queryImages(Integer carId);
}
