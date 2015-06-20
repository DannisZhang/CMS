package org.dannis.cms.dal.db;

import org.dannis.cms.dal.entity.CarLevelEntity;

import java.util.List;
import java.util.Map;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-13 23:20
 */
public interface CarLevelMapper {
    /**
     * 保存汽车级别信息
     *
     * @param level 汽车级别信息
     */
    void save(CarLevelEntity level);

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
    void update(CarLevelEntity level);

    /**
     * 根据ID查询汽车级别信息
     *
     * @param id 汽车级别ID
     * @return 汽车级别信息
     */
    CarLevelEntity query(Integer id);

    /**
     * 分页查询汽车级别信息
     *
     * @param params 查询参数
     * @return 汽车级别信息列表
     */
    List<CarLevelEntity> queryByPage(Map<String,Object> params);

    /**
     * 根据条件查询汽车级别信息总数
     *
     * @param params 查询条件
     * @return 符合条件的汽车级别信息总数
     */
    Long queryTotal(Map<String, Object> params);

    /**
     * 查询所有汽车级别
     *
     * @return 汽车级别列表
     */
    List<CarLevelEntity> queryAll();
}
