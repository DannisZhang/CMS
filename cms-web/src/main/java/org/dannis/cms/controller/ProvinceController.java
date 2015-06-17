package org.dannis.cms.controller;

import org.dannis.cms.model.Province;
import org.dannis.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Dannis
 * @version 1.0.0
 * @date 2015-05-30 00:00
 */
@Controller
@RequestMapping(value = "/province")
public class ProvinceController {
    /**
     * 省份服务类
     */
    @Autowired
    private ProvinceService provinceService;

    /**
     * 查询所有省份
     *
     * @return 省份列表
     */
    @RequestMapping(value = "/queryAll.json")
    @ResponseBody
    public List<Province> queryAllProvinces() {
        return provinceService.queryAllProvinces();
    }
}
