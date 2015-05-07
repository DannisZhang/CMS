package org.dannis.cms.service.impl;

import org.dannis.cms.dal.entity.MobilePhoneNumberEntity;
import org.dannis.cms.manager.MobilePhoneNumberManager;
import org.dannis.cms.model.MobilePhoneNumber;
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

    public void deleteMobilePhoneNumberById(Integer id) {
        mobilePhoneNumberManager.deleteMobilePhoneNumberById(id);
    }

    public void deleteMobilePhoneNumberByNumber(String number) {
        mobilePhoneNumberManager.deleteMobilePhoneNumberByNumber(number);
    }

    public MobilePhoneNumberEntity findMobilePhoneNumberById(Integer id) {
        return mobilePhoneNumberManager.findMobilePhoneNumberById(id);
    }

    public MobilePhoneNumberEntity findMobilePhoneNumberByNumber(String number) {
        return mobilePhoneNumberManager.findMobilePhoneNumberByNumber(number);
    }

    public List<MobilePhoneNumber> findMobilePhoneNumberByPage(Map<String, Object> queryParams) {
        return mobilePhoneNumberManager.findMobilePhoneNumberByPage(queryParams);
    }
}
