package org.dannis.cms.controller;

import org.dannis.cms.model.Car;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.result.BaseResult;
import org.dannis.cms.service.CarService;
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
 * @date 2015-06-14 13:39
 */
@Controller
public class CarController {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    /**
     * 汽车服务类
     */
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/save.ajax", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult save(Car car) {
        LOGGER.info("保存汽车信息");
        BaseResult result = new BaseResult();
        try {
            car.setCreatedBy(1);
            carService.save(car);
            result.setSuccess(true);
            result.setMessage("保存汽车信息成功");
            LOGGER.info("保存汽车信息成功");
        } catch (Exception e) {
            result.setSuccess(false);
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("Duplicate entry")) {
                result.setMessage("汽车信息已存在！");
            } else {
                result.setMessage("服务器异常！");
            }
            LOGGER.error("保存汽车信息失败", e);
        }
        return result;
    }

    /**
     * 分页查询汽车信息
     *
     * @param queryParams 查询参数
     * @return 查询结果
     */
    @RequestMapping(value = "queryByPage.ajax")
    @ResponseBody
    public PaginationQueryResult<?> queryByPage(QueryParams queryParams) {
        PaginationQueryResult<Car> result = new PaginationQueryResult<Car>();
        try {
            PaginationQueryResult<Car> queryResult = carService.queryByPage(queryParams);
            result.setTotal(queryResult.getTotal());
            result.setRows(queryResult.getRows());
        } catch (Exception e) {
            LOGGER.error("分页查询出错", e);
        }

        return result;
    }
}
