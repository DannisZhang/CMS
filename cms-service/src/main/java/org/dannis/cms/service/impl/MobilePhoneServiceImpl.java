package org.dannis.cms.service.impl;

import org.dannis.cms.dal.entity.PhoneNumberEntity;
import org.dannis.cms.manager.MobilePhoneManager;
import org.dannis.cms.service.MobilePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 14:23
 */
@Service
public class MobilePhoneServiceImpl implements MobilePhoneService {
    /**
     * 电话号码管理类
     */
    @Autowired
    private MobilePhoneManager mobilePhoneManager;

    public void save(PhoneNumberEntity entity) {
        System.out.println("保存电话号码");
//        mobilePhoneManager.save(entity);
    }

    public void batchSave(List<PhoneNumberEntity> entities) {
        mobilePhoneManager.batchSave(entities);
    }

    public void delete(Integer id) {
        mobilePhoneManager.delete(id);
    }

    public void deleteByNumber(String number) {
        mobilePhoneManager.deleteByNumber(number);
    }

    public PhoneNumberEntity find(Integer id) {
        return mobilePhoneManager.find(id);
    }

    public PhoneNumberEntity findByNumber(String number) {
        return mobilePhoneManager.findByNumber(number);
    }
}
