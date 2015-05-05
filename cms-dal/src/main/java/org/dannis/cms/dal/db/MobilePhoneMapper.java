package org.dannis.cms.dal.db;

import org.dannis.cms.dal.entity.PhoneNumberEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 13:33
 */
@Component
public interface MobilePhoneMapper {
    /**
     * ����绰����
     *
     * @param entity �绰����ʵ��
     */
    void save(PhoneNumberEntity entity);

    /**
     * ��������绰����
     * @param entities �绰����ʵ���б�
     */
    void batchSave(List<PhoneNumberEntity> entities);

    /**
     * ����IDɾ���绰����
     *
     * @param id �绰����ID
     */
    void delete(Integer id);

    /**
     * ���ݺ���ɾ���绰�����¼
     * @param number ����
     */
    void deleteByNumber(String number);

    /**
     * ����ID���ҵ绰����
     *
     * @param id �绰����ID
     * @return �绰����ʵ��
     */
    PhoneNumberEntity find(Integer id);

    /**
     * ���ݺ�����ҵ绰����
     *
     * @param number ����
     * @return �绰����ʵ��
     */
    PhoneNumberEntity findByNumber(String number);
}
