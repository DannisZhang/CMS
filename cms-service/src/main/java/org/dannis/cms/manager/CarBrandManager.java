package org.dannis.cms.manager;

import org.dannis.cms.dal.db.CarBrandMapper;
import org.dannis.cms.dal.entity.CarBrandEntity;
import org.dannis.cms.model.CarBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-14 00:30
 */
@Component
public class CarBrandManager {
    @Autowired
    private CarBrandMapper carBrandMapper;

    /**
     * 保存汽车品牌信息
     *
     * @param brand 汽车品牌信息
     */
    public void save(CarBrand brand) {
        carBrandMapper.save(convertToEntity(brand));
    }

    /**
     * 根据ID删除汽车品牌信息
     *
     * @param id 汽车品牌ID
     */
    public void delete(Integer id) {
        carBrandMapper.delete(id);
    }

    /**
     * 根据ID批量删除汽车品牌
     *
     * @param ids ID列表
     */
    public void deleteByIds(Integer[] ids) {
        carBrandMapper.deleteByIds(ids);
    }

    /**
     * 修改汽车品牌信息
     *
     * @param brand 汽车品牌信息
     */
    public void update(CarBrand brand) {
        carBrandMapper.update(convertToEntity(brand));
    }

    /**
     * 根据ID查询汽车品牌信息
     *
     * @param id 汽车品牌ID
     * @return 汽车品牌信息
     */
    public CarBrand query(Integer id) {
        return convertToModel(carBrandMapper.query(id));
    }

    /**
     * 分页查询汽车品牌信息
     *
     * @param params 查询参数
     * @return 汽车品牌信息列表
     */
    public List<CarBrand> queryByPage(Map<String, Object> params) {
        return convertToModels(carBrandMapper.queryByPage(params));
    }

    /**
     * 根据条件查询汽车品牌信息总数
     *
     * @param params 查询条件
     * @return 符合条件的汽车品牌信息总数
     */
    public Long queryTotal(Map<String, Object> params) {
        return carBrandMapper.queryTotal(params);
    }

    /**
     * 查询所有汽车品牌
     *
     * @return 汽车品牌列表
     */
    public List<CarBrand> queryAll() {
        return convertToModels(carBrandMapper.queryAll());
    }

    private CarBrandEntity convertToEntity(CarBrand brand) {
        CarBrandEntity entity = null;
        if (brand != null) {
            entity = new CarBrandEntity();
            entity.setId(brand.getId());
            entity.setName(brand.getName());
            entity.setEnglishName(brand.getEnglishName());
            entity.setCountry(brand.getCountry());
            entity.setLogoUrl(brand.getLogoUrl());
            entity.setRemark(brand.getRemark());
            entity.setCreatedBy(brand.getCreatedBy());
            entity.setLastModifiedBy(brand.getLastModifiedBy());
        }

        return entity;
    }

    private List<CarBrand> convertToModels(List<CarBrandEntity> entities) {
        List<CarBrand> brands = new ArrayList<>();
        if (null != entities && entities.size() > 0) {
            for (CarBrandEntity entity : entities) {
                brands.add(convertToModel(entity));
            }
        }

        return brands;
    }

    private CarBrand convertToModel(CarBrandEntity entity) {
        CarBrand brand = null;
        if (entity != null) {
            brand = new CarBrand();
            brand.setId(entity.getId());
            brand.setName(entity.getName());
            brand.setEnglishName(entity.getEnglishName());
            brand.setCountry(entity.getCountry());
            brand.setLogoUrl(entity.getLogoUrl());
            brand.setRemark(entity.getRemark());
            brand.setCreatedBy(entity.getCreatedBy());
            brand.setCreatedOn(entity.getCreatedOn());
            brand.setLastModifiedBy(entity.getLastModifiedBy());
            brand.setLastModifiedOn(entity.getLastModifiedOn());
        }

        return brand;
    }
}
