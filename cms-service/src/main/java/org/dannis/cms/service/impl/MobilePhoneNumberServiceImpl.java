package org.dannis.cms.service.impl;

import org.dannis.cms.dal.entity.MobilePhoneNumberEntity;
import org.dannis.cms.manager.MobilePhoneNumberManager;
import org.dannis.cms.model.MobilePhoneNumber;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.service.MobilePhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 14:23
 */
@Service
public class MobilePhoneNumberServiceImpl implements MobilePhoneNumberService {
    /**
     * 电话号码管理类
     */
    @Autowired
    private MobilePhoneNumberManager mobilePhoneNumberManager;

    public void saveMobilePhoneNumber(MobilePhoneNumber mobilePhoneNumber) {
        mobilePhoneNumberManager.saveMobilePhoneNumber(mobilePhoneNumber);
    }

    public void saveMobilePhoneNumbers(List<MobilePhoneNumber> mobilePhoneNumbers) {
        mobilePhoneNumberManager.saveMobilePhoneNumbers(mobilePhoneNumbers);
    }

    public void deleteById(Integer id) {
        mobilePhoneNumberManager.deleteById(id);
    }

    public MobilePhoneNumberEntity findMobilePhoneNumberById(Integer id) {
        return mobilePhoneNumberManager.findMobilePhoneNumberById(id);
    }

    public MobilePhoneNumberEntity findMobilePhoneNumberByNumber(String number) {
        return mobilePhoneNumberManager.findMobilePhoneNumberByNumber(number);
    }

    @Override
    public PaginationQueryResult<MobilePhoneNumber> queryByPage(QueryParams queryParams) {
        PaginationQueryResult<MobilePhoneNumber> result = new PaginationQueryResult<>();
        if (null == queryParams) {
            result.setMessage("查询部门失败，未指定查询参数");
            return result;
        }
        result.setTotal(mobilePhoneNumberManager.queryTotal(queryParams.getParams()));
        result.setRows(mobilePhoneNumberManager.queryByPage(queryParams.getParams()));

        return result;
    }
}
