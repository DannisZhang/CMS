package org.dannis.cms.service;

import org.dannis.cms.model.CarBrand;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;

import java.util.List;

/**
 * 汽车品牌服务类
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-17 21:18
 */
public interface CarBrandService {
    /**
     * 保存汽车品牌信息
     *
     * @param brand 汽车品牌信息
     */
    void save(CarBrand brand);

    /**
     * 根据ID删除汽车品牌信息
     *
     * @param id 汽车品牌ID
     */
    void delete(Integer id);

    /**
     * 根据ID批量删除汽车品牌
     *
     * @param ids ID列表
     */
    void deleteByIds(Integer[] ids);

    /**
     * 修改汽车品牌信息
     *
     * @param brand 汽车品牌信息
     */
    void update(CarBrand brand);

    /**
     * 根据ID查询汽车品牌信息
     *
     * @param id 汽车品牌ID
     * @return 汽车品牌信息
     */
    CarBrand query(Integer id);

    /**
     * 分页查询汽车品牌信息
     *
     * @param queryParams 查询参数
     * @return 汽车品牌信息列表
     */
    PaginationQueryResult<CarBrand> queryByPage(QueryParams queryParams);

    /**
     * 查询所有汽车品牌
     * @return 汽车品牌列表
     */
    List<CarBrand> queryAll();
}
