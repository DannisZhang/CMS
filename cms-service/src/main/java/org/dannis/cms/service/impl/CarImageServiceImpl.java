package org.dannis.cms.service.impl;

import org.apache.commons.io.FileUtils;
import org.dannis.cms.service.CarImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-20 22:57
 */
@Service
public class CarImageServiceImpl implements CarImageService {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CarImageServiceImpl.class);
    /**
     * 保存汽车图片文件路径
     */
    private static final String CAR_IMAGE_DIR = "/data/web/images/car";
    /**
     * 图片URL前缀
     */
    private static final String IMAGE_URL_PREFIX = "http://che0593.com/images/car/";

    @Override
    public String saveImage(String fileName, byte[] imageBytes) {
        String imageUrl = null;
        if (null != imageBytes && imageBytes.length > 0) {
            fileName = generateImageFileName(fileName);
            try {
                FileUtils.writeByteArrayToFile(new File(CAR_IMAGE_DIR, fileName), imageBytes);
                imageUrl = IMAGE_URL_PREFIX + fileName;
            } catch (IOException e) {
                LOGGER.error("保存图片失败", e);
            }
        }
        return imageUrl;
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
