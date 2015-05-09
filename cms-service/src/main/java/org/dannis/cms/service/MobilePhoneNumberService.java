package org.dannis.cms.service;

import org.dannis.cms.dal.entity.MobilePhoneNumberEntity;
import org.dannis.cms.model.MobilePhoneNumber;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;

import java.util.List;
import java.util.Map;

/**
 * 手机号码服务接口
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 14:21
 */
public interface MobilePhoneNumberService {
    /**
     * 保存手机号码信息
     *
     * @param mobilePhoneNumber 手机号码业务模型对象
     */
    void saveMobilePhoneNumber(MobilePhoneNumber mobilePhoneNumber);

    /**
     * 批量保存手机号码信息
     *
     * @param mobilePhoneNumbers 手机号码业务模型对象列表
     */
    void saveMobilePhoneNumbers(List<MobilePhoneNumber> mobilePhoneNumbers);

    /**
     * 根据ID删除手机号码信息
     *
     * @param id 手机号码ID
     */
    void deleteById(Integer id);

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
     * 分页查询手机号码
     *
     * @param queryParams 查询参数
     * @return 查询结果
     */
    PaginationQueryResult<MobilePhoneNumber> queryByPage(QueryParams queryParams);
}
