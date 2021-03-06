package org.dannis.cms.manager;

import org.dannis.cms.common.Constants;
import org.dannis.cms.dal.db.CarMapper;
import org.dannis.cms.dal.entity.CarEntity;
import org.dannis.cms.model.Car;
import org.dannis.cms.model.CarBrand;
import org.dannis.cms.model.CarLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-14 00:29
 */
@Component
public class CarManager {
    @Autowired
    private CarMapper carMapper;

    /**
     * 保存汽车信息
     *
     * @param car 汽车信息
     */
    public void save(Car car) {
        CarEntity entity = convertToEntity(car);
        carMapper.save(entity);
        car.setId(entity.getId());
    }

    /**
     * 根据ID删除汽车信息
     *
     * @param id 汽车ID
     */
    public void delete(Integer id) {
        carMapper.delete(id);
    }

    /**
     * 根据ID批量删除汽车信息
     *
     * @param ids ID列表
     */
    public void deleteByIds(Integer[] ids) {
        carMapper.deleteByIds(ids);
    }

    /**
     * 修改汽车信息
     *
     * @param car 汽车信息
     */
    public void update(Car car) {
        carMapper.update(convertToEntity(car));
    }

    /**
     * 根据ID查询汽车信息
     *
     * @param id 汽车ID
     * @return 汽车信息
     */
    public Car query(Integer id) {
        return convertToModel(carMapper.query(id));
    }

    /**
     * 分页查询汽车信息
     *
     * @param params 查询参数
     * @return 汽车信息列表
     */
    public List<Car> queryByPage(Map<String, Object> params) {
        return convertToModels(carMapper.queryByPage(params));
    }

    /**
     * 根据条件查询汽车信息总数
     *
     * @param params 查询条件
     * @return 符合条件的汽车信息总数
     */
    public Long queryTotal(Map<String, Object> params) {
        return carMapper.queryTotal(params);
    }

    private CarEntity convertToEntity(Car car) {
        CarEntity entity = null;
        if (car != null) {
            entity = new CarEntity();
            entity.setId(car.getId());
            if (car.getBrand() != null) {
                entity.setBrandId(car.getBrand().getId());
            }
            if (car.getLevel() != null) {
                entity.setLevelId(car.getLevel().getId());
            }
            entity.setSeries(car.getSeries());
            entity.setStructure(car.getStructure());
            entity.setDisplacement(car.getDisplacement());
            entity.setEmissionStandard(car.getEmissionStandard());
            entity.setGearbox(car.getGearbox());
            entity.setRegistrationTime(car.getRegistrationTime());
            entity.setMileage(car.getMileage());
            entity.setPrice(car.getPrice());
            entity.setLowestPrice(car.getLowestPrice());
            entity.setPriority(car.getPriority());
            entity.setRemark(car.getRemark());
            entity.setStatus(Constants.ACTIVE);
            entity.setCreatedBy(car.getCreatedBy());
            entity.setCreatedOn(car.getCreatedOn());
            entity.setLastModifiedBy(car.getLastModifiedBy());
            entity.setLastModifiedOn(car.getLastModifiedOn());
        }

        return entity;
    }

    private List<Car> convertToModels(List<CarEntity> entities) {
        List<Car> cars = new ArrayList<>();
        if (null != entities && entities.size() > 0) {
            for (CarEntity entity : entities) {
                cars.add(convertToModel(entity));
            }
        }

        return cars;
    }

    private Car convertToModel(CarEntity entity) {
        Car car = null;
        if (entity != null) {
            car = new Car();
            car.setId(entity.getId());
            if (entity.getBrandId() != null) {
                CarBrand brand = new CarBrand();
                brand.setId(entity.getBrandId());
                car.setBrand(brand);
            }
            if (entity.getLevelId() != null) {
                CarLevel level = new CarLevel();
                level.setId(entity.getLevelId());
                car.setLevel(level);
            }
            car.setSeries(entity.getSeries());
            car.setStructure(entity.getStructure());
            car.setDisplacement(entity.getDisplacement());
            car.setEmissionStandard(entity.getEmissionStandard());
            car.setGearbox(entity.getGearbox());
            car.setRegistrationTime(entity.getRegistrationTime());
            car.setMileage(entity.getMileage());
            car.setPrice(entity.getPrice());
            car.setLowestPrice(entity.getLowestPrice());
            car.setPriority(entity.getPriority());
            car.setRemark(entity.getRemark());
            car.setCreatedBy(entity.getCreatedBy());
            car.setCreatedOn(entity.getCreatedOn());
            car.setLastModifiedBy(entity.getLastModifiedBy());
            car.setLastModifiedOn(entity.getLastModifiedOn());
        }

        return car;
    }
}
