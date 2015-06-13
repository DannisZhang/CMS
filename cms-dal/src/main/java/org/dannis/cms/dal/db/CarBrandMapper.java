package org.dannis.cms.dal.db;

import org.dannis.cms.dal.entity.CarBrandEntity;

import java.util.List;
import java.util.Map;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-13 23:20
 */
public interface CarBrandMapper {
    /**
     * 保存汽车品牌信息
     *
     * @param brand 汽车品牌信息
     */
    void save(CarBrandEntity brand);

    /**
     * 根据ID删除汽车品牌信息
     *
     * @param id 汽车品牌ID
     */
    void delete(Integer id);

    /**
     * 修改汽车品牌信息
     *
     * @param brand 汽车品牌信息
     */
    void update(CarBrandEntity brand);

    /**
     * 根据ID查询汽车品牌信息
     *
     * @param id 汽车品牌ID
     * @return 汽车品牌信息
     */
    CarBrandEntity query(Integer id);

    /**
     * 分页查询汽车品牌信息
     *
     * @param params 查询参数
     * @return 汽车品牌信息列表
     */
    List<CarBrandEntity> queryByPage(Map<String,Object> params);

    /**
     * 根据条件查询汽车品牌信息总数
     *
     * @param params 查询条件
     * @return 符合条件的汽车品牌信息总数
     */
    Long queryTotal(Map<String, Object> params);
}
