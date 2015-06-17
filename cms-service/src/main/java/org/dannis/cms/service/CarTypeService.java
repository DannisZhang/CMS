package org.dannis.cms.service;

import org.dannis.cms.model.CarType;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;

/**
 * 车型服务类
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-17 21:18
 */
public interface CarTypeService {
    /**
     * 保存车型信息
     *
     * @param brand 车型信息
     */
    void save(CarType brand);

    /**
     * 根据ID删除车型信息
     *
     * @param id 车型ID
     */
    void delete(Integer id);

    /**
     * 修改车型信息
     *
     * @param brand 车型信息
     */
    void update(CarType brand);

    /**
     * 根据ID查询车型信息
     *
     * @param id 车型ID
     * @return 车型信息
     */
    CarType query(Integer id);

    /**
     * 分页查询车型信息
     *
     * @param queryParams 查询参数
     * @return 车型信息列表
     */
    PaginationQueryResult<CarType> queryByPage(QueryParams queryParams);
}
