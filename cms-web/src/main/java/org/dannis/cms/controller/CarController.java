package org.dannis.cms.controller;

import org.apache.commons.io.FileUtils;
import org.dannis.cms.model.Car;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.query.result.SingleQueryResult;
import org.dannis.cms.result.BaseResult;
import org.dannis.cms.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-14 13:39
 */
@Controller
@RequestMapping(value = "/car")
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
     * 根据ID删除汽车信息
     *
     * @param id ID
     * @return 删除操作执行结果
     */
    @RequestMapping(value = "/deleteById.ajax", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteById(Integer id) {
        LOGGER.info("删除汽车信息，汽车信息ID： " + id);
        BaseResult result = new BaseResult();
        try {
            if (null != id) {
                carService.delete(id);
                result.setMessage("删除汽车信息成功");
            } else {
                result.setSuccess(false);
                result.setMessage("未指定汽车信息ID");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除汽车信息失败");
            LOGGER.error("删除汽车信息失败", e);
        }
        return result;
    }

    /**
     * 根据ID查询汽车信息
     *
     * @param id ID
     * @return 汽车信息
     */
    @RequestMapping(value = "/queryById.ajax", method = RequestMethod.POST)
    @ResponseBody
    public SingleQueryResult<?> queryById(Integer id) {
        SingleQueryResult<Car> result = new SingleQueryResult<>();
        try {
            Car car = carService.query(id);
            result.setData(car);
            if (null == car) {
                result.setSuccess(false);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            LOGGER.error("根据ID查找汽车信息失败", e);
        }
        return result;
    }

    /**
     * 分页查询汽车信息
     *
     * @param queryParams 查询参数
     * @return 查询结果
     */
    @RequestMapping(value = "/queryByPage.ajax")
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

    /**
     * 上传汽车图片
     *
     * @param file    汽车图片文件
     * @return 上传成功返回图片URL
     */
    @RequestMapping(value = "/uploadImage.ajax", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult uploadImage(@RequestParam("carImageFile") MultipartFile file) {
        LOGGER.info("开始上传图片......");
        BaseResult result = new BaseResult();
        if (!file.isEmpty()) {
            String dir = "/data/web/images/car";    //设定文件保存的目录

            String filename = file.getOriginalFilename();    //得到上传时的文件名
            filename = generateImageFileName(filename);
            String imageUrl = "http://localhost/images/car/" + filename;
            try {
                FileUtils.writeByteArrayToFile(new File(dir, filename), file.getBytes());
                result.setSuccess(true);
                result.setMessage(imageUrl);
            } catch (IOException e) {
                result.setSuccess(false);
                result.setMessage("导入失败");
                LOGGER.error("上传汽车图片失败", e);
            }
        }
        LOGGER.info("上传图片结束......");
        return result;
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
