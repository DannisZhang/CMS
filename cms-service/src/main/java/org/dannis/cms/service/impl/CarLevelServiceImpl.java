package org.dannis.cms.service.impl;

import org.dannis.cms.manager.CarLevelManager;
import org.dannis.cms.model.CarLevel;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.service.CarLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-17 21:21
 */
@Service
public class CarLevelServiceImpl implements CarLevelService {
    /**
     * 汽车级别管理类
     */
    @Autowired
    private CarLevelManager carLevelManager;

    @Override
    public void save(CarLevel level) {
        carLevelManager.save(level);
    }

    @Override
    public void delete(Integer id) {
        carLevelManager.delete(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        carLevelManager.deleteByIds(ids);
    }

    @Override
    public void update(CarLevel level) {
        carLevelManager.update(level);
    }

    @Override
    public CarLevel query(Integer id) {
        return carLevelManager.query(id);
    }

    @Override
    public PaginationQueryResult<CarLevel> queryByPage(QueryParams queryParams) {
        PaginationQueryResult<CarLevel> result = new PaginationQueryResult<>();
        if (null == queryParams || null == queryParams.getParams()) {
            result.setTotal(0);
            result.setMessage("查询失败，未指定查询参数");
        } else {
            result.setRows(carLevelManager.queryByPage(queryParams.getParams()));
            result.setTotal(carLevelManager.queryTotal(queryParams.getParams()));
        }
        return result;
    }

    @Override
    public List<CarLevel> queryAll() {
        return carLevelManager.queryAll();
    }
}
