package org.dannis.cms.service;

import org.dannis.cms.model.CarLevel;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;

import java.util.List;

/**
 * 汽车级别服务类
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-17 21:18
 */
public interface CarLevelService {
    /**
     * 保存汽车级别信息
     *
     * @param level 汽车级别信息
     */
    void save(CarLevel level);

    /**
     * 根据ID删除汽车级别信息
     *
     * @param id 汽车级别ID
     */
    void delete(Integer id);

    /**
     * 根据ID批量删除汽车级别
     *
     * @param ids ID列表
     */
    void deleteByIds(Integer[] ids);

    /**
     * 修改汽车级别信息
     *
     * @param level 汽车级别信息
     */
    void update(CarLevel level);

    /**
     * 根据ID查询汽车级别信息
     *
     * @param id 汽车级别ID
     * @return 汽车级别信息
     */
    CarLevel query(Integer id);

    /**
     * 分页查询汽车级别信息
     *
     * @param queryParams 查询参数
     * @return 汽车级别信息列表
     */
    PaginationQueryResult<CarLevel> queryByPage(QueryParams queryParams);

    /**
     * 查询所有汽车级别
     * @return 汽车级别列表
     */
    List<CarLevel> queryAll();
}
