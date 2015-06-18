package org.dannis.cms.controller;

import org.apache.commons.io.FileUtils;
import org.dannis.cms.model.CarBrand;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.result.BaseResult;
import org.dannis.cms.service.CarBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
     * 保存汽车品牌
     *
     * @param logoFile LOGO文件
     * @param carBrand 汽车品牌
     * @return 执行结果
     */
    @RequestMapping(value = "/save.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult save(MultipartFile logoFile, CarBrand carBrand) {
        LOGGER.info("保存汽车品牌");
        BaseResult result = new BaseResult();
        if (!logoFile.isEmpty()) {
            String dir = "/data/web/images/car";    //设定文件保存的目录

            String filename = logoFile.getOriginalFilename();    //得到上传时的文件名
            filename = generateImageFileName(filename);
            try {
                FileUtils.writeByteArrayToFile(new File(dir, filename), logoFile.getBytes());

                String imageUrl = "http://localhost/images/car/" + filename;
                carBrand.setLogoUrl(imageUrl);
                carBrand.setCreatedBy(1);
                carBrandService.save(carBrand);
                result.setSuccess(true);
                result.setMessage("保存汽车品牌成功");
                LOGGER.info("保存汽车品牌成功");
            } catch (IOException e) {
                result.setSuccess(false);
                result.setMessage("上传汽车品牌LOGO失败");
                LOGGER.error("上传汽车品牌LOGO失败", e);
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
        } else {
            result.setSuccess(false);
            result.setMessage("未上传汽车品牌LOGO");
            LOGGER.error("上传失败,未上传汽车品牌LOGO");
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

    private String generateImageFileName(String fileName) {
        Calendar calendar = Calendar.getInstance();
        return "car_"
                + calendar.get(Calendar.YEAR)
                + calendar.get(Calendar.MONTH)
                + calendar.get(Calendar.DAY_OF_MONTH) + 1
                + calendar.get(Calendar.HOUR)
                + calendar.get(Calendar.MINUTE)
                + calendar.get(Calendar.SECOND)
                + calendar.get(Calendar.MILLISECOND)
                + fileName.substring(fileName.lastIndexOf("."));
    }
}
