package org.dannis.cms.manager;

import org.dannis.cms.dal.db.CarTypeMapper;
import org.dannis.cms.dal.entity.CarTypeEntity;
import org.dannis.cms.model.CarType;
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
public class CarTypeManager {
    @Autowired
    private CarTypeMapper carTypeMapper;

    /**
     * 保存车型信息
     *
     * @param brand 车型信息
     */
    public void save(CarType brand) {
        carTypeMapper.save(convertToEntity(brand));
    }

    /**
     * 根据ID删除车型信息
     *
     * @param id 车型ID
     */
    public void delete(Integer id) {
        carTypeMapper.delete(id);
    }

    /**
     * 修改车型信息
     *
     * @param brand 车型信息
     */
    public void update(CarType brand) {
        carTypeMapper.update(convertToEntity(brand));
    }

    /**
     * 根据ID查询车型信息
     *
     * @param id 车型ID
     * @return 车型信息
     */
    public CarType query(Integer id) {
        return convertToModel(carTypeMapper.query(id));
    }

    /**
     * 分页查询车型信息
     *
     * @param params 查询参数
     * @return 车型信息列表
     */
    public List<CarType> queryByPage(Map<String, Object> params) {
        return convertToModels(carTypeMapper.queryByPage(params));
    }

    /**
     * 根据条件查询车型信息总数
     *
     * @param params 查询条件
     * @return 符合条件的车型信息总数
     */
    public Long queryTotal(Map<String, Object> params) {
        return carTypeMapper.queryTotal(params);
    }

    private CarTypeEntity convertToEntity(CarType brand) {
        CarTypeEntity entity = null;
        if (brand != null) {
            entity = new CarTypeEntity();
            entity.setId(brand.getId());
            entity.setName(brand.getName());
            entity.setEnglishName(brand.getEnglishName());
        }

        return entity;
    }

    private List<CarType> convertToModels(List<CarTypeEntity> entities) {
        List<CarType> brands = new ArrayList<>();
        if (null != entities && entities.size() > 0) {
            for (CarTypeEntity entity : entities) {
                brands.add(convertToModel(entity));
            }
        }

        return brands;
    }

    private CarType convertToModel(CarTypeEntity entity) {
        CarType brand = null;
        if (entity != null) {
            brand = new CarType();
            brand.setId(entity.getId());
            brand.setName(entity.getName());
            brand.setEnglishName(entity.getEnglishName());
        }

        return brand;
    }
}
