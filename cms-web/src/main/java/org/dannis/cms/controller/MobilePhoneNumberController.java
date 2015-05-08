package org.dannis.cms.controller;

import org.apache.commons.io.FileUtils;
import org.dannis.cms.MobilePhoneNumberExcelParser;
import org.dannis.cms.model.MobilePhoneNumber;
import org.dannis.cms.result.BaseResult;
import org.dannis.cms.service.MobilePhoneNumberService;
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
import java.util.List;

/**
 * 手机号码控制器
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 12:03
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/mobilePhoneNumber")
public class MobilePhoneNumberController {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MobilePhoneNumberController.class);
    /**
     * 手机号码服务
     */
    @Autowired
    private MobilePhoneNumberService mobilePhoneNumberService;

    @RequestMapping(value = "/save.json")
    @ResponseBody
    public BaseResult saveMobilePhoneNumber(MobilePhoneNumber mobilePhoneNumber) {
        LOGGER.info("保存电话号码");
        BaseResult result = new BaseResult();
        if (null != mobilePhoneNumber) {
            try {
                mobilePhoneNumberService.saveMobilePhoneNumber(mobilePhoneNumber);
                result.setSuccess(true);
                result.setMessage("保存手机号码成功！");
                LOGGER.info("保存手机号码成功");
            } catch (Exception e) {
                result.setSuccess(false);
                result.setMessage("保存手机号码失败，服务器异常！");
                LOGGER.error("保存手机号码失败，发生异常",e);
            }
        } else {
            result.setSuccess(false);
            result.setMessage("手机号码为空！");
            LOGGER.info("保存手机号码失败，原因： 手机号码为空");
        }
        return result;
    }

    @RequestMapping(value = "/upload.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult uploadMobilePhoneNumbers(@RequestParam("mobilePhoneNumberExcel") MultipartFile file,HttpServletRequest request) throws IOException {
        LOGGER.info("批量导入手机号码开始......");
        BaseResult result = new BaseResult();
        if (!file.isEmpty()) {
            List<MobilePhoneNumber> mobilePhoneNumbers = MobilePhoneNumberExcelParser.parseExcel(file.getInputStream());
            try {
                mobilePhoneNumberService.saveMobilePhoneNumbers(mobilePhoneNumbers);
                result.setSuccess(true);
                result.setMessage("导入成功，共导入" + mobilePhoneNumbers.size() + "条手机号码");
                LOGGER.info("批量导入手机号码成功");
            } catch (Exception e) {
                result.setSuccess(false);
                result.setMessage("批量导入手机号码失败");
                LOGGER.error("批量导入手机号码失败",e);
            }
        }
        LOGGER.info("批量导入手机号码结束......");
        return result;
    }
}
