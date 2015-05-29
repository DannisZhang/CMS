package org.dannis.cms.service.impl;

import org.dannis.cms.manager.CityManager;
import org.dannis.cms.model.City;
import org.dannis.cms.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-29 23:50
 */
@Service
public class CityServiceImpl implements CityService {
    /**
     * 城市管理类
     */
    @Autowired
    private CityManager cityManager;

    @Override
    public List<City> queryAllCities() {
        return cityManager.queryAllCities();
    }

    @Override
    public List<City> queryCityByProvinceCode(String provinceCode) {
        return cityManager.queryCityByProvinceCode(provinceCode);
    }

    @Override
    public String queryCityCodeByName(String cityName) {
        return cityManager.queryCityCodeByName(cityName);
    }
}
