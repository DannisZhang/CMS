package org.dannis.cms.service.impl;

import org.dannis.cms.manager.CityManager;
import org.dannis.cms.manager.ProvinceManager;
import org.dannis.cms.model.Province;
import org.dannis.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-29 23:51
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {
    /**
     * 省份管理类
     */
    @Autowired
    private ProvinceManager provinceManager;
    /**
     * 城市管理类
     */
    @Autowired
    private CityManager cityManager;

    @Override
    public List<Province> queryAllProvinces() {
        List<Province> provinces = provinceManager.queryAllProvinces();
        if (provinces != null && provinces.size() > 0) {
            for (Province province : provinces) {
                province.setCities(cityManager.queryCityByProvinceCode(province.getCode()));
            }
        }
        return provinces;
    }
}
