package org.dannis.cms.service;

import org.dannis.cms.model.Province;

import java.util.List;

/**
 * 省份服务类
 *
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-29 01:07
 */
public interface ProvinceService {
    /**
     * 查询所有省份
     *
     * @return 省份列表
     */
    List<Province> queryAllProvinces();
}
