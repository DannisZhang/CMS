package org.dannis.cms.service;

import org.dannis.cms.dal.entity.PhoneNumberEntity;

import java.util.List;

/**
 * 电话号码服务接口
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 14:21
 */
public interface MobilePhoneService {
    /**
     * 保存电话号码
     *
     * @param entity 电话号码实体
     */
    void save(PhoneNumberEntity entity);

    /**
     * 批量保存电话号码
     * @param entities 电话号码实体列表
     */
    void batchSave(List<PhoneNumberEntity> entities);

    /**
     * 根据ID删除电话号码
     *
     * @param id 电话号码ID
     */
    void delete(Integer id);

    /**
     * 根据号码删除电话号码记录
     * @param number 号码
     */
    void deleteByNumber(String number);

    /**
     * 根据ID查找电话号码
     *
     * @param id 电话号码ID
     * @return 电话号码实体
     */
    PhoneNumberEntity find(Integer id);

    /**
     * 根据号码查找电话号码
     *
     * @param number 号码
     * @return 电话号码实体
     */
    PhoneNumberEntity findByNumber(String number);
}
