package org.dannis.cms.controller;

import org.dannis.cms.model.CarLevel;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.result.BaseResult;
import org.dannis.cms.service.CarLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-17 21:16
 */
@Controller
@RequestMapping("/carLevel")
public class CarLevelController {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CarLevelController.class);
    /**
     * 汽车级别服务类
     */
    @Autowired
    private CarLevelService carLevelService;

    /**
     * 保存汽车级别
     *
     * @param carLevel 汽车级别
     * @return 执行结果
     */
    @RequestMapping(value = "/save.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult save(CarLevel carLevel) {
        LOGGER.info("保存汽车级别");
        BaseResult result = new BaseResult();
        try {
            carLevel.setCreatedBy(1);
            carLevelService.save(carLevel);
            result.setSuccess(true);
            result.setMessage("保存汽车级别成功");
            LOGGER.info("保存汽车级别成功");
        } catch (Exception e) {
            result.setSuccess(false);
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("Duplicate entry")) {
                result.setMessage("汽车级别已存在！");
            } else {
                result.setMessage("服务器异常！");
            }
            LOGGER.error("保存汽车级别失败", e);
        }
        return result;
    }

    /**
     * 分页查询汽车级别
     *
     * @param queryParams 查询参数
     * @return 查询结果
     */
    @RequestMapping(value = "/queryByPage.json")
    @ResponseBody
    public PaginationQueryResult<?> queryByPage(QueryParams queryParams) {
        PaginationQueryResult<CarLevel> result = new PaginationQueryResult<>();
        try {
            PaginationQueryResult<CarLevel> queryResult = carLevelService.queryByPage(queryParams);
            result.setTotal(queryResult.getTotal());
            result.setRows(queryResult.getRows());
        } catch (Exception e) {
            LOGGER.error("分页查询出错", e);
        }

        return result;
    }

    /**
     * 查询所有汽车级别
     *
     * @return 查询结果
     */
    @RequestMapping(value = "/queryAll.json")
    @ResponseBody
    public List<CarLevel> queryAll() {
        List<CarLevel> carLevels = new ArrayList<>();
        try {
            carLevels = carLevelService.queryAll();
        } catch (Exception e) {
            LOGGER.error("查询所有汽车级别出错", e);
        }

        return carLevels;
    }
}
