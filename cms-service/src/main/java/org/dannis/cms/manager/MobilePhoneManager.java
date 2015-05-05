package org.dannis.cms.manager;

import org.dannis.cms.dal.db.MobilePhoneMapper;
import org.dannis.cms.dal.entity.PhoneNumberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 14:18
 */
@Component
public class MobilePhoneManager {
    /**
     * 电话号码mapper
     */
    @Autowired
    private MobilePhoneMapper mobilePhoneMapper;

    /**
     * 保存电话号码
     *
     * @param entity 电话号码实体
     */
    public void save(PhoneNumberEntity entity) {
        mobilePhoneMapper.save(entity);
    }

    /**
     * 批量保存电话号码
     * @param entities 电话号码实体列表
     */
    public void batchSave(List<PhoneNumberEntity> entities) {
        mobilePhoneMapper.batchSave(entities);
    }

    /**
     * 根据ID删除电话号码
     *
     * @param id 电话号码ID
     */
    public void delete(Integer id) {
        mobilePhoneMapper.delete(id);
    }

    /**
     * 根据号码删除电话号码记录
     * @param number 号码
     */
    public void deleteByNumber(String number) {
        mobilePhoneMapper.deleteByNumber(number);
    }

    /**
     * 根据ID查找电话号码
     *
     * @param id 电话号码ID
     * @return 电话号码实体
     */
    public PhoneNumberEntity find(Integer id) {
        return mobilePhoneMapper.find(id);
    }

    /**
     * 根据号码查找电话号码
     *
     * @param number 号码
     * @return 电话号码实体
     */
    public PhoneNumberEntity findByNumber(String number) {
        return mobilePhoneMapper.findByNumber(number);
    }
}
