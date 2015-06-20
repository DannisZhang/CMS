package org.dannis.cms.service.impl;

import org.dannis.cms.manager.CarBrandManager;
import org.dannis.cms.model.CarBrand;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-18 21:25
 */
@Service
public class CarBrandServiceImpl implements CarBrandService {
    /**
     * 汽车品牌管理类
     */
    @Autowired
    private CarBrandManager carBrandManager;

    @Override
    public void save(CarBrand brand) {
        carBrandManager.save(brand);
    }

    @Override
    public void delete(Integer id) {
        carBrandManager.delete(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        carBrandManager.deleteByIds(ids);
    }

    @Override
    public void update(CarBrand brand) {
        carBrandManager.update(brand);
    }

    @Override
    public CarBrand query(Integer id) {
        return carBrandManager.query(id);
    }

    @Override
    public PaginationQueryResult<CarBrand> queryByPage(QueryParams queryParams) {
        PaginationQueryResult<CarBrand> result = new PaginationQueryResult<>();
        if (null == queryParams || null == queryParams.getParams()) {
            result.setTotal(0);
            result.setMessage("查询失败，未指定查询参数");
        } else {
            result.setRows(carBrandManager.queryByPage(queryParams.getParams()));
            result.setTotal(carBrandManager.queryTotal(queryParams.getParams()));
        }
        return result;
    }

    @Override
    public List<CarBrand> queryAll() {
        return carBrandManager.queryAll();
    }
}
