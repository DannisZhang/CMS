package org.dannis.cms.manager;

import org.dannis.cms.dal.db.CityMapper;
import org.dannis.cms.dal.entity.CityEntity;
import org.dannis.cms.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市管理类
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-29 23:42
 */
@Component
public class CityManager {
    /**
     * 城市mapper
     */
    @Autowired
    private CityMapper cityMapper;


    /**
     * 查询所有城市
     *
     * @return 所有城市列表
     */
    public List<City> queryAllCities() {
        return convertToModels(cityMapper.queryAllCities());
    }

    /**
     * 根据省份编码查询城市
     *
     * @param provinceCode 省份编码
     * @return 城市列表
     */
    public List<City> queryCityByProvinceCode(String provinceCode) {
        return convertToModels(cityMapper.queryCityByProvinceCode(provinceCode));
    }

    /**
     * 根据城市名称查找城市编码
     *
     * @param cityName 城市名称
     * @return 城市编码
     */
    public String queryCityCodeByName(String cityName) {
        return cityMapper.queryCityCodeByName(cityName);
    }

    private List<City> convertToModels(List<CityEntity> cityEntities) {
        List<City> cities = new ArrayList<>();
        if (cityEntities != null && cityEntities.size() > 0) {
            for (CityEntity cityEntity : cityEntities) {
                cities.add(convertToModel(cityEntity));
            }
        }
        return cities;
    }
    private City convertToModel(CityEntity cityEntity) {
        City city = null;
        if (cityEntity != null) {
            city = new City();
            city.setName(cityEntity.getName());
            city.setCode(cityEntity.getCode());
        }
        return city;
    }
}
