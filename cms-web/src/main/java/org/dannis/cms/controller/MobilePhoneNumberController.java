package org.dannis.cms.controller;

import org.dannis.cms.MobilePhoneNumberExcelParser;
import org.dannis.cms.model.MobilePhoneNumber;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.BaseResult;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.query.result.SingleQueryResult;
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

import java.util.Arrays;
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

    @RequestMapping(value = "/save.ajax", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult save(MobilePhoneNumber mobilePhoneNumber) {
        LOGGER.info("保存电话号码");
        BaseResult result = new BaseResult();
        try {
            mobilePhoneNumber.setCreatedBy(1);
            mobilePhoneNumberService.save(mobilePhoneNumber);
            result.setSuccess(true);
            result.setMessage("保存手机号码成功");
            LOGGER.info("保存手机号码成功");
        } catch (Exception e) {
            result.setSuccess(false);
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("Duplicate entry")) {
                result.setMessage("手机号码已存在！");
            } else {
                result.setMessage("服务器异常！");
            }
            LOGGER.error("保存手机号码失败", e);
        }
        return result;
    }

    /**
     * 批量导入手机号码
     *
     * @param file Excel文件
     * @return 导入结果
     */
    @RequestMapping(value = "/importExcel.ajax", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult importExcel(@RequestParam("mobilePhoneNumberExcel") MultipartFile file) {
        LOGGER.info("批量导入手机号码开始......");
        BaseResult result = new BaseResult();
        if (!file.isEmpty()) {
            try {
                List<MobilePhoneNumber> mobilePhoneNumbers = MobilePhoneNumberExcelParser.parseExcel(file.getInputStream());
                mobilePhoneNumberService.saveMobilePhoneNumbers(mobilePhoneNumbers);
                result.setMessage("共导入" + mobilePhoneNumbers.size() + "条手机号码");
                LOGGER.info("批量导入手机号码成功");
            } catch (Exception e) {
                result.setSuccess(false);
                result.setMessage("批量导入手机号码失败");
                LOGGER.error("批量导入手机号码失败", e);
            }
        }
        LOGGER.info("批量导入手机号码结束......");
        return result;
    }

    /**
     * 根据ID删除手机号码
     *
     * @param id ID
     * @return 删除操作执行结果
     */
    @RequestMapping(value = "/deleteById.ajax", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteById(Integer id) {
        LOGGER.info("删除手机号码，手机号码ID： " + id);
        BaseResult result = new BaseResult();
        try {
            if (null != id) {
                mobilePhoneNumberService.deleteById(id);
                result.setMessage("删除手机号码成功");
            } else {
                result.setSuccess(false);
                result.setMessage("未指定手机号码ID");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除手机号码失败");
            LOGGER.error("删除手机号码失败", e);
        }
        return result;
    }

    /**
     * 根据ID批量删除手机号码
     *
     * @param ids ID列表
     * @return 删除操作执行结果
     */
    @RequestMapping(value = "deleteByIds.ajax", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteByIds(Integer[] ids) {
        LOGGER.info("批量删除手机号码，手机号码ID列表： " + Arrays.toString(ids));
        BaseResult result = new BaseResult();
        try {
            mobilePhoneNumberService.deleteByIds(ids);
            result.setMessage("删除手机号码成功");
            LOGGER.info("批量删除手机号码成功");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除手机号码失败");
            LOGGER.error("批量删除手机号码失败",e);
        }

        return result;
    }

    /**
     * 根据ID查询手机号码
     *
     * @param id ID
     * @return 手机号码
     */
    @RequestMapping(value = "queryById.ajax", method = RequestMethod.POST)
    @ResponseBody
    public SingleQueryResult<?> queryById(Integer id) {
        SingleQueryResult<MobilePhoneNumber> result = new SingleQueryResult<MobilePhoneNumber>();
        try {
            MobilePhoneNumber mobilePhoneNumber = mobilePhoneNumberService.queryById(id);
            result.setData(mobilePhoneNumber);
            if (null == mobilePhoneNumber) {
                result.setSuccess(false);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            LOGGER.error("根据ID查找手机号码失败", e);
        }
        return result;
    }

    /**
     * 分页查询手机号码
     *
     * @param queryParams 查询参数
     * @return 查询结果
     */
    @RequestMapping(value = "queryByPage.ajax")
    @ResponseBody
    public PaginationQueryResult<?> queryByPage(QueryParams queryParams) {
        PaginationQueryResult<MobilePhoneNumber> result = new PaginationQueryResult<MobilePhoneNumber>();
        try {
            PaginationQueryResult<MobilePhoneNumber> queryResult = mobilePhoneNumberService.queryByPage(queryParams);
            result.setTotal(queryResult.getTotal());
            result.setRows(queryResult.getRows());
        } catch (Exception e) {
            LOGGER.error("分页查询出错", e);
        }

        return result;
    }
}
