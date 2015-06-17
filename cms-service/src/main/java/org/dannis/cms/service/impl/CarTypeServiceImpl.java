package org.dannis.cms.service.impl;

import org.dannis.cms.manager.CarTypeManager;
import org.dannis.cms.model.CarType;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-17 21:21
 */
@Service
public class CarTypeServiceImpl implements CarTypeService {
    /**
     * 车型管理类
     */
    @Autowired
    private CarTypeManager carTypeManager;

    @Override
    public void save(CarType type) {
        carTypeManager.save(type);
    }

    @Override
    public void delete(Integer id) {
        carTypeManager.delete(id);
    }

    @Override
    public void update(CarType type) {
        carTypeManager.update(type);
    }

    @Override
    public CarType query(Integer id) {
        return carTypeManager.query(id);
    }

    @Override
    public PaginationQueryResult<CarType> queryByPage(QueryParams queryParams) {
        PaginationQueryResult<CarType> result = new PaginationQueryResult<>();
        if (null == queryParams || null == queryParams.getParams()) {
            result.setTotal(0);
            result.setMessage("查询失败，未指定查询参数");
        } else {
            result.setRows(carTypeManager.queryByPage(queryParams.getParams()));
            result.setTotal(carTypeManager.queryTotal(queryParams.getParams()));
        }
        return result;
    }
}
