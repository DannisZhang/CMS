package org.dannis.cms.service;

import org.dannis.cms.model.Car;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;

/**
 * 汽车服务类
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-14 10:38
 */
public interface CarService {
    /**
     * 保存汽车信息
     *
     * @param car 汽车信息
     */
    void save(Car car);

    /**
     * 根据ID删除汽车信息
     *
     * @param id 汽车ID
     */
    void delete(Integer id);

    /**
     * 根据ID查询汽车信息
     *
     * @param id 汽车ID
     * @return 汽车信息
     */
    Car query(Integer id);

    /**
     * 分页查询汽车信息
     *
     * @param queryParams 查询参数
     * @return 汽车信息列表
     */
    PaginationQueryResult<Car> queryByPage(QueryParams queryParams);
}
