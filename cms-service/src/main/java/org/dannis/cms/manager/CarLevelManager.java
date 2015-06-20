package org.dannis.cms.manager;

import org.dannis.cms.dal.db.CarLevelMapper;
import org.dannis.cms.dal.entity.CarLevelEntity;
import org.dannis.cms.model.CarLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-14 00:30
 */
@Component
public class CarLevelManager {
    @Autowired
    private CarLevelMapper carLevelMapper;

    /**
     * 保存汽车级别信息
     *
     * @param level 汽车级别信息
     */
    public void save(CarLevel level) {
        carLevelMapper.save(convertToEntity(level));
    }

    /**
     * 根据ID删除汽车级别信息
     *
     * @param id 汽车级别ID
     */
    public void delete(Integer id) {
        carLevelMapper.delete(id);
    }

    /**
     * 根据ID批量删除汽车级别
     *
     * @param ids ID列表
     */
    public void deleteByIds(Integer[] ids) {
        carLevelMapper.deleteByIds(ids);
    }

    /**
     * 修改汽车级别信息
     *
     * @param level 汽车级别信息
     */
    public void update(CarLevel level) {
        carLevelMapper.update(convertToEntity(level));
    }

    /**
     * 根据ID查询汽车级别信息
     *
     * @param id 汽车级别ID
     * @return 汽车级别信息
     */
    public CarLevel query(Integer id) {
        return convertToModel(carLevelMapper.query(id));
    }

    /**
     * 分页查询汽车级别信息
     *
     * @param params 查询参数
     * @return 汽车级别信息列表
     */
    public List<CarLevel> queryByPage(Map<String, Object> params) {
        return convertToModels(carLevelMapper.queryByPage(params));
    }

    /**
     * 根据条件查询汽车级别信息总数
     *
     * @param params 查询条件
     * @return 符合条件的汽车级别信息总数
     */
    public Long queryTotal(Map<String, Object> params) {
        return carLevelMapper.queryTotal(params);
    }

    /**
     * 查询所有汽车级别
     *
     * @return 汽车级别列表
     */
    public List<CarLevel> queryAll() {
        return convertToModels(carLevelMapper.queryAll());
    }

    private CarLevelEntity convertToEntity(CarLevel level) {
        CarLevelEntity entity = null;
        if (level != null) {
            entity = new CarLevelEntity();
            entity.setId(level.getId());
            entity.setName(level.getName());
            entity.setEnglishName(level.getEnglishName());
            entity.setRemark(level.getRemark());
            entity.setCreatedBy(level.getCreatedBy());
            entity.setCreatedOn(level.getCreatedOn());
            entity.setLastModifiedBy(level.getLastModifiedBy());
            entity.setLastModifiedOn(level.getLastModifiedOn());
        }

        return entity;
    }

    private List<CarLevel> convertToModels(List<CarLevelEntity> entities) {
        List<CarLevel> levels = new ArrayList<>();
        if (null != entities && entities.size() > 0) {
            for (CarLevelEntity entity : entities) {
                levels.add(convertToModel(entity));
            }
        }

        return levels;
    }

    private CarLevel convertToModel(CarLevelEntity entity) {
        CarLevel level = null;
        if (entity != null) {
            level = new CarLevel();
            level.setId(entity.getId());
            level.setName(entity.getName());
            level.setEnglishName(entity.getEnglishName());
            level.setRemark(entity.getRemark());
            level.setCreatedBy(entity.getCreatedBy());
            level.setCreatedOn(entity.getCreatedOn());
            level.setLastModifiedBy(entity.getLastModifiedBy());
            level.setLastModifiedOn(entity.getLastModifiedOn());
        }

        return level;
    }
}
