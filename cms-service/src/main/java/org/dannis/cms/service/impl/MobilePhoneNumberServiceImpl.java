package org.dannis.cms.service.impl;

import org.dannis.cms.manager.MobilePhoneNumberManager;
import org.dannis.cms.model.MobilePhoneNumber;
import org.dannis.cms.query.QueryParams;
import org.dannis.cms.query.result.PaginationQueryResult;
import org.dannis.cms.service.MobilePhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void save(MobilePhoneNumber mobilePhoneNumber) {
        if (mobilePhoneNumber.getId() == -1) {//添加手机号码
            mobilePhoneNumberManager.save(mobilePhoneNumber);
        } else {//修改手机号码
            mobilePhoneNumberManager.update(mobilePhoneNumber);
        }
    }

    public void saveMobilePhoneNumbers(List<MobilePhoneNumber> mobilePhoneNumbers) {
        if (mobilePhoneNumbers == null || mobilePhoneNumbers.size() == 0) {
            return;
        }
        for (MobilePhoneNumber mobilePhoneNumber : mobilePhoneNumbers) {
            MobilePhoneNumber savedMobilePhoneNumber = mobilePhoneNumberManager.queryByNumber(mobilePhoneNumber.getNumber());
            if (savedMobilePhoneNumber != null) {
                mobilePhoneNumber.setId(savedMobilePhoneNumber.getId());
                mobilePhoneNumberManager.update(mobilePhoneNumber);
            } else {
                mobilePhoneNumberManager.save(mobilePhoneNumber);
            }
        }
    }

    public void deleteById(Integer id) {
        mobilePhoneNumberManager.deleteById(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        mobilePhoneNumberManager.deleteByIds(ids);
    }

    public MobilePhoneNumber queryById(Integer id) {
        return mobilePhoneNumberManager.queryById(id);
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
