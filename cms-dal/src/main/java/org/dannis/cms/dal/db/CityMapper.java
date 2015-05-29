package org.dannis.cms.dal.db;

import org.dannis.cms.dal.entity.CityEntity;

import java.util.List;

/**
 * 城市mapper
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-29 23:21
 */
public interface CityMapper {
    /**
     * 查询所有城市
     *
     * @return 所有城市列表
     */
    List<CityEntity> queryAllCities();

    /**
     * 根据省份编码查询城市
     *
     * @param provinceCode 省份编码
     * @return 城市列表
     */
    List<CityEntity> queryCityByProvinceCode(String provinceCode);

    /**
     * 根据城市名称查找城市编码
     *
     * @param cityName 城市名称
     * @return 城市编码
     */
    String queryCityCodeByName(String cityName);
}
