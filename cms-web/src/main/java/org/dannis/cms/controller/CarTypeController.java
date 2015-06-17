package org.dannis.cms.controller;

import org.dannis.cms.model.CarType;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.result.BaseResult;
import org.dannis.cms.service.CarTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-17 21:16
 */
@Controller
@RequestMapping("/carType")
public class CarTypeController {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CarTypeController.class);
    /**
     * 车型服务类
     */
    @Autowired
    private CarTypeService carTypeService;

    /**
     * 保存车型
     *
     * @param carType 车型
     * @return 执行结果
     */
    @RequestMapping(value = "/save.ajax", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult save(CarType carType) {
        LOGGER.info("保存车型");
        BaseResult result = new BaseResult();
        try {
            carType.setCreatedBy(1);
            carTypeService.save(carType);
            result.setSuccess(true);
            result.setMessage("保存车型成功");
            LOGGER.info("保存车型成功");
        } catch (Exception e) {
            result.setSuccess(false);
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("Duplicate entry")) {
                result.setMessage("车型已存在！");
            } else {
                result.setMessage("服务器异常！");
            }
            LOGGER.error("保存车型失败", e);
        }
        return result;
    }

    /**
     * 分页查询车型
     *
     * @param queryParams 查询参数
     * @return 查询结果
     */
    @RequestMapping(value = "/queryByPage.ajax")
    @ResponseBody
    public PaginationQueryResult<?> queryByPage(QueryParams queryParams) {
        PaginationQueryResult<CarType> result = new PaginationQueryResult<>();
        try {
            PaginationQueryResult<CarType> queryResult = carTypeService.queryByPage(queryParams);
            result.setTotal(queryResult.getTotal());
            result.setRows(queryResult.getRows());
        } catch (Exception e) {
            LOGGER.error("分页查询出错", e);
        }

        return result;
    }
}
