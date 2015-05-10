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
    public void save(MobilePhoneNumber mobilePhoneNumber) {
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
     * 修改手机号码
     *
     * @param mobilePhoneNumber 手机号码
     */
    public void update(MobilePhoneNumber mobilePhoneNumber) {
        mobilePhoneNumberMapper.update(mobilePhoneNumber);
    }

    /**
     * 根据ID删除手机号码信息
     *
     * @param id 手机号码ID
     */
    public void deleteById(Integer id) {
        mobilePhoneNumberMapper.deleteById(id);
    }

    /**
     * 根据ID批量删除手机号码
     *
     * @param ids ID列表
     */
    public void deleteByIds(Integer[] ids) {
        mobilePhoneNumberMapper.deleteByIds(ids);
    }

    /**
     * 根据ID查找手机号码信息
     *
     * @param id 手机号码ID
     * @return 手机号码
     */
    public MobilePhoneNumber queryById(Integer id) {
        return convertToModel(mobilePhoneNumberMapper.queryById(id));
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
    public List<MobilePhoneNumber> queryByPage(Map<String, Object> queryParams) {
        return convertToModels(mobilePhoneNumberMapper.queryByPage(queryParams));
    }

    /**
     * 查询符合条件的手机号码总数
     *
     * @param queryParams 查询参数
     * @return 符合条件的手机号码总数
     */
    public long queryTotal(Map<String, Object> queryParams) {
        return mobilePhoneNumberMapper.queryTotal(queryParams);
    }

    private List<MobilePhoneNumber> convertToModels(List<MobilePhoneNumberEntity> entities) {
        List<MobilePhoneNumber> mobilePhoneNumbers = new ArrayList<>();
        if (null != entities && entities.size() > 0) {
            for (MobilePhoneNumberEntity entity : entities) {
                mobilePhoneNumbers.add(convertToModel(entity));
            }
        }
        return mobilePhoneNumbers;
    }

    private MobilePhoneNumber convertToModel(MobilePhoneNumberEntity entity) {
        MobilePhoneNumber mobilePhoneNumber = null;
        if (null != entity) {
            mobilePhoneNumber = new MobilePhoneNumber();
            mobilePhoneNumber.setId(entity.getId());
            mobilePhoneNumber.setNumber(entity.getNumber());
            mobilePhoneNumber.setOperator(entity.getOperator());
            mobilePhoneNumber.setAttribution(entity.getAttribution());
            mobilePhoneNumber.setWholesalePrice(entity.getWholesalePrice());
            mobilePhoneNumber.setFloorPrice(entity.getFloorPrice());
            mobilePhoneNumber.setBalance(entity.getBalance());
            mobilePhoneNumber.setPriority(entity.getPriority());
            mobilePhoneNumber.setRemark(entity.getRemark());
            mobilePhoneNumber.setCreatedOn(entity.getCreatedOn());
            mobilePhoneNumber.setCreatedBy(entity.getCreatedBy());
            mobilePhoneNumber.setLastModifiedOn(entity.getLastModifiedOn());
            mobilePhoneNumber.setLastModifiedBy(entity.getLastModifiedBy());
        }

        return mobilePhoneNumber;
    }


    private MobilePhoneNumberEntity convertToEntity(MobilePhoneNumber mobilePhoneNumber) {
        MobilePhoneNumberEntity entity = null;
        if (null != mobilePhoneNumber) {
            entity = new MobilePhoneNumberEntity();
            entity.setId(mobilePhoneNumber.getId());
            entity.setNumber(mobilePhoneNumber.getNumber());
            entity.setOperator(mobilePhoneNumber.getOperator());
            entity.setAttribution(mobilePhoneNumber.getAttribution());
            entity.setWholesalePrice(mobilePhoneNumber.getWholesalePrice());
            entity.setFloorPrice(mobilePhoneNumber.getFloorPrice());
            entity.setBalance(mobilePhoneNumber.getBalance());
            entity.setPriority(mobilePhoneNumber.getPriority());
            entity.setRemark(mobilePhoneNumber.getRemark());
            entity.setCreatedBy(mobilePhoneNumber.getCreatedBy());
            entity.setLastModifiedBy(mobilePhoneNumber.getLastModifiedBy());
        }
        return entity;
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
}
