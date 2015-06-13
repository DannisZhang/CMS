package org.dannis.cms.dal.db;

import org.dannis.cms.dal.entity.CarEntity;

import java.util.List;
import java.util.Map;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-13 22:02
 */
public interface CarMapper {
    /**
     * 保存汽车信息
     *
     * @param car 汽车信息
     */
    void save(CarEntity car);

    /**
     * 根据ID删除汽车信息
     *
     * @param id 汽车ID
     */
    void delete(Integer id);

    /**
     * 修改汽车信息
     *
     * @param car 汽车信息
     */
    void update(CarEntity car);

    /**
     * 根据ID查询汽车信息
     *
     * @param id 汽车ID
     * @return 汽车信息
     */
    CarEntity query(Integer id);

    /**
     * 分页查询汽车信息
     *
     * @param params 查询参数
     * @return 汽车信息列表
     */
    List<CarEntity> queryByPage(Map<String, Object> params);

    /**
     * 根据条件查询汽车信息总数
     *
     * @param params 查询条件
     * @return 符合条件的汽车信息总数
     */
    Long queryTotal(Map<String, Object> params);
}
