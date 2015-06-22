package org.dannis.cms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-22 21:23
 */
public class DateUtil {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 日期对象转换成字符串
     *
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String dateToStr(Date date) {
        String dateStr = null;
        if (null != date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            dateStr = dateFormat.format(date);
        }
        return dateStr;
    }

    /**
     * 日期字符串转换日期对象
     *
     * @param dateStr 日期字符串
     * @return 日期对象
     */
    public static Date strToDate(String dateStr) {
        Date date = null;
        if (null != dateStr && !"".equals(dateStr.trim())) {
            dateStr = dateStr.replace("年","").replace("月","").replace("日","");
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                date = dateFormat.parse(dateStr);
            } catch (ParseException e) {
                LOGGER.error("日期字符串转换日期对象失败",e);
            }
        }
        return date;
    }
}
