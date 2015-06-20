package org.dannis.cms.service.impl;

import org.dannis.cms.manager.CarBrandManager;
import org.dannis.cms.manager.CarImageManager;
import org.dannis.cms.manager.CarManager;
import org.dannis.cms.manager.CarLevelManager;
import org.dannis.cms.model.Car;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-14 11:40
 */
@Service
public class CarServiceImpl implements CarService {
    /**
     * 汽车管理类
     */
    @Autowired
    private CarManager carManager;
    /**
     * 汽车品牌管理类
     */
    @Autowired
    private CarBrandManager carBrandManager;
    /**
     * 车型管理类
     */
    @Autowired
    private CarLevelManager carLevelManager;
    /**
     * 汽车图片管理类
     */
    @Autowired
    private CarImageManager carImageManager;

    @Override
    public void save(Car car) {
        if (null == car) {
            return;
        }
        if (null == car.getId() || -1 == car.getId()) {
            carManager.save(car);
            if (null != car.getImageUrls() && car.getImageUrls().size() > 0) {
                carImageManager.saveImages(car.getId(),car.getImageUrls());
            }
        } else {
            carManager.update(car);
        }
    }

    @Override
    public void delete(Integer id) {
        carManager.delete(id);
        carImageManager.deleteImagesByCarId(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        carManager.deleteByIds(ids);
        carImageManager.deleteImagesByCarIds(ids);
    }

    @Override
    public Car query(Integer id) {
        Car car = carManager.query(id);
        assembleCar(car);
        return car;
    }

    @Override
    public PaginationQueryResult<Car> queryByPage(QueryParams queryParams) {
        PaginationQueryResult<Car> result = new PaginationQueryResult<>();
        if (null == queryParams || null == queryParams.getParams()) {
            result.setTotal(0);
            result.setMessage("查询失败，未指定查询参数");
        } else {
            List<Car> cars = carManager.queryByPage(queryParams.getParams());
            if (null != cars && cars.size() > 0) {
                for (Car car : cars) {
                    assembleCar(car);
                }
            }
            result.setRows(cars);
            result.setTotal(carManager.queryTotal(queryParams.getParams()));
        }
        return result;
    }

    private void assembleCar(Car car) {
        if (null != car) {
            if (null != car.getBrand()) {
                car.setBrand(carBrandManager.query(car.getBrand().getId()));
            }
            if (null != car.getLevel()) {
                car.setLevel(carLevelManager.query(car.getLevel().getId()));
            }
            car.setImageUrls(carImageManager.queryImages(car.getId()));
        }
    }
}
