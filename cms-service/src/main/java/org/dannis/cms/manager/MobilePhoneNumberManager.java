package org.dannis.cms.manager;

import org.dannis.cms.dal.db.MobilePhoneNumberMapper;
import org.dannis.cms.dal.entity.MobilePhoneNumberEntity;
import org.dannis.cms.model.MobilePhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 手机号码管理类
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 14:18
 */
@Component
public class MobilePhoneNumberManager {
    /**
     * 手机号码mapper
     */
    @Autowired
    private MobilePhoneNumberMapper mobilePhoneNumberMapper;

    /**
     * 保存手机号码信息
     *
     * @param mobilePhoneNumber 手机号码业务模型对象
     */
    public void saveMobilePhoneNumber(MobilePhoneNumber mobilePhoneNumber) {
        mobilePhoneNumberMapper.saveMobilePhoneNumber(convertToEntity(mobilePhoneNumber));
    }

    /**
     * 批量保存手机号码信息
     *
     * @param mobilePhoneNumbers 手机号码业务模型对象列表
     */
    public void saveMobilePhoneNumbers(List<MobilePhoneNumber> mobilePhoneNumbers) {
        mobilePhoneNumberMapper.saveMobilePhoneNumbers(convertToEntities(mobilePhoneNumbers));
    }

    /**
     * 根据ID删除手机号码信息
     *
     * @param id 手机号码ID
     */
    public void deleteMobilePhoneNumberById(Integer id) {
        mobilePhoneNumberMapper.deleteMobilePhoneNumberById(id);
    }

    /**
     * 根据号码删除手机号码信息
     *
     * @param number 号码
     */
    public void deleteMobilePhoneNumberByNumber(String number) {
        mobilePhoneNumberMapper.deleteMobilePhoneNumberByNumber(number);
    }

    /**
     * 根据ID查找手机号码信息
     *
     * @param id 手机号码ID
     * @return 若存在，返回手机号码实体对象；否则返回null
     */
    public MobilePhoneNumberEntity findMobilePhoneNumberById(Integer id) {
        return mobilePhoneNumberMapper.findMobilePhoneNumberById(id);
    }

    /**
     * 根据号码查询手机号码信息
     *
     * @param number 号码
     * @return 若存在，返回手机号码实体对象；否则返回null
     */
    public MobilePhoneNumberEntity findMobilePhoneNumberByNumber(String number) {
        return mobilePhoneNumberMapper.findMobilePhoneNumberByNumber(number);
    }

    /**
     * 分页查询手机号码信息
     *
     * @param queryParams 分页查询参数
     * @return 手机号码实体对象列表
     */
    public List<MobilePhoneNumber> findMobilePhoneNumberByPage(Map<String,Object> queryParams) {
        return mobilePhoneNumberMapper.findMobilePhoneNumberByPage(queryParams);
    }

    private List<MobilePhoneNumberEntity> convertToEntities(List<MobilePhoneNumber> mobilePhoneNumbers) {
        List<MobilePhoneNumberEntity> entities = new ArrayList<MobilePhoneNumberEntity>();
        if (null != mobilePhoneNumbers && mobilePhoneNumbers.size() > 0) {
            for (MobilePhoneNumber mobilePhoneNumber : mobilePhoneNumbers) {
                entities.add(convertToEntity(mobilePhoneNumber));
            }
        }
        return entities;
    }

    private MobilePhoneNumberEntity convertToEntity(MobilePhoneNumber mobilePhoneNumber) {
        MobilePhoneNumberEntity entity = null;
        if (null != mobilePhoneNumber) {
            entity = new MobilePhoneNumberEntity();
            entity.setNumber(mobilePhoneNumber.getNumber());
            entity.setOperator(mobilePhoneNumber.getOperator());
            entity.setAttribution(mobilePhoneNumber.getAttribution());
            entity.setWholesalePrice(mobilePhoneNumber.getWholesalePrice());
            entity.setFloorPrice(mobilePhoneNumber.getFloorPrice());
            entity.setBalance(mobilePhoneNumber.getBalance());
            entity.setPriority(mobilePhoneNumber.getPriority());
            entity.setRemark(mobilePhoneNumber.getRemark());
            entity.setCreatedBy(0);
        }
        return entity;
    }
}
