package org.dannis.cms.dal.db;

import org.dannis.cms.dal.entity.MobilePhoneNumberEntity;
import org.dannis.cms.model.MobilePhoneNumber;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 手机号码mapper类
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 13:33
 */
@Component
public interface MobilePhoneNumberMapper {
    /**
     * 保存手机号码信息
     *
     * @param entity 手机号码实体对象
     */
    void saveMobilePhoneNumber(MobilePhoneNumberEntity entity);

    /**
     * 批量保存手机号码信息
     *
     * @param entities 手机号码实体列表
     */
    void saveMobilePhoneNumbers(List<MobilePhoneNumberEntity> entities);

    /**
     * 根据ID删除手机号码信息
     *
     * @param id 手机号码ID
     */
    void deleteMobilePhoneNumberById(Integer id);

    /**
     * 根据号码删除手机号码信息
     *
     * @param number 号码
     */
    void deleteMobilePhoneNumberByNumber(String number);

    /**
     * 根据ID查找手机号码信息
     *
     * @param id 手机号码ID
     * @return 若存在，返回手机号码实体对象；否则返回null
     */
    MobilePhoneNumberEntity findMobilePhoneNumberById(Integer id);

    /**
     * 根据号码查询手机号码信息
     *
     * @param number 号码
     * @return 若存在，返回手机号码实体对象；否则返回null
     */
    MobilePhoneNumberEntity findMobilePhoneNumberByNumber(String number);

    /**
     * 分页查询手机号码信息
     *
     * @param queryParams 分页查询参数
     * @return 手机号码实体对象列表
     */
    List<MobilePhoneNumber> findMobilePhoneNumberByPage(Map<String,Object> queryParams);
}
