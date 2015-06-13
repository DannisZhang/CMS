package org.dannis.cms.dal.db;

import org.dannis.cms.dal.entity.CarTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-13 23:20
 */
public interface CarTypeMapper {
    /**
     * 保存车型信息
     *
     * @param brand 车型信息
     */
    void save(CarTypeEntity brand);

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
    void update(CarTypeEntity brand);

    /**
     * 根据ID查询车型信息
     *
     * @param id 车型ID
     * @return 车型信息
     */
    CarTypeEntity query(Integer id);

    /**
     * 分页查询车型信息
     *
     * @param params 查询参数
     * @return 车型信息列表
     */
    List<CarTypeEntity> queryByPage(Map<String,Object> params);

    /**
     * 根据条件查询车型信息总数
     *
     * @param params 查询条件
     * @return 符合条件的车型信息总数
     */
    Long queryTotal(Map<String, Object> params);
}
