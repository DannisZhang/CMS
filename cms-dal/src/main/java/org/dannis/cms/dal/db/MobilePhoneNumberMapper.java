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
     * 修改手机号码
     *
     * @param mobilePhoneNumber 手机号码
     */
    void update(MobilePhoneNumber mobilePhoneNumber);

    /**
     * 根据ID删除手机号码信息
     *
     * @param id 手机号码ID
     */
    void deleteById(Integer id);

    /**
     * 根据ID批量删除手机号码
     *
     * @param ids ID列表
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID查找手机号码信息
     *
     * @param id 手机号码ID
     * @return 若存在，返回手机号码实体对象；否则返回null
     */
    MobilePhoneNumberEntity queryById(Integer id);

    /**
     * 根据号码查询手机号码信息
     *
     * @param number 号码
     * @return 若存在，返回手机号码实体对象；否则返回null
     */
    MobilePhoneNumberEntity findMobilePhoneNumberByNumber(String number);

    /**
     * 分页查询符合查询条件的手机号码
     *
     * @param queryParams 分页查询参数
     * @return 手机号码列表
     */
    List<MobilePhoneNumberEntity> queryByPage(Map<String, Object> queryParams);

    /**
     * 查询符合条件的手机号码总数
     *
     * @param queryParams 查询参数
     * @return 符合条件的手机号码总数
     */
    long queryTotal(Map<String, Object> queryParams);
}
