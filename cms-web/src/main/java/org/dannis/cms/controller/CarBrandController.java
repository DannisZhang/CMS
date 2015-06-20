package org.dannis.cms.controller;

import org.dannis.cms.model.CarBrand;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.query.result.SingleQueryResult;
import org.dannis.cms.result.BaseResult;
import org.dannis.cms.service.CarBrandService;
import org.dannis.cms.service.CarImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-17 21:16
 */
@Controller
@RequestMapping("/carBrand")
public class CarBrandController {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CarBrandController.class);
    /**
     * 车型服务类
     */
    @Autowired
    private CarBrandService carBrandService;
    /**
     * 汽车图片服务类
     */
    @Autowired
    private CarImageService carImageService;

    /**
     * 保存汽车品牌
     *
     * @param logoFile LOGO文件
     * @param carBrand 汽车品牌
     * @return 执行结果
     */
    @RequestMapping(value = "/save.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult save(MultipartFile logoFile, CarBrand carBrand) {
        BaseResult result = new BaseResult();
        try {
            if (carBrand.getId() != null && carBrand.getId() > 0) {
                LOGGER.info("修改汽车品牌");
                if (!logoFile.isEmpty()) {
                    String imageUrl = carImageService.saveImage(logoFile.getOriginalFilename(), logoFile.getBytes());
                    if (imageUrl != null) {
                        carBrand.setLogoUrl(imageUrl);
                    }
                }
                carBrand.setLastModifiedBy(1);
                carBrandService.update(carBrand);
                result.setSuccess(true);
                result.setMessage("修改汽车品牌成功");
                LOGGER.info("修改汽车品牌成功");
            } else {
                LOGGER.info("添加汽车品牌");
                if (!logoFile.isEmpty()) {
                    String imageUrl = carImageService.saveImage(logoFile.getOriginalFilename(), logoFile.getBytes());
                    if (imageUrl != null) {
                        carBrand.setLogoUrl(imageUrl);
                    }
                }
                carBrand.setCreatedBy(1);
                carBrandService.save(carBrand);
                result.setSuccess(true);
                result.setMessage("添加汽车品牌成功");
                LOGGER.info("添加汽车品牌成功");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("Duplicate entry")) {
                result.setMessage("汽车品牌已存在！");
            } else {
                result.setMessage("服务器异常！");
            }
            LOGGER.error("保存汽车品牌失败", e);
        }
        return result;
    }

    /**
     * 根据ID删除汽车品牌
     *
     * @param id 汽车品牌ID
     * @return 删除操作执行结果
     */
    @RequestMapping(value = "/deleteById.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteById(Integer id) {
        LOGGER.info("删除汽车品牌，汽车品牌ID： " + id);
        BaseResult result = new BaseResult();
        try {
            if (null != id) {
                carBrandService.delete(id);
                result.setSuccess(true);
                result.setMessage("删除汽车品牌成功");
            } else {
                result.setSuccess(false);
                result.setMessage("未指定汽车品牌ID");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除汽车品牌失败");
            LOGGER.error("删除汽车品牌失败", e);
        }
        return result;
    }

    /**
     * 根据ID批量删除汽车品牌
     *
     * @param ids ID列表
     * @return 删除操作执行结果
     */
    @RequestMapping(value = "deleteByIds.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteByIds(Integer[] ids) {
        LOGGER.info("批量删除汽车品牌，汽车品牌ID列表： " + Arrays.toString(ids));
        BaseResult result = new BaseResult();
        try {
            carBrandService.deleteByIds(ids);
            result.setSuccess(true);
            result.setMessage("删除汽车品牌成功");
            LOGGER.info("批量删除汽车品牌成功");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除汽车品牌失败");
            LOGGER.error("批量删除汽车品牌失败", e);
        }

        return result;
    }

    /**
     * 根据ID查询汽车品牌
     *
     * @param id ID
     * @return 汽车品牌
     */
    @RequestMapping(value = "queryById.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleQueryResult<?> queryById(Integer id) {
        SingleQueryResult<CarBrand> result = new SingleQueryResult<>();
        try {
            CarBrand carBrand = carBrandService.query(id);
            result.setData(carBrand);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            LOGGER.error("根据ID查找汽车品牌失败", e);
        }
        return result;
    }

    /**
     * 分页查询汽车品牌
     *
     * @param queryParams 查询参数
     * @return 查询结果
     */
    @RequestMapping(value = "/queryByPage.json")
    @ResponseBody
    public PaginationQueryResult<?> queryByPage(QueryParams queryParams) {
        PaginationQueryResult<CarBrand> result = new PaginationQueryResult<>();
        try {
            PaginationQueryResult<CarBrand> queryResult = carBrandService.queryByPage(queryParams);
            result.setTotal(queryResult.getTotal());
            result.setRows(queryResult.getRows());
        } catch (Exception e) {
            LOGGER.error("分页查询出错", e);
        }

        return result;
    }

    /**
     * 查询所有汽车品牌
     *
     * @return 查询结果
     */
    @RequestMapping(value = "/queryAll.json")
    @ResponseBody
    public List<CarBrand> queryAll() {
        List<CarBrand> carBrands = new ArrayList<>();
        try {
            carBrands = carBrandService.queryAll();
        } catch (Exception e) {
            LOGGER.error("查询所有汽车品牌出错", e);
        }

        return carBrands;
    }
}
